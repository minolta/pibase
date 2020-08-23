package me.pixka.kt.pibase.s

import me.pixka.kt.pibase.d.Pijob
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

/**
 * ใช้สำหรับหาว่ามีงานที่กำหนดมาหรือเปล่า
 */
@Service
class FindJob(val js: JobService, val pjs: PijobService) {
    fun loadjob(job: String): List<Pijob>? {
        try {
            var job = js.findByName(job)
            if (job != null) {
                var jobtorun = pjs.findJob(job.id)
                return jobtorun
            }
            return null
        } catch (e: Exception) {
            throw e
        }
    }

    companion object {
        internal var logger = LoggerFactory.getLogger(FindJob::class.java)
    }
}