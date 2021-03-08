package me.pixka.kt.pibase.s

import me.pixka.kt.pibase.d.Pijob
import me.pixka.kt.pibase.d.Portstatusinjob
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
class TestAllPijob {

    @Autowired
    lateinit var pjs:PijobService

    @Autowired
    lateinit var psipjs:PortstatusinjobService

    @Test
    fun TestDelete()
    {
//        var pj = Pijob()
//        pj.name = "test"
//        pj  = pjs.save(pj)
//
//
//        var port1 = Portstatusinjob()
//        port1.pijob =pj
//
//        psipjs.save(port1)
//
//       var port2 = Portstatusinjob()
//        port2.pijob =pj
//        psipjs.save(port2)
//
//
//        Assertions.assertTrue(pjs.all().size>0)
//        Assertions.assertTrue(psipjs.all().size>0)
//
//
//        psipjs.deleteBypideviceId(pj.id)
//
//        pjs.delete(pj)
//
//        Assertions.assertTrue(pjs.all().size==0)
    }
}