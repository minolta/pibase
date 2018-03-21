package me.pixka.kt.pibase.d

import me.pixka.kt.base.d.En
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.ManyToOne

@Entity
class Userwaterinformation(@ManyToOne var pidevice: PiDevice? = null,
                           @Column(insertable = false, updatable = false) var pidevice_id: Long?,
                           var enduse: Date? = null, var devicegroup_id: Long? = null) : En() {
}
