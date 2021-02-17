package me.pixka.kt.pibase.s

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import me.pixka.kt.pibase.d.Pijob
import me.pixka.kt.pibase.d.PijobgroupService
import me.pixka.kt.pibase.d.Portstatusinjob
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import java.io.File
import java.io.IOException
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Paths


@DataJpaTest
class TestImportjob {

    @Autowired
    lateinit var pds: PideviceService

    @Autowired
    lateinit var js: JobService

    @Autowired
    lateinit var pjs: PijobService

    @Autowired
    lateinit var pos: PortnameService

    @Autowired
    lateinit var dsse: DS18sensorService

    @Autowired
    lateinit var pjgs: PijobgroupService

    @Autowired
    lateinit var  ls:LogistateService

    @Autowired
    lateinit var psis: PortstatusinjobService

    fun readString(): String {
        val contentBuilder = StringBuilder()

        try {
            Files.lines(Paths.get("aj.json"), StandardCharsets.UTF_8).use({ stream ->
                stream.forEach { s ->
                    contentBuilder.append(
                        s
                    ).append("\n")
                }
            })
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return contentBuilder.toString()
    }

    val om = ObjectMapper()

    fun saveport(ports: List<Portstatusinjob>, pijob: Pijob) {

        ports.forEach {
            println(it.portname)
            it.portname = pos.findorcreate(it.portname?.name!!)
            it.pijob = pijob
            it.status = ls.findorcreate(it.status?.name!!)
            if(it.device!=null)
            it.device = pds.findOrCreate(it.device?.mac!!)
            psis.save(it)

        }

//        (it.ports as ArrayList<Portstatusinjob>).clear()

    }

    @Test
    fun importJobFromfile() {
        var file = File("c:\\tmp\\aj.json")
        Assertions.assertTrue(file.exists())
        var re = readString()
        var jobs = om.readValue<List<Pijob>>(re)
        Assertions.assertTrue(jobs.size > 0)

        jobs.forEach {

            println(it.name)

            var d = it.pidevice

            if (d != null) {
                d = pds.findOrCreate(d.mac!!)
                d.name = it.pidevice?.name
                it.pidevice = pds.save(d)
            }
            var tg = it.desdevice
            if (tg != null) {
                tg = pds.findOrCreate(tg.mac!!)
                tg.name = it.desdevice?.name
                it.desdevice = pds.save(tg)

            }
            var dss = it.ds18sensor
            if (dss != null) {
                it.ds18sensor = dsse.findorcreate(dss.name!!)

            }

            var job = it.job
            if (job != null) {
                it.job = js.findorcreate(job.name!!)
            }

            var pg = it.pijobgroup
            if (pg != null) {
                it.pijobgroup = pjgs.findOrCreate(pg.name!!)
            }

            var ports = (it.ports as ArrayList<Portstatusinjob>).clone() as ArrayList<Portstatusinjob>

            (it.ports as ArrayList<Portstatusinjob>).clear()


            if (ports != null) {
                var i = pjs.save(it)
                saveport(ports, i)
            }

        }


        Assertions.assertTrue(pds.all().size > 0)
        Assertions.assertTrue(js.all().size > 0)
        Assertions.assertTrue(pos.all().size > 0)
    }
}