package me.pixka.kt.pibase

import me.pixka.kt.pibase.d.Distance
import me.pixka.kt.pibase.d.DistanceService
import me.pixka.kt.pibase.d.PiDevice
import me.pixka.kt.pibase.s.PideviceService
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import java.math.BigDecimal
import java.text.SimpleDateFormat
import kotlin.test.assertEquals

@DataJpaTest
class TestSEarchDistance {

    var df = SimpleDateFormat("dd/MM/yyyy")

    @Autowired
    lateinit var ds: DistanceService

    @Autowired
    lateinit var dds: PideviceService


    fun add() {

        var device = dds.findOrCreate("Test device")


        var d = Distance()
        d.pidevice = device
        d.valuedate = df.parse("1/10/2020")
        d.distancevalue = BigDecimal(10.1)
        ds.save(d)

        d = Distance()
        d.pidevice = device
        d.valuedate = df.parse("3/10/2020")
        d.distancevalue = BigDecimal(15.1)
        ds.save(d)


    }

    @Test
    fun test() {
        add()

//        var alls = ds.all()
        var search = ds.searchBydate(1, df.parse("1/9/2020"), df.parse("31/10/2020"))
//        assertEquals(2, alls.size)
        assertEquals(2, search?.size)
    }
}