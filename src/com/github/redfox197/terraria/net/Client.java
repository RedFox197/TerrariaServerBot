package com.github.redfox197.terraria.net;

import com.github.redfox197.terraria.net.packets.Packet;

import java.io.IOException;
import java.net.Socket;

public class Client {
    public String ip;
    public int port;
    public Socket socket;
    public PacketOutputStream out;
    public PacketInputStream in;

    public Client() {
        this("localhost");
    }

    public Client(String ip) {
        this(ip, 7777);
    }

    public Client(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public void start() throws IOException {
        socket = new Socket(ip, port);
        out = new PacketOutputStream(socket.getOutputStream());
        in = new PacketInputStream(socket.getInputStream());
        /*new Thread(() -> {
            try {
                while (true) {
                    int packetLength = in.readUnsignedShortL();
                    int packetId = in.readUnsignedByte();

                    int dataLength = packetLength - 3;
                    byte[] data = new byte[dataLength];
                    in.readFully(data);

                    int[] converted = new int[dataLength];
                    for (int i = 0; i < data.length; i++) {
                        converted[i] = Byte.toUnsignedInt(data[i]);
                    }

                    StringBuilder builder = new StringBuilder("Ricevuto Pacchetto ")
                            .append(packetId).append("\n")
                            .append("PacketLength: ").append(packetLength).append("\n")
                            .append("Contenuto: ").append(Arrays.toString(converted));
                    System.out.println(builder);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();*/
    }

    public void sendPacket(Packet packet) throws IOException {
        packet.write(out);
    }
}
