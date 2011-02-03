/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package packets;

import craftmine2.Client;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 *
 * @author joseph
 */
public class PositionLook implements Packet {

    String name = "Position and Look";
    byte index = 0x0D;
    boolean Write = false;
    Client client = null;

    public PositionLook(boolean write) {
        Write = write;
    }

    public PositionLook(boolean write, Client cli) {
        Write = write;
        client = cli;
    }

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
        if (client != null) {
            //client.data.setX(X);
            //client.data.setY(Y);
            //client.data.setZ(Z);
        }
        System.out.println("Player is at ("+X+","+Y+","+Z+") with orientation ("+pitch+","+yaw+")");
    }

    public void Write(DataOutputStream out) throws IOException {
        if (Write == true) {
            out.writeByte(index);
            if (client != null) {
                out.writeDouble(client.data.getX()); //X
                out.writeDouble(client.data.getY()); //Y
                out.writeDouble(client.data.getZ() + 1.62); //Stance: Y+1.62
                out.writeDouble(10); //Z
                out.writeFloat(client.data.getRot());
                out.writeFloat(client.data.getYaw());
            } else {
                out.writeDouble(0);
                out.writeDouble(100);
                out.writeDouble(101.62);
                out.writeDouble(0);
                out.writeFloat(0);
                out.writeFloat(0);
            }

            out.writeBoolean(true);
        }
    }

    public int GetMode() {
        return -1;
    }

    public int Increase() {
        return 0;
    }
}
