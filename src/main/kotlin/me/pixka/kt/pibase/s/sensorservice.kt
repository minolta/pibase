package me.pixka.kt.pibase.s

import com.fasterxml.jackson.databind.ObjectMapper
import me.pixka.c.HttpControl
import me.pixka.kt.base.s.DbconfigService
import me.pixka.kt.base.s.IptableServicekt
import me.pixka.kt.pibase.d.DS18value
import me.pixka.pibase.s.DS18sensorService
import me.pixka.pibase.s.PideviceService
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.io.IOException
import java.util.*

@Service
class SensorService(val dbconfigService: DbconfigService, val ps: PideviceService, val dss: DS18sensorService, val iptableServicekt: IptableServicekt, val http: HttpControl) {
    private val om = ObjectMapper()

    var readbuffer = ArrayList<DS18ReadBuffer>()

    fun readDsOther(desid: Long, sensorid: Long): DS18value? {
        try {

            var desdevice = ps.find(desid) //เปลียน ip แล้ว
            //var desdevice = ps.findByRefid(desid) //refid จะ save ตอน load pijob
            logger.debug("Find pidevice ${desid} found ===> ${desdevice}")
            var sensor = dss.find(sensorid)
            logger.debug("Find sensor ${sensorid} found ===> ${sensor}")
            var ip = iptableServicekt.findByMac(desdevice?.mac!!)
            logger.debug("Find ip of pidevice ${desdevice} found ===> ${ip}")
            if (ip == null || ip.ip == null) {
                logger.error("Can not find ip ${ip}")
            }

            if (sensor.name == null) {
                logger.error("Sensor name is null")
                return null
            }
            var url = "http://${ip?.ip}/ds18valuebysensor/${sensor.name}"

            if (sensor.name?.indexOf("28-") == -1) {
                //เป็น ktype จะไม่มี 28- ให้อานตรงๆเลย
                url = "http://${ip?.ip}/ktype"
            }


            logger.debug("Read URL: ${url}")
            var value = ""

            try {
                value = http.get(url)
            } catch (e: Exception) {
                logger.error("Find ip ${e.message}")
                var old = readBuffer(desid, sensorid)
                logger.debug("Try to read Old value in device : ${old}")
                if (old != null) {
                    if (checkage(old)) {
                        return old.value
                    }
                }
            }
            logger.debug("After read : ${value}")
            if (value != null) {
                try {
                    val value = om.readValue<DS18value>(value, DS18value::class.java)
                    logger.debug("[readservice readdsvalue] read  other complete ds18b20 value :" + value)
                    update(desid, sensorid, value)
                    return value
                } catch (e: IOException) {
                    logger.error("Error   ${e.message}")
                    //ถ้า error จะอ่านจาก Buffer ก่อน แล้ว ถ้าไม่เจอก็ null
                    var old = readBuffer(desid, sensorid)
                    logger.debug("Try to read Old value in device : ${old}")
                    if (old != null) {
                        if (checkage(old)) {
                            return old.value
                        } else {
                            logger.debug("")
                        }
                    }
                }

            }
        } catch (e: Exception) {
            logger.error("Read DS OTher ${e.message}")

        }
        return null
    }

    fun checkage(old: DS18ReadBuffer): Boolean {
        logger.debug("Check age ")
        //21600000  = 6 ชม
        var readtimeout = dbconfigService.findorcreate("readcache", "21600000").value?.toLong()
        var now = Date().time
        var readtime = old.readdate.time
        var t = now - readtime

        logger.debug("Test Age ${t} $readtimeout")
        if (t > readtimeout!!) {
            logger.error("Data can not use")
            return false
        }

        logger.debug("Old data can use")
        return true


    }

    fun readBuffer(dis: Long, sid: Long): DS18ReadBuffer? {
        if (readbuffer.size < 1)
            return null

        for (b in readbuffer) {
            if (b.disid.equals(dis) && b.sensorid.equals(sid))
                return b
        }

        return null
    }

    /**
     * ใช้สำหรับ update ว่าอ่านเมื่อไหร่
     */
    fun update(did: Long, sid: Long, value: DS18value) {
        logger.debug("Update Read buffer ")
        if (readbuffer.size > 0) {
            for (i in readbuffer) {
                if (i.disid.equals(did) && i.sensorid.equals(sid)) {
                    i.value = value
                    i.readdate = Date()
                    return
                }

            }
        }

        var n = DS18ReadBuffer(did, sid, value, Date())
        readbuffer.add(n)
        logger.debug("Buffer size ${readbuffer.size}")

    }

    companion object {
        internal var logger = LoggerFactory.getLogger(SensorService::class.java)
    }
}

class DS18ReadBuffer(var disid: Long, var sensorid: Long, var value: DS18value, var readdate: Date)