/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package packets;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;

/**
 *
 * @author joseph
 */
public class Chunk implements Packet {
    String name = "Chunk";
    byte index = 0x33;
    int WorldX, WorldZ, Height, Material;
    int SizeX,SizeY,SizeZ;

    public Chunk(int X,int height,int Z, int Type){
        SizeX = 15;
        SizeY = 127;
        SizeZ = 15;
        WorldX=X;
        WorldZ=Z;
        Height=height;
        Material = Type;
    }

    public String GetName() {
        return name;
    }

    public int GetIndex(int x, int y, int z) {
        return y + (z * (SizeY+1)) + (x * (SizeY+1) * (SizeZ+1));
    }

    public byte[] GetByteArray() throws IOException{
        byte[] Blocks = new byte[((SizeX+1) * (SizeY+1) * (SizeZ+1))];
        byte[] metadata = new byte[(((SizeX+1) * (SizeY+1) * (SizeZ+1))) / 2];
        byte[] light = new byte[(((SizeX+1) * (SizeY+1) * (SizeZ+1))) / 2];
        byte[] skylight = new byte[(((SizeX+1) * (SizeY+1) * (SizeZ+1))) / 2];

        for (int X = 0; X < SizeX; X++) {
            for (int Z = 0; Z < SizeZ; Z++) {
                for (int Y = 0; Y < SizeY; Y++) {
                    Blocks[GetIndex(X, Y, Z)] = 0;
                }
            }
        }

        for (int X = 0; X < SizeX; X++) {
            for (int Z = 0; Z < SizeZ; Z++) {
                for (int Y = 0; Y < Height; Y++) {
                    Blocks[GetIndex(X, Y, Z)] = (byte) Material;
                }
            }
        }


        for (int i = 0; i < (SizeX * SizeY * SizeX) / 2; i++) {
            byte nibblea = (byte) 0x00;
            byte nibbleb = (byte) 0x00;
            byte Both = (byte) ((nibblea << 4) | (nibbleb & 0x0F));
            metadata[i] = Both;
            light[i] = Both;

            nibblea = (byte) 0xf;
            nibbleb = (byte) 0xf;
            Both = (byte) ((nibblea << 4) | (nibbleb & 0x0F));
            skylight[i] = Both;
        }
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        DeflaterOutputStream deflate = new DeflaterOutputStream(stream);
        deflate.write(Blocks);
        deflate.write(metadata);
        deflate.write(light);
        deflate.write(skylight);
        
        
        //System.out.println("Uncompressed size: "+stream.toByteArray().length+", Compressed size: "+data.length);
        deflate.finish();

        return stream.toByteArray();
    }

    public void Read(DataInputStream in) throws IOException {
    }

    public void Write(DataOutputStream out) throws IOException {
        System.out.println("Sending map chunk at ("+WorldX+","+0+","+WorldZ+")");
        out.writeByte(index);
        out.writeInt(WorldX);
        out.writeShort(0);
        out.writeInt(WorldZ);
        out.writeByte(SizeX);
        out.writeByte(SizeY);
        out.writeByte(SizeZ);
        byte[] chunk = GetByteArray();
        out.writeInt(chunk.length);
        out.write(chunk);
    }

    public int GetMode() {
        return 2;
    }

    public int Increase() {
        return 0;
    }

}
