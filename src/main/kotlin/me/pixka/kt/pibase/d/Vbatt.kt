package me.pixka.kt.pibase.d


import me.pixka.base.d.En
import me.pixka.base.s.DefaultService
import me.pixka.kt.pibase.d.PiDevice
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.util.*
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.ManyToOne


@Entity
class Vbatt(@ManyToOne var pidevice: PiDevice? = null,
            @Column(insertable = false, updatable = false) var pidevice_id: Long? = null,
            var v: BigDecimal? = null, var valuedate: Date? = null,var toserver:Boolean?=false) : En()

@Repository
interface VbattRepo : JpaRepository<Vbatt, Long>
{
    fun findByToserver(b:Boolean):List<Vbatt>?
    @Query("from Vbatt v where v.valuedate >=?2 and v.valuedate <= ?3 and v.pidevice_id =?1")
    fun searchbydate(id:Long,s:Date,e:Date) : List<Vbatt>?
}

@Service
class VbattService (val r:VbattRepo): DefaultService<Vbatt>()
{
    fun nottoserver(): List<Vbatt>? {
        return r.findByToserver(false)
    }
    fun findBydate(id:Long,s:Date,e:Date) = r.searchbydate(id,s,e)
}