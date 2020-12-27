package me.pixka.kt.pibase.r

import org.springframework.data.jpa.repository.JpaRepository

import me.pixka.kt.pibase.d.Devicecheckin
import org.springframework.data.jpa.repository.Query
import org.springframework.web.bind.annotation.RestController

@RestController
interface DevicecheckinRepo : JpaRepository<Devicecheckin, Long> {
    fun findTop1ByPidevice_idOrderByIdDesc(id: Long): Devicecheckin?
    @Query("from Devicecheckin dc where dc.pidevice.mac = ?1")
    fun findByMac(mac: String): Devicecheckin?
}
