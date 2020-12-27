package me.pixka.kt.pibase.d

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import me.pixka.base.d.En
import me.pixka.base.s.DefaultService
import me.pixka.base.s.findByName
import me.pixka.base.s.search
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.ManyToOne

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
class Pm(@ManyToOne var pidevice: PiDevice? = null,
         @Column(insertable = false, updatable = false) var pidevice_id: Long? = null,
         var pm25: BigDecimal? = null, var pm1: BigDecimal? = null, var pm10: BigDecimal? = null,
         var valuedate: Date? = null, var toserver: Boolean? = false) : En() {
    override fun toString(): String {
        return "PM2.5:${pm25}  pm1:${pm1} pm10:${pm10}"
    }
}

@Repository
interface PmRepo : JpaRepository<Pm, Long>, search<Pm>, findByName<Pm> {
    @Query("from Pm p where p.pidevice.name like %?1%")
    override fun search(s: String, page: Pageable): List<Pm>?

    @Query("from Pm p where p.pidevice.name = ?1")
    override fun findByName(n: String): Pm?

    fun findByToserver(t: Boolean): List<Pm>?

    @Query("from Pm p where p.pidevice_id = ?1 and p.valuedate >= ?2 and p.valuedate <= ?3")
    fun findByDate(id: Long, s: Date, e: Date): List<Pm>?

}

@Service
class PmService(val r: PmRepo) : DefaultService<Pm>() {
    fun findByToServer(f: Boolean) = r.findByToserver(f)
    fun findByDate(pid: Long, s: Date, e: Date) = r.findByDate(pid, s, e)

}
