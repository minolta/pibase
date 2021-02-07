package me.pixka.kt.pibase.s

import me.pixka.kt.pibase.d.Pijob
import me.pixka.kt.pibase.d.PumpforpijobService
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
class Testpumpbypijobid {

    @Autowired
    lateinit var ps:PumpforpijobService

    @Autowired
    lateinit var pjs:PijobService

    fun addJob(jn:String="job"): Pijob {
        var p = Pijob()
        p.name = jn
        return pjs.save(p)
    }

    @Test
    fun testPumpbypijobID()
    {

        var j1 = addJob()

    }
}