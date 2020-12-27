package me.pixka.kt.pibase

import me.pixka.kt.pibase.d.IptableServicekt
import me.pixka.kt.pibase.d.Iptableskt
import me.pixka.kt.pibase.t.Ip
import me.pixka.kt.pibase.t.Iptask
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.format.annotation.DateTimeFormat
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.test.assertEquals

//@SpringBootTest
@DataJpaTest
class TestScanIP {
    @Autowired
    lateinit var service:IptableServicekt
    var command: String? = null
    fun setup() {
        // logger.debug("Set ups")
        var p = System.getProperty("scancommand")
        if (p != null) {
            command = "${p} -n -sP "
        } else
            command = System.getProperty("scancommand", "C:\\nmap.exe -n -sP ")


    }

    @Test
    fun testscan() {
        r()
    }

    fun r(): ArrayList<Ip>? {

        var buf = ArrayList<Ip>()
        try {
            var network = arrayListOf("192.168.88.0/24")


//            for (n in network) {
//                var b = findIP(n, command!!)
//                buf.addAll(b)
//            }

        } catch (e: Exception) {
            Iptask.logger.error("R() ${e.message}")
            e.printStackTrace()
        }
        return buf
    }

    var s: String? = null
    fun readline(std: BufferedReader): String? {
        s = std.readLine()
        Iptask.logger.debug("Read line : ${s}")
        return s
    }

    fun readOutput(stdInput: BufferedReader): ArrayList<Ip> {
        val buf = ArrayList<Ip>()
        var ip: Ip? = null
        while (readline(stdInput) != null) {
            Iptask.logger.debug("================>${s}")
            if (s != null) {
                if (s!!.startsWith("Nmap scan")) {
                    if (ip == null)
                        ip = Ip()
                    val g = s!!.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                    ip.ip = g[4]
                    Iptask.logger.debug("Save ip ${ip.ip}")
                }

                if (s!!.startsWith("MAC Address:")) {
                    val g = s!!.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                    ip!!.mac = g[2]
                    buf.add(ip)
                    Iptask.logger.debug("Save mac ${ip.mac}")
                    ip = null
                }
            }

        }
        return buf

    }
    @Test
    fun scannow() {
        val proc = Runtime.getRuntime().exec("c:\\nmap.exe -sP 192.168.88.0/24")
        TimeUnit.SECONDS.sleep(10)
        val stdInput = BufferedReader(InputStreamReader(proc.inputStream))
        var buf = readOutput(stdInput)
        println(buf)
    }

    fun savetoDB(buf: ArrayList<Ip>) {
        try {
            for (i in buf) {
                //  logger.debug("find iptables : ${i}")
                if (i != null && i.mac != null) {

                    //      logger.debug("I for find ADDRESS: ${i.mac} Service is ${service}")

                    var mac = i.mac
                    //      logger.debug("mac value : ${mac}")
                    var ii: Iptableskt? = service.findByMac(mac!!)
                    //      logger.debug("Address  Found: ${ii}")
                    if (ii == null) {
                        ii = Iptableskt()
                        ii.ip = i.ip
                        ii.mac = i.mac
//                        newIptable(ii)
                    } else {
                        service.updateiptable(ii, i.ip!!)
                    }
                }
            }


            buf.clear()
            Iptask.logger.debug("Clear buffer : ${buf} buf size: ${buf.size}")
        } catch (e: Exception) {
            Iptask.logger.debug("Error in for : ${e}")
            e.printStackTrace()
        }
    }

    @Test
    fun TestnewIpTable()
    {
        var n = Iptableskt()
        n.ip = "192.168.88.1"
        n.mac= "00:00:00:00"
        n.id=0
        n = service.save(n)

        assertEquals(1L,n.id )

    }
}