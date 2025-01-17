package com.consumer.infrastructure.maillog

import jakarta.persistence.*
import jakarta.validation.constraints.NotNull
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.LocalDateTime

@Entity
@Table(name = "mail_logs")
class MailLogEntity(

        @field:Id
        @field:GeneratedValue(strategy = GenerationType.IDENTITY)
        @field:Column(name = "mail_log_id")
        var id: Long? = null,

        @field:NotNull
        @field:Column(length = 50, name = "email")
        var email: String,

        @field:Column(length = 255, name = "error_response")
        var errorResponse: String? = null,

        @field:Column(length = 300, name = "message_content")
        var messageContent: String? = null,

        @field:CreatedDate
        @field:Column(updatable = false, name = "registered_at", columnDefinition = "TIMESTAMP")
        var registeredAt: LocalDateTime? = null,

        @field:LastModifiedDate
        @field:Column(name = "modified_at", columnDefinition = "TIMESTAMP")
        var modifiedAt: LocalDateTime? = null
){
    companion object{
        fun create(email: String, content: String, e: Throwable): MailLogEntity{
            return MailLogEntity(
                    email = email,
                    messageContent = content,
                    errorResponse = e.message
            )
        }

        fun create(email: String, content: String): MailLogEntity {
            return MailLogEntity(
                    email = email,
                    messageContent = content
            )
        }
    }
}
