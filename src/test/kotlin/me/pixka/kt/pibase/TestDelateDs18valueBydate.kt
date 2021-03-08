package me.pixka.kt.pibase

import me.pixka.kt.pibase.d.DS18value
import me.pixka.kt.pibase.d.PiDevice
import me.pixka.kt.pibase.s.Ds18valueService
import me.pixka.kt.pibase.s.PideviceService
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import java.text.SimpleDateFormat
import kotlin.test.assertEquals

@DataJpaTest
class TestDelateDs18valueBydate {

    val df = SimpleDateFormat("dd/MM/yyyy")

    @Autowired
    lateinit var service: Ds18valueService

    @Autowired
    lateinit var pds: PideviceService
    var pi:PiDevice?=null
    fun add() {
        pi = pds.findOrCreate("for delete ds18value by date ")


        var ds = DS18value()
        ds.valuedate = df.parse("10/10/2020")
        ds.pidevice = pi
        service.save(ds)

        ds = DS18value()
        ds.valuedate = df.parse("31/10/2020")
        ds.pidevice = pi
        service.save(ds)


    }


    @Test
    fun testDeleteByDate() {
        add()
        assertEquals(2, service.all().size)

        service.deleteBydate(pi?.id!!,df.parse("1/10/2020"),df.parse("11/10/2020"))
        assertEquals(1, service.all().size)

    }
}