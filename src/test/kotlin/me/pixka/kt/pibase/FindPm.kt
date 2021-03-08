package me.pixka.kt.pibase

import me.pixka.kt.pibase.d.PiDevice
import me.pixka.kt.pibase.d.Pm
import me.pixka.kt.pibase.d.PmService
import me.pixka.kt.pibase.s.PideviceService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import java.math.BigDecimal
import java.text.SimpleDateFormat
import kotlin.test.assertEquals

@DataJpaTest
class TestGetPM {
    var df = SimpleDateFormat("dd/MM/yyyy hh:mm:ss")

    @Autowired
    lateinit var pms: PmService

    @Autowired
    lateinit var pds: PideviceService
    fun add() {
        var p = PiDevice()
        p.name = "H-14"
        p.mac = "c8:3a:35:c3:74:f4"
        p = pds.save(p)
        var pm = Pm()
        pm.pm25 = BigDecimal(2)
        pm.pm1 = BigDecimal(11)
        pm.pm10 = BigDecimal(10)
        pm.pidevice = p
        pm.valuedate = df.parse("9/9/2020 10:10:00")

        pm = pms.save(pm)
    }

    @Test
    fun testGetPm() {
        add()
        var all = pms.all()
        assertEquals(1,all.size)

        var values = pms.findByDate(1,df.parse("1/9/2020 00:00:00"),df.parse("30/9/2020 00:00:00"))

        Assertions.assertEquals(0,values?.size)
    }
}