package me.pixka.kt.pibase.s

import me.pixka.kt.base.s.Ds
import me.pixka.kt.pibase.c.Piio
import me.pixka.kt.pibase.d.Message
import me.pixka.kt.pibase.d.MessageRepo
import me.pixka.kt.pibase.d.MessageType
import me.pixka.kt.pibase.d.MessageTypeRepo
import me.pixka.pibase.s.LogistateService
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service
import java.util.*
import com.fasterxml.jackson.databind.ObjectMapper



@Service
//@Profile("pi","lite","server")
class MessageService(override val repo: MessageRepo, val io: Piio, val mtr: MessageTypeRepo) : Ds<Message>() {
    override fun search(search: String, page: Long, limit: Long): List<Message>? {
        return repo.search(search, topage(page, limit))
    }

    fun notsend(): List<Message>? {
        return repo.notsend(false, topage(0, 100))
    }

    fun cleanToserver() {
        repo.deleteBySend()
    }

    @Synchronized fun message(msg: String, msgtype: String): Message? {

        try {
            if (msg.equals("")) {
                logger.error("Message is empty")
                return null
            }
            var mess = Message()
            //  var p = PiDevice()
            // p.mac = io.wifiMacAddress()
            // mess.pidevice = p
            mess.message = msg
            mess.messagedate = Date()
            var mt = mtr.findByName(msgtype)
            if (mt == null) {
                mt = MessageType(msgtype)
                mt = mtr.save(mt)
            }
            mess.messagetype = mt


            var m = save(mess)

            logger.debug("Message is ${m}")
            return m
        }
        catch (e:Exception)
        {
            logger.error("${e.message}")
            e.printStackTrace()
            throw e
        }


    }
    @Synchronized fun tojsonmessage(msg: Any, msgtype: String): Message? {

        val objectMapper = ObjectMapper()
        try {
            if (msg.equals("")) {
                logger.error("Message is empty")
                return null
            }
            var mess = Message()
            //  var p = PiDevice()
            // p.mac = io.wifiMacAddress()
            // mess.pidevice = p
            mess.message = objectMapper.writeValueAsString(msg)
            mess.messagedate = Date()
            var mt = mtr.findByName(msgtype)
            if (mt == null) {
                mt = MessageType(msgtype)
                mt = mtr.save(mt)
            }
            mess.messagetype = mt


            var m = save(mess)

            logger.debug("Message is ${m}")
            return m
        }
        catch (e:Exception)
        {
            logger.error("${e.message}")
            e.printStackTrace()
            throw e
        }


    }

    companion object {
        internal var logger = LoggerFactory.getLogger(MessageService::class.java!!)
    }
}