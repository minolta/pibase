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
    var pidevice_id:Long?=null,var enable:Boolean? = true,var refid:Long?=null
            ): En()
{
    override fun equals(other: Any?): Boolean {
        if(other is Pumpforpijob)
        {
            if(refid!=null && refid?.equals(other.refid) == true)
                return true
        }
        return super.equals(other)
    }
            }

@Repository
interface PumpforpijobRepo : JpaRepository<Pumpforpijob,Long>
{
    fun findByPijob_id(id:Long):List<Pumpforpijob>?
    fun findByPidevice_idAndPijob_id(pid:Long,jid:Long):Pumpforpijob?
}
@Service
class PumpforpijobService(val r:PumpforpijobRepo): DefaultService<Pumpforpijob>()
{
    fun bypijobid(id:Long)=r.findByPijob_id(id)
    @Synchronized
    fun checkPumpinjob(pid:Long,jid:Long)=r.findByPidevice_idAndPijob_id(pid,jid)
}