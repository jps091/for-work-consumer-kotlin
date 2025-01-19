package com.forwork.consumer.auth

import com.forwork.consumer.auth.message.AuthMessage
import com.forwork.infrastructure.mail.MailSender
import com.forwork.infrastructure.redis.RedisStringUtils
import com.forwork.infrastructure.uuid.SystemUuidHolder
import com.forwork.validator.EmailValidator
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Component
const val EMAIL_PREFIX = "email:"
@Component
class AuthMailConsumer(
        private val mailSender: MailSender,
        private val uuidHolder: SystemUuidHolder,
        private val redisStringUtils: RedisStringUtils
) {
    @RabbitListener(queues = ["verify.q"], containerFactory = "customRabbitListenerContainerFactory")
    fun sendVerifyCodeMail(email: String){
        val authMessage = createMessage(email)
        EmailValidator.validate(authMessage)
        mailSender.send(authMessage)
    }

    fun createMessage(email: String): AuthMessage{
        val certificationCode = issueCertificationCode(email)
        return AuthMessage(
                email,
                "for-work 인증 코드 발송",
                "인증코드 : $certificationCode"
        )
    }

    fun issueCertificationCode(email: String): String{
        val certificationCode = uuidHolder.random()
        val key = getKeyByEmail(email)
        redisStringUtils.setValueWithTimeout(key, certificationCode, 300L)
        return certificationCode
    }

    fun getKeyByEmail(email: String): String = redisStringUtils.createKeyForm(EMAIL_PREFIX, email)
}