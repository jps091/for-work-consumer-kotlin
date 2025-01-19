package com.forwork.consumer.notice

import com.forwork.consumer.notice.message.NoticeMessage
import com.forwork.infrastructure.mail.MailSender
import com.forwork.validator.EmailValidator
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Component

@Component
class NoticeMailConsumer(
        private val mailSender: MailSender
) {
    @RabbitListener(queues = ["user.notice.q"], containerFactory = "customRabbitListenerContainerFactory")
    fun sendSalesRequestResultMail(message: NoticeMessage){
        EmailValidator.validate(message)
        mailSender.send(message)
    }
}