package me.pixka.kt.pibase.d

import me.pixka.base.d.En
import me.pixka.base.s.DefaultService
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.ManyToOne

@Entity
class Deviceinzone(var name:String?=null,@ManyToOne var pijobgroup: Pijobgroup?=null,
@Column(insertable = false,updatable = false) var pijobgroup_id:Long?=null,var opentime:Int?=null,
@ManyToOne var pidevice:PiDevice?=null,@Column(insertable = false,updatable = false) var pidevice_id:Long?=null) : En(){
}

@Repository
interface DeviceinzoneRepo:JpaRepository<Deviceinzone,Long>
{
    fun findByPidevice_idAndPijobgroup_id(did:Long,zid:Long):Deviceinzone?
    fun findByPijobgroup_id(id:Long):List<Deviceinzone>?
}

@Service
class DeviceinzoneService(val r:DeviceinzoneRepo):DefaultService<Deviceinzone>()
{
    fun find(did:Long,zid:Long)=r.findByPidevice_idAndPijobgroup_id(did,zid)
    fun deviceinszone(id:Long)=r.findByPijobgroup_id(id)

}