package com.forwork.consumer.deadletter

import com.forwork.consumer.deadletter.message.FailMessage
import com.forwork.service.MailLogService
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import org.slf4j.LoggerFactory
import org.springframework.amqp.core.Message
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

const val RETRY_COUNT_HEADER = "x-retries_count"
@Component
class DlqConsumer(
        private val rabbitTemplate: RabbitTemplate,
        private val objectMapper: ObjectMapper,
        private val mailLogService: MailLogService,
        @Value("\${spring.rabbitmq.listener.simple.retry.max-attempts}")
        private val retryCount: Int
) {
    private val log = LoggerFactory.getLogger(this::class.java)

    @RabbitListener(queues = ["retry.q"], containerFactory = "customRabbitListenerContainerFactory")
    fun processFailedMessagesRequeue(failedMessage: Message){
        var retriesCnt: Int = failedMessage.messageProperties.headers[RETRY_COUNT_HEADER] as? Int ?: 0 // TODO

        if (retriesCnt >= retryCount) {
            retryMailLogRegister(failedMessage)
            log.error("DLQ retry fail message discarding")
            return
        }

        failedMessage.messageProperties.headers[RETRY_COUNT_HEADER] = ++retriesCnt

        val originalExchange = failedMessage.messageProperties.receivedExchange
        val originalRoutingKey = failedMessage.messageProperties.receivedRoutingKey

        if (originalExchange.isNullOrEmpty()) {
            log.error("exchange not valid={}", failedMessage)
            return
        }

        log.info("Retrying message for the {} time", retriesCnt)
        rabbitTemplate.send(originalExchange, originalRoutingKey, failedMessage)
    }

    private fun retryMailLogRegister(failedMessage: Message) {
        val messageBody = String(failedMessage.body, Charsets.UTF_8)
        val message = parsing(messageBody)
        mailLogService.registerFailLog(message)
    }

    private fun parsing(messageBody: String): FailMessage {
        return try {
            objectMapper.readValue(messageBody, FailMessage::class.java)
        } catch (e: JsonProcessingException) {
            log.error("Json Parsing ={}", messageBody)
            throw IllegalStateException("Json Parsing Error", e)
        }
    }
}