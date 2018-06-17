package me.pixka.pibase.s

import me.pixka.kt.base.s.Ds
import me.pixka.kt.pibase.d.PiDevice
import me.pixka.pibase.r.PideviceRepo
import org.springframework.stereotype.Service

@Service
class PideviceService(override val repo: PideviceRepo) : Ds<PiDevice>() {
    override fun search(search: String, page: Long, limit: Long): List<PiDevice>? {
        return repo.search(search, this.topage(page, limit))
    }

    fun findByMac(s: String): PiDevice ?{
        return repo.findByMac(s)
    }

    fun search(s: String, uid: Long, page: Long?, limit: Long?): List<PiDevice>? {
        return repo.search(s, uid, this.topage(page!!, limit!!))
    }

    fun findByRefid(id: Long): PiDevice? {
        return repo.findByRefid(id)
    }

    fun create(mac: String, refid: Long): PiDevice? {
        val pd = PiDevice()
        pd.name = "name-" + mac
        pd.mac = mac
        pd.refid = refid


        return save(pd)
    }
    fun create(mac: String, name: String): PiDevice? {
        val pd = PiDevice()
        pd.name = name
        pd.mac = mac
        return save(pd)
    }

    fun searchMatch(n: String): PiDevice {
        return repo.findByName(n)
    }
    fun clear()
    {
        repo.clear()
    }
    fun showTables(): List<*>? {
        var tables = repo.showtables()
        return tables
    }
}
