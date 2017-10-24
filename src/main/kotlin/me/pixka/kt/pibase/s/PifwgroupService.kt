package me.pixka.pibase.s

import me.pixka.kt.base.s.Ds
import me.pixka.pibase.d.Pifwgroup
import me.pixka.pibase.r.PifwgroupRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PifwgroupService : Ds<Pifwgroup>() {
    override fun search(search: String, page: Long, limit: Long): List<Pifwgroup>? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    @Autowired
    private val dao: PifwgroupRepo? = null

    fun searchMatch(n: String): Pifwgroup? {
        return null
    }

    fun findorcreate(appname: String): Pifwgroup? {
        var pn: Pifwgroup? = dao!!.findByName(appname)
        if (pn == null) {
            pn = Pifwgroup()
            pn.name = appname
            pn = save(pn)
        }
        return pn
    }

}
