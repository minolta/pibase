package me.pixka.kt.pibase.o

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import me.pixka.kt.pibase.d.Pijob
import java.math.BigDecimal

@JsonIgnoreProperties(ignoreUnknown = true)
class HObject (var h:BigDecimal?=null,var ip:String?=null,var mac:String?=null){

}