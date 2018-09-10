/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.RaspiPin;

/**
 *
 * @author Lukasz
 */
public class leds
{

    GpioController gpio0 = GpioFactory.getInstance();
    GpioPinDigitalOutput pin0 = gpio0.provisionDigitalOutputPin(RaspiPin.GPIO_00);
    GpioPinDigitalOutput pin2 = gpio0.provisionDigitalOutputPin(RaspiPin.GPIO_02);
    GpioPinDigitalOutput pin3 = gpio0.provisionDigitalOutputPin(RaspiPin.GPIO_03);
    GpioPinDigitalOutput pin4 = gpio0.provisionDigitalOutputPin(RaspiPin.GPIO_04);
    GpioPinDigitalOutput pin5 = gpio0.provisionDigitalOutputPin(RaspiPin.GPIO_05);

    public leds()
    {
    }

    public void change(int number, int state)
    {
        switch (number)
        {
            case 0:
            {
                if (state == 0)
                {
                    pin0.setState(false);
                } else
                {
                    pin0.setState(true);
                }
                break;
            }
            case 1:
            {
                if (state == 0)
                {
                    pin2.setState(false);
                } else
                {
                    pin2.setState(true);
                }
                break;
            }
            case 2:
            {
                if (state == 0)
                {
                    pin4.setState(false);
                } else
                {
                    pin4.setState(true);
                }
                break;
            }
            case 3:
            {
                if (state == 0)
                {
                    pin5.setState(false);
                } else
                {
                    pin5.setState(true);
                }
                break;
            }
            case 4:
            {
                if (state == 0)
                {
                    pin3.setState(false);
                } else
                {
                    pin3.setState(true);
                }
                break;
            }
        }
    }
}
