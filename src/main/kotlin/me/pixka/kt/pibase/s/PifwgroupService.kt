package me.pixka.kt.pibase.s

import me.pixka.base.s.DefaultService
import me.pixka.kt.pibase.d.Pifwgroup
import me.pixka.kt.pibase.r.PifwgroupRepo
import org.springframework.stereotype.Service

@Service
class PifwgroupService( val r: PifwgroupRepo) : DefaultService<Pifwgroup>() {


    fun searchMatch(n: String): Pifwgroup? {
        return null
    }
    @Synchronized
    fun findorcreate(appname: String): Pifwgroup? {
        var pn: Pifwgroup? = r.findByName(appname)
        if (pn == null) {
            pn = Pifwgroup()
            pn.name = appname
            pn = save(pn)
        }
        return pn
    }

}
