package com.forwork.service

import com.forwork.consumer.buyer.message.BuyerMessage
import com.forwork.infrastructure.clock.ClockHolder
import com.forwork.infrastructure.orderresume.OrderResumeJpaRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class OrderStatusUpdateService(
        private val orderResumeJpaRepository: OrderResumeJpaRepository,
        private val clockHolder: ClockHolder,

) {
    @Transactional
    fun updateOrderStatusSent(message: BuyerMessage){
        val orderResumes = orderResumeJpaRepository
                .findByOrderEntity_IdAndResumeEntity_Id(message.orderId, message.resumeId)
        orderResumes.forEach{ it.updateStatusSent(clockHolder) }
    }
}