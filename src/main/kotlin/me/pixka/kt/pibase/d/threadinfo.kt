package me.pixka.kt.pibase.d

import me.pixka.base.d.En
import me.pixka.base.s.DefaultService
import me.pixka.base.s.search
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.ManyToOne


@Entity
class Threadinfo(@ManyToOne var pidevice: PiDevice? = null, @Column(insertable = false, updatable = false)
var pidevice_id: Long? = null,
                 @Column(columnDefinition = "text") var info: String? = null) : En() {

}


@Repository
interface ThreadinfoRepo : JpaRepository<Threadinfo, Long>, search<Threadinfo> {
    fun findByPidevice_id(id: Long): Threadinfo?

    @Query("from Threadinfo d where d.pidevice.name like %?1%")
    override fun search(s: String, page: Pageable): List<Threadinfo>?
}


@Service
class ThreadinfoService(val r: ThreadinfoRepo) : DefaultService<Threadinfo>() {
    fun findBydeviceId(id: Long): Threadinfo? {
        return r.findByPidevice_id(id)
    }




}
