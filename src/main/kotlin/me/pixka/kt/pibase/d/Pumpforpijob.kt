package me.pixka.kt.pibase.d

import me.pixka.base.d.En
import me.pixka.base.s.DefaultService
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.ManyToOne

@Entity
class Pumpforpijob(var name:String?=null,
                   @ManyToOne var pijob: Pijob?=null,@Column(insertable = false,updatable = false)
var pijob_id:Long?=null,@ManyToOne var pidevice:PiDevice?=null,@Column(insertable = false,updatable = false)
    var pidevice_id:Long?=null,var enable:Boolean? = true
            ): En()

@Repository
interface PumpforpijobRepo : JpaRepository<Pumpforpijob,Long>

@Service
class PumpforpijobService: DefaultService<Pumpforpijob>()