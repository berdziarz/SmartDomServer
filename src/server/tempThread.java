/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 *
 * @author Lukasz
 */
public class tempThread extends Thread
{

    Socket socket;
    temperature temp;
    float pom1 = 0, pom2 = 0;
    String tempvalue;
    GpioController gpio = GpioFactory.getInstance();
    GpioPinDigitalOutput grzejnik = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_25);

    public tempThread(temperature temp, Socket socket)
    {
        this.temp = temp;
        this.socket = socket;
    }

    @Override
    public void run()
    {
        try
        {
            

            while (true)
            {
                
                tempvalue = temp.odczyt();
               
                pom2 = Float.parseFloat(tempvalue);
                if (temp.getKontrola() == 1)
                {
                    if (pom2 < temp.getMin())
                    {
                        grzejnik.setState(true);
                    } else if (pom2 > temp.getMax())
                    {
                        grzejnik.setState(false);
                    }
                }else
                {
                    grzejnik.setState(false);
                }
                if (socket!=null &&( (pom2 - pom1) > 1 || (pom2 - pom1) < -1))
                {
                    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                    bw.write("TEMP" + tempvalue);
                    bw.newLine();
                    bw.flush();
                    System.out.println("Wyslalem: "+tempvalue);
                    pom1 = pom2;
                }
                
                Thread.sleep(5000);
            }
        } catch (IOException ioex)
        {

        } catch (InterruptedException iex)
        {

        }
    }
}
