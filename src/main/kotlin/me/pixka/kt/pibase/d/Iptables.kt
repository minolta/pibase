package me.pixka.pibase.d

import me.pixka.kt.base.d.En
import org.hibernate.annotations.Cache
import java.util.*
import javax.persistence.Cacheable
import javax.persistence.Entity

@Entity
@Cacheable
@Cache(usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_WRITE)
class Iptables : En() {

    var mac: String? = null
    var ip: String? = null
    var lastcheckin: Date? = null

}
