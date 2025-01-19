package com.forwork.handler

import com.forwork.exception.CustomSesException
import com.forwork.exception.InvalidEmailException
import org.springframework.amqp.rabbit.listener.ConditionalRejectingErrorHandler
import org.springframework.amqp.rabbit.listener.FatalExceptionStrategy
import org.springframework.data.redis.RedisConnectionFailureException
import org.springframework.mail.MailAuthenticationException
import org.springframework.stereotype.Component

@Component
class MyFatalExceptionStrategy: FatalExceptionStrategy {

    private val fatalExceptionStrategy = ConditionalRejectingErrorHandler.DefaultExceptionStrategy()

    override fun isFatal(t: Throwable): Boolean {
        return fatalExceptionStrategy.isFatal(t)
                || t.cause is CustomSesException
                || t.cause is RedisConnectionFailureException
                || t.cause is MailAuthenticationException
                || t.cause is InvalidEmailException;
    }
}