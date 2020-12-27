package me.pixka.kt.pibase.s

import me.pixka.kt.pibase.d.Deviceinzone
import me.pixka.kt.pibase.d.DeviceinzoneService
import me.pixka.kt.pibase.d.PijobgroupService
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import kotlin.test.assertNotNull

@DataJpaTest
class Testfinddz {

    @Autowired
    lateinit var service:DeviceinzoneService

    @Autowired
    lateinit var ps: PideviceService

    @Autowired
    lateinit var  pigs: PijobgroupService
    @Test
    fun testfind()
    {
        var diz = Deviceinzone()
        diz.pijobgroup = pigs.findOrCreate("g1")
        diz.pidevice = ps.findOrCreate("0")

        diz = service.save(diz)


        var found = service.find(diz.pidevice?.id!!,diz.pijobgroup?.id!!)
        assertNotNull(found)
    }

}