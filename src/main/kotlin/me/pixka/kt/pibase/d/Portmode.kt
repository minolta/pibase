package me.pixka.pibase.d

import me.pixka.kt.base.d.En
import org.hibernate.annotations.Cache
import javax.persistence.Cacheable
import javax.persistence.Entity

@Entity
@Cacheable
@Cache(usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_WRITE)
class Portmode : En() {
    var name: String? = null

    override fun toString(): String {
        return "Portmode [name=$name]"
    }
}
