package me.pixka.kt.pibase.s

import me.pixka.base.s.DefaultService
import me.pixka.kt.pibase.d.Pifw
import me.pixka.kt.pibase.r.PidevicegroupRepo
import me.pixka.kt.pibase.r.PifwRepo
import org.springframework.stereotype.Service

@Service
class PifwService(val r: PifwRepo, val pdrp: PidevicegroupRepo) : DefaultService<Pifw>() {

    fun searchMatch(n: String): Pifw? {
        return r.findByVerno(n)
    }

    fun findByVersion(ver: String): Pifw? {
        return r.findByVerno(ver)
    }

    fun last(): Pifw? {
        return r.findTop1ByOrderByIdDesc()
    }


    fun findlast(): Pifw? {
        return r.findTop1ByOrderByIdDesc()
    }

    fun last(groupid: Long?): Pifw? {
        return r.findTop1ByPifwgroup_idOrderByIdDesc(groupid)
    }

}
