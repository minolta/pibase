package me.pixka.kt.pibase.s

import me.pixka.kt.base.s.Ds
import me.pixka.kt.pibase.d.OpenStatus
import me.pixka.kt.pibase.d.OpenstatusRepo
import org.springframework.stereotype.Service

@Service
class OpenstatusService(override val repo: OpenstatusRepo) : Ds<OpenStatus>() {
    override fun search(search: String, page: Long, limit: Long): List<OpenStatus>? {
        return repo.search(search, topage(page, limit))
    }

    fun findOpen(): OpenStatus? {
        return repo.findTop1ByOpenOrderByAdddateDesc(true)
    }
}