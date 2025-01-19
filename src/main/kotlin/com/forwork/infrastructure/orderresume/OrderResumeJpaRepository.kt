package com.forwork.infrastructure.orderresume

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.query.Param

interface OrderResumeJpaRepository: JpaRepository<OrderResumeEntity, Long> {
    fun findByOrderEntity_IdAndResumeEntity_Id(@Param("orderId") orderId: Long, @Param("resumeId") resumeId: Long): List<OrderResumeEntity>
}
