// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.module.movement;

import de.paxii.clarinet.event.EventHandler;
import de.paxii.clarinet.event.events.player.PlayerMoveEvent;
import de.paxii.clarinet.module.ModuleCategory;
import de.paxii.clarinet.module.Module;

public class ModuleSprint extends Module
{
    public ModuleSprint() {
        super("Sprint", ModuleCategory.MOVEMENT, 49);
        this.setRegistered(true);
        this.setDescription("Automatically sprints for you.");
    }
    
    @EventHandler
    public void onMove(final PlayerMoveEvent event) {
        final boolean canSprint = event.getPlayer().movementInput.moveForward > 0.0f && (event.getPlayer().getFoodStats().getFoodLevel() > 6 || event.getPlayer().capabilities.isCreativeMode) && !event.getPlayer().capabilities.isFlying && !event.getPlayer().isCollidedHorizontally;
        event.getPlayer().setSprinting(canSprint);
    }
}
