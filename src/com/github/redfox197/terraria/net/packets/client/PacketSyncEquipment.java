package com.github.redfox197.terraria.net.packets.client;

import com.github.redfox197.terraria.net.PacketOutputStream;
import com.github.redfox197.terraria.net.packets.Packet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class PacketSyncEquipment extends Packet {
    public byte playerId = 0x00;
    public short slotId; //forse arriva fino a 259 -> probabilmente dovrò convertire perché è troppo piccolo lo short java
    public short stack = 0;
    public byte prefix = 0x00;
    public short itemNetId = 0;

    public PacketSyncEquipment(short slotId) {
        packetId = 0x05;
        this.slotId = slotId;
    }

    @Override
    public void write(PacketOutputStream packetOutputStream) throws IOException {
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        PacketOutputStream out = new PacketOutputStream(bout);

        out.writeByte(packetId);
        out.writeByte(playerId);
        out.writeShortL(slotId);
        out.writeShortL(stack);
        out.writeByte(prefix);
        out.writeShortL(itemNetId);

        data = bout.toByteArray();
        bout.reset();
        out.writeShortL(data.length + 2);
        out.write(data);

        packetOutputStream.write(bout.toByteArray());
    }
}
