package me.pixka.kt.pibase.d

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import java.math.BigDecimal
import java.util.*

@DataJpaTest
class TestC02 {

    @Autowired
    lateinit var co2Service: Co2Service

    fun add(n:String="test add", value:BigDecimal= BigDecimal(0), vd: Date=Date()): Co2 {
        var co2 = Co2(n)
        return co2Service.save(co2)
    }

    @Test
    fun testAdd()
    {
        add("co2 1", BigDecimal(200),Date())
        add("co2 2",BigDecimal(333),Date())
        Assertions.assertTrue(co2Service.all().size>0)
    }
}