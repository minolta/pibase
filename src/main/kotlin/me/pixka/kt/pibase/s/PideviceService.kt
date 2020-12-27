package me.pixka.kt.pibase.s

import me.pixka.base.s.DefaultService
import me.pixka.kt.pibase.d.PiDevice
import me.pixka.kt.pibase.r.PideviceRepo
import org.springframework.stereotype.Service

@Service
class PideviceService(val r: PideviceRepo) : DefaultService<PiDevice>() {


    fun findByMac(s: String): PiDevice? {
        return r.findByMac(s)
    }

    fun search(s: String, uid: Long, page: Long?, limit: Long?): List<PiDevice>? {
        return r.search(s, uid, this.topage(page!!, limit!!))
    }

    fun listcheckin(ids: List<Long>) = r.findbyid(ids)
    fun findByRefid(id: Long): PiDevice? {
        return r.findByRefid(id)
    }
    fun searchMatch(n: String): PiDevice? {
        return r.findByName(n)
    }

    fun clear() {
        r.clear()
    }

    @Synchronized
    fun findOrCreate(mac:String): PiDevice {
        var d = findByMac(mac)
        if(d==null)
        {
            d = PiDevice()
            d.name = mac
            d.mac = mac
            return save(d)
        }
        return d
    }
    @Synchronized
    fun findOrCreate(pd: PiDevice): PiDevice? {
        try {
            var d = findByMac(pd.mac!!)
            if (d == null) {
                d = PiDevice()
                d.name = pd.name
                d.mac = pd.mac
                d.ip = pd.ip
                return save(d)
            }
            return d
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }

    }

}
