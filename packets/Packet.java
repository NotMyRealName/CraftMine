/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package packets;

import java.io.*;

/**
 *
 * @author joseph
 */
public interface Packet {
    public int GetMode();
    public int Increase();
    public String GetName();
    public void Read(DataInputStream in) throws IOException;
    public void Write(DataOutputStream out) throws IOException;
}
