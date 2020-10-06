package me.pixka.kt.pibase.s

import com.fasterxml.jackson.databind.ObjectMapper
import me.pixka.kt.pibase.t.HttpGetTask
import org.springframework.stereotype.Service
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.nio.charset.Charset
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

@Service
class HttpService() {
    val om = ObjectMapper()

    @Throws(Exception::class)
    fun get(url: String, timeout: Int): String {
        try {
//            println("call")
            var url = URL(url)
            var c = url.openConnection() as HttpURLConnection
            c.setRequestProperty("Accept-Charset", "UTF-8");
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
                        c.inputStream, Charset.forName("UTF-8")))
        val response = StringBuilder()
        var inputLine: String? = ""

        while (buf.readLine().also({ inputLine = it }) != null)
            response.append(inputLine)

        return response
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

    fun get2(url: String, timeout: Int = 2000): String? {
        val ee = Executors.newSingleThreadExecutor()
        var t = HttpGetTask(url)

        try {
            var f = ee.submit(t)
            return f.get(timeout.toLong(), TimeUnit.MILLISECONDS)

        } catch (e: Exception) {
            throw e
        }
    }
}