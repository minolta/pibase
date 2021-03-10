package me.pixka.kt.pibase.d

import me.pixka.kt.pibase.s.PideviceService
import me.pixka.kt.pibase.s.PijobService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
class Testpumpinjob {

    @Autowired
    lateinit var pipj:PumpforpijobService

    @Autowired
    lateinit var pjs:PijobService

    @Autowired
    lateinit var  pideviceService: PideviceService

    @Test
    fun testCheck()
    {

        var pijob = Pijob()
        pijob.name = "1"
        pijob = pjs.save(pijob)


        var pd = pideviceService.findOrCreate("1")

        var pumpinpijob = Pumpforpijob()
        pumpinpijob.pijob = pijob
        pumpinpijob.pidevice = pd

        pipj.save(pumpinpijob)


        Assertions.assertNotNull(pipj.checkPumpinjob(pd.id,pijob.id))

    }
}