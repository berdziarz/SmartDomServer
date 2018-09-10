/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.RaspiPin;

/**
 *
 * @author Lukasz
 */
public class DetectionThread extends Thread
{
   GpioController gpio = GpioFactory.getInstance();
  GpioPinDigitalInput sensor = gpio.provisionDigitalInputPin(RaspiPin.GPIO_06, PinPullResistance.PULL_DOWN);
   Camera camera;
    public DetectionThread(Camera camera)
    {
        this.camera=camera;
    }
    
    @Override
    public void run()
    {
      while(true)
        {
            if(sensor.getState().isHigh())
            {
                try
                {
                    camera.photo();
                    Thread.sleep(5000);
                }catch(InterruptedException ex)
                {
                   
                }
            }
        }
    }
    
}
