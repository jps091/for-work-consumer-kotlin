package com.forwork.consumer.password

import com.forwork.consumer.password.message.PasswordMessage
import com.forwork.infrastructure.mail.MailSender
import com.forwork.validator.EmailValidator
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Component

@Component
class PasswordMailConsumer(
        private val mailSender: MailSender
) {
    @RabbitListener(queues = ["user.password.q"], containerFactory = "customRabbitListenerContainerFactory")
    fun sendTempPasswordMail(message: PasswordMessage){
        EmailValidator.validate(message)
        mailSender.send(message)
    }
}