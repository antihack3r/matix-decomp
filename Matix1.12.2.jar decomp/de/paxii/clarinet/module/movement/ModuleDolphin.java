// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.module.movement;

import de.paxii.clarinet.event.EventHandler;
import de.paxii.clarinet.Wrapper;
import de.paxii.clarinet.event.events.game.IngameTickEvent;
import de.paxii.clarinet.module.ModuleCategory;
import de.paxii.clarinet.module.Module;

public class ModuleDolphin extends Module
{
    public ModuleDolphin() {
        super("Dolphin", ModuleCategory.MOVEMENT, 36);
        this.setRegistered(true);
        this.setDescription("Automatically swims for you.");
    }
    
    @EventHandler
    public void onTick(final IngameTickEvent event) {
        if (!Wrapper.getGameSettings().keyBindSneak.isKeyDown() && Wrapper.getPlayer().isInWater() && !Wrapper.getPlayer().isCollidedHorizontally) {
            Wrapper.getPlayer().motionY = 0.04;
        }
    }
}
