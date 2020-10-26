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
    fun add() {
        var pi = PiDevice()
        pi.name = "TEst"
        pi = pds.save(pi)

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

        service.deleteAll(2L)
        assertEquals(2,service.all().size)

        service.deleteAll(1L)
        assertEquals(0,service.all().size)

    }
}