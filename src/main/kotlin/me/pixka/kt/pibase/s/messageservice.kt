package me.pixka.kt.pibase.s

import me.pixka.kt.base.s.Ds
import me.pixka.kt.pibase.d.Message
import me.pixka.kt.pibase.d.MessageRepo
import org.springframework.stereotype.Service

@Service
class MessageService(override val repo:MessageRepo): Ds<Message>() {
    override fun search(search: String, page: Long, limit: Long): List<Message>? {
        return repo.search(search,topage(page,limit))
    }
}