package com.forwork.infrastructure.resume

import com.forwork.infrastructure.enums.FieldType
import com.forwork.infrastructure.enums.LevelType
import com.forwork.infrastructure.resume.enums.ResumeStatus
import com.forwork.infrastructure.user.UserEntity
import jakarta.persistence.*
import jakarta.validation.constraints.NotNull
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
@Table(name = "resumes")
class ResumeEntity(

        @field:Id
        @field:GeneratedValue(strategy = GenerationType.IDENTITY)
        @field:Column(name = "resume_id")
        var id: Long? = null,

        @field:NotNull
        @field:ManyToOne(fetch = FetchType.LAZY)
        @field:JoinColumn(name = "seller_id")
        var sellerEntity: UserEntity,

        @field:Enumerated(EnumType.STRING)
        @field:Column(name ="level")
        @field:NotNull
        var levelType: LevelType,

        @field:Enumerated(EnumType.STRING)
        @field:Column(name ="field")
        @field:NotNull
        var fieldType: FieldType,

        @field:NotNull
        @field:Column(length = 300, name = "resume_url")
        var resumeUrl: String,

        @field:NotNull
        @field:Column(length = 300, name = "description_image_url")
        var descriptionUrl: String,

        @field:NotNull
        @field:Column(precision = 6, scale = 0)
        var price: BigDecimal,

        @field:NotNull
        @field:Column(name = "sales_quantity")
        var salesQuantity: Int,

        @field:NotNull
        @field:Column(length = 5000)
        var description: String,

        @field:Enumerated(EnumType.STRING)
        @field:Column(name ="status")
        @field:NotNull
        var resumeStatus: ResumeStatus,

        @field:CreatedDate
        @field:Column(updatable = false, name = "registered_at", columnDefinition = "TIMESTAMP")
        var registeredAt: LocalDateTime? = null,

        @field:LastModifiedDate
        @field:Column(name = "modified_at", columnDefinition = "TIMESTAMP")
        var modifiedAt: LocalDateTime? = null
)