package me.pixka.kt.pibase

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.databind.ObjectMapper
import me.pixka.kt.pibase.s.ReadStatusService
import me.pixka.kt.pibase.t.HttpGetTask
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.math.BigDecimal
import java.util.concurrent.Executors

@SpringBootTest
class TestReadPressure {
    @Autowired
    lateinit var readStatusService: ReadStatusService
    @Test
    fun ReadPressure() {
//
//        var get = HttpGetTask("http://192.168.89.23/")
//        var ee = Executors.newSingleThreadExecutor()
//        var f = ee.submit(get)
//
//        var re = f.get()
//        var psi=  om.readValue<PSI>(re, PSI::class.java)

//        var psi = readStatusService.readPSI("http://192.168.89.23")
//        println(psi)
    }
}
@JsonIgnoreProperties(ignoreUnknown = true)
class PSI(var psi: BigDecimal? = null)
{
    override fun toString(): String {
        return "me.pixka.kt.pibase.PSI:${psi}"
    }
}