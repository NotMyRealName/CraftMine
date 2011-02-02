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
    Client parse;
    String name = "Login";
    byte index = 0x01;

    public Login(int id, Client prs) {
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
            parse.data.setUsername(User);
            parse.data.setPassword(Pass);
        } catch (IOException ex) {
        }
    }

    public void Write(DataOutputStream out) {
        try {
            out.writeByte(index);
            out.writeInt(ID+1);
            out.writeUTF("test");
            out.writeUTF("test");
            out.writeLong(9717680);
            out.writeByte(0);
            out.flush();
            parse.SpawnClient();
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
