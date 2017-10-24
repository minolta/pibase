package me.pixka.pibase.r

import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

import me.pixka.pibase.d.Portname

@Repository
interface PortnameRepo: JpaRepository<Portname, Long> {

    fun findByName(n: String): Portname

    @Query("from Portname pn where pn.name like %?1% or pn.piport like %?1%")
    fun search(s: String, page: Pageable): List<Portname>?

    fun findByRefid(id: Long?): Portname

}
