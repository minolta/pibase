package me.pixka.kt.pibase.d

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import me.pixka.base.d.En
import jakarta.persistence.Entity

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
class Userindevicegroup(var user_id: Long? = null, var devicegroup_id: Long? = null) : En() {

}