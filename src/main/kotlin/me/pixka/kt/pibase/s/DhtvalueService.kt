package me.pixka.pibase.s

import me.pixka.kt.base.s.Ds
import me.pixka.pibase.d.Dhtvalue
import me.pixka.pibase.d.PiDevice
import me.pixka.pibase.r.DhtvalueRepo
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.util.*

@Service
class DhtvalueService(override val repo: DhtvalueRepo) : Ds<Dhtvalue>() {
    override fun search(search: String, page: Long, limit: Long): List<Dhtvalue>? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun create(device: PiDevice, t: BigDecimal, h: BigDecimal, ip: String, adddate: Date): Dhtvalue {
        var dv = Dhtvalue()
        dv.pidevice = device
        dv.h = h
        dv.t = t
        dv.valuedate = adddate
        dv.ip = ip
        return save(dv)!!
    }

    fun last(): Dhtvalue? {
        // return dao.findTop1ByOrderByIdDesc();
        return repo.findTop1ByOrderByValuedateDesc()
    }

    /**
     * ใช้สำหรับดึงข้อมูลจาก local ที่ยังไม่ส่งไปยัง Server toserver = false;
     *
     * @return
     */
    fun notInserver(): List<*> {
        return repo.findByToserver(false, topage(0, 200)!!)
    }


    fun findValueforgraph(pideviceid: Long?, s: Date, e: Date): List<*> {
        return repo.findgraph(pideviceid, s, e)
    }
}
