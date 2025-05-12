package com.github.redfox197.terraria;

import com.github.redfox197.terraria.net.Client;
import com.github.redfox197.terraria.net.packets.client.*;
import com.github.redfox197.terraria.twitch.TwitchClient;

import java.io.IOException;

public class TerrariaBot {
    public static int PROTOCOL = 248;
    public static Client client;

    public static void main(String[] args) {
        try {
            TwitchClient twitchClient = new TwitchClient("NO", "NO");
            twitchClient.connect();
            twitchClient.login();
            twitchClient.join("NO");
            twitchClient.startReader();

            client = new Client();
            System.out.println("Avvio client: localhost:7777");
            client.start();
            System.out.println("Avviato!");
            System.out.println("Invio del pacchetto: Hello");
            client.sendPacket(new PacketHello());
            System.out.println("Invio del pacchetto: SyncPlayer");
            client.sendPacket(new PacketSyncPlayer("lel"));
            System.out.println("Invio del pacchetto: UUID");
            client.sendPacket(new PacketUUID());
            System.out.println("Invio del pacchetto: PlayerLifeMana");
            client.sendPacket(new PacketPlayerLifeMana());
            System.out.println("Invio del pacchetto: PlayerManaLife");
            client.sendPacket(new PacketPlayerManaLife());
            System.out.println("Invio del pacchetto: PlayerBuff");
            client.sendPacket(new PacketPlayerBuff());
            System.out.println("Invio di 260 pacchetti: SyncEquipment");
            for (short i = 0; i < 260; i++) {
                client.sendPacket(new PacketSyncEquipment(i));
            }
            System.out.println("Invio del pacchetto: RequestWorldData");
            client.sendPacket(new PacketRequestWorldData());
            System.out.println("Invio del pacchetto: RequestEssentialsTiles");
            client.sendPacket(new PacketPlayerRequestEssentialsTiles());
            //non so se contnua così, con il pacchetto PlayerSpawn, ma sembra andare
            System.out.println("Invio del pacchetto: PlayerSpawn");
            client.sendPacket(new PacketPlayerSpawn());
            client.sendPacket(new PacketChat("Ciao sono il bot n"));
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
