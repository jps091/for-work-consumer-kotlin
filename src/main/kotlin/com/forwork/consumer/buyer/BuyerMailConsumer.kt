package com.forwork.consumer.buyer

import com.forwork.consumer.buyer.message.BuyerMessage
import com.forwork.infrastructure.mail.MailSender
import com.forwork.infrastructure.redis.RedisStringUtils
import com.forwork.service.OrderStatusUpdateService
import com.forwork.validator.EmailValidator
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