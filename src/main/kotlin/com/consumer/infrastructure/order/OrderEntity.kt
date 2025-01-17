package com.consumer.infrastructure.order

import com.consumer.infrastructure.order.enums.OrderStatus
import com.consumer.infrastructure.user.UserEntity
import jakarta.persistence.*
import jakarta.validation.constraints.NotNull
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
@Table(name = "orders")
class OrderEntity(

        @field:Id
        @field:GeneratedValue(strategy = GenerationType.IDENTITY)
        @field:Column(name = "order_id")
        var id: Long? = null,

        @field:NotNull
        @field:ManyToOne(fetch = FetchType.LAZY)
        @field:JoinColumn(name = "seller_id")
        var sellerEntity: UserEntity,

        @field:NotNull
        @field:Column(length = 25, name = "request_id")
        var requestId: String,

        @field:NotNull
        @field:Column(precision = 8, name = "total_amount")
        var totalAmount: BigDecimal,

        @field:Enumerated(EnumType.STRING)
        @field:Column(name ="status")
        @field:NotNull
        var status: OrderStatus,

        @field:Column(name = "paid_at", columnDefinition = "TIMESTAMP")
        var paidAt: LocalDateTime? = null,

        @field:CreatedDate
        @field:Column(updatable = false, name = "registered_at", columnDefinition = "TIMESTAMP")
        var registeredAt: LocalDateTime? = null,

        @field:LastModifiedDate
        @field:Column(name = "modified_at", columnDefinition = "TIMESTAMP")
        var modifiedAt: LocalDateTime? = null
)