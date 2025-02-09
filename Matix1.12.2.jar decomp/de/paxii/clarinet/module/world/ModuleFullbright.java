// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.module.world;

import de.paxii.clarinet.event.EventHandler;
import de.paxii.clarinet.Wrapper;
import de.paxii.clarinet.event.events.game.IngameTickEvent;
import de.paxii.clarinet.module.ModuleCategory;
import de.paxii.clarinet.module.Module;

public class ModuleFullbright extends Module
{
    public ModuleFullbright() {
        super("Fullbright", ModuleCategory.WORLD, 48);
        this.setRegistered(true);
        this.setDescription("Renders the world with full brightness.");
    }
    
    @EventHandler
    public void onTick(final IngameTickEvent event) {
        if (Wrapper.getWorld().provider.getLightBrightnessTable()[0] != 1.0f) {
            for (int i = 0; 15 >= i; ++i) {
                Wrapper.getWorld().provider.getLightBrightnessTable()[i] = 1.0f;
            }
        }
    }
    
    @Override
    public void onDisable() {
        Wrapper.getWorld().provider.generateLightBrightnessTable();
    }
}
