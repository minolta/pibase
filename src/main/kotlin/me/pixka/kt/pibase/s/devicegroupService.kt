package me.pixka.kt.pibase.s

import me.pixka.kt.base.s.Ds
import me.pixka.kt.pibase.d.Devicegroup
import me.pixka.kt.pibase.d.DevicegroupRepo
import org.springframework.stereotype.Service

@Service
class DevicegroupService(override val repo: DevicegroupRepo) : Ds<Devicegroup>() {
    override fun search(search: String, page: Long, limit: Long): List<Devicegroup>? {
        return repo.search(search,topage(page,limit))
    }

    fun findByName(n:String): Devicegroup? {
        return repo.findByName(n)
    }
}