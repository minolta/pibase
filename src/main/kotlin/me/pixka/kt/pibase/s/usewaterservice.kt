package me.pixka.kt.pibase.s

import me.pixka.base.s.DefaultService
import me.pixka.kt.pibase.d.Usewaterinformation
import me.pixka.kt.pibase.d.UsewaterinformationRepo
import org.springframework.stereotype.Service
import java.util.*

@Service
class UsewaterService(val r: UsewaterinformationRepo) : DefaultService<Usewaterinformation>() {


    fun findUse(): Usewaterinformation? {

        var notend = r.findTop1ByEndstatus(false)
        return notend

    }

    fun finUse(groupid: Long): Usewaterinformation? {
        return r.findTop1ByEndstatusAndDevicegroupid(false, groupid)
    }

}