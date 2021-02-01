package me.pixka.kt.pibase.s

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import me.pixka.kt.pibase.d.Pijob
import me.pixka.kt.pibase.d.PijobgroupService
import me.pixka.kt.pibase.d.Portstatusinjob
import org.apache.commons.io.IOUtils
import org.springframework.stereotype.Service
import java.io.File
import java.io.IOException
import java.io.InputStream
import java.io.StringWriter
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Paths


@Service
class ImportpijobService(
    val pds: PideviceService, val js: JobService, val pjs: PijobService,
    val pos: PortnameService, val dsse: DS18sensorService, val pjgs: PijobgroupService,
    val ls: LogistateService, val psis: PortstatusinjobService
) {
    fun readString(): String {
        val contentBuilder = StringBuilder()

        try {
            Files.lines(Paths.get("c:\\tmp\\aj.json"), StandardCharsets.UTF_8).use({ stream ->
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
            if (it.device != null)
                it.device = pds.findOrCreate(it.device?.mac!!)
            psis.save(it)

        }

//        (it.ports as ArrayList<Portstatusinjob>).clear()

    }

    fun import(inputStream: InputStream) {
        val writer = StringWriter()
        IOUtils.copy(inputStream, writer, "UTF-8")
        var re = writer.toString()
        var jobs = om.readValue<List<Pijob>>(re)

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
    }

    fun import(file: String) {
        var file = File(file)
        var re = readString()
        var jobs = om.readValue<List<Pijob>>(re)

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
    }
}