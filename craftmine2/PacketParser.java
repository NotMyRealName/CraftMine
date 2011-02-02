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
    Socket socket;
    DataInputStream in;
    DataOutputStream out;
    Map<Integer, Packet> packets = new HashMap<Integer, Packet>();
    public Client cli;
    public PacketParser(Socket sock,Client clie){
        cli=clie;
        socket = sock;
        try {
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(PacketParser.class.getName()).log(Level.SEVERE, null, ex);
        }
        AddPackets();
    }

    public void AddPackets(){
        packets.put(0x01, new packets.Login(cli.ID,this));
        packets.put(0x02, new packets.Handshake());
        packets.put(0x0D, new PositionLook());
        packets.put(-1, new Disconnect(cli));
    }

    public void Handle() throws IOException{
        int ID = in.readByte();
        if(ID!=0){
            if(packets.get(ID)!=null){
                Packet pack = (Packet)packets.get(ID);
                if(pack.GetMode()==cli.Mode){
                    pack.Read(in);
                    pack.Write(out);
                    out.flush();
                    System.out.println("Handled packet "+pack.GetName());
                    cli.Mode+=pack.Increase();
                }
            } else{
                System.out.println("Unknown packet ID: "+ID);
            }
        }

    }
}
