package me.pixka.kt.pibase.d

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import me.pixka.base.d.En
import org.hibernate.annotations.Cache
import jakarta.persistence.Cacheable
import jakarta.persistence.Column
import jakarta.persistence.Entity

/**
 * ใช้สำหรับเบ่งกลุ่มของ pi fw ต่างๆ
 *
 * @author kykub
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
class Pifwgroup : En() {
    var name: String? = null
    @Column(columnDefinition = "text")
    var description: String? = null

}
