package me.pancakse.pancakeclient.event.impl;

import me.pancakse.pancakeclient.event.Event;
import net.minecraft.entity.LivingEntity;

public class DeathEvent extends Event {
    private final LivingEntity entity;

    public DeathEvent(LivingEntity entity) {
        this.entity = entity;
    }

    public LivingEntity getEntity() {
        return entity;
    }
}
