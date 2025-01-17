package com.consumer.infrastructure.orderresume.enums

import com.fasterxml.jackson.annotation.JsonCreator

enum class OrderResumeStatus(description: String) {

    ORDERED("결제완료"),
    PAYMENT_FAIL("결제실패"),
    CANCEL("주문취소"),
    CONFIRM("구매확정"),
    SENT("발송완료")
    ;

    companion object{
        @JsonCreator
        fun from(value: String): OrderResumeStatus {
            return entries.find{ it.name.equals(value, ignoreCase = true)}
                    ?: throw IllegalArgumentException("Invalid Status: $value")
        }
    }
}