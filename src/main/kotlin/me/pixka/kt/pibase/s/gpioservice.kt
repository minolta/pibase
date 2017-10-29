package me.pixka.kt.pibase.s

import com.pi4j.io.gpio.GpioController
import com.pi4j.io.gpio.GpioFactory
import com.pi4j.io.gpio.PinState
import com.pi4j.io.gpio.RaspiPin
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service


@Service
@Profile("pi")
class GpioService(var gpio: GpioController?) {

    constructor():this(gpio = GpioFactory.getInstance())
    {
        logger.info("New GPIO Service")

        /**
         * สำหรับเปิด DELAY ต้อง HIGH ก่อน
         */
        gpio!!.provisionDigitalOutputPin(RaspiPin.GPIO_00, "p0", PinState.HIGH)
        gpio!!.provisionDigitalOutputPin(RaspiPin.GPIO_01, "p1", PinState.HIGH)
        gpio!!.provisionDigitalOutputPin(RaspiPin.GPIO_02, "p2", PinState.HIGH)
        gpio!!.provisionDigitalOutputPin(RaspiPin.GPIO_03, "p3", PinState.HIGH)
        gpio!!.provisionDigitalOutputPin(RaspiPin.GPIO_04, "p4", PinState.HIGH)
        gpio!!.provisionDigitalOutputPin(RaspiPin.GPIO_05, "p5", PinState.HIGH)


        gpio!!.provisionDigitalOutputPin(RaspiPin.GPIO_21, "p21", PinState.LOW)
        gpio!!.provisionDigitalOutputPin(RaspiPin.GPIO_22, "p22", PinState.LOW)
        gpio!!.provisionDigitalOutputPin(RaspiPin.GPIO_23, "p23", PinState.LOW)
        gpio!!.provisionDigitalOutputPin(RaspiPin.GPIO_24, "p24", PinState.LOW)
        gpio!!.provisionDigitalOutputPin(RaspiPin.GPIO_25, "p25", PinState.LOW)
        gpio!!.provisionDigitalOutputPin(RaspiPin.GPIO_26, "p26", PinState.LOW)
        gpio!!.provisionDigitalOutputPin(RaspiPin.GPIO_27, "p27", PinState.LOW)
        gpio!!.provisionDigitalOutputPin(RaspiPin.GPIO_28, "p28", PinState.LOW)
        gpio!!.provisionDigitalOutputPin(RaspiPin.GPIO_29, "p29", PinState.LOW)
    }

    companion object {
        internal var logger = LoggerFactory.getLogger(GpioService::class.java)
    }
}
        