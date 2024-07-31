package me.pancakse.pancakeclient.event.impl;

import me.pancakse.pancakeclient.event.Event;

public class ChatEvent extends Event {
    private final String content;

    public ChatEvent(String content) {
        this.content = content;
    }

    public String getMessage() {
        return content;
    }
}
