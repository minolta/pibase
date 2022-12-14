package me.pixka.kt.pibase.d

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import me.pixka.base.d.En
import org.hibernate.annotations.Cache
import java.math.BigDecimal
import jakarta.persistence.Cacheable
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.ManyToOne

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity


class DS18sensor(var name: String? = null,// สำหรับ บอกว่าเว่าใครอ่าน /dsvalue
                 var forread: Boolean? = false,// จะใช้ตัวนี้สำหรับอ่านค่าแต่ถ้ามีตัวเดียวก็ใช้ตัวนี้อ่าน
                 var last: BigDecimal? = null,
                 var refid: Long? = null,
                 @Column(columnDefinition = "text") var description: String? = null,
                 var callname: String? = null,
                 @ManyToOne var devicegroup: Devicegroup? = null,
                 @Column(insertable = false, updatable = false) var devicegroup_id: Long? = null,
                 @ManyToOne var pidevice: PiDevice? = null,
                 @Column(insertable = false, updatable = false) var pidevice_id: Long? = null,
                 var user_id: Long? = null) : En() {

    fun copy(item: DS18sensor) {

        this.name = item.name
        this.callname = item.callname
        this.forread = item.forread
    }

    override fun toString(): String {
        return "DS18B20 Name:${name}  Call:${callname}"
    }
}
