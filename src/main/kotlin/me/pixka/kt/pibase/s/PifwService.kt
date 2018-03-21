package me.pixka.pibase.s

import me.pixka.kt.base.s.Ds
import me.pixka.kt.pibase.d.Pifw
import me.pixka.pibase.r.PifwRepo
import org.springframework.stereotype.Service

@Service
class PifwService(override val repo:PifwRepo) : Ds<Pifw>() {
    override fun search(search: String, page: Long, limit: Long): List<Pifw>? {

        return repo.search(search,topage(page,limit))
    }


    fun searchMatch(n: String): Pifw? {
        return repo.findByVerno(n)
    }

    fun findByVersion(ver: String): Pifw {
        return repo.findByVerno(ver)
    }

    fun last(): Pifw {
        return repo.findTop1ByOrderByIdDesc()
    }


    fun findlast(): Pifw {
        return repo.findTop1ByOrderByIdDesc()
    }

    fun last(groupid: Long?): Pifw {
        return repo.findTop1ByPifwgroup_idOrderByIdDesc(groupid)
    }

}
