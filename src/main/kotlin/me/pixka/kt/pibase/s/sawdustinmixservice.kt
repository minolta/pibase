package me.pixka.kt.pibase.s

import me.pixka.kt.base.s.Ds
import me.pixka.kt.pibase.d.Sawdustinmix
import me.pixka.kt.pibase.d.SawdustinmixRepo
import org.springframework.stereotype.Service

@Service
class SawdustinmixService(override val repo: SawdustinmixRepo) : Ds<Sawdustinmix>() {
    override fun search(search: String, page: Long, limit: Long): List<Sawdustinmix>? {
        TODO()
        //return repo.search(search,topage(page,limit))
    }

    fun findByMixdataId(id:Long):List<Sawdustinmix>?
    {
        return repo.findByMixdata_id(id)
    }
}