package me.pixka.kt.pibase.s

import com.pi4j.io.gpio.*
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service


@Service
@Profile("pi")
class GpioService(var gpio: GpioController?) {

    var ports = ArrayList<Portstatus>()

    constructor() : this(gpio = GpioFactory.getInstance()) {
        logger.info("New GPIO Service")

        /**
         * สำหรับเปิด DELAY ต้อง HIGH ก่อน
         */

        addToports(gpio!!.provisionDigitalOutputPin(RaspiPin.GPIO_00, "p0", PinState.HIGH), "p0")
        addToports(gpio!!.provisionDigitalOutputPin(RaspiPin.GPIO_01, "p1", PinState.HIGH), "p1")
        addToports(gpio!!.provisionDigitalOutputPin(RaspiPin.GPIO_02, "p2", PinState.HIGH), "p2")
        addToports(gpio!!.provisionDigitalOutputPin(RaspiPin.GPIO_03, "p3", PinState.HIGH), "p3")
        addToports(gpio!!.provisionDigitalOutputPin(RaspiPin.GPIO_04, "p4", PinState.HIGH), "p4")
        addToports(gpio!!.provisionDigitalOutputPin(RaspiPin.GPIO_05, "p5", PinState.HIGH), "p5")


        addToports(gpio!!.provisionDigitalOutputPin(RaspiPin.GPIO_21, "p21", PinState.LOW), "p21")
        addToports(gpio!!.provisionDigitalOutputPin(RaspiPin.GPIO_22, "p22", PinState.LOW), "p22")
        addToports(gpio!!.provisionDigitalOutputPin(RaspiPin.GPIO_23, "p23", PinState.LOW), "p23")
        addToports(gpio!!.provisionDigitalOutputPin(RaspiPin.GPIO_24, "p24", PinState.LOW), "p24")
        addToports(gpio!!.provisionDigitalOutputPin(RaspiPin.GPIO_25, "p25", PinState.LOW), "p25")
        addToports(gpio!!.provisionDigitalOutputPin(RaspiPin.GPIO_26, "p26", PinState.LOW), "p26")
        addToports(gpio!!.provisionDigitalOutputPin(RaspiPin.GPIO_27, "p27", PinState.LOW), "p27")
        addToports(gpio!!.provisionDigitalOutputPin(RaspiPin.GPIO_28, "p28", PinState.LOW), "p28")
        addToports(gpio!!.provisionDigitalOutputPin(RaspiPin.GPIO_29, "p29", PinState.LOW), "p29")
    }

    fun addToports(pin: GpioPinDigitalOutput, name: String) {
        var pi = pin as Pin
        var p = Portstatus(pi, false, name)
    }

    fun getDigitalPin(pinname: String): GpioPinDigitalOutput? {
        try {
            logger.debug("Get Digital pin ${pinname}")
            if (gpio != null) {


                var pin = gpio?.getProvisionedPin(pinname) as GpioPinDigitalOutput
                logger.debug("Get pin ${pin}")
                return pin
            }
        } catch (e: Exception) {
            logger.error("Get pin Error: ${e.message}")
        }
        return null
    }

    fun unlockport(name:String)
    {
        var port=findstatus(name)
        if(port!=null)
        {
            port.inuse=false //unlock port
        }
    }
    fun resettoDefault(pin: GpioPinDigitalOutput) {
        try {
            if (pin.name.equals("p0")) {
                setPort(pin, true)
            } else if (pin.name.equals("p1")) {
                setPort(pin, true)
            } else if (pin.name.equals("p2")) {
                setPort(pin, true)
            } else if (pin.name.equals("p3")) {
                setPort(pin, true)
            } else if (pin.name.equals("p4")) {
                setPort(pin, true)
            } else if (pin.name.equals("p5")) {
                setPort(pin, true)
            } else
                setPort(pin, false)
            unlockport(pin.name)


        } catch (e: Exception) {
            logger.error("Error ${e.message}")
            throw e
        }


    }

    /**
     * ใช้สำหรับ check ว่า port ว่างอยู่เปล่า
     */
    fun checkPort(pin: GpioPinDigitalOutput): Portstatus? {
        var pn = pin.name

        var port = findstatus(pn)

        if (port != null) {
            if (!port.inuse)
                return port //ถ้าไม่มีการใช้
        }
        return null//can not use

    }

    fun findstatus(name: String): Portstatus? {
        for (port in ports) {
            if (name.equals(port.portname))
                return port
        }
        return null

    }

    /**
     * ใช้สำหรับ lock port สำหรับ thread
     */
    fun setPort(pin: GpioPinDigitalOutput, state: Boolean): Boolean {
        var portstatus = checkPort(pin)
        if (portstatus == null) {
            throw Exception("Port in use")
        }

        portstatus.inuse = true


        logger.debug("Set ${pin} to ${state}")
        pin.setState(state)

        if (state) {
            if (!pin.isHigh) {
                portstatus.inuse = false
                throw Exception("Can not set status ${pin} to ${state}")

            }
            return true
        }

        if (!pin.isLow) {
            portstatus.inuse = false
            throw Exception("Can not set status ${pin} to ${state}")
        }
        return true
    }

    fun revertDigitalpin(pin: GpioPinDigitalOutput): Boolean {

        try {
            logger.debug("Current State pin ${pin} ${pin.state}")
            if (pin.isHigh) {

                setPort(pin, false)
                logger.debug("Set to Low ${pin.state}")


            } else {
                setPort(pin, true)

            }
        } catch (e: Exception) {
            logger.error("Revert pin (reset) ${pin} ${e.message}")
            throw e
        }

        return true
    }


    companion object {
        internal var logger = LoggerFactory.getLogger(GpioService::class.java)
    }
}

class Portstatus(var pin: Pin? = null, var inuse: Boolean = false, var portname: String = "")