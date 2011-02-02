/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package craftmine2;

import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author joseph
 */
public class Server {

    int Port = 25565;
    int CID = 0;
    ServerSocket socket;
    int Index;

    public Server() {
        System.out.println("Starting server");
        try {
            socket = new ServerSocket(Port);
            Listen();
        } catch (IOException ex) {
            System.out.println("Port already in use!");
        }
    }
    
    public void Listen() throws IOException{
        System.out.println("Server started.");
        Socket connection = null;
        while(true){
            connection = socket.accept();
            System.out.println("A user has connected.");
            Client cli = new Client(connection,this,Index++);
            new Thread(cli).run();
            connection = null;
        }
    }
    public static void main(String[] args) {
        new Server();
    }
}
