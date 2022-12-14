package me.pixka.kt.pibase.s

import me.pixka.base.s.DefaultService
import me.pixka.kt.pibase.d.Portmode
import me.pixka.kt.pibase.r.PortmodeRepo
import org.springframework.stereotype.Service

@Service
class PortmodeService(val r: PortmodeRepo) : DefaultService<Portmode>() {
    fun searchMatch(n: String): Portmode? {
        // TODO Auto-generated method stub
        return null
    }

    @Synchronized
    fun findorcreate(name: String): Portmode {
        var pm: Portmode? = r.findByName(name)
        if (pm == null) {
            pm = newmode(name)
        }
        return pm
    }

    fun newmode(name: String): Portmode {
        val pm = Portmode()
        pm.name = name
        return save(pm)!!
    }

}
