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
public class PositionLook implements Packet{
    String name = "Position and Look";
    byte index = 0x0D;
    public String GetName() {
        return name;
    }

    public void Read(DataInputStream in) throws IOException {
        double X = in.readDouble();
        double Stance = in.readDouble();
        double Y = in.readDouble();
        double Z = in.readDouble();
        float pitch = in.readFloat();
        float yaw = in.readFloat();
        in.readBoolean();
        System.out.println("Player is at ("+X+","+Y+","+Z+") with orientation ("+pitch+","+yaw+")");
    }

    public void Write(DataOutputStream out) throws IOException {
        out.writeByte(index);
        out.writeDouble(10); //X
        out.writeDouble(55); //Y
        out.writeDouble(55+1.62); //Stance: Y+1.62
        out.writeDouble(10); //Z
        out.writeFloat(1);
        out.writeFloat(1);
        out.writeBoolean(true);
    }

    public int GetMode() {
        return 3;
    }

    public int Increase() {
        return 0;
    }

}
