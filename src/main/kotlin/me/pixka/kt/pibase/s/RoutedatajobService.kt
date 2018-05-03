package me.pixka.pibase.s

import me.pixka.kt.base.s.Ds
import me.pixka.kt.pibase.d.Routedatajob
import me.pixka.pibase.r.RoutedatajobRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class RoutedatajobService : Ds<Routedatajob>() {
    override fun search(search: String, page: Long, limit: Long): List<Routedatajob>? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    @Autowired
    private val dao: RoutedatajobRepo? = null

    fun search(s: String?, page: Long?, limit: Long?): List<*> {
        return dao!!.search(s!!, this.topage(page!!, limit!!)!!)
    }

    fun searchMatch(n: String): Routedatajob {
        return dao!!.findByName(n)
    }

    fun create(name: String, description: String, url: String): Routedatajob {
        val i = Routedatajob()
        i.name = name
        i.description = description
        i.url = url
        return save(i)!!
    }
}
