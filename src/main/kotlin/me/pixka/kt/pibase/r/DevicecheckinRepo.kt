package me.pixka.pibase.r

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.web.bind.annotation.RestController

import me.pixka.pibase.d.Devicecheckin

@RestController
interface DevicecheckinRepo : JpaRepository<Devicecheckin, Long> {
    fun findTop1ByPidevice_idOrderByIdDesc(id: Long): Devicecheckin?
}