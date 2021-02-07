package me.pixka.kt.pibase.o

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import me.pixka.kt.pibase.d.Pijob
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.math.BigDecimal

class TestHTObject {

    @Test
    fun testHTObject()
    {
        var htObject = HTObject()
        htObject.h = 10.0
        htObject.t = 20.0

        var piJob = Pijob()
        piJob.tlow = BigDecimal(5)
        piJob.thigh = BigDecimal(100)

        piJob.hlow = BigDecimal(10)
        piJob.hhigh = BigDecimal(100)

        Assertions.assertTrue(htObject.equals(piJob))
    }
    
    @Test
    fun testCast()
    {
        var re = "{\"t\":100,\"h\":100}"
        val om = ObjectMapper()
        var h  = om.readValue<HTObject>(re)
        Assertions.assertTrue(h.h!!>0)
    }
}