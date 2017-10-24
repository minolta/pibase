package me.pixka.pibase.r

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

import me.pixka.pibase.d.Portmode

@Repository
interface PortmodeRepo : JpaRepository<Portmode, Long> {

    fun findByName(name: String): Portmode

}
