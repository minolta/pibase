package me.pixka.pibase.o

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

import me.pixka.pibase.d.Pijob

@JsonIgnoreProperties(ignoreUnknown = true)
class Pijobobj {

    var pijob: Pijob? = null
    var ports: List<Portstatusobj>? = null

}
