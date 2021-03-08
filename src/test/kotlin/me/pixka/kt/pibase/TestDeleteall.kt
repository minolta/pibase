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
class TestDeleteall
{
    val df = SimpleDateFormat("dd/MM/yyyy")

    @Autowired
    lateinit var service: Ds18valueService

    @Autowired
    lateinit var pds: PideviceService

    var pi:PiDevice?=null
    fun add() {
        pi = pds.findOrCreate("TEst for delete all")

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
    fun testDeleteAll()
    {
        add()
        assertEquals(2,service.all().size)
        service.deleteAll(pi!!.id)
        assertEquals(0,service.all().size)
    }
}