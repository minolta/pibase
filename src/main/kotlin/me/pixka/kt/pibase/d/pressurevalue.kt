package me.pixka.kt.pibase.d

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import me.pixka.base.d.En
import me.pixka.base.s.DefaultService
import me.pixka.base.s.search
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.ManyToOne
import javax.transaction.Transactional

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
class PressureValue(var rawvalue: BigDecimal? = null, var pressurevalue: BigDecimal? = null,
                    @ManyToOne var device: PiDevice? = null,
                    @Column(insertable = false, updatable = false) var device_id: Long? = null,
                    var valuedate: Date? = null, var toserver: Boolean? = false) : En() {
    override fun toString(): String {
        return "${rawvalue} ${pressurevalue} ${device}"
    }
}


@Repository
interface PressureValueRepo : JpaRepository<PressureValue, Long>,search<PressureValue> {
//    @Query("from PressureValue pv where pv.device.name like %?1%")
//    override fun search(search: String?, page: Pageable): List<PressureValue>?

    @Query("from PressureValue pv where pv.device.name like %?1%")
    override fun search(s: String, page: Pageable): List<PressureValue>?
    @Query("from PressureValue pv where pv.device.id=?1 and pv.valuedate >= ?2 and pv.valuedate<=?3")
    fun findDataforgraph(piid: Long?, s: Date?, e: Date?): List<PressureValue>?

    fun findByToserver(b: Boolean): List<PressureValue>?
    @Modifying
    @Transactional
    @Query("delete from PressureValue d where d.toserver = true")
    fun cleanToserver()
}

@Service
class PressurevalueService(val r: PressureValueRepo) : DefaultService<PressureValue>() {

    fun findGraphvalue(piid: Long?, s: Date, e: Date): List<PressureValue>? {
        return r.findDataforgraph(piid, s, e)
    }

    fun findNottoserver(): List<PressureValue>? {
        return r.findByToserver(false)
    }

    fun clean() = r.cleanToserver()

}

