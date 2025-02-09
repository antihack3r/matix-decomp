// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.module.player;

import de.paxii.clarinet.event.EventHandler;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.item.ItemBow;
import net.minecraft.util.EnumHand;
import de.paxii.clarinet.Wrapper;
import de.paxii.clarinet.event.events.game.IngameTickEvent;
import de.paxii.clarinet.module.ModuleCategory;
import java.util.Random;
import de.paxii.clarinet.util.module.killaura.TimeManager;
import de.paxii.clarinet.module.Module;

public class ModuleRotationDerp extends Module
{
    private final TimeManager timeManager;
    private final Random random;
    
    public ModuleRotationDerp() {
        super("RotationDerp", ModuleCategory.PLAYER, -1);
        this.setRegistered(true);
        this.setDescription("Spins your head. Only visible server-side.");
        this.timeManager = new TimeManager();
        this.random = new Random();
    }
    
    @EventHandler
    public void onTick(final IngameTickEvent event) {
        this.timeManager.updateTimer();
        if (Wrapper.getPlayer().getHeldItem(EnumHand.MAIN_HAND) != null) {
            final ItemStack it = Wrapper.getPlayer().getHeldItem(EnumHand.MAIN_HAND);
            if (it != null && it.getItem() instanceof ItemBow) {
                return;
            }
        }
        if (this.timeManager.sleep(200L)) {
            final float derpYaw = (float)this.random.nextInt(360);
            final float derpPitch = (float)this.random.nextInt(180);
            Wrapper.getSendQueue().addToSendQueue(new CPacketPlayer.Rotation(derpYaw, derpPitch, Wrapper.getPlayer().onGround));
            this.timeManager.updateLast();
        }
    }
}
