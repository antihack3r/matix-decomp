// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.module.combat;

import de.paxii.clarinet.event.EventHandler;
import de.paxii.clarinet.event.events.player.PlayerAttackEntityEvent;
import de.paxii.clarinet.module.ModuleCategory;
import de.paxii.clarinet.module.Module;

public class ModuleCriticals extends Module
{
    private final float jumpDistance = 0.07f;
    
    public ModuleCriticals() {
        super("Criticals", ModuleCategory.COMBAT);
        this.setRegistered(true);
        this.setDescription("Forces criticals on entity attack.");
    }
    
    @EventHandler
    public void onAttack(final PlayerAttackEntityEvent event) {
        if (event.getSource().onGround && (!event.getSource().handleWaterMovement() || !event.getSource().isInLava())) {
            event.getSource().onGround = false;
            event.getSource().motionY = 0.07000000029802322;
            event.getSource().fallDistance = 0.07f;
        }
    }
}
