/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package craftmine2;

import java.io.EOFException;
import java.io.IOException;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author joseph
 */
public class Client implements Runnable {

    Socket socket;
    PacketParser parse;
    Server server;
    int ID;
    public ClientData data;
    boolean Running = true;
    public int Mode = 0;
    
    public Client(Socket sock, Server serv, int ind) {
        ID = ind;
        socket = sock;
        parse = new PacketParser(sock, this);
        server = serv;
        data = new ClientData();
    }

    public void run() {
        while (Running) {
            try {
                parse.Handle();
            } catch (EOFException e) {
                System.out.println("Client disconnected.");
                break;
            } catch (IOException e) {
                System.out.println("Network error.");
                break;
            }
        }
        Disconnect();
    }

    public void Disconnect(){
        System.out.println("A client disconnected from server.");
        Running=false;
    }
}
