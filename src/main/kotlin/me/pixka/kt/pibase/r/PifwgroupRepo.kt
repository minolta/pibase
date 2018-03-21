package me.pixka.pibase.r

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

import me.pixka.kt.pibase.d.Pifwgroup
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Query

@Repository
interface PifwgroupRepo : JpaRepository<Pifwgroup, Long> {

    fun findByName(appname: String): Pifwgroup
    @Query("from Pifwgroup p where p.name like %?1%")
    fun search(search: String, topage: Pageable): List<Pifwgroup>?

}
