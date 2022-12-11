package me.pixka.kt.pibase.d

import me.pixka.base.d.En
import org.hibernate.annotations.Cache
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.math.BigDecimal
import jakarta.persistence.Cacheable
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.ManyToOne


@Entity
class Sawdustinmix(@ManyToOne var mixdata: Mixdata? = null,var orderno:Int?=null,
                   @Column(insertable = false, updatable = false) var mixdata_id: Long? = null,
                   var value1: BigDecimal? = null) : En()


@Repository
interface SawdustinmixRepo : JpaRepository<Sawdustinmix, Long> {
    fun findByMixdata_id(id: Long): List<Sawdustinmix>?
    //@Query("from Sawdustinmix ")
    //fun search(search: String, topage: Pageable): List<Sawdustinmix>?
}


