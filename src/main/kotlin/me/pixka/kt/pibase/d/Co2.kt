package me.pixka.kt.pibase.d

import me.pixka.base.d.En
import me.pixka.base.s.DefaultService
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.ManyToOne

@Entity
class Co2(var name:String?=null,var ppm:BigDecimal?=null,var valuedate: Date?=null,
@ManyToOne var piDevice: PiDevice?=null
          ,@Column(insertable = false, updatable = false) var pi_device_id:Long?=null,var t:Long?=null): En() {
}


@Repository
interface Co2Repo:JpaRepository<Co2,Long>
{

    @Query("from Co2 c where c.valuedate >=?1 and c.valuedate <=?2")
    fun searchbydate(s:Date,e:Date):List<Co2>?

    @Query("from Co2 c where c.valuedate >=?2 and c.valuedate<=?3 and c.pi_device_id = ?1")
    fun searchBydeviceanddate(pid:Long,s:Date,e:Date):List<Co2>?
}


@Service
class Co2Service(val r:Co2Repo):DefaultService<Co2>()
{
    fun searchbydate(s:Date,e:Date) = r.searchbydate(s,e)
    fun searchbydateanddevice(pid:Long,s:Date,e:Date)=r.searchBydeviceanddate(pid,s,e)
}

