package me.pixka.kt.pibase.d

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class TestpijobEQ {


    @Test
    fun testPijobE()
    {

        var p1 = Pijob()
        p1.id = 1


        var p2 = Pijob()
        p2.id = 1


        Assertions.assertEquals(true,p1.equals(p2))
    }

}