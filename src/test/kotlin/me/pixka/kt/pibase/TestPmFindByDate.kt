package me.pixka.kt.pibase

import me.pixka.kt.pibase.d.PmService
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import java.text.SimpleDateFormat
import kotlin.test.assertEquals

@DataJpaTest
class TestPmFindByDate {
    @Autowired
    lateinit var service: PmService
    val df = SimpleDateFormat("dd-MM-yyyy")

    @Test
    fun testFindByDate() {

        var s = df.parse("1-01-2020")
        var e = df.parse("21-01-2020")
        var result = service.findByDate(1L, s, e)
        assertEquals(0, result?.size)
    }
}