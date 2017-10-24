package me.pixka.pibase.d

import me.pixka.kt.base.d.En
import org.hibernate.annotations.Cache
import java.util.*
import javax.persistence.Cacheable
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.ManyToOne

@Entity
@Cacheable
@Cache(usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_WRITE)
class Devicecheckin(@ManyToOne var pidevice: PiDevice? = null,
                    @Column(insertable = false, updatable = false) var pidevice_id: Long? = null,
                    var ip: String? = null, var checkindate: Date? = null, var password: String? = null) : En() {

}
