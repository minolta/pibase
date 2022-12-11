package me.pixka.kt.pibase.d

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import me.pixka.base.d.En
import org.hibernate.annotations.Cache
import jakarta.persistence.Cacheable
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.ManyToOne

/**
 * สำหรับเก็บ job ไว้ run ใน pi device
 *
 * @author kykub
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity


class Jobatpi : En() {

    @ManyToOne
    var pidevice: PiDevice? = null
    @Column(insertable = false, updatable = false)
    var pidevice_id: Long? = null

    @ManyToOne
    var job: Job? = null
    @Column(insertable = false, updatable = false)
    var job_id: Long? = null

}
