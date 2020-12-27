package me.pixka.kt.pibase.d

import me.pixka.kt.pibase.s.DevicegroupService
import me.pixka.kt.pibase.s.PideviceService
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@DataJpaTest
class TestDeviceingroup {

    @Autowired
    lateinit var dgs: DevicegroupService

    @Autowired
    lateinit var service: DeviceingroupService

    @Autowired
    lateinit var piservice: PideviceService


    fun addDevice(n: String, m: String = "00:00:00:00"): PiDevice {
        var d = PiDevice()
        d.name = n
        d.mac = m
        return piservice.save(d)
    }

    fun addDevicegroup(n: String = "devicegroup"): Devicegroup {
        var dg = Devicegroup()
        dg.name = n
        return dgs.save(dg)
    }

    @Test
    fun testDevice() {

        var d1 = addDevice("device1")
        var d2 = addDevice("device2")

        var g1 = addDevicegroup("g1")
        var g2 = addDevicegroup("g2")


        var dig = Deviceingroup()
        dig.devicegroup = g1
        dig.pidevice = d1

        service.save(dig)

        dig = Deviceingroup()
        dig.devicegroup = g2
        dig.pidevice = d1
        service.save(dig)
        assertTrue(service.all().size > 0)
        assertEquals(2, service.all().size)
    }
}