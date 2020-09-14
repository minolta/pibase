package me.pixka.kt.pibase.s

import org.springframework.stereotype.Service
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

@Service
class HttpService
{

    fun  get(url:String,timeout:Int=2000): String {
        println("call")
        var url = URL(url)
        var c = url.openConnection() as HttpURLConnection
        c.requestMethod ="GET"
        c.connectTimeout = timeout
        val buf = BufferedReader(
                InputStreamReader(
                        c.getInputStream()))
        val response = StringBuilder()
        var inputLine: String? =""

        while (buf.readLine().also({ inputLine = it }) != null)
            response.append(inputLine)
        return response.toString()
    }



}