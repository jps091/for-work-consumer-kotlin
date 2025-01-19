package com.forwork.exception

class CustomSesException(message: String, cause: Throwable) : RuntimeException(message, cause) {
}