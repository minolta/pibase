package me.pixka.kt.pibase.s

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.databind.ObjectMapper
import me.pixka.kt.pibase.t.HttpGetTask
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

/**
 * ใช้สำหรับ อ่านค่าต่างๆ ของstatus
 */
@Service
class ReadStatusService(val httpService: HttpService) {
    var om = ObjectMapper()

    fun get(url: String, timeout: Long): String? {
        try {
            var get = httpService.get(url,timeout.toInt()*1000)
            return get
        } catch (e: Exception) {
            throw e
        }
    }

    fun readPSI(url: String, timeout: Long = 2): Double? {
        try {
            var re = get(url, timeout)
            var psi = om.readValue<PSI>(re, PSI::class.java)
            return psi.psi?.toDouble()
        } catch (e: Exception) {
            throw e
        }
    }

    /**
     *Read Tmp propreties at status
     */
    fun readTmp(url: String, timeout: Long = 2): Double? {
        try {
            var re = get(url, timeout)
            var tmp = om.readValue<Tmp>(re, Tmp::class.java)
            return tmp.tmp?.toDouble()
        } catch (e: Exception) {
            throw e
        }
    }

    /**
     *Read HT propreties at status
     */
    fun readHT(url: String, timeout: Long = 2): Double? {
        try {
            var re = get(url,timeout)
            var tmp = om.readValue<Tmp>(re, Tmp::class.java)
            return tmp.tmp?.toDouble()
        } catch (e: Exception) {
            throw e
        }
    }
}

@JsonIgnoreProperties(ignoreUnknown = true)
class PSI(var psi: BigDecimal? = null) {
    override fun toString(): String {
        return "me.pixka.kt.pibase.PSI:${psi}"
    }
}

@JsonIgnoreProperties(ignoreUnknown = true)
class Tmp(var tmp: BigDecimal? = null) {
    override fun toString(): String {
        return "me.pixka.kt.pibase.Tmp:${tmp}"
    }
}

@JsonIgnoreProperties(ignoreUnknown = true)
class HT(var t: BigDecimal? = null, var h: BigDecimal? = null) {
    override fun toString(): String {
        return "me.pixka.kt.pibase.T:${t} H:${h}"
    }
}
