package com.consumer.consumer.notice

import com.consumer.consumer.notice.message.NoticeMessage
import com.consumer.infrastructure.mail.MailSender
import com.consumer.validator.EmailValidator
import org.slf4j.LoggerFactory
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