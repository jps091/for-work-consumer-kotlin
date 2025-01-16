package com.consumer.infrastructure.resume

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface ResumeJpaRepository: JpaRepository<ResumeEntity, Long> {
    @Modifying
    @Query("UPDATE ResumeEntity r SET r.salesQuantity = r.salesQuantity + :quantity WHERE r.id = :resumeId")
    fun updateSalesQuantity(@Param("resumeId") resumeId: Long, @Param("quantity") quantity: Int)
}