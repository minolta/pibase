package me.pixka.pibase.s

import me.pixka.kt.base.s.Ds
import me.pixka.pibase.d.Pifwgroup
import me.pixka.pibase.r.PifwgroupRepo
import org.springframework.stereotype.Service

@Service
class PifwgroupService(override val repo: PifwgroupRepo) : Ds<Pifwgroup>() {
    override fun search(search: String, page: Long, limit: Long): List<Pifwgroup>? {

        return repo.search(search, topage(page, limit))
    }


    fun searchMatch(n: String): Pifwgroup? {
        return null
    }

    fun findorcreate(appname: String): Pifwgroup? {
        var pn: Pifwgroup? = repo.findByName(appname)
        if (pn == null) {
            pn = Pifwgroup()
            pn.name = appname
            pn = save(pn)
        }
        return pn
    }

}
