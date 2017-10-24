package me.pixka.pibase.r

import java.util.Date

import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

import me.pixka.pibase.d.Dhtvalue

@Repository
interface DhtvalueRepo : JpaRepository<Dhtvalue, Long> {

    fun findTop1ByOrderByIdDesc(): Dhtvalue?

    fun findByToserver(b: Boolean, page: Pageable): List<*>

    @Query("select hour(t.valuedate),day(t.valuedate),month(t.valuedate),year(t.valuedate),avg(t.h),avg(t.t) from Dhtvalue  t where t.pidevice_id = ?1 and t.valuedate >= ?2 and valuedate <= ?3 group by hour(t.valuedate),day(t.valuedate),month(t.valuedate),year(t.valuedate) order by year(t.valuedate),month(t.valuedate),day(t.valuedate),hour(t.valuedate) ")
    fun findgraph(device_id: Long?, s: Date, e: Date): List<*>

    fun findTop1ByOrderByValuedateDesc(): Dhtvalue?

}
