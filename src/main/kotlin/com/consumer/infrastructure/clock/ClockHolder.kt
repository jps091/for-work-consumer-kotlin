package com.consumer.infrastructure.clock

import java.time.LocalDateTime

interface ClockHolder {
    fun millis(): Long
    fun now(): LocalDateTime
}