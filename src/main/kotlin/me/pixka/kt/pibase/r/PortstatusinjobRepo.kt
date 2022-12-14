package me.pixka.kt.pibase.r

import me.pixka.kt.pibase.d.Portstatusinjob
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import jakarta.transaction.Transactional

@Repository
interface PortstatusinjobRepo : JpaRepository<Portstatusinjob, Long> {

    fun findByPijob_id(pijobid: Long?): List<*>?
    fun findByRefid(id: Long?): Portstatusinjob?

    @Modifying
    @Transactional
    @Query("delete from Portstatusinjob p where p.pijob_id = ?1")
    fun deleteByPijobId(id: Long)


//    @Modifying
//    @Transactional
//    @Query("delete from Portstatusinjob p where p.id= ?1")
//    fun deleteById(id: Long)


}
