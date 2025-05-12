package com.github.redfox197.terraria.twitch;

import com.github.redfox197.terraria.TerrariaBot;
import com.github.redfox197.terraria.net.packets.client.PacketChat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class TwitchClient {
    private final String nickname;
    private final String password;
    private final List<String> joinedChannels = new ArrayList<>();
    private Socket socket;
    private BufferedReader reader;
    private PrintStream writer;

    public TwitchClient(String nickname, String password) {
        this.nickname = nickname;
        this.password = password;
    }

    public void connect() {
        try {
            socket = new Socket("irc.chat.twitch.tv", 6667);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
            writer = new PrintStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void login() {
        writer.println("PASS " + password);
        writer.println("Nick " + nickname);
    }

    public void join(String channel) {
        if (!joinedChannels.contains(channel)) {
            writer.println("JOIN #" + channel);
            joinedChannels.add(channel);
        }
    }

    public void leave(String channel) {
        if (joinedChannels.contains(channel)) {
            writer.println("PART #" + channel);
            joinedChannels.remove(channel);
        }
    }

    public void startReader() {
        new Thread(() -> {
            try {
                String line = "";
                while ((line = reader.readLine()) != null) {
                    handleMessageData(processLine(line));
                }
            } catch (Exception ignored) {
            }
        }).start();
    }

    public MessageData processLine(String line) {
        System.out.println(line);

        String prefix = null;
        String command = null;
        List<String> params = new ArrayList<>();

        int position = 0;
        int nextspace = 0;

        if (line.charAt(position) == ':') {
            nextspace = line.indexOf(' ', position);
            if (nextspace == -1) throw new IllegalStateException("Messaggio malformato! Nessun prefix");
            prefix = line.substring(position + 1, nextspace);
            position = nextspace + 1;
            while (line.charAt(position) == ' ') {
                position++;
            }
        }

        nextspace = line.indexOf(' ', position);
        if (nextspace == -1) {
            if (line.length() - 1 > position) {
                command = line.substring(position);
                return new MessageData(prefix, command, params);
            }
            return null;
        }

        command = line.substring(position, nextspace);
        position = nextspace + 1;

        while (line.charAt(position) == ' ') {
            position++;
        }

        while (position < line.length() - 1) {
            nextspace = line.indexOf(' ', position);

            if (line.charAt(position) == ':') {
                params.add(line.substring(position + 1));
                break;
            }

            if (nextspace != -1) {
                params.add(line.substring(position, nextspace));
                position = nextspace + 1;

                while (line.charAt(position) == ' ') {
                    position++;
                }

                continue;
            }

            if (nextspace == -1) {
                params.add(line.substring(position));
                break;
            }
        }
        return new MessageData(prefix, command, params);
    }

    public void handleMessageData(MessageData data) {
        if (data != null) {
            if (data.command().equals("PING")) {
                writer.println("PONG :tmi.twitch.tv");
            }

            if (data.command().equals("PRIVMSG")) {
                String prefix = data.prefix();
                String username = prefix.substring(0, prefix.indexOf('!'));
                sendToTerraria(username, data.params().get(1));
            }
        }
    }

    public void sendToTerraria(String username, String message) {
        try {
            TerrariaBot.client.sendPacket(new PacketChat(username + ": " + message));
        } catch (IOException ignored) {
        }
    }

    public void sendMessage(String channel, String message) {
        if (!channel.startsWith("#")) {
            writer.println("PRIVMSG #" + channel + " :" + message);
        } else {
            writer.println("PRIVMSG " + channel + " :" + message);
        }
    }

    public void disconnect() {
        try {
            writer.close();
            reader.close();
            socket.close();
        } catch (IOException ignored) {
        }
    }
}
