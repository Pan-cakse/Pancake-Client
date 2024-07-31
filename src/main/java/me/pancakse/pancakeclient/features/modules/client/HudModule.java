package me.pancakse.pancakeclient.features.modules.client;

import me.pancakse.pancakeclient.PancakeClient;
import me.pancakse.pancakeclient.event.impl.Render2DEvent;
import me.pancakse.pancakeclient.features.modules.Module;

public class HudModule extends Module {
    public HudModule() {
        super("Hud", "hud", Category.CLIENT, true, false, false);
    }

    @Override public void onRender2D(Render2DEvent event) {
        event.getContext().drawTextWithShadow(
                mc.textRenderer,
                PancakeClient.NAME + " " + PancakeClient.VERSION,
                2, 2,
                -1
        );
    }
}
