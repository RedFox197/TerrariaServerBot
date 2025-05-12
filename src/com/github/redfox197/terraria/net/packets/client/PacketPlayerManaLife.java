package com.github.redfox197.terraria.net.packets.client;

import com.github.redfox197.terraria.net.PacketOutputStream;
import com.github.redfox197.terraria.net.packets.Packet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class PacketPlayerManaLife extends Packet {
    public byte playerId = 0x00;
    public short mana = 20;
    public short manaMax = 20;

    public PacketPlayerManaLife() {
        packetId = 0x2a; //id 42
    }

    @Override
    public void write(PacketOutputStream packetOutputStream) throws IOException {
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        PacketOutputStream out = new PacketOutputStream(bout);

        out.writeByte(packetId);
        out.writeByte(playerId);
        out.writeShortL(mana);
        out.writeShortL(manaMax);

        data = bout.toByteArray();
        bout.reset();
        out.writeShortL(data.length + 2);
        out.write(data);

        packetOutputStream.write(bout.toByteArray());
    }
}
