package me.pixka.pibase.d

import me.pixka.kt.base.d.En
import org.hibernate.annotations.Cache
import javax.persistence.Cacheable
import javax.persistence.Column
import javax.persistence.Entity

@Entity
@Cacheable
@Cache(usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_WRITE)
class Routedatajob : En() {
    var name: String? = null
    @Column(columnDefinition = "text")
    var description: String? = null
    var url: String? = null// สำหรับเก็บว่าให้เรียกต่อ url อะไร

}
