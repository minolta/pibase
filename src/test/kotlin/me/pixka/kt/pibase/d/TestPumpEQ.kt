package me.pixka.kt.pibase.d

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class TestPumpEQ {

    @Test
    fun testEQ()
    {

        var p1 = Pumpforpijob()
        var p2 = Pumpforpijob()

        p1.refid=1
        p2.refid = 2

        Assertions.assertTrue(!p1.equals(p2))
    }
}