package me.pixka.kt.pibase.d

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import me.pixka.base.d.En
import org.hibernate.annotations.Cache
import java.math.BigDecimal
import java.util.*
import jakarta.persistence.Cacheable
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.ManyToOne

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)


class DS18value(@ManyToOne var pidevice: PiDevice? = null,
                @Column(insertable = false, updatable = false) var pidevice_id: Long? = null,
                @Column(precision = 19, scale = 4) var t: BigDecimal? = null,// วันที่อ่านสำหรับเวลาเก็บข้อมูล
                var valuedate: Date? = null,// ส่งมาจาก ip อะไร
                var ip: String? = null, var toserver: Boolean? = false,
                @ManyToOne var ds18sensor: DS18sensor? = null,
                @Column(insertable = false, updatable = false) var ds18sensor_id: Long? = null) : En() {

    override fun toString(): String {
        return ("DS18value [pidevice=" + pidevice?.name + ", pidevice_id=" + pidevice_id + ", t=" + t + ", valuedate="
                + valuedate + ", ip=" + ip + ", toserver=" + toserver + "${ds18sensor}]")
    }

}
