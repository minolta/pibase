package me.pixka.pibase.r

import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

import me.pixka.pibase.d.PiDevice

@Repository
interface PideviceRepo : JpaRepository<PiDevice, Long> {

    fun findByMac(s: String): PiDevice

    @Query("from PiDevice p where p.name like %?1% or p.mac like %?1%")
    fun search(s: String, page: Pageable): List<*>

    fun findByName(n: String): PiDevice

}
