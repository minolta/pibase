package me.pixka.kt.pibase.d

import me.pixka.base.d.En
import me.pixka.base.s.DefaultService
import me.pixka.kt.pibase.d.PiDevice
import me.pixka.kt.pibase.d.Pijob
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.ManyToOne

@Entity
class Sensorinjob(
    var name: String? = null, @ManyToOne var pijob: Pijob? = null,
    @Column(insertable = false, updatable = false) var pijob_id: Long? = null, @ManyToOne var sensor: PiDevice? = null,
    @Column(insertable = false, updatable = false) var pidevice_id: Long? = null, var refid: Long? = null
) : En() {
    override fun toString(): String {
        return "Pijob : ${pijob?.name} Sensor :${sensor?.name}"
    }
}

@Repository
interface SensorinjobRepo : JpaRepository<Sensorinjob, Long> {
    fun findByPijob_id(id: Long): List<Sensorinjob>?

    fun findByRefid(refid: Long): List<Sensorinjob>?

    @Query("from Sensorinjob sj where sj.pijob.pidevice_id = ?1")
    fun byDevice(id: Long): List<Sensorinjob>?

}

@Service
class SensorinjobService(val r: SensorinjobRepo) : DefaultService<Sensorinjob>() {
    fun findByPijob_id(id: Long) = r.findByPijob_id(id)
    fun findByDeviceId(id: Long) = r.byDevice(id)

    fun findByRefid(refid:Long)=r.findByRefid(refid)
}