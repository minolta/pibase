package me.pixka.pibase.d

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonManagedReference
import me.pixka.kt.base.d.En
import org.hibernate.annotations.Cache
import java.math.BigDecimal
import java.util.*
import javax.persistence.*

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Cacheable
@Cache(usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_WRITE)
class Pijob(var refid: Long? = null, var sdate: Date? = null, var edate: Date? = null, var runtime: Long? = null,
            var waittime: Long? = null, var enable: Boolean? = true, @ManyToOne var ds18sensor: DS18sensor? = null,
            @Column(insertable = false, updatable = false) var ds18sensor_id: Long? = null,
            @OneToMany(mappedBy = "pijob",
            fetch = FetchType.EAGER, cascade = arrayOf(CascadeType.ALL))
            @JsonManagedReference
            var ports: List<Portstatusinjob>? = null,// สำหรับ เก็บว่า job นี้ทำงานกับ อะไรงานไหนเช่น H
            @Column(insertable = false, updatable = false) var job_id: Long? = null,// ก็ทำงานกับค่า h อย่างเดียว HT ทำงานกับ H และ T
            @ManyToOne var job: Job? = null, @ManyToOne var pidevice: PiDevice? = null,
            @Column(insertable = false, updatable = false) var pidevice_id: Long? = null, var name: String? = null,// ช่วงความร้อนที่ทำงาน
            @Column(precision = 10, scale = 3) var tlow: BigDecimal? = null, @Column(precision = 10, scale = 3) var thigh: BigDecimal? = null,// ช่วงความชื้นที่ทำงาน
            @Column(precision = 10, scale = 3) var hlow: BigDecimal? = null, @Column(precision = 10, scale = 3) var hhigh: BigDecimal? = null,
           /* @Temporal(TemporalType.TIME) @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss") */
            var stime: Date? = null,
            /*@Temporal(TemporalType.TIME) @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss") */
            var etime: Date? = null,
            @Column(columnDefinition = "text") var description:String?=null,
            @ManyToOne var desdevice:PiDevice?=null,@Column(insertable = false,updatable = false) var desdevice_id:Long?=null,
            var user_id:Long?=null) : En() {


    override fun toString(): String {

        return " id:${id} ref:${refid} name:${name}  runtime:${runtime} waittime${waittime} tlow:${tlow} thigh${thigh} hlow:${hlow} hhigh:${hhigh} job:${job} dssensor:${ds18sensor} read from ${desdevice}"
    }

    override fun equals(obj: Any?): Boolean {

        return false

    }

    fun copy(from: Pijob) {
        this.hhigh = from.hhigh
        this.hlow = from.hlow
        this.tlow = from.tlow
        this.thigh = from.thigh
        this.sdate = from.sdate
        this.edate = from.edate
        this.stime = from.stime
        this.etime = from.etime
        this.name = from.name
        this.runtime = from.runtime
        this.waittime = from.waittime
        this.enable = from.enable
    }
}
