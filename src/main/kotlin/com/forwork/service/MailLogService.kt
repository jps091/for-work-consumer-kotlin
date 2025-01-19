package com.forwork.service

import com.forwork.consumer.MessageIfs
import com.forwork.infrastructure.maillog.MailLogEntity
import com.forwork.infrastructure.maillog.MailLogJpaRepository
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

@Service
class MailLogService(
        private val objectMapper: ObjectMapper,
        private val mailLogRepository: MailLogJpaRepository
) {
    private val log = LoggerFactory.getLogger(this::class.java)

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    fun registerFailLog(message: MessageIfs, e: Throwable){
        val content = setMessageContent(message)
        val mailLog = MailLogEntity.create(message.email, content, e)
        mailLogRepository.save(mailLog)
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    fun registerFailLog(message: MessageIfs){
        val content = setMessageContent(message)
        val mailLog = MailLogEntity.create(message.email, content)
        mailLogRepository.save(mailLog)
    }

    private fun setMessageContent(message: MessageIfs): String{
        var mailContent: String? = null

        try{
            val title = "title : " + objectMapper.writeValueAsString(message.title)
            val content = " content : " + objectMapper.writeValueAsString(message.content)
            mailContent = title + content;
            return mailContent
        }catch(e: JsonProcessingException){
            mailContent = "JSON 직렬화 오류: " + e.message
            return mailContent
        }
    }
}