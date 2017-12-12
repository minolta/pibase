package me.pixka.kt.pibase.s

import me.pixka.kt.base.s.Ds
import me.pixka.kt.pibase.d.Mixdata
import me.pixka.kt.pibase.d.MixdataRepo
import org.springframework.stereotype.Service
import java.util.*

@Service
class Mixdataservice(override val repo:MixdataRepo) : Ds<Mixdata>() {
    override fun search(search: String, page: Long, limit: Long): List<Mixdata>? {
        return repo.search(search,topage(page,limit))
    }

    fun findByDate(s: Date, e:Date):List<Mixdata>?
    {
        return repo.findByDate(s,e)
    }

}