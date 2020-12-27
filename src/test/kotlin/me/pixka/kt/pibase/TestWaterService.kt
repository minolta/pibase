package me.pixka.kt.pibase

import me.pixka.kt.pibase.d.WaterflowService
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class TestWaterService {

    @Autowired
    lateinit var service:WaterflowService
    @Test
    fun testWaterService()
    {
        println(service)
    }
}