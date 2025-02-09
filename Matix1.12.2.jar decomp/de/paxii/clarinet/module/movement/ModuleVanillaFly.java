// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.module.movement;

import de.paxii.clarinet.event.EventHandler;
import de.paxii.clarinet.Wrapper;
import de.paxii.clarinet.event.events.player.PreMotionUpdateEvent;
import de.paxii.clarinet.module.ModuleCategory;
import de.paxii.clarinet.module.Module;

public class ModuleVanillaFly extends Module
{
    private double savedX;
    private double savedY;
    private double savedZ;
    
    public ModuleVanillaFly() {
        super("VanillaFly", ModuleCategory.MOVEMENT, -1);
        this.setRegistered(true);
        this.setDescription("Delays the \"Kicked for flying\" - Vanilla AntiFly.");
    }
    
    @EventHandler
    public void preMotionUpdate(final PreMotionUpdateEvent event) {
        if (event.getPlayer().onGround || Wrapper.getModuleManager().isModuleActive("KillAura")) {
            return;
        }
        final double distanceX = event.getPlayer().posX - this.savedX;
        final double distanceY = event.getPlayer().posY - this.savedY;
        final double distanceZ = event.getPlayer().posZ - this.savedZ;
        final double distanceSq = distanceX * distanceX + distanceY * distanceY + distanceZ * distanceZ;
        if (distanceSq <= 80.0) {
            event.setCancelled(true);
            return;
        }
        this.savedX = event.getPlayer().posX;
        this.savedY = event.getPlayer().posY;
        this.savedZ = event.getPlayer().posZ;
    }
}
