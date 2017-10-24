package me.pixka.pibase.s

import me.pixka.kt.base.s.Ds
import me.pixka.pibase.d.Devicecheckin
import me.pixka.pibase.r.DevicecheckinRepo
import org.springframework.stereotype.Service

@Service
class DevicecheckinService(override val repo: DevicecheckinRepo) : Ds<Devicecheckin>() {
    override fun search(search: String, page: Long, limit: Long): List<Devicecheckin>? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun last(id: Long): Devicecheckin? {
        return repo.findTop1ByPidevice_idOrderByIdDesc(id)
    }
}
