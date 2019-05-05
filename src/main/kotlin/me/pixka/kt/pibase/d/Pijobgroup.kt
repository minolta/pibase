package me.pixka.kt.pibase.d

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import me.pixka.kt.base.d.En
import me.pixka.kt.base.s.ServiceImpl
import org.hibernate.annotations.Cache
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service
import javax.persistence.Cacheable
import javax.persistence.Entity

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Cacheable
@Cache(usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_WRITE)
class Pijobgroup(var name: String? = null) : En()

@Repository
interface PijobgroupRepo : JpaRepository<Pijobgroup, Long> {
    fun findByName(n: String): Pijobgroup?
}

@Service
class PijobgroupService : ServiceImpl<Pijobgroup>() {
    @Autowired
    lateinit var r: PijobgroupRepo

    fun findByName(n: String) = r.findByName(n)


}