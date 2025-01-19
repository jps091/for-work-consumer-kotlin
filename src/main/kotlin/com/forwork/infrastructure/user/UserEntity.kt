package com.forwork.infrastructure.user

import com.forwork.infrastructure.user.enums.UserStatus
import jakarta.persistence.*
import jakarta.validation.constraints.NotNull
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.LocalDateTime

@Entity
@Table(name = "users")
class UserEntity(
        @field:Id
        @field:GeneratedValue(strategy = GenerationType.IDENTITY)
        @field:Column(name = "user_id")
        var id: Long?=null,

        @field:NotNull
        @field:Column(length = 50, unique = true)
        var email: String,

        @field:NotNull
        @field:Column(length = 20)
        var password: String,

        @field:NotNull
        @field:Column(length = 10)
        var name: String,

        @field:NotNull
        @field:Column(name = "status")
        @field:Enumerated(EnumType.STRING)
        var status: UserStatus,

        @field:Column(name = "last_login_at", columnDefinition = "TIMESTAMP")
        var lastLoginAt: LocalDateTime?=null,

        @field:CreatedDate
        @field:Column(updatable = false, name = "registered_at", columnDefinition = "TIMESTAMP")
        var registeredAt: LocalDateTime?=null,

        @field:LastModifiedDate
        @field:Column(name = "modified_at", columnDefinition = "TIMESTAMP")
        var modifiedAt: LocalDateTime?=null
)