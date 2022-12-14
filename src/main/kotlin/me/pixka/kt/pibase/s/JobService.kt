package me.pixka.kt.pibase.s

import me.pixka.base.s.DefaultService
import me.pixka.kt.pibase.d.Job
import me.pixka.kt.pibase.r.JobRepo
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class JobService(val r: JobRepo) : DefaultService<Job>() {


    fun searchMatch(n: String): Job? {
        return r.findByName(n)
    }

    @Synchronized
    fun findOrCreate(name: String = "test"): Job {

        var f = findByName(name)
        if(f==null)
        {
            var ff = Job()
            ff.name = name
            return save(ff)

        }

        return f
    }

    fun create(name: String, description: String, refid: Long?): Job {
        var j = Job()
        j.name = name
        j.description = description
        j.refid = refid
        j = save(j)!!
        return j
    }

    fun findByRefid(id: Long?): Job? {
        return r.findByRefid(id)
    }

    @Synchronized
    fun findorcreate(n: String): Job {
        var f = findByName(n)
        if (f == null) {
            return create(n, "auto", null)
        }
        return f
    }

    fun findandcreateLocal(from: Job): Job? {

        logger.debug("[jobservice] find job from " + from)
        var job: Job? = findByRefid(from.id)
        if (job == null) {
            job = Job()
            job.name = from.name
            job.description = from.description
            job.refid = from.id
            job = save(job)
        }
        return job
    }

    fun findTop1ByName(name: String): Job? {
        return r.findTop1ByName(name)
    }

    fun search(s: String?, page: Long?, limit: Long?): List<*>? {
        try {
            logger.debug("[jobservce search ] try to search : " + s!!)
            val list = r.search(s, this.topage(page!!, limit!!)!!)

            logger.debug("[jobservce search ] founds " + list?.size)
            return list
        } catch (e: Exception) {
            logger.error("[jobservce search ] error " + e.message)
            e.printStackTrace()
        }

        return null
    }


    fun clear() {
        r.clear()
    }

    var logger = LoggerFactory.getLogger(JobService::class.java)

    companion object {
        val HT = 0
        val H = 1
        val T = 2
        val DS = 3
    }

}
