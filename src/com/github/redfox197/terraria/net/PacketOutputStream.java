package com.github.redfox197.terraria.net;

import java.awt.*;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class PacketOutputStream extends DataOutputStream {

    public PacketOutputStream(OutputStream out) {
        super(out);
    }

    public final void writeVarInt(int v) throws IOException {
        while (true) {
            if ((v & 0xFFFFFF80) == 0) {
                writeByte(v);
                return;
            }
            writeByte(v & 0x7F | 0x80);
            v >>>= 7;
        }
    }

    public final void writeString(String v) throws IOException {
        //writeVarInt(v.length());
        //writeBytes(v);
        //cambiato per utf8
        byte[] string = v.getBytes(StandardCharsets.UTF_8);
        writeVarInt(string.length);
        write(string);
    }

    public final void writeRGB(Color v) throws IOException {
        writeByte(v.getRed());
        writeByte(v.getGreen());
        writeByte(v.getBlue());
    }

    public final void writeShortL(int v) throws IOException {
        out.write((v >>> 0) & 0xFF);
        out.write((v >>> 8) & 0xFF);
        incCount(2);
    }

    public final void writeIntL(int v) throws IOException {
        out.write((v >>> 0) & 0xFF);
        out.write((v >>> 8) & 0xFF);
        out.write((v >>> 16) & 0xFF);
        out.write((v >>> 24) & 0xFF);
        incCount(4);
    }

    private void incCount(int value) {
        int temp = written + value;
        if (temp < 0) {
            temp = Integer.MAX_VALUE;
        }
        written = temp;
    }
}
