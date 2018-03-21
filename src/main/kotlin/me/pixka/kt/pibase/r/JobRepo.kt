package me.pixka.pibase.r

import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

import me.pixka.kt.pibase.d.Job

@Repository
interface JobRepo : JpaRepository<Job, Long> {

    fun findByName(n: String): Job

    fun findByRefid(id: Long?): Job

    fun findTop1ByName(name: String): Job

    @Query("from Job j where j.name like %?1%")
    fun search(s: String, page: Pageable): List<Job>?

}
