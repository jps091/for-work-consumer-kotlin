package com.consumer.consumer.password

import com.consumer.consumer.admin.message.AdminInquiryMessage
import com.consumer.consumer.password.message.PasswordMessage
import com.consumer.infrastructure.mail.MailSender
import com.consumer.validator.EmailValidator
import org.slf4j.LoggerFactory
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