package me.pixka.c

import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.http.HttpResponse
import org.apache.http.client.methods.HttpGet
import org.apache.http.client.methods.HttpPost
import org.apache.http.entity.StringEntity
import org.apache.http.impl.client.HttpClientBuilder
import org.apache.http.impl.client.HttpClients
import org.apache.http.util.EntityUtils
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import java.io.IOException

@Controller
class HttpControl {

    @Throws(IOException::class)
    operator fun get(s: String): String {
        val httpclient = HttpClients.createDefault()

        val httpGet = HttpGet(s)
        val response1 = httpclient.execute(httpGet)

        try {
            println(response1.statusLine)
            val entity1 = response1.entity
            val re = EntityUtils.toString(entity1)
            EntityUtils.consume(entity1)
            return re
        } catch (e: Exception) {
            logger.error("HTTP Control error :" + e.message)
            throw e
        } finally {
            response1.close()
        }

    }

    @Throws(Exception::class)
    fun postJson(url: String, obj: Any): HttpResponse {
        val httpClient = HttpClientBuilder.create().build() // Use this
        // instead
        val mapper = ObjectMapper()

        try {

            val request = HttpPost(url)
            val jvalue = mapper.writeValueAsString(obj)
            logger.debug("JACK son:" + jvalue)
            val params = StringEntity(jvalue)
            request.entity = params
            request.setHeader("Content-type", "application/json")
            return httpClient.execute(request)
            // handle response here...

        } catch (ex: Exception) {
            logger.error("HTTP POST " + ex.message)
            throw ex

            // handle exception here

        } finally {
            // Deprecated
            // httpClient.getConnectionManager().shutdown();
        }
    }

    fun checkcanconnect(checkserver: String): Boolean {
        try {
            val re = get(checkserver)
            logger.debug("[checkconnection] connection ok :" + re)
            return true
        } catch (e: IOException) {
            logger.error("[checkconnection] Can not connect to server " + e.message)
            e.printStackTrace()
        }

        return false
    }

    companion object {
        internal var logger = LoggerFactory.getLogger(HttpControl::class.java!!)
    }

}
