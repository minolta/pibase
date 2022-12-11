package me.pixka.kt.pibase.d

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import me.pixka.base.d.En
import me.pixka.kt.pibase.d.PiDevice
import org.hibernate.annotations.Cache
import java.util.*
import jakarta.persistence.Cacheable
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.ManyToOne
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity


class Devicecheckin(@ManyToOne var pidevice: PiDevice? = null,
                    @Column(insertable = false, updatable = false) var pidevice_id: Long? = null,
                    var ip: String? = null, var checkindate: Date? = null,
                    var password: String? = null,var uptime:Long?=null) : En() {

}
