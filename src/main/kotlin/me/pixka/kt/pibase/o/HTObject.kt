package me.pixka.kt.pibase.o

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import me.pixka.kt.pibase.d.Pijob

@JsonIgnoreProperties(ignoreUnknown = true)
class HTObject(var h: Double? = null, var t: Double?=null) {

    override fun equals(other: Any?): Boolean {
        if (other is Pijob) {
            var tl = other.tlow?.toDouble()
            var th = other.thigh?.toDouble()

            var hl = other.hlow?.toDouble()
            var hh = other.hhigh?.toDouble()

            //ถ้า t ไม่อยู่ในช่วงที่กำหนดของ pijob ให้ออกเลย
            if (t!! < tl!! || t!! > th!!)
                return false

            if(h!! < hl!! || h!!>hh!!)
                return false


            return true
        }
        return super.equals(other)
    }
}