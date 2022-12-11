package me.pixka.kt.pibase.d

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import me.pixka.base.d.En
import me.pixka.base.s.DefaultService
import me.pixka.base.s.search
import org.hibernate.annotations.Cache
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.util.*
import jakarta.persistence.Cacheable
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.ManyToOne

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity


class Waterflow(var name: String? = null, var waterflowtime: Date? = null,
                @ManyToOne var pidevice: PiDevice? = null,
                @Column(insertable = false, updatable = false) var pidevice_id: Long? = null,
                var waterflowvalue: BigDecimal? = null, var rawvalue: BigDecimal? = null) : En()

@Repository
interface WaterflowRepo : JpaRepository<Waterflow, Long>, search<Waterflow> {
    @Query("from Waterflow w where w.pidevice.name like %?1% ")
    override fun search(s: String, page: Pageable): List<Waterflow>?


    @Query("from Waterflow w where w.pidevice_id = ?1 and (w.waterflowtime >=?2 and w.waterflowtime <= ?3)")
    fun searchByDate(id: Long, s: Date, e: Date): List<Waterflow>?
}


@Service
class WaterflowService(val r: WaterflowRepo) : DefaultService<Waterflow>() {


    fun searchBydevice(pideviceid: Long, s: Date, e: Date): List<Waterflow>? {
        return r.searchByDate(pideviceid, s, e)
    }
}