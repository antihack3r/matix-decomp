// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.module.player;

import de.paxii.clarinet.event.EventHandler;
import de.paxii.clarinet.Wrapper;
import de.paxii.clarinet.event.events.game.IngameTickEvent;
import de.paxii.clarinet.util.module.settings.ValueBase;
import de.paxii.clarinet.module.ModuleCategory;
import de.paxii.clarinet.module.Module;

public class ModuleFastPlace extends Module
{
    public ModuleFastPlace() {
        super("Fastplace", ModuleCategory.PLAYER, -1);
        this.setRegistered(true);
        this.getModuleValues().put("clickSpeed", new ValueBase("FastPlace Delay", 0.0f, 0.0f, 4.0f, false, "Delay"));
    }
    
    @EventHandler
    public void onTick(final IngameTickEvent event) {
        try {
            Wrapper.getMinecraft().setRightClickDelayTimer(Math.round(this.getModuleValues().get("clickSpeed").getValue()));
        }
        catch (final Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void onDisable() {
        Wrapper.getMinecraft().setRightClickDelayTimer(4);
    }
}
