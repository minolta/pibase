package me.pixka.kt.pibase.s

import me.pixka.kt.base.s.Ds
import me.pixka.kt.pibase.d.Mixedinfo
import me.pixka.kt.pibase.d.MixedinfoRepo
import org.springframework.stereotype.Service

@Service
class MixedinfoService(override var repo: MixedinfoRepo) : Ds<Mixedinfo>() {
    override fun search(search: String, page: Long, limit: Long): List<Mixedinfo>? {

        return repo.search(search, topage(page, limit))

    }

    fun findByName(n: String): Mixedinfo? = repo.findByName(n)


}