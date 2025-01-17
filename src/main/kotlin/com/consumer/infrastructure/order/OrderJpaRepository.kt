package com.consumer.infrastructure.order

import org.springframework.data.jpa.repository.JpaRepository

interface OrderJpaRepository: JpaRepository<OrderEntity, Long> {
}