package me.pixka.kt.pibase.r

import me.pixka.kt.pibase.d.DS18value
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.math.BigDecimal
import java.util.*
import jakarta.transaction.Transactional

@Repository
interface Ds18valueRepo : JpaRepository<DS18value, Long> {
    @Modifying
    @Transactional
    @Query("delete from DS18value d where d.pidevice_id = ?1 and ?2 <= d.valuedate and d.valuedate <=?3")
    fun deleteByDate(id:Long,s:Date,e:Date)

    @Modifying
    @Transactional
    @Query("delete from DS18value d where d.pidevice_id = ?1 ")
    fun deleteByAll(id:Long)

    fun findTop1ByOrderByIdDesc(): DS18value?

    @Query("select avg(t.t) from DS18value t where t. ds18sensor_id = ?1 and t.valuedate >= ?2 and t.valuedate <= ?3 ")
    fun findAvg(id: Long?, s: Date, e: Date): BigDecimal?
    fun findTop100ByToserver(b: Boolean): List<DS18value>?
    fun findTop500ByToserver(b: Boolean): List<DS18value>?

    @Query(" from  DS18value  t where t.pidevice_id = ?1 and t.valuedate >= ?2 and t.valuedate <= ?3 order by t.valuedate ")
    fun findgraphvalue(device_id: Long?, s: Date, e: Date): List<*>?

    fun findTop1ByOrderByValuedateDesc(): DS18value?

    fun findTop1ByIdOrderByValuedateDesc(id: Long?): DS18value?

    fun findTop1ByPidevice_idOrderByValuedateDesc(id: Long?): DS18value?

    fun findTop1ByDs18sensor_idOrderByValuedateDesc(id: Long?): DS18value?

    //            @Query(" from  DS18value  t where t.ds18sensor_id = ?1 and t.valuedate >= ?2 and t.valuedate <= ?3 order by t.valuedate ")
    @Query(" from  DS18value  t where t.pidevice_id = ?1 and t.valuedate >= ?2 and t.valuedate <= ?3 order by t.valuedate ")
    fun findgraphvalueBySensor(sid: Long?, s: Date, e: Date): List<DS18value>?

//    @Query("from DS18value d where  ")
//    override fun search(search: String, topage: Pageable): List<DS18value>?

    @Modifying
    @Transactional
    @Query("delete from DS18value d where d.toserver = true")
    fun cleanToserver()
}
