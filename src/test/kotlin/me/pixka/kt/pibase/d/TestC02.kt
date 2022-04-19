package me.pixka.kt.pibase.d

import me.pixka.kt.pibase.s.PideviceService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import java.math.BigDecimal
import java.text.SimpleDateFormat
import java.util.*

@DataJpaTest
class TestC02 {

    @Autowired
    lateinit var co2Service: Co2Service

    @Autowired
    lateinit var pds: PideviceService

    val sd = SimpleDateFormat("dd-MM-yyyy")
    fun add(n: String = "test add", value: BigDecimal = BigDecimal(0), vd: Date = Date(), pd: PiDevice? = null): Co2 {
        var co2 = Co2(n)
        co2.valuedate = vd
        co2.ppm = value
        co2.piDevice = pd
        return co2Service.save(co2)
    }

    @Test
    fun testAdd() {
        add("co2 1", BigDecimal(200), Date())
        add("co2 2", BigDecimal(333), Date())
        Assertions.assertTrue(co2Service.all().size > 0)
    }


    @Test
    fun searchbyDate() {
        var p = pds.findOrCreate("222222222222")
        add("co2 1", BigDecimal(200), sd.parse("2-10-2022"), p)
        add("co2 2", BigDecimal(333), sd.parse("3-10-2022"), p)

        var list = co2Service.searchbydate(sd.parse("1-10-2022"), sd.parse("31-10-2022"))
        Assertions.assertTrue(list?.size!! > 1)

        var listpd  = co2Service.searchbydateanddevice(p.id,sd.parse("1-10-2022"), sd.parse("31-10-2022"))
        Assertions.assertTrue(listpd?.size!! > 1)
    }

}