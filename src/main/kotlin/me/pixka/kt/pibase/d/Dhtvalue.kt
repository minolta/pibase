package me.pixka.kt.pibase.d

import java.math.BigDecimal
import java.util.Date

import jakarta.persistence.Cacheable
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.ManyToOne

import org.hibernate.annotations.Cache

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import me.pixka.base.d.En


@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
class Dhtvalue : En() {

    @ManyToOne
    var pidevice: PiDevice? = null

    @Column(insertable = false, updatable = false)
    var pidevice_id: Long? = null

    @Column(precision = 19, scale = 4)
    var h: BigDecimal? = null

    @Column(precision = 19, scale = 4)
    var t: BigDecimal? = null

    var ip: String? = null // ส่งมาจาก ip อะไร
    var valuedate: Date? = null // เวลาที่อ่าน

    var toserver: Boolean? = false // ใช้บอกว่าส่งไปยัง Server ยัง

    override fun toString(): String {
        return "Dhtvalue [h=$h, t=$t, ip=$ip, valuedate=$valuedate]"
    }

}


@JsonIgnoreProperties(ignoreUnknown = true)
class DHTObject(var t:BigDecimal?=null,var h:BigDecimal?=null,var mac:String?=null,var ip:String?=null)
