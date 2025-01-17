package com.consumer.consumer.seller

import com.consumer.consumer.admin.message.AdminInquiryMessage
import com.consumer.consumer.seller.message.SalesRequestResultMessage
import com.consumer.infrastructure.mail.MailSender
import com.consumer.validator.EmailValidator
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