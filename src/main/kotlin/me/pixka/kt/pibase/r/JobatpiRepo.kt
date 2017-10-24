package me.pixka.pibase.r

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

import me.pixka.pibase.d.Jobatpi

@Repository
interface JobatpiRepo : JpaRepository<Jobatpi, Long> {

    fun findByPidevice_id(id: Long?): List<*>

}
