package me.pixka.kt.pibase.o

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.math.BigDecimal

@JsonIgnoreProperties(ignoreUnknown = true)
class VbattObject (var batt_volt:BigDecimal?=null,var mac:String?=null,var ip:String?=null)