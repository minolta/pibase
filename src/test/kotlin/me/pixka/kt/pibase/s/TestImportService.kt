package me.pixka.kt.pibase.s

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import java.io.FileInputStream

@DataJpaTest
class TestImportService
{
    @Autowired
    lateinit var ips:ImportpijobService

    @Autowired
    lateinit var ps:PijobService

    @Test
    fun testImportServer()
    {
//        ips.import("c:\\tmp\\aj.json")X
        var f = FileInputStream("aj.json")
        ips.import(f)
        Assertions.assertTrue(ps.all().size>0)
    }
}