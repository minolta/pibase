package me.pixka.pibase.r

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

import me.pixka.pibase.d.Pifw

@Repository
interface PifwRepo : JpaRepository<Pifw, Long> {

    fun findByVerno(ver: String): Pifw

    fun findTop1ByOrderByIdDesc(): Pifw


    fun findTop1ByPifwgroup_idOrderByIdDesc(groupid: Long?): Pifw

}
