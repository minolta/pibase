package me.pixka.kt.pibase.r

import me.pixka.kt.pibase.d.Pijob
import me.pixka.kt.pibase.d.Portname
import me.pixka.kt.pibase.d.Portstatusinjob
import me.pixka.kt.pibase.s.PideviceService
import me.pixka.kt.pibase.s.PijobService
import me.pixka.kt.pibase.s.PortnameService
import me.pixka.kt.pibase.s.PortstatusinjobService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
class TestDelepijob {

    @Autowired
    lateinit var pijobRepo: PijobRepo
    @Autowired
    lateinit var ps:PijobService
    @Autowired
    lateinit var pijs:PortstatusinjobService
    @Autowired
    lateinit var pds:PideviceService
    @Autowired
    lateinit var pns:PortnameService

    @Test
    fun testDeletePijob()
    {

        var p = Pijob()
        p.pidevice = pds.findOrCreate("fortestdeletepijob")
        p.pidevice_id = p.pidevice!!.id
        p = ps.save(p)

        Assertions.assertNotNull(p)
        Assertions.assertNotNull(p.pidevice)


        var port = pns.findorcreate("d1222")

        var portstatusinjob = Portstatusinjob()
        portstatusinjob.portname = port
        portstatusinjob.pijob = p
        portstatusinjob = pijs.save(portstatusinjob)

        var id = p.pidevice_id!!
        Assertions.assertNotNull(portstatusinjob)
        pijs.deleteBypideviceId(id)
        ps.deleteByPideviceId(id)

//        var jobs = ps.searchByDeviceid(id,0L,1000L)
//        println(jobs!!.size)
//        Assertions.assertTrue(jobs!!.size==0)

    }

}