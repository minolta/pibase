package me.pixka.kt.pibase.o

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import me.pixka.kt.pibase.d.Devicegroup
import me.pixka.kt.pibase.d.PiDevice

@JsonIgnoreProperties(ignoreUnknown = true)
class AddDeviceingroup (var devicegroup: Devicegroup?=null,var pidevices:List<PiDevice>?=null)
