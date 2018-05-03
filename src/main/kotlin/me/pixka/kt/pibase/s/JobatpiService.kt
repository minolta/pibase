package me.pixka.pibase.s

import me.pixka.kt.base.s.Ds
import me.pixka.kt.pibase.d.Jobatpi
import me.pixka.pibase.r.JobatpiRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class JobatpiService : Ds<Jobatpi>() {
    override fun search(search: String, page: Long, limit: Long): List<Jobatpi>? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    @Autowired
    private val dao: JobatpiRepo? = null


    fun findbyMac(mac: String): List<*>? {
        return null
    }

    fun findByPiID(id: Long?): List<*> {
        return dao!!.findByPidevice_id(id)
    }

}
