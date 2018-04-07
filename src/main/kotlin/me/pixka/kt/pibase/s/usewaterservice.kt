package me.pixka.kt.pibase.s

import me.pixka.kt.base.s.Ds
import me.pixka.kt.pibase.d.Usewaterinformation
import me.pixka.kt.pibase.d.UsewaterinformationRepo
import org.springframework.stereotype.Service
import java.util.*

@Service
class UsewaterService(override val repo:UsewaterinformationRepo): Ds<Usewaterinformation>()
{
    override fun search(search: String, page: Long, limit: Long): List<Usewaterinformation>? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun findUse():Usewaterinformation?
    {

        var n = Date()
        var notend = repo.findTop1ByEnd(false)
        return notend

    }

}