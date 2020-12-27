package me.pixka.kt.pibase.d

import me.pixka.kt.pibase.s.PideviceService
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import kotlin.test.assertEquals

@DataJpaTest
class TestDeviceinzone {

    @Autowired
    lateinit var service: DeviceinzoneService

    @Autowired
    lateinit var ps: PideviceService

    @Autowired
    lateinit var  pigs:PijobgroupService
    @Test
    fun testAdd() {
        var diz = Deviceinzone()
        diz.pijobgroup = pigs.findOrCreate("g1")
        diz.pidevice = ps.findOrCreate("0")

        service.save(diz)

        assertEquals(1,service.all().size)
    }
}