package me.pixka.kt.pibase.d

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import me.pixka.base.d.En
import org.hibernate.annotations.Cache
import jakarta.persistence.Cacheable
import jakarta.persistence.Entity

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
class Pidevicegroup : En() {

    var name: String? = null
    var email: String? = null// สำหรับ คนดูแล
    var tel: String? = null// เบอร์
    var own: String? = null

}
