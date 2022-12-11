package me.pixka.kt.pibase.d

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import me.pixka.base.d.En
import org.hibernate.annotations.Cache
import jakarta.persistence.Cacheable
import jakarta.persistence.Entity

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity


class Portmode(var name: String? = null
) : En() {

    override fun toString(): String {
        return "Portmode [name=$name]"
    }
}
