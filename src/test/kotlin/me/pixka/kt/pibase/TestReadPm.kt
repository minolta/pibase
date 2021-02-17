package me.pixka.kt.pibase

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import me.pixka.kt.pibase.d.Pm
import me.pixka.kt.pibase.t.HttpGetTask
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.HttpClients
import org.junit.jupiter.api.Test
import java.math.BigDecimal

class TestReadPm ()
{

    var om = ObjectMapper()

    @Test
    fun readPm()
    {
//       var http = HttpGetTask("http://192.168.89.243")
//
//        var re = http.call()
//
//        var o = om.readValue<Pm>(re!!)
//        println(o)
    }
}

