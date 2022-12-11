package me.pixka.kt.pibase.d

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import me.pixka.base.d.En
import org.hibernate.annotations.Cache
import jakarta.persistence.Cacheable
import jakarta.persistence.Column
import jakarta.persistence.Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity


class Routedatajob : En() {
    var name: String? = null
    @Column(columnDefinition = "text")
    var description: String? = null
    var url: String? = null// สำหรับเก็บว่าให้เรียกต่อ url อะไร

}
