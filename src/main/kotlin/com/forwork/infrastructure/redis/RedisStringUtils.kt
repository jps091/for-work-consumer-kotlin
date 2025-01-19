package com.forwork.infrastructure.redis

import org.slf4j.LoggerFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.core.script.RedisScript
import org.springframework.stereotype.Component
import java.util.concurrent.TimeUnit

@Component
class RedisStringUtils(
        private val redisTemplate: RedisTemplate<String, String>
) {
    private val log = LoggerFactory.getLogger(this::class.java)

    fun getKeys(key: String): Set<String> = redisTemplate.keys(key)

    fun getValue(key: String): String = redisTemplate.opsForValue().get(key)!!

    fun setValueWithTimeout(key: String, value: String, ttlSeconds: Long)
        = redisTemplate.opsForValue().set(key, value, ttlSeconds, TimeUnit.SECONDS)

    fun deleteKey(key: String) = redisTemplate.delete(key);

    fun createKeyForm(prefix: String, value: Any):String = prefix + value

    fun safeIncrement(key: String) = redisTemplate.opsForValue().increment(key, 1)

    //@Suppress("UNCHECKED_CAST")
    fun syncAndResetKeys(keys: Set<String>): Map<String, Int>{
        val script = """
            local result = {}
            for i, key in ipairs(KEYS) do
                local current = redis.call("GET", key)
                if current then
                    table.insert(result, key)
                    table.insert(result, tonumber(current))
                    redis.call("SET", key, "0")
                end
            end
            return result
        """

        //val redisScript = RedisScript.of(script, List::class.java) as RedisScript<List<Any>>
        val redisScript: RedisScript<List<*>> = RedisScript.of(script, List::class.java)
        val result = redisTemplate.execute(redisScript, keys.toList())

        val syncData = mutableMapOf<String, Int>() // TODO
        result.let{
            for (i in it.indices step 2) {
                val key = it[i] as String
                val value = (it[i + 1] as Long).toInt()
                syncData[key] = value
            }
        }
        return syncData
    }
}
