package me.pixka.kt.pibase.r

import me.pixka.base.s.findByName
import me.pixka.kt.pibase.d.Portmode
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PortmodeRepo : JpaRepository<Portmode, Long>, findByName<Portmode> {



}
