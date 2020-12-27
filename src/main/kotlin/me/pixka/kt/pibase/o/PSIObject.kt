package me.pixka.kt.pibase.o

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.math.BigDecimal


@JsonIgnoreProperties(ignoreUnknown = true)
class PSIObject (var psi:BigDecimal?=null,val ip:String?=null,val mac:String?=null)
{
    override fun toString(): String {
        return "${psi} ip:${ip} mac:${mac}"
    }
}