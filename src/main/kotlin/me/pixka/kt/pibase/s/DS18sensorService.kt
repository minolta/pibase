package me.pixka.kt.pibase.s

import me.pixka.base.s.DefaultService
import me.pixka.kt.pibase.d.DS18sensor
import me.pixka.kt.pibase.r.Ds18sensorRepo
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class DS18sensorService(val r: Ds18sensorRepo) : DefaultService<DS18sensor>() {

    fun searchMatch(n: String): DS18sensor? {
        return r.findByName(n)
    }

    //    fun findByMac(n: String) = r.findByMac(n)
    @Synchronized
    fun findorcreate(n: String): DS18sensor? {
        logger.debug("Find Sensor by ${n}")
        var s: DS18sensor? = r.findByName(n)
        if (s == null) {
            s = newobject(n)
            s = save(s)
        }
        logger.debug("Find ${n} return ${s}")
        return s
    }

    @Synchronized
    fun findorcreate(n: String, callname: String): DS18sensor? {
        logger.debug("Find Sensor by ${n} cn ${callname}")
        var s: DS18sensor? = r.findByName(n)
        if (s == null) {
            s = newobject(n, callname)
            s = save(s)
        }
        logger.debug("Find ${n} call ${callname} return ${s}")
        return s
    }

    fun newobject(n: String): DS18sensor {
        val s = DS18sensor()
        s.name = n
        s.callname = n
        return s
    }

    fun newobject(n: String, callname: String): DS18sensor {
        val s = DS18sensor()
        s.name = n
        s.callname = callname
        return s
    }


    fun search(s: String, uid: Long, page: Long = 0, limit: Long = 50): List<DS18sensor>? {
        return r.search(s, uid, this.topage(page, limit))
    }

    fun search(s: String?, page: Long?, limit: Long?): List<DS18sensor>? {
        return r.search(s!!, this.topage(page!!, limit!!))
    }

    fun findByname(name: String): DS18sensor? {
        return r.findByName(name)
    }

    fun findForread(): DS18sensor? {

        return r.findTop1ByForreadOrderByLasteditDesc(true)
    }

    @Synchronized
    fun findorcreate(ds18sensor: DS18sensor?): DS18sensor? {

        if (ds18sensor == null)
            return null
        var ds: DS18sensor? = r.findByName(ds18sensor.name!!)

        if (ds == null) {
            ds = DS18sensor()
            ds.callname = ds18sensor.callname
            ds.forread = ds18sensor.forread
            ds.name = ds18sensor.name
            ds.refid = ds18sensor.id.toLong()
            ds = save(ds)
        }
        return ds
    }


    fun clear() {
        r.clear()
    }

    companion object {
        internal var logger = LoggerFactory.getLogger(DS18sensorService::class.java)
    }
}
