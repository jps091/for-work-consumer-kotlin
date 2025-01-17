package com.consumer.service

import com.consumer.infrastructure.redis.RedisStringUtils
import com.consumer.infrastructure.resume.ResumeJpaRepository
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

@Service
class ResumeQuantityService(
        private val resumeRepository: ResumeJpaRepository,
        private val redisStringUtils: RedisStringUtils
) {
    private val log = LoggerFactory.getLogger(this::class.java)

    @Scheduled(fixedRate = 600000) //10분
    fun syncRedisToDB(){
        val keys = redisStringUtils.getKeys("resume:*")
        if(keys.isEmpty()){
            log.info("No keys found");
            return
        }

        val syncData: Map<String, Int> = redisStringUtils.syncAndResetKeys(keys)
        syncData.forEach { (key, quantity) ->
            val resumeId = key.split(":")[1].toLong()
            resumeRepository.updateSalesQuantity(resumeId, quantity)
        }

        syncData.keys.forEach { key ->
            if (redisStringUtils.getValue(key) == "0") {
                redisStringUtils.deleteKey(key)
            }
        }
    }
}