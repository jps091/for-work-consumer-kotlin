package com.forwork.infrastructure.clock

import java.time.LocalDateTime

interface ClockHolder {
    fun millis(): Long
    fun now(): LocalDateTime
}