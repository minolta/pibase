package me.pixka.kt.pibase.s

import me.pixka.base.s.DefaultService
import me.pixka.kt.pibase.d.Onecommand
import me.pixka.kt.pibase.d.OnecommandRepo
import org.springframework.stereotype.Service

@Service
class OnecommandService( val r: OnecommandRepo): DefaultService<Onecommand>()
{

    fun findNotrunByeviceId(did:Long): List<Onecommand>? {
        return r.findByPidevice_idAndRun(did,false)
    }
    fun deletebypijob(id:Long):Boolean
    {
        r.deleteBypijob(id)
        return true
    }

}
