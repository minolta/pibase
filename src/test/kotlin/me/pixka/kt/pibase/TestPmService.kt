package me.pixka.kt.pibase

import me.pixka.kt.pibase.d.PiDevice
import me.pixka.kt.pibase.d.Pm
import me.pixka.kt.pibase.d.PmService
import me.pixka.kt.pibase.s.PideviceService
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.context.SpringBootTest
import kotlin.test.assertEquals

@DataJpaTest
class TestPmService()
{

    @Autowired
    lateinit var  service:PmService

    @Autowired
    lateinit var ds:PideviceService

    @Test
    fun TestPmService()
    {


        var pm = Pm()
        pm.pidevice = ds.save(PiDevice("xxx"))
        service.save(pm)
        var p = service.findByName("xxx")
        assertEquals("xxx",p?.pidevice?.name)
    }

    @Test
    fun TestToServer()
    {
        service.findByToServer(true)
    }

}