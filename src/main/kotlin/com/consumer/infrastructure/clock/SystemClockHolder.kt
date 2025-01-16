package com.consumer.infrastructure.clock

import java.time.Clock
import java.time.LocalDateTime

class SystemClockHolder: ClockHolder {
    override fun millis(): Long = Clock.systemUTC().millis()

    override fun now(): LocalDateTime = LocalDateTime.now()
}