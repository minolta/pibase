package me.pixka.kt.pibase.d

import me.pixka.kt.pibase.s.DevicegroupService
import me.pixka.kt.pibase.s.PideviceService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
class SaveDeviceinzone {

    @Autowired
    lateinit var deviceinzoneService: DeviceinzoneService
    @Autowired
    lateinit var ds:PideviceService

    @Autowired
    lateinit var pjgs:PijobgroupService

    @Test
    fun testSave()
    {

        var device = ds.findOrCreate("1111")
        var diz = Deviceinzone()
        diz.name = "!"
        diz.pidevice=device
        diz.pijobgroup = pjgs.findOrCreate("g1")
        deviceinzoneService.save(diz)

        Assertions.assertTrue(deviceinzoneService.all().size>0)


    }

}