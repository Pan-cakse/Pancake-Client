package me.pancakse.pancakeclient.manager;

import com.google.common.eventbus.Subscribe;
import me.pancakse.pancakeclient.PancakeClient;
import me.pancakse.pancakeclient.event.Stage;
import me.pancakse.pancakeclient.event.impl.*;
import me.pancakse.pancakeclient.features.Feature;
import me.pancakse.pancakeclient.features.commands.Command;
import me.pancakse.pancakeclient.util.models.Timer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.packet.s2c.play.WorldTimeUpdateS2CPacket;
import net.minecraft.util.Formatting;

public class EventManager extends Feature {
    private final Timer logoutTimer = new Timer();

    public void init() {
        EVENT_BUS.register(this);
    }

    public void onUnload() {
        EVENT_BUS.unregister(this);
    }

    @Subscribe
    public void onUpdate(UpdateEvent event) {
        mc.getWindow().setTitle("PancakeClient 0.0.3");
        if (!fullNullCheck()) {
//            PancakeClient.inventoryManager.update();
            PancakeClient.moduleManager.onUpdate();
            PancakeClient.moduleManager.sortModules(true);
            onTick();
//            if ((HUD.getInstance()).renderingMode.getValue() == HUD.RenderingMode.Length) {
//                PancakeClient.moduleManager.sortModules(true);
//            } else {
//                PancakeClient.moduleManager.sortModulesABC();
//            }
        }
    }

    public void onTick() {
        if (fullNullCheck())
            return;
        PancakeClient.moduleManager.onTick();
        for (PlayerEntity player : mc.world.getPlayers()) {
            if (player == null || player.getHealth() > 0.0F)
                continue;
            EVENT_BUS.post(new DeathEvent(player));
//            PopCounter.getInstance().onDeath(player);
        }
    }

    @Subscribe
    public void onUpdateWalkingPlayer(UpdateWalkingPlayerEvent event) {
        if (fullNullCheck())
            return;
        if (event.getStage() == Stage.PRE) {
            PancakeClient.speedManager.updateValues();
            PancakeClient.rotationManager.updateRotations();
            PancakeClient.positionManager.updatePosition();
        }
        if (event.getStage() == Stage.POST) {
            PancakeClient.rotationManager.restoreRotations();
            PancakeClient.positionManager.restorePosition();
        }
    }

    @Subscribe
    public void onPacketReceive(PacketEvent.Receive event) {
        PancakeClient.serverManager.onPacketReceived();
        if (event.getPacket() instanceof WorldTimeUpdateS2CPacket)
            PancakeClient.serverManager.update();
    }

    @Subscribe
    public void onWorldRender(Render3DEvent event) {
        PancakeClient.moduleManager.onRender3D(event);
    }

    @Subscribe public void onRenderGameOverlayEvent(Render2DEvent event) {
        PancakeClient.moduleManager.onRender2D(event);
    }

    @Subscribe public void onKeyInput(KeyEvent event) {
        PancakeClient.moduleManager.onKeyPressed(event.getKey());
    }

    @Subscribe public void onChatSent(ChatEvent event) {
        if (event.getMessage().startsWith(Command.getCommandPrefix())) {
            event.cancel();
            try {
                if (event.getMessage().length() > 1) {
                    PancakeClient.commandManager.executeCommand(event.getMessage().substring(Command.getCommandPrefix().length() - 1));
                } else {
                    Command.sendMessage("Please enter a command.");
                }
            } catch (Exception e) {
                e.printStackTrace();
                Command.sendMessage(Formatting.RED + "An error occurred while running this command. Check the log!");
            }
        }
    }
}