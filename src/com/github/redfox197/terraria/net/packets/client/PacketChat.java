package com.github.redfox197.terraria.net.packets.client;

import com.github.redfox197.terraria.net.PacketOutputStream;
import com.github.redfox197.terraria.net.packets.Packet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class PacketChat extends Packet {
    public String message;

    public PacketChat(String message) {
        packetId = 0x52; //82 netmodule load 1 short netmodule id per la chat
        this.message = message;
    }

    @Override
    public void write(PacketOutputStream packetOutputStream) throws IOException {
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        PacketOutputStream out = new PacketOutputStream(bout);

        out.writeByte(packetId);
        out.writeShortL(1); //module id per la chat
        out.writeString("Say");
        out.writeString(message);

        data = bout.toByteArray();
        bout.reset();
        out.writeShortL(data.length + 2);
        out.write(data);

        packetOutputStream.write(bout.toByteArray());
    }
}
