/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package craftmine2;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import packets.*;

/**
 *
 * @author joseph
 */
public class PacketParser {

    public static void Handle(DataInputStream in, DataOutputStream out, Client cli) throws IOException {
        int ID = in.readByte();
        if (cli.packets.get(ID) != null) {
            Packet pack = (Packet) cli.packets.get(ID);
            //System.out.println("Trying to handle "+pack.GetName()+" packet with required mode: "+pack.GetMode()+", actual mode: "+cli.Mode);
            if (pack.GetMode() == cli.Mode || pack.GetMode() == -1) {
                pack.Read(in);
                pack.Write(out);
                out.flush();
                //System.out.println("Handled packet " + pack.GetName());
                cli.Mode += pack.Increase();
            }
        } else if(ID!=10){
            System.out.println("Unknown packet ID: " + ID);
        }

    }
}
