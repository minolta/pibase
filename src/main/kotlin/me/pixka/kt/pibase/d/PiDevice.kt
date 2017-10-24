package me.pixka.pibase.d

import me.pixka.kt.base.d.En
import me.pixka.kt.pibase.d.Devicegroup
import org.hibernate.annotations.Cache
import java.util.*
import javax.persistence.Cacheable
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.ManyToOne

@Entity
@Cacheable
@Cache(usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_WRITE)
class PiDevice(@ManyToOne var pidevicegroup: Pidevicegroup? = null,
               @Column(insertable = false, updatable = false) var pidevicegroup_id: Long? = null,
               var name: String? = null, var mac: String? = null,// last ip
               var ip: String? = null, var password: String? = null,
               @Column(columnDefinition = "text") var description: String? = null, var lastupdate: Date? = null,
               @ManyToOne var devicegroup:Devicegroup?=null,@Column(insertable = false,updatable = false) var devicegroup_id:Long?=null,
               var user_id:Long?=null) : En() {

    constructor() : this(ip = null)
}
