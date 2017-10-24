package me.pixka.pibase.o

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

import me.pixka.pibase.d.Portname

@JsonIgnoreProperties(ignoreUnknown = true)
class Portobj {

    var obj: Portname? = null
}
