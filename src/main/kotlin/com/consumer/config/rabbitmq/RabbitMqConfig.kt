package com.consumer.config.rabbitmq

import com.consumer.handler.MyErrorHandler
import com.consumer.handler.MyFatalExceptionStrategy
import com.consumer.service.MailLogService
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.amqp.core.AcknowledgeMode

import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.amqp.support.converter.MessageConverter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.util.ErrorHandler

@Configuration
class RabbitMqConfig(
        private val objectMapper: ObjectMapper,
        private val mailLogService: MailLogService,
        private val myFatalExceptionStrategy: MyFatalExceptionStrategy
) {
    @Bean
    fun customRabbitListenerContainerFactory(
            connectionFactory: ConnectionFactory,
            messageConverter: MessageConverter
    ): SimpleRabbitListenerContainerFactory{
        val factory = SimpleRabbitListenerContainerFactory().apply {
            setConnectionFactory(connectionFactory)
            setMessageConverter(messageConverter);
            setConcurrentConsumers(3);
            setMaxConcurrentConsumers(10);
            setAcknowledgeMode(AcknowledgeMode.AUTO)
            setDefaultRequeueRejected(false)
            setErrorHandler(errorHandler())
        }
        return factory
    }

    @Bean
    fun errorHandler(): ErrorHandler{
        return MyErrorHandler(myFatalExceptionStrategy, objectMapper, mailLogService)
    }

    @Bean
    fun rabbitTemplate(
            connectionFactory: ConnectionFactory, messageConverter: MessageConverter
    ): RabbitTemplate{
        val rabbitTemplate = RabbitTemplate(connectionFactory)
        rabbitTemplate.messageConverter = messageConverter
        return rabbitTemplate
    }

    @Bean
    fun messageConverter(objectMapper: ObjectMapper): MessageConverter{
        return Jackson2JsonMessageConverter(objectMapper)
    }
}