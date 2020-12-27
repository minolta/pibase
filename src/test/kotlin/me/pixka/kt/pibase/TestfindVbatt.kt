package me.pixka.kt.pibase

import me.pixka.kt.pibase.d.PiDevice
import me.pixka.kt.pibase.d.Vbatt
import me.pixka.kt.pibase.d.VbattService
import me.pixka.kt.pibase.s.PideviceService
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import java.math.BigDecimal
import java.text.SimpleDateFormat
import kotlin.test.assertEquals


@DataJpaTest
class TestfindVbatt {
    val df = SimpleDateFormat("dd/MM/yyyy")

    @Autowired
    lateinit var vs: VbattService

    @Autowired
    lateinit var ps: PideviceService

    @Test
    fun testSearch() {

        var p = PiDevice()
        p.name = "pd"
        p.mac = "11:@2:"
        p = ps.save(p)
        assertEquals(1, p.id)
        var v = Vbatt()
        v.v = BigDecimal(10.2)
        v.pidevice = p
        v.valuedate = df.parse("1/1/2020")
        v = vs.save(v)

        assertEquals(1, vs.all().size)

        var re = vs.findBydate(1, df.parse("1/12/2019"), df.parse("2/2/2020"))
        assertEquals(1, re?.size)
    }
}