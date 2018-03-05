package me.pixka.pibase.r

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

import me.pixka.pibase.d.Pifw
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Query

@Repository
interface PifwRepo : JpaRepository<Pifw, Long> {

    fun findByVerno(ver: String): Pifw

    fun findTop1ByOrderByIdDesc(): Pifw


    fun findTop1ByPifwgroup_idOrderByIdDesc(groupid: Long?): Pifw
    @Query("from Pifw p where p.verno like %?1%")
    fun search(search: String, topage: Pageable): List<Pifw>?

}
