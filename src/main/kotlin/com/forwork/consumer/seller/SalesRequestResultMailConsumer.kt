package com.forwork.consumer.seller

import com.forwork.consumer.seller.message.SalesRequestResultMessage
import com.forwork.infrastructure.mail.MailSender
import com.forwork.validator.EmailValidator
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Component

@Component
class SalesRequestResultMailConsumer(
        private val mailSender: MailSender
) {
    @RabbitListener(queues = ["user.seller.result.q"], containerFactory = "customRabbitListenerContainerFactory")
    fun sendSalesRequestResultMail(message: SalesRequestResultMessage){
        EmailValidator.validate(message)
        mailSender.send(message)
    }
}