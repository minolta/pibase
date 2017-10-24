package me.pixka.pibase.s

import me.pixka.kt.base.s.Ds
import me.pixka.pibase.d.Pidevicegroup
import me.pixka.pibase.r.PidevicegroupRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PidevicegroupService : Ds<Pidevicegroup>() {
    override fun search(search: String, page: Long, limit: Long): List<Pidevicegroup>? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    @Autowired
    private val dao: PidevicegroupRepo? = null

    fun create(name: String): Pidevicegroup {
        val pg = Pidevicegroup()
        pg.name = name
        return save(pg)!!
    }

    fun getorcreate(name: String): Pidevicegroup {

        return dao!!.findByName(name) ?: return create(name)
    }

    fun searchMatch(n: String): Pidevicegroup {
        return dao!!.findByName(n)
    }
}
