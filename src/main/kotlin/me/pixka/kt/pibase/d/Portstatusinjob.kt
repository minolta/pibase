package me.pixka.pibase.d

import com.fasterxml.jackson.annotation.JsonBackReference
import me.pixka.kt.base.d.En
import org.hibernate.annotations.Cache
import javax.persistence.Cacheable
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.ManyToOne

@Entity
@Cacheable
@Cache(usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_WRITE)
class Portstatusinjob : En() {
    @ManyToOne
    var portname: Portname? = null
    @Column(insertable = false, updatable = false)
    var portname_id: Long? = null

    // private String status;
    var refid: Long? = null

    @ManyToOne
    var status: Logistate? = null

    @Column(insertable = false, updatable = false)
    var status_id: Long? = null

    @ManyToOne
    @JsonBackReference
    var pijob: Pijob? = null
    @Column(insertable = false, updatable = false)
    var pijob_id: Long? = null

    fun copy(from: Portstatusinjob) {
        this.portname = from.portname
        this.status = from.status
    }
}
