package com.github.redfox197.terraria.net.packets.client;

import com.github.redfox197.terraria.net.PacketOutputStream;
import com.github.redfox197.terraria.net.packets.Packet;

import java.io.IOException;

public class PacketRequestWorldData extends Packet {

    public PacketRequestWorldData() {
        packetId = 0x06;
        packetLength = 3;
        data = new byte[]{0x03, 0x00, packetId};
    }

    @Override
    public void write(PacketOutputStream packetOutputStream) throws IOException {
        packetOutputStream.write(data);
    }
}
