package com.consumer.handler

import com.consumer.infrastructure.deadletter.message.FailMessage
import com.consumer.service.MailLogService
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import org.slf4j.LoggerFactory
import org.springframework.amqp.AmqpRejectAndDontRequeueException
import org.springframework.amqp.ImmediateAcknowledgeAmqpException
import org.springframework.amqp.core.Message
import org.springframework.amqp.rabbit.listener.FatalExceptionStrategy
import org.springframework.amqp.rabbit.support.ListenerExecutionFailedException
import org.springframework.util.ErrorHandler
import java.nio.charset.StandardCharsets

class MyErrorHandler(
        private val exceptionStrategy: FatalExceptionStrategy,
        private val objectMapper: ObjectMapper,
        private val mailLogService: MailLogService
): ErrorHandler {

    private val log = LoggerFactory.getLogger(this::class.java)

    override fun handleError(t: Throwable) {
        if(exceptionStrategy.isFatal(t) && t is ListenerExecutionFailedException){
            val failedMessage = t.failedMessage
            registerMailLog(failedMessage, t)
            throw ImmediateAcknowledgeAmqpException(
                    "Fatal exception Immediate Discarding: ${t.message}", t
            )
        }
        throw AmqpRejectAndDontRequeueException(
                "Moving to DLX for retries: ${t.message}", t
        )
    }

    private fun registerMailLog(failedMessage: Message, e: Throwable){
        val messageBody = String(failedMessage.body, StandardCharsets.UTF_8)
        val message = parsing(messageBody)
        mailLogService.registerFailLog(message, e)
    }

    private fun parsing(messageBody: String): FailMessage{
        try{
            return objectMapper.readValue(messageBody, FailMessage::class.java)
        }catch (e: JsonProcessingException){
            log.error("Json Parsing ={}", messageBody)
            throw IllegalStateException("Json Parsing Error")
        }
    }
}