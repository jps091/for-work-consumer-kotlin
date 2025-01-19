package com.forwork.infrastructure.uuid

import org.springframework.stereotype.Component
import java.util.*

@Component
class SystemUuidHolder: UuidHolder {
    override fun random(): String {
        return UUID.randomUUID().toString().substring(0, 7) + "@";
    }
}