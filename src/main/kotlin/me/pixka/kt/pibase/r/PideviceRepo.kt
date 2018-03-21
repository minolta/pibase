package me.pixka.pibase.r

import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

import me.pixka.kt.pibase.d.PiDevice

@Repository
interface PideviceRepo : JpaRepository<PiDevice, Long> {

    fun findByMac(s: String): PiDevice

    @Query("from PiDevice p where p.name like %?1% or p.mac like %?1% order by p.id")
    fun search(s: String, page: Pageable): List<PiDevice>?

    @Query("from PiDevice p where (p.name like %?1% or p.mac like %?1%) and p.user_id = ?2 order by p.id")
    fun search(s: String,uid:Long, page: Pageable): List<PiDevice>?

    fun findByName(n: String): PiDevice

}
