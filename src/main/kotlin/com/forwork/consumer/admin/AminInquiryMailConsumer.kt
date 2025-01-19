package com.forwork.consumer.admin

import com.forwork.consumer.admin.message.AdminInquiryMessage
import com.forwork.infrastructure.mail.MailSender
import com.forwork.validator.EmailValidator
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Component

@Component
class AminInquiryMailConsumer(
        private val mailSender: MailSender
) {
    @RabbitListener(queues = ["user.admin.inquiry.q"], containerFactory = "customRabbitListenerContainerFactory")
    fun sendInquiryMail(message: AdminInquiryMessage){
        EmailValidator.validate(message)
        mailSender.sendBySes(message)
    }
}