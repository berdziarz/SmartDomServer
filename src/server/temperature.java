/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;



/**
 *
 * @author Lukasz
 */
public class temperature
{
    private int min=15;
    private int max=30;
    private int kontrola =0;

    public int getKontrola()
    {
        return kontrola;
    }

    public void setKontrola(int kontrola)
    {
        this.kontrola = kontrola;
    }

    public int getMin()
    {
        return min;
    }

    public void setMin(int min)
    {
        this.min = min;
    }

    public int getMax()
    {
        return max;
    }

    public void setMax(int max)
    {
        this.max = max;
    }

    public String odczyt()
    {
        BufferedReader br = null;
        float tempf = 0.0f;
        try
        {
         br = new BufferedReader(new FileReader(new File("/sys/bus/w1/devices/28-0416841332ff/w1_slave")));
         br.readLine();
         String readTemp =br.readLine();
         String tabStr[] = readTemp.split("=");
 
         tempf = (Float.parseFloat(tabStr[1])/1000);
        System.out.printf("%.1f",tempf);
           
        }
        catch(FileNotFoundException ex)
        {
           
        }
        catch(IOException ex)
        {
           
        }
        
        return tempf + "";
    }

}
