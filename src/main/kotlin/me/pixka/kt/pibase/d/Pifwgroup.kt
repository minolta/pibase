package me.pixka.pibase.d

import me.pixka.kt.base.d.En
import org.hibernate.annotations.Cache
import javax.persistence.Cacheable
import javax.persistence.Column
import javax.persistence.Entity

/**
 * ใช้สำหรับเบ่งกลุ่มของ pi fw ต่างๆ
 *
 * @author kykub
 */
@Entity
@Cacheable
@Cache(usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_WRITE)
class Pifwgroup : En() {

    var name: String? = null

    @Column(columnDefinition = "text")
    var description: String? = null

}
