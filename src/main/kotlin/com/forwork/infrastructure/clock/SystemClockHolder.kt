package com.forwork.infrastructure.clock

import org.springframework.stereotype.Component
import java.time.Clock
import java.time.LocalDateTime
@Component
class SystemClockHolder: ClockHolder {
    override fun millis(): Long = Clock.systemUTC().millis()

    override fun now(): LocalDateTime = LocalDateTime.now()
}