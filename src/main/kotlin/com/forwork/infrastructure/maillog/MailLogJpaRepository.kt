package com.forwork.infrastructure.maillog

import org.springframework.data.jpa.repository.JpaRepository

interface MailLogJpaRepository: JpaRepository<MailLogEntity, Long> {
}