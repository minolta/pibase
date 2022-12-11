package me.pixka.kt.pibase.d

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import me.pixka.base.d.En
import me.pixka.kt.pibase.d.Devicegroup
import org.hibernate.annotations.Cache
import java.math.BigDecimal
import java.util.*
import jakarta.persistence.Cacheable
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.ManyToOne

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity


class PiDevice(
        var name: String? = null,
        var mac: String? = null,// last ip
        var code: String? = null,
        var ip: String? = null, var password: String? = null,
        @Column(columnDefinition = "text") var description: String? = null,
        var lastupdate: Date? = null,
        var refid:Long?=null,
        @ManyToOne var devicegroup: Devicegroup? = null,
        @Column(insertable = false, updatable = false) var devicegroup_id: Long? = null,
        var user_id: Long? = null, var lastuptime:Long?=null, var lastcheckinlong:Long?=null,
        var a0: BigDecimal?= BigDecimal.ZERO) : En() {

    constructor() : this(ip = null)


    override fun toString(): String {
        return "Device : ${name} ${mac}"
    }
}
