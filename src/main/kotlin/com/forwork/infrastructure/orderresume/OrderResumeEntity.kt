package com.forwork.infrastructure.orderresume

import com.forwork.infrastructure.clock.ClockHolder
import com.forwork.infrastructure.order.OrderEntity
import com.forwork.infrastructure.orderresume.enums.OrderResumeStatus
import com.forwork.infrastructure.resume.ResumeEntity
import jakarta.persistence.*
import jakarta.validation.constraints.NotNull
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.LocalDateTime

@Entity
@Table(name = "order_resumes")
class OrderResumeEntity(
        @field:Id
        @field:GeneratedValue(strategy = GenerationType.IDENTITY)
        @field:Column(name = "order_resume_id")
        var id: Long? = null,

        @field:NotNull
        @field:ManyToOne(fetch = FetchType.LAZY)
        @field:JoinColumn(name = "order_id")
        var orderEntity: OrderEntity,

        @field:NotNull
        @field:ManyToOne(fetch = FetchType.LAZY)
        @field:JoinColumn(name = "resume_id")
        var resumeEntity: ResumeEntity,

        @field:Enumerated(EnumType.STRING)
        @field:Column(name ="status")
        @field:NotNull
        var status: OrderResumeStatus,

        @field:Column(name = "sent_at", columnDefinition = "TIMESTAMP")
        var sentAt: LocalDateTime? = null,

        @field:Column(name = "canceled_at", columnDefinition = "TIMESTAMP")
        var canceledAt: LocalDateTime? = null,

        @field:CreatedDate
        @field:Column(updatable = false, name = "registered_at", columnDefinition = "TIMESTAMP")
        var registeredAt: LocalDateTime? = null,

        @field:LastModifiedDate
        @field:Column(name = "modified_at", columnDefinition = "TIMESTAMP")
        var modifiedAt: LocalDateTime? = null
){
    fun updateStatusSent(clockHolder: ClockHolder){
        status = OrderResumeStatus.SENT
        sentAt = clockHolder.now()
    }
}