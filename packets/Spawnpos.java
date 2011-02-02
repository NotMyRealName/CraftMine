/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package packets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 *
 * @author joseph
 */
public class Spawnpos implements Packet{
    String name = "Spawn position";
    byte index = 0x06;
    public String GetName() {
        return name;
    }

    public void Read(DataInputStream in) throws IOException {
    }

    public void Write(DataOutputStream out) throws IOException {
        out.writeByte(index);
        out.writeInt(0);
        out.writeInt(0);
        out.writeInt(0);
    }

    public int GetMode() {
        return 2;
    }

    public int Increase() {
        return 1;
    }

}
