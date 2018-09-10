/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Lukasz
 */
public class Camera 
{
    Runtime rt = Runtime.getRuntime();
    SimpleDateFormat dd = new SimpleDateFormat("yyyy-MM-dd_HHmmss");
    String date = "";
    String locPhoto ="/home/pi/Desktop/kamera/photo/";
    String locVideo="/home/pi/Desktop/kamera/video/";
   
    
   
  
    public void photo() 
    {
        try
        {
        date= dd.format(new Date());
        String cmdPhoto = "raspistill -o "+locPhoto + date + ".jpg";
        Process prc = rt.exec(cmdPhoto);
        prc.waitFor();
        System.out.println("Zdjęcie zrobione");
        }catch(IOException ex)
        {
           
        }catch(InterruptedException ex)
        {
            
        }
    }
    public void video()
    {
        try
        {
        date= dd.format(new Date());
        String cmdVideo = "raspivid -t10000 -o " + date + ".h264";
        Process prc = rt.exec(cmdVideo);
        prc.waitFor();
        System.out.println("Zdjęcie zrobione");
        }catch(IOException ex)
        {
            
        }catch(InterruptedException ex)
        {
            
        }
    }
}
