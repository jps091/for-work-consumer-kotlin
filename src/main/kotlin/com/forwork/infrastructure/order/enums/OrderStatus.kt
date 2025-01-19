package com.forwork.infrastructure.order.enums

import com.fasterxml.jackson.annotation.JsonCreator

enum class OrderStatus(description: String) {
    ORDERED("주문 생성"),
    PAYMENT_FAILED("결제 실패"),
    PAID("결제 완료"),
    WAIT("발송 대기"),
    PARTIAL_WAIT("발송 부분 대기"),

    CANCEL("주문 취소"),
    PARTIAL_CANCEL("부분 취소"),
    CONFIRM("구매 확정"),
    PARTIAL_CONFIRM("부분 구매 확정"),
    ;

    companion object{
        @JsonCreator
        fun from(value: String): OrderStatus{
            return entries.find{ it.name.equals(value, ignoreCase = true)}
                    ?: throw IllegalArgumentException("Invalid Status: $value")
        }
    }
}