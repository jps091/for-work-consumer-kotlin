package com.forwork.infrastructure.mail

import com.forwork.consumer.MessageIfs
import com.forwork.exception.CustomSesException
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Component
import software.amazon.awssdk.services.ses.SesClient
import software.amazon.awssdk.services.ses.model.*
@Component
class MailSenderImpl(
        @Value("\${aws.mail}") private val mail: String,
        private val javaMailSender: JavaMailSender,
        private val sesClient: SesClient
): MailSender {

    private val log = LoggerFactory.getLogger(this::class.java)
    override fun send(message: MessageIfs) {
        val mailMessage  = SimpleMailMessage().apply{
            setTo(message.email)
            subject = message.title
            text = message.content
        }
        javaMailSender.send(mailMessage)
    }

    override fun sendBySes(message: MessageIfs) {
        try {
            // SES 이메일 요청 생성
            val request = SendEmailRequest.builder()
                    .destination(
                            Destination.builder()
                                    .toAddresses(message.email) // 수신자 이메일
                                    .build()
                    )
                    .message(
                            Message.builder()
                                    .subject(Content.builder().data(message.title).build()) // 이메일 제목
                                    .body(
                                        Body.builder()
                                                .text(Content.builder().data(message.content).build()) // 이메일 본문
                                                .build()
                                    ).build()
                    )
                    .source(mail) // SES에서 인증된 발신자 이메일
                    .build()

            // 이메일 전송
            val response = sesClient.sendEmail(request)
            log.info("Email sent successfully. Message ID: {}", response.messageId())
        } catch (e: Exception) {
            throw CustomSesException("Failed to send SES email", e)
        }
    }
}