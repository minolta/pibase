package me.pixka.kt.pibase.d

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import me.pixka.base.d.En
import org.hibernate.annotations.Cache
import jakarta.persistence.Cacheable
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.ManyToOne

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
class Pifw : En() {

    @ManyToOne
    var pifwgroup: Pifwgroup? = null
    @Column(insertable = false, updatable = false)
    var pifwgroup_id: Long? = null
    var verno: String? = null
    var checksum: String? = null
    var pathtofile: String? = null
    var filename: String? = null
    var fwsize: Long? = null

}
