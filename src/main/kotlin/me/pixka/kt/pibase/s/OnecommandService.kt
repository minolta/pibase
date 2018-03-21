package me.pixka.kt.pibase.s

import me.pixka.kt.base.s.Ds
import me.pixka.kt.pibase.d.Onecommand
import me.pixka.kt.pibase.d.OnecommandRepo
import org.springframework.stereotype.Service

@Service
class OnecommandService(override val repo: OnecommandRepo): Ds<Onecommand>()
{
    override fun search(search: String, page: Long, limit: Long): List<Onecommand>? {
        return repo.search(search,topage(page,limit))
    }

    fun findNotrunByeviceId(did:Long): List<Onecommand>? {
        return repo.findByPidevice_idAndRun(did,false)
    }

}
