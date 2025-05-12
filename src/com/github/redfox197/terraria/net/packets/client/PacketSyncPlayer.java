package com.github.redfox197.terraria.net.packets.client;

import com.github.redfox197.terraria.net.PacketOutputStream;
import com.github.redfox197.terraria.net.packets.Packet;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class PacketSyncPlayer extends Packet {
    public byte playerId = 0x00; //in teoria si prende dal packet 3 playerinfo
    public byte skinVarient = 0x00;
    public byte hair = 0x00;
    public String name = "TwitchTest";
    public byte hairDye = 0x00;
    public byte hideVAccessory = 0x00;
    public byte hideVAccessory2 = 0x00;
    public byte hideMisc = 0x00;
    public Color hairColor = new Color(215, 90, 55);
    public Color skinColor = new Color(255, 125, 90);
    public Color eyeColor = new Color(105, 90, 75);
    public Color shirtColor = new Color(175, 165, 140);
    public Color underShirtColor = new Color(160, 180, 215);
    public Color pantsColor = new Color(255, 230, 175);
    public Color shoeColor = new Color(160, 105, 60);
    public byte difficulty = 0x00; // 0 = softcore
    public byte torch = 0x00;

    public PacketSyncPlayer(String name) {
        packetId = 0x04;
        this.name = name;
    }

    @Override
    public void write(PacketOutputStream packetOutputStream) throws IOException {
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        PacketOutputStream out = new PacketOutputStream(bout);

        out.writeByte(packetId);
        out.writeByte(playerId);
        out.writeByte(skinVarient);
        out.writeByte(hair);
        out.writeString(name);
        out.writeByte(hairDye);
        out.writeByte(hideVAccessory);
        out.writeByte(hideVAccessory2);
        out.writeByte(hideMisc);
        out.writeRGB(hairColor);
        out.writeRGB(skinColor);
        out.writeRGB(eyeColor);
        out.writeRGB(shirtColor);
        out.writeRGB(underShirtColor);
        out.writeRGB(pantsColor);
        out.writeRGB(shoeColor);
        out.writeByte(difficulty);
        out.writeByte(torch);

        data = bout.toByteArray();
        bout.reset();
        out.writeShortL(data.length + 2);
        out.write(data);

        packetOutputStream.write(bout.toByteArray());

        /*StringBuilder builder = new StringBuilder("[ ");
        for (byte b : bout.toByteArray()) {
            builder.append(String.format("%02X", b)).append(", ");
        }
        System.out.println(builder);*/
    }
}
