package me.pixka.pibase.r

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

import me.pixka.pibase.d.Pifwgroup

@Repository
interface PifwgroupRepo : JpaRepository<Pifwgroup, Long> {

    fun findByName(appname: String): Pifwgroup

}
