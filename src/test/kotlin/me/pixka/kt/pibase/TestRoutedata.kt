package me.pixka.kt.pibase

import me.pixka.kt.pibase.r.RoutedataRepo
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class TestRoutedata{

    @Autowired
    lateinit var r: RoutedataRepo

    @Test
    fun testRouteRepo()
    {
        println(r)
    }
}