// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.module.movement;

import de.paxii.clarinet.event.EventHandler;
import net.minecraft.client.entity.EntityPlayerSP;
import de.paxii.clarinet.Wrapper;
import de.paxii.clarinet.event.events.game.IngameTickEvent;
import de.paxii.clarinet.util.module.settings.ValueBase;
import de.paxii.clarinet.module.ModuleCategory;
import de.paxii.clarinet.module.Module;

public class ModuleFly extends Module
{
    public ModuleFly() {
        super("Fly", ModuleCategory.MOVEMENT, 19);
        this.setDescription("Allows you to fly.");
        this.setRegistered(true);
        this.getModuleValues().put("flySpeed", new ValueBase("Fly Speed", 0.5f, 0.1f, 5.0f, "Speed"));
    }
    
    @EventHandler
    public void onTick(final IngameTickEvent event) {
        if (Wrapper.getPlayer().isInWater()) {
            return;
        }
        final float flightSpeed = this.getModuleValues().get("flySpeed").getValue();
        Wrapper.getPlayer().jumpMovementFactor = flightSpeed;
        Wrapper.getPlayer().motionY = 0.0;
        Wrapper.getPlayer().motionX = 0.0;
        Wrapper.getPlayer().motionZ = 0.0;
        if (Wrapper.getGameSettings().keyBindJump.isKeyDown()) {
            final EntityPlayerSP player = Wrapper.getPlayer();
            player.motionY += flightSpeed / 1.2;
        }
        else if (Wrapper.getGameSettings().keyBindSneak.isKeyDown()) {
            final EntityPlayerSP player2 = Wrapper.getPlayer();
            player2.motionY -= flightSpeed / 1.2;
        }
    }
}
