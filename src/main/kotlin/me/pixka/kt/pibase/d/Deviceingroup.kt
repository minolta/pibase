package me.pixka.kt.pibase.d

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import me.pixka.base.d.En
import me.pixka.base.s.DefaultService
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.ManyToOne

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
class Deviceingroup(var name:String?=null,@ManyToOne var devicegroup: Devicegroup?=null,
@Column(insertable = false,updatable = false) var devicegroup_id:Long?=null,
@ManyToOne var pidevice:PiDevice?=null,@Column(insertable = false,updatable = false) var pidevice_id:Long?=null):En()


@Repository
interface DeviceingroupRepo:JpaRepository<Deviceingroup,Long>

@Service
class DeviceingroupService:DefaultService<Deviceingroup>()