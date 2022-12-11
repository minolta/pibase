package me.pixka.kt.pibase.d

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import me.pixka.base.d.En
import org.hibernate.annotations.Cache
import jakarta.persistence.Cacheable
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.ManyToOne

/**
 * Port ของ rasberry pi
 *
 * @author kykub
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity


class Portname(var name: String? = null, var piport: String? = null, var refid: Long? = null,var portenable:Boolean?=false,
               @ManyToOne var portmode: Portmode? = null,
               @Column(insertable = false, updatable = false) var portmode_id: Long? = null,
               var checkversion:Long?=0) : En() {

    fun toInt(): Int {
        if(name?.toLowerCase()?.indexOf("high")!=-1)
            return 1
        if(name?.toLowerCase()?.indexOf("1")!=-1)
            return 1
        if(name?.toLowerCase()?.indexOf("true")!=-1)
            return 1
        return 0
    }

    override fun toString(): String {
        return "${name}"
    }
}
