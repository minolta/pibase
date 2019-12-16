package me.pixka.pibase.s

import me.pixka.kt.base.s.Ds
import me.pixka.kt.pibase.d.DS18sensor
import me.pixka.pibase.r.Ds18sensorRepo
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class DS18sensorService(override val repo: Ds18sensorRepo) : Ds<DS18sensor>() {
    override fun search(search: String, page: Long, limit: Long): List<DS18sensor>? {
        return repo.search(search, topage(page, limit))
    }

    fun searchMatch(n: String): DS18sensor? {
        return repo.findByName(n)
    }

    fun findorcreate(n: String): DS18sensor? {
        var s: DS18sensor? = repo.findByName(n)
        if (s == null) {
            s = newobject(n)
            s = save(s)
        }

        return s
    }

    fun findorcreate(n: String, callname: String): DS18sensor? {
        var s: DS18sensor? = repo.findByName(n)
        if (s == null) {
            s = newobject(n, callname)
            s = save(s)
        }

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
        return repo.search(s, uid, this.topage(page, limit))
    }

    fun test() {}
    fun search(s: String?, page: Long?, limit: Long?): List<DS18sensor>? {
        return repo.search(s!!, this.topage(page!!, limit!!))
    }

    fun findByname(name: String): DS18sensor? {
        return repo.findByName(name)
    }

    fun findForread(): DS18sensor? {

        return repo.findTop1ByForreadOrderByLasteditDesc(true)
    }

    fun findorcreate(ds18sensor: DS18sensor?): DS18sensor? {
        try {
            if (ds18sensor == null)
                return null
            var ds: DS18sensor? = repo.findByName(ds18sensor.name!!)

            if (ds == null) {
                ds = DS18sensor()
                ds.callname = ds18sensor.callname
                ds.forread = ds18sensor.forread
                ds.name = ds18sensor.name
                ds.refid = ds18sensor.id
                ds = save(ds)
            }
            return ds
        } catch (e: Exception) {
            logger.error("Find or create ERROR ${e.message}")
            throw e
        }
    }


    fun clear() {
        repo.clear()
    }

    companion object {
        internal var logger = LoggerFactory.getLogger(DS18sensorService::class.java)
    }
}
