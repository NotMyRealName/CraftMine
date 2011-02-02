/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package packets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author joseph
 */
public class Handshake implements Packet{
    String user;
    String name = "Handshake";
    byte index = 0x02;
    
    public void Read(DataInputStream in) {
        try {
            user = in.readUTF();
        } catch (IOException ex) {}
    }

    public void Write(DataOutputStream out) {
        try {
            out.writeByte(index);
            out.writeUTF("-");
        } catch (IOException ex) {}
    }

    public String GetName() {
        return name;
    }

}
