package me.pixka.kt.pibase.o

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.math.BigDecimal

@JsonIgnoreProperties(ignoreUnknown = true)
class DistanceObj (var distance:BigDecimal?=null,var uptime:Long?=0L,var mac:String?=null)
