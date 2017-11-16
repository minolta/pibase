package me.pixka.kt.pibase.s

import me.pixka.kt.base.s.DbconfigService
import me.pixka.pi.io.Dotmatrix
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Profile
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import java.util.*

@Service
@Profile("pi")
class DisplayService(val dot: Dotmatrix, val dbs: DbconfigService) {

    var lock: Boolean = false
    var obj: Any? = null //who use display
    var displaybuf = ArrayList<Displayjob>()

    @Scheduled(fixedDelay = 1000)
    fun run() {

    }

    /*สำหรับจอง Display*/
    fun lockdisplay(any: Any): Dotmatrix {
        if (lock) {
            logger.error("Display in use ${obj}")
            throw Exception("Display in use ${obj}")
        }
        this.obj = any
        lock = true
        return dot;
    }

    fun unlock(any: Any) {
        logger.info("Unlock display ${obj}")
        obj = null
        lock = false
    }


    companion object {
        internal var logger = LoggerFactory.getLogger(DisplayService::class.java)
    }

}

class Displayjob(val forprint: String = "", val jobdate: Date = Date(), val jobtype: Int) {
    companion object {
        const val TEST = 20
    }
}