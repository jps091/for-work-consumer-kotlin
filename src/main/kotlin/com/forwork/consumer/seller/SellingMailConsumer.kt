package com.forwork.consumer.seller

import com.forwork.consumer.seller.message.SellingMessage
import com.forwork.infrastructure.mail.MailSender
import com.forwork.validator.EmailValidator
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Component

@Component
class SellingMailConsumer(
        private val mailSender: MailSender
) {
    @RabbitListener(queues = ["user.seller.selling.q"], containerFactory = "customRabbitListenerContainerFactory")
    fun sendSellingMail(message: SellingMessage){
        EmailValidator.validate(message)
        mailSender.send(message)
    }
}