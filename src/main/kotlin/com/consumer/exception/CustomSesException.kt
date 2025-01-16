package com.consumer.exception

class CustomSesException(message: String, cause: Throwable) : RuntimeException(message, cause) {
}