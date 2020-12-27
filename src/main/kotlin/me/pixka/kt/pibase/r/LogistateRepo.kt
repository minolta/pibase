package me.pixka.kt.pibase.r

import me.pixka.base.s.findByName
import me.pixka.base.s.search
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

import me.pixka.kt.pibase.d.Logistate
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Query

@Repository
interface LogistateRepo : JpaRepository<Logistate, Long>,findByName<Logistate>,search<Logistate> {


    fun findByRefid(id: Long?): Logistate?
    @Query("from Logistate l where l.name like %?1%")
    override  fun search(search: String, topage: Pageable): List<Logistate>?

}
