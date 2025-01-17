package com.consumer.consumer.seller

import com.consumer.consumer.seller.message.SellingMessage
import com.consumer.infrastructure.mail.MailSender
import com.consumer.validator.EmailValidator
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