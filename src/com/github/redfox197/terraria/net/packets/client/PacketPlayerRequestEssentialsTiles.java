package com.github.redfox197.terraria.net.packets.client;

import com.github.redfox197.terraria.net.PacketOutputStream;
import com.github.redfox197.terraria.net.packets.Packet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class PacketPlayerRequestEssentialsTiles extends Packet {
    public int playerSpawnX = (int) 4294967295L;
    public int playerSpawnY = (int) 4294967295L;

    public PacketPlayerRequestEssentialsTiles() {
        packetId = 0x08;
    }

    @Override
    public void write(PacketOutputStream packetOutputStream) throws IOException {
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        PacketOutputStream out = new PacketOutputStream(bout);

        out.writeByte(packetId);
        out.writeIntL(playerSpawnX);
        out.writeIntL(playerSpawnY);

        data = bout.toByteArray();
        bout.reset();
        out.writeShortL(data.length + 2);
        out.write(data);

        packetOutputStream.write(bout.toByteArray());
    }
}
