package me.pixka.pibase.s

import me.pixka.kt.base.s.DefaultService
import me.pixka.kt.base.s.Ds
import me.pixka.kt.pibase.d.Pidevicegroup
import me.pixka.pibase.r.PidevicegroupRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PidevicegroupService : DefaultService<Pidevicegroup>() {


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

    fun searchMatch(n: String): Pidevicegroup? {
        return dao!!.findByName(n)
    }
}
