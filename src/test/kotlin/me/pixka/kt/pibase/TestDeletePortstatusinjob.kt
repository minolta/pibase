package me.pixka.kt.pibase

import me.pixka.kt.pibase.d.Logistate
import me.pixka.kt.pibase.d.Pijob
import me.pixka.kt.pibase.d.Portstatusinjob
import me.pixka.kt.pibase.s.LogistateService
import me.pixka.kt.pibase.s.PijobService
import me.pixka.kt.pibase.s.PortstatusinjobService
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import kotlin.test.assertEquals

@DataJpaTest
class TestDeletePortstatusinjob {
    @Autowired
    lateinit var ps: PijobService

    @Autowired
    lateinit var ls: LogistateService

    @Autowired
    lateinit var psts: PortstatusinjobService
    fun addjob(): Pijob {
        var p = Pijob()
        p.name = "test1"
        p.runtime = 10
        p = ps.save(p)
        return p
    }

    fun addPort(pijob: Pijob, r: Int = 10, w: Int = 10): Portstatusinjob {
        var port = Portstatusinjob()
        port.waittime = w
        port.runtime = r
        port.pijob = pijob
        var s = Logistate()
        s.name = "high"
        port.status = ls.save(s)
        return psts.save(port)
    }

    @Test
    fun testDelete() {

        var pj = addjob()
        addPort(pj)
        var port = addPort(pj)


        assertEquals(2,psts.all().size)

        port.portname = null
        port.status = null
        port.ver = 10
        psts.delete(port)

        assertEquals(1,psts.all().size)
    }
}