// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.module.movement;

import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayer;
import de.paxii.clarinet.event.events.game.SendPacketEvent;
import de.paxii.clarinet.event.events.player.PlayerStepEvent;
import de.paxii.clarinet.event.EventHandler;
import de.paxii.clarinet.Wrapper;
import de.paxii.clarinet.event.events.game.IngameTickEvent;
import de.paxii.clarinet.module.ModuleCategory;
import java.util.concurrent.atomic.AtomicBoolean;
import de.paxii.clarinet.module.Module;

public class ModuleStep extends Module
{
    private final AtomicBoolean canPacket;
    
    public ModuleStep() {
        super("Step", ModuleCategory.MOVEMENT, 37);
        this.canPacket = new AtomicBoolean();
        this.setRegistered(true);
        this.setDescription("Allows you to step up full blocks.");
    }
    
    @EventHandler
    public void onTick(final IngameTickEvent event) {
        Wrapper.getPlayer().stepHeight = (Wrapper.getPlayer().onGround ? 1.0f : 0.5f);
    }
    
    @EventHandler
    public void onStep(final PlayerStepEvent event) {
        this.canPacket.set(Wrapper.getPlayer().onGround && event.getStepHeight() >= 1.0);
    }
    
    @EventHandler
    public void onPacketSend(final SendPacketEvent event) {
        if (event.getPacket() instanceof CPacketPlayer && this.canPacket.compareAndSet(true, false)) {
            final CPacketPlayer curPacket = (CPacketPlayer)event.getPacket();
            curPacket.setY(curPacket.getY() + 0.063);
            event.setPacket(curPacket);
        }
    }
    
    @Override
    public void onDisable() {
        Wrapper.getPlayer().stepHeight = 0.5f;
    }
}
