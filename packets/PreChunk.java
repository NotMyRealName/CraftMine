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
public class PreChunk implements Packet{
    byte index = 0x32;
    String name = "PreChunk";
    int X,Z;
    public PreChunk(int x, int z){
        X=x;
        Z=z;
    }

    public String GetName() {
        return name;
    }

    public void Read(DataInputStream in) {
    }

    public void Write(DataOutputStream out) throws IOException {
        out.writeByte(index);
        out.writeInt(X);
        out.writeInt(Z);
        System.out.println("Sending a prechunk at ("+X+",0,"+Z+")");
        out.writeByte(1);
    }

    public int GetMode() {
        return 2;
    }

    public int Increase() {
        return 0;
    }

}
