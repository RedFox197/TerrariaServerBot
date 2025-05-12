package com.github.redfox197.terraria.net;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

public class PacketInputStream extends DataInputStream {

    public PacketInputStream(InputStream in) {
        super(in);
    }

    public final int readUnsignedShortL() throws IOException {
        int ch1 = in.read();
        int ch2 = in.read();
        if ((ch1 | ch2) < 0)
            throw new EOFException();
        return (ch2 << 8) + (ch1 << 0);
    }
}
