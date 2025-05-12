package com.github.redfox197.terraria.net.packets.client;

import com.github.redfox197.terraria.net.PacketOutputStream;
import com.github.redfox197.terraria.net.packets.Packet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class PacketPlayerSpawn extends Packet {
    public byte playerId = 0x00;
    public short spawnX = (short) 65535;
    public short spawnY = (short) 65535;
    public int respawnTime = 0;
    public byte spawnContext = 0x01;

    public PacketPlayerSpawn() {
        packetId = 0x0c;// id 12
    }

    @Override
    public void write(PacketOutputStream packetOutputStream) throws IOException {
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        PacketOutputStream out = new PacketOutputStream(bout);

        out.writeByte(packetId);
        out.writeByte(playerId);
        out.writeShortL(spawnX);
        out.writeShortL(spawnY);
        out.writeIntL(respawnTime);
        out.writeByte(spawnContext);

        data = bout.toByteArray();
        bout.reset();
        out.writeShortL(data.length + 2);
        out.write(data);

        packetOutputStream.write(bout.toByteArray());
    }
}
