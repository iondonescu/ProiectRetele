package com.donescuion;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private Socket socket = null;
    private ServerSocket server = null;
    private DataInputStream in = null;

    //constructor cu port
    public Server(int port){
        //porneste serverul si asteapta o conexiune
        try{
            server = new ServerSocket(port);
            System.out.println("Server started");
            System.out.println("Waiting for a client");

            socket = server.accept();
            System.out.println("Client accepted");
            //preia informatia trimisa de la client

            in = new DataInputStream(
                    new BufferedInputStream(socket.getInputStream())
            );
            String line ="";

            // preia pana cand "Over" este afisat
            while(!line.equals("Over")){
                try{
                    line = in.readUTF();
                    System.out.println(line);
                }
                catch (IOException e){
                    System.out.println(e);
                }
            }
            System.out.println("Closing connection");
            //inchide conexiunea
            socket.close();
            in.close();
        }
        catch (IOException e){
            System.out.println(e);
        }
    }
}
