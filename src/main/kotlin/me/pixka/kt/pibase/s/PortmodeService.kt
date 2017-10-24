package me.pixka.pibase.s

import me.pixka.kt.base.s.Ds
import me.pixka.pibase.d.Portmode
import me.pixka.pibase.r.PortmodeRepo
import me.pixka.pibase.r.PortnameRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PortmodeService(override val repo:PortmodeRepo) : Ds<Portmode>() {
    override fun search(search: String, page: Long, limit: Long): List<Portmode>? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    @Autowired
    private val dao: PortmodeRepo? = null

    fun searchMatch(n: String): Portmode? {
        // TODO Auto-generated method stub
        return null
    }

    fun findorcreate(name: String): Portmode {
        var pm: Portmode? = dao!!.findByName(name)
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
