package me.pixka.kt.pibase.s

import com.fasterxml.jackson.databind.ObjectMapper
import me.pixka.kt.pibase.t.HttpGetTask
import org.springframework.stereotype.Service
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder
import java.nio.charset.Charset
import java.nio.charset.StandardCharsets
import java.util.*
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

@Service
class HttpService {
    val om = ObjectMapper()
    var caches = ArrayList<RequestCache>()

    @Throws(Exception::class)
    fun get(url: String, timeout: Int = 2000): String {
        var cache = findCache(url)
        if (cache != null)
            return cache
        try {
//            println("call")
            var u = URL(url)
            var c = u.openConnection() as HttpURLConnection
            c.setRequestProperty("Accept-Charset", "UTF-8")
            c.setRequestProperty("Accept", "application/json")
            c.requestMethod = "GET"
            c.connectTimeout = timeout
            c.readTimeout = timeout
            var response = readrespone(c)
            if (response != null)
                addCache(url, response.toString())
            return response.toString()
        } catch (e: Exception) {
            throw e
        }
    }


    @Throws(Exception::class)
    //ไม่ใช้ cache
    fun getNoCache(url: String, timeout: Int = 2000): String {
        try {
            var u = URL(url)
            var c = u.openConnection() as HttpURLConnection
            c.setRequestProperty("Accept-Charset", "UTF-8")
            c.setRequestProperty("Accept", "application/json")
            c.requestMethod = "GET"
            c.connectTimeout = timeout
            c.readTimeout = timeout
            var response = readrespone(c)
            return response.toString()
        } catch (e: Exception) {
            throw e
        }
    }

    fun readrespone(c: HttpURLConnection): StringBuilder {
        val buf = BufferedReader(
                InputStreamReader(
                        c.inputStream))
        val response = StringBuilder()
        var inputLine: String? = ""

        while (buf.readLine().also({ inputLine = it }) != null)
            response.append(inputLine)

        return response
    }

    fun encode(s: String): String? {
        try {
            return URLEncoder.encode(s, StandardCharsets.UTF_8.toString())
        } catch (e: Exception) {
            throw e
        }
    }

    @Throws(Exception::class)
    fun post(url: String, json: Any, timeout: Int): String {
        try {
            var url = URL(url)
            var c = url.openConnection() as HttpURLConnection
            c.requestMethod = "POST"
            c.setRequestProperty("Content-Type", "application/json; utf-8")
            c.setRequestProperty("Accept", "application/json")
            c.connectTimeout = timeout
            c.readTimeout = timeout
            c.doOutput = true

            var jsonstring = om.writeValueAsString(json)
            c.outputStream.use({ os ->
                val input: ByteArray = jsonstring.toByteArray(Charset.forName("UTF-8"))
                os.write(input, 0, input.size)
            })
            var response = readrespone(c)
            return response.toString()
        } catch (e: Exception) {
            throw e
        }
    }

    //สำหรับค้นหา cache ที่มีอยู่ในระบบ
    fun findCache(url: String): String? {
        var cachetime = System.getProperty("cachetime")

        var t = 2000

        if (cachetime != null)
            t = cachetime.toInt()
        var h = url.hashCode()
        var have = caches.find { it.urlid == h }
        if (have != null) {
            var now = Date().time
            if (now - have.exittime!! < t)
                return have.result
            //to update request return null
        }

        return null
    }

    fun addCache(url: String, result: String) {
        var have = caches.find { it.urlid == url.hashCode() }

        if (have == null) {
            var c = RequestCache()
            c.urlid = url.hashCode()
            c.exittime = Date().time
            c.result = result
            c.requestcount = 1
            c.url = url
            caches.add(c)

        } else {
            have.exittime = Date().time
            have.result = result
            have.requestcount = have.requestcount!! + 1
        }

    }

    /**
     * Support Thai parameter
     */
    fun get2(url: String, timeout: Int = 2000): String? {
        var cache = findCache(url)
        if (cache != null)
            return cache
        val ee = Executors.newSingleThreadExecutor()
        var t = HttpGetTask(url)

        try {
            var f = ee.submit(t)
            var re = f.get(timeout.toLong(), TimeUnit.MILLISECONDS)
            if (re != null)
                addCache(url, re)
            return re
        } catch (e: Exception) {
            throw e
        }
    }

    //ไม่ใช้ cache
    fun get2Nocache(url: String, timeout: Int = 2000): String? {
        val ee = Executors.newSingleThreadExecutor()
        var t = HttpGetTask(url)

        try {
            var f = ee.submit(t)
            var re = f.get(timeout.toLong(), TimeUnit.MILLISECONDS)
            return re
        } catch (e: Exception) {
            throw e
        }
    }

}


class RequestCache(var urlid: Int? = null, var url: String? = null,
                   var result: String? = null, var exittime: Long? = null,
                   var requestcount: Long? = 0)