package com.github.redfox197.terraria.twitch;

import java.util.List;

public class MessageData {
    private final String prefix;
    private final String command;
    private final List<String> params;

    public MessageData(String prefix, String command, List<String> params) {
        this.prefix = prefix;
        this.command = command;
        this.params = params;
    }

    public String prefix() {
        return prefix;
    }

    public String command() {
        return command;
    }

    public List<String> params() {
        return params;
    }
}
