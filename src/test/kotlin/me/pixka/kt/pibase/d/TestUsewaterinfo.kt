package me.pixka.kt.pibase.d

import me.pixka.kt.pibase.s.UsewaterService
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class TestUsewaterinfo {

    @Autowired
    lateinit var us:UsewaterService


    @Test
    fun testAddUse()
    {
        var u = Usewaterinformation()

        var c  = us.save(u)
        println(c.id)
    }

}