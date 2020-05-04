package com.donescuion;

import org.w3c.dom.ls.LSOutput;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    //initializeaza socketul si input output streams
    private Socket socket = null;
    private DataInputStream input = null;
    private DataOutputStream out = null;

    // constructor pt IP address and port
    public Client(String address,int port){
        // realizeaza conexiunea
        try{
            socket = new Socket(address,port);
            System.out.println("Client connected");

            //preiau text de la terminal
            input = new DataInputStream(System.in);

            //transmit catre socket
            out = new DataOutputStream(socket.getOutputStream());
        }
        catch (UnknownHostException e){
            System.out.println(e);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //introduc textul de transmis
        String line ="";
        // citesc text pana cand scriu Over
        while (!line.equals("Over")){
            try{
                System.out.print("Client:");
                line = input.readLine();
                out.writeUTF(line);
            }
            catch (IOException e){
                System.out.println(e);
            }
        }
        try{
            input.close();
            out.close();
            socket.close();
        }
        catch (IOException e){
            System.out.println(e);
        }
    }

}
