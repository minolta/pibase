package me.pixka.pibase.o

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import me.pixka.kt.pibase.d.DS18value
import me.pixka.kt.pibase.d.Dhtvalue
import me.pixka.kt.pibase.d.PiDevice

@JsonIgnoreProperties(ignoreUnknown = true)
class Infoobj {
    var uptime: Long? = 0
    var mac: String? = null
    var ip: String? = null
    var dhtvalue: Dhtvalue? = null
    var ds18value: DS18value? = null
    var token: String? = null
    var password: String? = null
    var pidevice: PiDevice? = null
    var device: PiDevice?=null
    var t: Float? = null
    var h: Float? = null
    var heap: Long? = null
    var name: String? = null
    var version: String? = null

    override fun toString(): String {
        var s = "==== MAC:[${mac}]  DHT[${dhtvalue}] DS18VAlue[${ds18value}] "
        if (ds18value != null) {
            if (ds18value!!.ds18sensor != null)
                s += "  DS18Sensor [${ds18value?.ds18sensor}] "
        }
        return s
    }
}
