package me.pixka.kt.pibase.d

import me.pixka.base.d.En
import me.pixka.base.s.DefaultService
import org.springframework.data.jpa.repository.JpaRepository
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
          ,@Column(insertable = false, updatable = false) var pi_device_id:Long?=null): En() {
}


@Repository
interface Co2Repo:JpaRepository<Co2,Long>


@Service
class Co2Service(val r:Co2Repo):DefaultService<Co2>()

