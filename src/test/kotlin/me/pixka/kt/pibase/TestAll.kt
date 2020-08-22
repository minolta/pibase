package me.pixka.kt.pibase

import me.pixka.kt.pibase.d.IptableServicekt
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class TestAll
{

    @Autowired
    lateinit var iptableServicekt: IptableServicekt
    @Test
    fun testFindAll()
    {
        var all = iptableServicekt.all()
        println(all)
    }
}