package me.pixka.kt.pibase.s

import me.pixka.kt.base.s.Ds
import me.pixka.kt.pibase.d.MessageType
import me.pixka.kt.pibase.d.MessageTypeRepo
import org.springframework.stereotype.Service

@Service
class MessagetypeService(override val repo:MessageTypeRepo): Ds<MessageType>() {
    override fun search(search: String, page: Long, limit: Long): List<MessageType>? {
        return repo.search(search,topage(page,limit))
    }
    fun findByName(n:String): MessageType? {
        return repo.findByName(n)
    }
    fun findOrCreate(n:String): MessageType? {

        var h = findByName(n)
        if(h==null)
        {
            var nn = MessageType(n)
            return save(nn)

        }

        return h
    }
}