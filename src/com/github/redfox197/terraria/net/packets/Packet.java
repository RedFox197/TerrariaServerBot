package com.github.redfox197.terraria.net.packets;

import com.github.redfox197.terraria.net.PacketOutputStream;

import java.io.IOException;

public abstract class Packet {
    // Ho notato un Write7BitEncodedInt nel write della stringa. quindi invia la stringa con la lunghezza in varint ma forse non il resto tipo il packet length
    // int16, probabilmente uint16, ma nel mio caso specifico dovrebbe andar bene short
    public short packetLength;
    //tutti i byte in c# e come li invia dovrebbero essere unsigned quindi probabilmente dovrei farli diversamente
    public byte packetId;
    public byte[] data;

    public abstract void write(PacketOutputStream packetOutputStream) throws IOException;
}