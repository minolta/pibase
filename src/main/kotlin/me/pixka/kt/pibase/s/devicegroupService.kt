package me.pixka.kt.pibase.s

import me.pixka.base.s.DefaultService
import me.pixka.kt.pibase.d.Devicegroup
import me.pixka.kt.pibase.d.DevicegroupRepo
import org.springframework.stereotype.Service

@Service
class DevicegroupService( val r: DevicegroupRepo) : DefaultService<Devicegroup>() {


    @Synchronized
    fun findOeCreate(n:String): Devicegroup? {
        var d  = findByName(n)
        if(d == null)
        {
            d = Devicegroup(n)
            return save(d)
        }
        return d
    }

}