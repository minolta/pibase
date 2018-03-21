package me.pixka.pibase.r

import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

import me.pixka.kt.pibase.d.DS18sensor

@Repository
interface Ds18sensorRepo : JpaRepository<DS18sensor, Long> {

    fun findByName(n: String): DS18sensor

    @Query("from DS18sensor d where d.name like %?1% or d.call like %?1% order by d.id")
    fun search(s: String, page: Pageable?): List<DS18sensor>?


    @Query("from DS18sensor d where (d.name like %?1% or d.call like %?1%) and d.user_id = ?2 order by d.id")
    fun search(s: String,uid:Long, page: Pageable?): List<DS18sensor>?

    fun findTop1ByForreadOrderByLasteditDesc(b: Boolean): DS18sensor

}
