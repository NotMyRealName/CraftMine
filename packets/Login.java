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
import craftmine2.*;

/**
 *
 * @author joseph
 */
public class Login implements Packet {

    int Version;
    String User;
    String Pass;
    long Seed;
    byte Dimension;
    int ID;
    PacketParser parse;
    String name = "Login";
    byte index = 0x01;

    public Login(int id, PacketParser prs) {
        ID = id;
        parse = prs;
    }

    public void Read(DataInputStream in) {
        try {
            Version = in.readInt();
            User = in.readUTF();
            Pass = in.readUTF();
            Seed = in.readLong();
            Dimension = in.readByte();
            parse.cli.data.setUsername(User);
            parse.cli.data.setPassword(Pass);
        } catch (IOException ex) {
        }
    }

    public void Write(DataOutputStream out) {
        try {
            out.writeByte(index);
            out.writeInt(ID);
            out.writeUTF("");
            out.writeUTF("");
            out.writeLong(0);
            out.writeByte(0);

            //If you uncomment out the loop here, it will make 49 chunks around the player, but cause the client to crash.
            int X=0;
            int Z=0;
            //for (int X = -7; X <= 7; X++) {
            //    for (int Z = -7; Z <= 7; Z++) {
                    PreChunk pre = new PreChunk(X, Z);
                    pre.Write(out);

                    Chunk ch = new Chunk(X, 50, Z, 7);
                    ch.Write(out);

                    out.flush();
            //    }
            //}

            Spawnpos pos = new Spawnpos();
            pos.Write(out);

            PositionLook posl = new PositionLook();
            posl.Write(out);
        } catch (IOException ex) {
        }

    }

    public String GetName() {
        return name;
    }

    public int GetMode() {
        return 1;
    }

    public int Increase() {
        return 1;
    }
}
