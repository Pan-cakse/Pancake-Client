package me.pancakse.pancakeclient.event.impl;

import me.pancakse.pancakeclient.event.Event;

public class KeyEvent extends Event {
    private final int key;

    public KeyEvent(int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }
}
