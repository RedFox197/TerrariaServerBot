package com.github.redfox197.terraria.net.packets.client;

import com.github.redfox197.terraria.TerrariaBot;
import com.github.redfox197.terraria.net.PacketOutputStream;
import com.github.redfox197.terraria.net.packets.Packet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class PacketHello extends Packet {
    private String message;

    public PacketHello() {
        packetId = 0x01;
        message = "Terraria" + TerrariaBot.PROTOCOL;
    }

    @Override
    public void write(PacketOutputStream packetOutputStream) throws IOException {
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        PacketOutputStream out = new PacketOutputStream(bout);

        out.writeByte(packetId);
        out.writeString(message);

        data = bout.toByteArray();
        bout.reset();
        out.writeShortL(data.length + 2);
        out.write(data);

        //packetOutputStream.writeShortL(data.length + 2);
        packetOutputStream.write(bout.toByteArray());
    }
}
