package me.pixka.pibase.r

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

import me.pixka.kt.pibase.d.Portstatusinjob

@Repository
interface PortstatusinjobRepo : JpaRepository<Portstatusinjob, Long> {

    fun findByPijob_id(pijobid: Long?): List<*>

    fun findByRefid(id: Long?): Portstatusinjob

}
