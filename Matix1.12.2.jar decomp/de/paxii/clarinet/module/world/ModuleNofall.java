// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.module.world;

import de.paxii.clarinet.event.EventHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayer;
import de.paxii.clarinet.Wrapper;
import de.paxii.clarinet.event.events.game.SendPacketEvent;
import de.paxii.clarinet.module.ModuleCategory;
import de.paxii.clarinet.module.Module;

public class ModuleNofall extends Module
{
    public ModuleNofall() {
        super("Nofall", ModuleCategory.WORLD);
        this.setRegistered(true);
        this.setDescription("Disables fall damage.");
    }
    
    @EventHandler
    public void onSendPacket(final SendPacketEvent e) {
        if (!Wrapper.getPlayer().onGround && e.getPacket() instanceof CPacketPlayer) {
            final CPacketPlayer curPacket = (CPacketPlayer)e.getPacket();
            curPacket.setOnGround(true);
            e.setPacket(curPacket);
        }
    }
}
