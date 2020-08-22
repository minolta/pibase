package me.pixka.kt.pibase.d

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import me.pixka.base.d.En
import me.pixka.base.s.DefaultService
import org.hibernate.annotations.Cache
import org.slf4j.LoggerFactory
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service
import java.util.*
import javax.persistence.Cacheable
import javax.persistence.Entity

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Cacheable
@Cache(usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_WRITE)
class Iptableskt(var devicename: String? = null, var ip: String? = null, var mac: String? = null, var lastupdate: Date, var lastcheckin: Date) : En() {
    constructor() : this(lastupdate = Date(), lastcheckin = Date())

    override fun toString(): String {
        return "IP: ${ip}  Last check in ${lastupdate} ${lastupdate}"
    }
}

@Repository
open interface IptablesktRepo : JpaRepository<Iptableskt, Long> {
    fun findByMac(mac: String): Iptableskt?
}


@Service
class IptableServicekt(val r: IptablesktRepo) : DefaultService<Iptableskt>() {


    fun findByMac(mac: String): Iptableskt? {
        return r.findByMac(mac)
    }
    fun resetAll() = r.deleteAll()
    fun updateiptable(iptable: Iptableskt, ip: String): Iptableskt? {
        try {
            if (!iptable.ip.equals(ip)) {
                println("Save ip")
                iptable.ip = ip
                logger.debug("Save iptables ${iptable}")
                return save(iptable)
            } else {
                iptable.lastcheckin = Date()
                return this.save(iptable)
            }

        } catch (e: Exception) {
            logger.error("Update ip tables: ${e.message}")
            e.printStackTrace()
        }
        return null
    }

    companion object {
        internal var logger = LoggerFactory.getLogger(IptableServicekt::class.java)
    }
}
