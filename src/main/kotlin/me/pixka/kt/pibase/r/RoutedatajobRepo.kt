package me.pixka.pibase.r

import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

import me.pixka.kt.pibase.d.Routedatajob

@Repository
interface RoutedatajobRepo : JpaRepository<Routedatajob, Long> {

    @Query("from Routedatajob r where r.name like %?1% or r.url like %?1%")
    fun search(s: String, page: Pageable): List<*>

    fun findByName(n: String): Routedatajob

}
