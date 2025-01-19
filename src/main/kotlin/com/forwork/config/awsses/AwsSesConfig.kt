package com.forwork.config.awsses

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.ses.SesClient

@Configuration
class AwsSesConfig(
        @Value("\${aws.credentials.accessKey}") private val accessKey: String,
        @Value("\${aws.credentials.secretKey}") private val secretKey: String,
        @Value("\${aws.region}") private val region: String
) {
    @Bean
    fun sesClient(): SesClient {
        return SesClient.builder().apply {
            region(Region.of(region))
            credentialsProvider(
                    StaticCredentialsProvider.create(
                            AwsBasicCredentials.create(accessKey, secretKey)
                    )
            )
        }.build()
    }
}