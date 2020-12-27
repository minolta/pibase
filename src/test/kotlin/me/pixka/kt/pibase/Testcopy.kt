package me.pixka.kt.pibase

import io.mockk.mockk
import me.pixka.kt.pibase.d.Logistate
import me.pixka.kt.pibase.d.Pijob
import me.pixka.kt.pibase.d.Portstatusinjob
import me.pixka.kt.pibase.s.LogistateService
import me.pixka.kt.pibase.s.PijobService
import me.pixka.kt.pibase.s.PortstatusinjobService
import me.pixka.pibase.o.Portstatus
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import java.math.BigDecimal
import kotlin.test.assertEquals

@DataJpaTest
class Testcopy {

    @Autowired
    lateinit var ps: PijobService

    @Autowired
    lateinit var ls:LogistateService
    @Autowired
    lateinit var psts:PortstatusinjobService


    fun addjob(): Pijob {
        var p = Pijob()
        p.name = "test1"
        p.runtime = 10

        p = ps.save(p)
        return p
    }
    @Test
    fun Testcopy() {
        var p = addjob()
        var pcopy = p.c()
        assertEquals("test1 copy",pcopy.name)
        pcopy = ps.save(pcopy)
        assertEquals(2,pcopy.id)
    }

    fun addPort(pijob:Pijob,r:Int=10,w:Int=10): Portstatusinjob {
        var port = Portstatusinjob()
        port.waittime=w
        port.runtime =r
        port.pijob = pijob
        var s = Logistate()
        s.name = "high"
        port.status = ls.save(s)
        return psts.save(port)

    }
    @Test
    fun Testcopyport()
    {
        var p = addjob()
        addPort(p)
        addPort(p)


        var all = psts.all()
        var list = ArrayList<Portstatusinjob>()

        if(all!=null)
        {
            all.forEach {

                list.add(it.c())
            }

        }

        assertEquals(2,list.size)

    }

}