package com.consumer.consumer.admin

import com.consumer.consumer.admin.message.AdminInquiryMessage
import com.consumer.infrastructure.mail.MailSender
import com.consumer.validator.EmailValidator
import org.slf4j.LoggerFactory
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