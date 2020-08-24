package me.pixka.kt.pibase.s

import me.pixka.base.s.DefaultService
import me.pixka.kt.pibase.d.Devicecheckin
import me.pixka.kt.pibase.r.DevicecheckinRepo
import org.springframework.stereotype.Service

@Service
class DevicecheckinService( val r: DevicecheckinRepo) : DefaultService<Devicecheckin>() {
    fun last(id: Long): Devicecheckin? {
        return r.findTop1ByPidevice_idOrderByIdDesc(id)
    }


    fun findByMac(mac:String) = r.findByMac(mac)
}
