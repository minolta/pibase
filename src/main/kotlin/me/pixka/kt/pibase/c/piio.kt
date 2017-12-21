package me.pixka.kt.pibase.c


import me.pixka.kt.base.s.DbconfigService
import me.pixka.kt.base.s.ErrorlogService
import me.pixka.ktbase.io.Configfilekt
import me.pixka.pibase.d.DS18value
import me.pixka.pibase.d.Dhtvalue
import me.pixka.pibase.s.DS18sensorService
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.io.File
import java.io.FileFilter
import java.io.IOException
import java.io.InputStream
import java.math.BigDecimal
import java.math.RoundingMode
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*

/**
 * ใช้สำหรับติดต่อกับ H/w ของ PI
 *
 * @author kykub
 */
@Service
class Piio(val dbcfg:DbconfigService, val ds18s: DS18sensorService, val err: ErrorlogService) {
    /**
     * ใช้สำหรับดึง MAC address ของ WIFI
     *
     * @return
     * @throws IOException
     */


    var dhtDirPath = "/sensor/dht22"

    var dhtTvalue: BigDecimal? = null
        private set
    var dhtHvalue: BigDecimal? = null
        private set
    private val ds18value: BigDecimal? = null

    @Throws(IOException::class)
    fun wifiMacAddress(): String {
        try {
            var path = dbcfg.findorcreate("wlpath","/sys/class/net/wlan0/address")
            var contents = String(Files.readAllBytes(Paths.get(path?.value)))
            contents = contents.replace(" ".toRegex(), "")
            contents = contents.replace("\n".toRegex(), "")
            contents = contents.replace("\r".toRegex(), "")
            return contents
        } catch (e: Exception) {
            logger.error("Piio ${e.message}")
            err.n("Piio", "53", "${e.message}")
        }
        return "error"
    }

    fun readDHT(): Dhtvalue? {

        loadpath()

        var content: String

        try {
            content = String(Files.readAllBytes(Paths.get(dhtDirPath)))
            logger.debug("DHT22 Raw value :" + content)
            content = content.replace(" ".toRegex(), "").replace("(\\r|\\n)".toRegex(), "")
            val value = content.split("\\|".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            logger.debug("[" + value[0] + "]----[" + value[1] + "]")
            if (value.size > 0) {
                this.dhtTvalue = BigDecimal(value[0])
                this.dhtHvalue = BigDecimal(value[1])
                val d = Dhtvalue()
                d.t = dhtTvalue
                d.h = dhtHvalue
                d.ip = wifiIpAddress()
                d.valuedate = Date()
                d.toserver = false
                return d
            }
        } catch (e: Exception) {

            err.n("Piio", "65-79", "${e.message}")
            logger.error("Canot read DHT22 ${e.message}")
            this.dhtTvalue = null
            this.dhtHvalue = null
        }

        return null
    }

    /**
     * ใช้ load config ถ้ามีการกำหนดก็จะเปลียน ตำแหน่งอ่านค่าของ dht22
     */
    private fun loadpath() {
        dhtDirPath = dbcfg.findorcreate("dhtpath","/sensor/dht22")?.value!!
    }

    /**
     * IP สำหรับ WL lan
     *
     * @return
     */
    fun wifiIpAddress(): String? {

        var path = dbcfg.findorcreate("wlpath","/sys/class/net/wlan0/address")?.value
        var contents: String? = String(Files.readAllBytes(Paths.get(path)))
        return contents
    }

    /**
     * Local read DS18B20
     *
     * @return
     * @throws Exception
     */
    @Throws(Exception::class)
    fun readDs18(): BigDecimal {
        // logger.debug("DS18b20 read");
        val dir = File(w1DirPath)
        val files = dir.listFiles(DirectoryFileFilter())
        if (files != null) {
            logger.debug("[ds18b20] found devices " + files.size)
            // while (true) {
            for (file in files) {
                // System.out.print(file.getName() + ": ");
                // Device data in w1_slave file
                val filePath = w1DirPath + File.separatorChar + file.name + File.separatorChar + "w1_slave"
                val f = File(filePath)
                try {
                    /*
                    BufferedReader(FileReader(f)).use { br ->
                        var output: String
                        while ((output = br.readLine()) != null) {
                            val idx = output.indexOf("t=")
                            if (idx > -1) {
                                // Temp data (multiplied by 1000) in 5 chars
                                // after t=
                                var tempC = java.lang.Float.parseFloat(output.substring(output.indexOf("t=") + 2))
                                // Divide by 1000 to get degrees Celsius
                                val t = BigDecimal((tempC /= 1000f).toDouble())

                                logger.debug("[ds18b20] value from io " + t)
                                // System.out.print(String.format("%.1f ", tempC));
                                // logger.debug(String.format("%.1f", tempF));
                                return t.setScale(1, RoundingMode.HALF_UP)
                            }
                        }
                    }
                    */


                } catch (ex: Exception) {
                    logger.error("[ds18b20 readio] error " + ex.message)
                }

            }
        }
        // }

        throw Exception("Canot read ds18b20")
    }

    val b1000 = BigDecimal("1000")
    @Throws(Exception::class)
    fun reads(): ArrayList<DS18value>? {
        // logger.debug("DS18b20 read");
        val buf = ArrayList<DS18value>()
        val dir = File(w1DirPath)
        val files = dir.listFiles(DirectoryFileFilter())

        if (files != null) {
            logger.debug("[ioreaddsvalue] found sensors : " + files.size)
            // while (true) {
            for (file in files) {
                // System.out.print(file.getName() + ": ");
                // Device data in w1_slave file
                val filePath = w1DirPath + File.separatorChar + file.name + File.separatorChar + "w1_slave"
                val f = File(filePath)
                try {
                    val inputStream: InputStream = f.inputStream()
                    inputStream.bufferedReader().useLines { lines ->
                        lines.forEach {
                            val index = it.indexOf("t=")
                            if (index != -1) {
                                var value = it.substring(it.indexOf("t=") + 2)
                                var bv = BigDecimal(value).divide(b1000, 2, RoundingMode.HALF_UP)
                                var d = DS18value()
                                d.t = bv
                                val ss = ds18s.findorcreate(file.name)
                                d.ds18sensor = ss
                                d.valuedate = Date()
                                buf.add(d)

                            }
                        }
                    }
                    /*
                    BufferedReader(FileReader(f)).use { br ->
                        var output: String
                        while ((output = br.readLine()) != null) {
                            val idx = output.indexOf("t=")
                            if (idx > -1) {
                                // Temp data (multiplied by 1000) in 5 chars
                                // after t=
                                var tempC = java.lang.Float.parseFloat(output.substring(output.indexOf("t=") + 2))
                                // Divide by 1000 to get degrees Celsius
                                val t = BigDecimal((tempC /= 1000f).toDouble())

                                // System.out.print(String.format("%.1f ", tempC));
                                val tempF = tempC * 9 / 5 + 32
                                // logger.debug(String.format("%.1f", tempF));
                                val ds = DS18value()
                                ds.t = t.setScale(1, RoundingMode.HALF_UP)

                                /*
                     * o.setTmp(t.setScale(1, RoundingMode.HALF_UP));
                     * o.setSn(file.getName()); buf.add(o);
                     */
                                /**
                                 * บันทึก Sensor ที่อ่านด้วย
                                 */
                                val ss = DS18sensor()
                                ss.name = file.name
                                ds.ds18sensor = ss
                                buf.add(ds)

                            }
                        }
                    }
                    */


                } catch (ex: Exception) {
                    logger.error(ex.message)
                    err.n("Piio", "198-214", "${ex.message}")
                }

            }

            return buf
        }
        // }
        logger.error("Read ds18b20 error")
        throw Exception("Canot read ds18b20")
    }

    internal inner class DirectoryFileFilter : FileFilter {
        override fun accept(file: File): Boolean {
            val dirName = file.name
            val startOfName = dirName.substring(0, 3)
            return file.isDirectory && startOfName == "28-"
        }
    }

    companion object {

        internal var logger = LoggerFactory.getLogger(Piio::class.java)

        internal var w1DirPath = "/sys/bus/w1/devices"
    }

}
