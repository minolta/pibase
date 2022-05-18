package me.pixka.kt.pibase.r

import me.pixka.kt.pibase.d.SensorinjobRepo
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
class TEstSensorinjob {
    @Autowired
    lateinit var sr:SensorinjobRepo

    @Test
    fun t()
    {
        Assertions.assertNotNull(sr)
    }
}