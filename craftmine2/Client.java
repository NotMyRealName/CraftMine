/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package craftmine2;

import java.io.*;
import java.net.*;
import java.util.*;
import packets.*;

/**
 *
 * @author joseph
 */
public class Client implements Runnable {

    Socket socket;
    PacketParser parse;
    Server server;
    int ID;
    public ClientData data;
    boolean Running = true;
    public int Mode = 0;
    Map<Integer, Packet> packets = new HashMap<Integer, Packet>();
    private final DataInputStream in;
    private final DataOutputStream out;

    public Client(Socket sock, Server serv, int ind) throws IOException {
        ID = ind;
        socket = sock;
        server = serv;
        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());
        data = new ClientData();
        AddPackets();
    }

    public void AddPackets() {
        packets.put(0x01, new packets.Login(ID, this));
        packets.put(0x02, new packets.Handshake());
        packets.put(0x0D, new PositionLook(false));
        packets.put(0xB, new packets.Position(this));
        packets.put(0xC, new packets.Look(this));
        //packets.put(-1, new Disconnect(this));
    }

    public void SpawnClient() throws IOException {
        for (int x = -5; x < 5; x++) {
            for (int z = -5; z < 5; z++) {
                new PreChunk(x, z).Write(out);
                new Chunk(x, 50, z, 1).Write(out);
            }
        }
        new Spawnpos().Write(out);
        new PositionLook(true).Write(out);
        out.flush();
        Mode=3;
    }

    public void run() {
        try {
            while (Running) {
                PacketParser.Handle(in, out, this);
            }
        } catch (EOFException e) {
            System.out.println("Client disconnected.");
        } catch (IOException e) {
            System.out.println("Network error.");
        }
        Disconnect();
    }

    public void Disconnect() {
        System.out.println("A client disconnected from server.");
        Running = false;
    }
}
