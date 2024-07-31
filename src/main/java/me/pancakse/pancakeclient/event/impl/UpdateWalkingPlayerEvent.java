package me.pancakse.pancakeclient.event.impl;

import me.pancakse.pancakeclient.event.Event;
import me.pancakse.pancakeclient.event.Stage;

public class UpdateWalkingPlayerEvent extends Event {
    private final Stage stage;

    public UpdateWalkingPlayerEvent(Stage stage) {
        this.stage = stage;
    }

    public Stage getStage() {
        return stage;
    }
}
