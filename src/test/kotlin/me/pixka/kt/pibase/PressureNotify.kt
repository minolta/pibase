package me.pixka.kt.pibase

import com.fasterxml.jackson.databind.ObjectMapper
import me.pixka.base.line.s.NotifyService
import me.pixka.kt.pibase.t.HttpGetTask
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.util.concurrent.Executors

@SpringBootTest
class PressureNotify
{

    @Autowired
    lateinit var notify: NotifyService
    var om = ObjectMapper()

    @Test
    fun TestReadPressureAndsend()
    {

        var get = HttpGetTask("http://192.168.89.23/")
        var ee = Executors.newSingleThreadExecutor()
        var f = ee.submit(get)

        var re = f.get()
        var psi=  om.readValue<PSI>(re, PSI::class.java)

        println(psi)
        if(psi.psi?.toDouble()!!<5.5)
            notify.message("Too low","oxTQIdmioYpPrwiXEMbA9dr7w2DkiMFcdm0ZqQJI0wR")
    }
}