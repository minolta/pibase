package me.pixka.kt.pibase.s

import me.pixka.kt.pibase.d.Pijob
import me.pixka.kt.pibase.d.Pumpforpijob
import me.pixka.kt.pibase.d.PumpforpijobService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
class TestPumpforpijob {

    @Autowired
    lateinit var pfps:PumpforpijobService

    @Autowired
    lateinit var pds:PideviceService

    @Autowired
    lateinit var  pjs:PijobService



    @Test
    fun testSave()
    {

        var p = Pumpforpijob()
        p.pidevice = pds.findOrCreate("forjob")
        var pj = Pijob()
        pj.name = "test"
        p.pijob  = pjs.save(pj)

        pfps.save(p)

        Assertions.assertTrue(pfps.all().size>0)

    }
}