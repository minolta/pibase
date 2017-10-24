package me.pixka.pibase.s

import me.pixka.kt.base.s.Ds
import me.pixka.pibase.d.Portstatusinjob
import me.pixka.pibase.r.PortstatusinjobRepo
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PortstatusinjobService(override val repo: PortstatusinjobRepo) : Ds<Portstatusinjob>() {
    override fun search(search: String, page: Long, limit: Long): List<Portstatusinjob>? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }



    fun searchMatch(n: String): Portstatusinjob? {
        return null
    }

    fun findByPijobid(pijobid: Long?): List<*> {
        return repo.findByPijob_id(pijobid)
    }

    fun finByRefId(refid: Long?): Portstatusinjob? {
        if (refid == null) {
            logger.error("[portstatusinjobservice finByRefId] error REFID is null : " + refid!!)
            return null
        }
        try {
            return repo.findByRefid(refid)
        } catch (e: Exception) {
            e.printStackTrace()
            logger.error("[portstatusinjobservice finByRefId] error : " + e.message)
        }

        return null
    }

    companion object {
        internal var logger = LoggerFactory.getLogger(PortstatusinjobService::class.java!!)
    }

}
