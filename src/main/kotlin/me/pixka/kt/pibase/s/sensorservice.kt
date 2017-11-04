package me.pixka.kt.pibase.s

import com.fasterxml.jackson.databind.ObjectMapper
import me.pixka.c.HttpControl
import me.pixka.kt.base.s.IptableServicekt
import me.pixka.pibase.d.DS18value
import me.pixka.pibase.s.DS18sensorService
import me.pixka.pibase.s.PideviceService
import me.pixka.pibase.s.ReadSensorService
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.io.IOException

@Service
class SensorService(val ps:PideviceService,val dss:DS18sensorService,val iptableServicekt: IptableServicekt,val http:HttpControl) {
    private val om = ObjectMapper()
    fun readDsOther(desid: Long, sensorid: Long): DS18value? {
        var desdevice = ps.find(desid)
        var sensor = dss.find(sensorid)
        var ip = iptableServicekt.findByMac(desdevice.mac!!)
        val url = "http://${ip?.ip}/ds18valuebysensor/${sensor.name}"
        logger.debug("Read URL: ${url}")
        var value = http.get(url)

        logger.debug("After read : ${value}")
        if (value != null) {
            try {
                val value = om.readValue<DS18value>(value, DS18value::class.java)
                logger.debug("[readservice readdsvalue] read  other complete ds18b20 value :" + value)
                return value
            } catch (e: IOException) {
                logger.error("Error   ${e.message}")
                e.printStackTrace()
            }

        }
        return null
    }


    companion object {
        internal var logger = LoggerFactory.getLogger(SensorService::class.java)
    }
}