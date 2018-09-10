package server;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server
{

    private leds led = new leds();
    private temperature temp = new temperature();
    private Camera cam = new Camera();
    private ServerSocket server;
    private Socket connection;
    BufferedWriter br;

    public static void main(String[] args) throws IOException
    {

        new Server().serverRun();

    }

    public void serverRun() throws IOException
    {
        server = new ServerSocket(1108);
        new DetectionThread(cam).start();
        new tempThread(temp, connection).start();
        System.out.println("Server gotowy");
        while (true)
        {
            connection = server.accept();
            System.out.println("Klient podłączony");
            br = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
            br.write("TEMP:"+temp.odczyt());
            br.newLine();
            br.flush();

            new ServerThread(connection).start();

        }

    }

    public class ServerThread extends Thread
    {

        Socket socket;

        public ServerThread(Socket socket)
        {
            this.socket = socket;
        }

        public void run()
        {
            try
            {
                String msg = null;
                String[] tab = null;
                String cmd = null;
                Scanner sc = new Scanner(socket.getInputStream());
                while ((msg = sc.nextLine()) != null)
                {
                    System.out.println(msg);
                    tab = msg.split(":");
                    cmd=tab[1];
                    switch (tab[0])
                    {
                        case "LED":
                        {
                            String[] tab2=cmd.split(",");
                            if(tab2[0].equals("9"))
                            {
                                for(int i=0;i<5;i++)
                                {
                                    led.change(i,Integer.parseInt(tab2[1]) );
                                }
                            }else{
                            led.change(Integer.parseInt(tab2[0]), Integer.parseInt(tab2[1]));
                        }}
                            break;
                        case "TEMP":
                        {
                            String[] tab3=cmd.split(",");
                            temp.setMin(Integer.parseInt(tab3[0]));
                            temp.setMax(Integer.parseInt(tab3[1]));
                            temp.setKontrola(Integer.parseInt(tab3[2]));
                        }
                            break;
                    }
                }
                socket.close();
            } catch (Exception ioex)
            {

            }
        }

    }
}
