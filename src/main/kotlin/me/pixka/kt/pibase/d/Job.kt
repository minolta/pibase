package me.pixka.kt.pibase.d

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import me.pixka.base.d.En
import org.hibernate.annotations.Cache
import jakarta.persistence.Cacheable
import jakarta.persistence.Column
import jakarta.persistence.Entity

/**
 * งานต่างๆที่มีในระบบ
 *
 * @author kykub
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity


class Job(var refid: Long? = null, var name: String? = null,
          @Column(columnDefinition = "text") var description: String? = null,var checkversion:Long?=0) : En() {

    override fun toString(): String {
        return "Job ${name} [id " + this.id + " refid=" + refid + ", name=" + name + ", description=" + description + "]"
    }

}
