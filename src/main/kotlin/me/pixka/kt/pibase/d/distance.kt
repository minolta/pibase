package me.pixka.kt.pibase.d

import me.pixka.base.d.En
import me.pixka.base.s.DefaultService
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
class Distance(@ManyToOne var pidevice: PiDevice? = null,
               @Column(insertable = false, updatable = false) var pidevice_id: Long? = null,
               var distancevalue: BigDecimal? = null, var valuedate: Date? = null,
               var toserver: Boolean? = false) : En()


@Repository
interface DistanceRepo : JpaRepository<Distance, Long> {

    fun findByToserver(p: Boolean): List<Distance>?
    @Query("from Distance d where d.pidevice_id =?1 and (d.valuedate>=?2 and d.valuedate <=?3)")
    fun searchByDate(id:Long,s:Date,e:Date):List<Distance>?
}


@Service
class DistanceService(val r:DistanceRepo) : DefaultService<Distance>()
{
    fun searchBydate(id:Long,s:Date,e:Date)=r.searchByDate(id,s,e)
}


