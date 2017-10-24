package me.pixka.kt.pibase.d

import me.pixka.kt.base.d.En
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import javax.persistence.Column
import javax.persistence.Entity

@Entity
class Devicegroup(var name: String? = null,@Column(columnDefinition = "text") var description:String?=null) : En() {

}

@Repository
interface DevicegroupRepo : JpaRepository<Devicegroup, Long> {
    @Query("from Devicegroup d where d.name like %?1%")
    fun search(search: String, topage: Pageable): List<Devicegroup>?

    fun findByName(n: String) :Devicegroup?
}