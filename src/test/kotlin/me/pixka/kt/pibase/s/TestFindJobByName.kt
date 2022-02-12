package me.pixka.kt.pibase.s

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
class TestFindJobByName {

    @Autowired
    lateinit var findJob: FindJob
    @Autowired
    lateinit var pjs:PijobService

    @Test
    fun testFindByJobname()
    {
        var c= "testfindByName"
        pjs.findOrCreate(c)

        var r = findJob.findJobByName(c)
        Assertions.assertNotNull(r)

    }

}