package com.consumer.consumer.buyer

import com.consumer.consumer.buyer.message.BuyerMessage
import com.consumer.infrastructure.mail.MailSender
import com.consumer.infrastructure.redis.RedisStringUtils
import com.consumer.service.OrderStatusUpdateService
import com.consumer.validator.EmailValidator
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Component

@Component
class BuyerMailConsumer(
        private val mailSender: MailSender,
        private val orderStatusUpdateService: OrderStatusUpdateService,
        private val redisStringUtils: RedisStringUtils
) {
    @RabbitListener(queues = ["user.buyer.q"], containerFactory = "customRabbitListenerContainerFactory")
    fun sendBuyerMail(message: BuyerMessage){
        EmailValidator.validate(message)
        mailSender.send(message)
        orderStatusUpdateService.updateOrderStatusSent(message)
        increaseResumeSalesQuantity(message)
    }

    fun increaseResumeSalesQuantity(message: BuyerMessage){
        val key = "resume:" + message.resumeId
        redisStringUtils.safeIncrement(key)
    }
}