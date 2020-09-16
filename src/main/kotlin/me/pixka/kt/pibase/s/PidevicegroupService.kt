package me.pixka.kt.pibase.s

import me.pixka.base.s.DefaultService
import me.pixka.kt.pibase.d.Pidevicegroup
import me.pixka.kt.pibase.r.PidevicegroupRepo
import org.springframework.stereotype.Service

@Service
class PidevicegroupService(val r: PidevicegroupRepo) : DefaultService<Pidevicegroup>() {

    fun create(name: String): Pidevicegroup {
        val pg = Pidevicegroup()
        pg.name = name
        return save(pg)
    }

    fun findOrcreate(name: String): Pidevicegroup {

        return r.findByName(name) ?: return create(name)
    }

    fun searchMatch(n: String): Pidevicegroup? {
        return r.findByName(n)
    }
}
