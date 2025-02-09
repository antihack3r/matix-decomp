// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.module.player;

import net.minecraft.client.audio.SoundEventAccessor;
import net.minecraft.client.audio.ISound;
import de.paxii.clarinet.event.EventHandler;
import net.minecraft.entity.Entity;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayerTryUseItem;
import net.minecraft.util.EnumHand;
import net.minecraft.entity.projectile.EntityFishHook;
import de.paxii.clarinet.event.events.entity.EntityVelocityEvent;
import de.paxii.clarinet.Wrapper;
import de.paxii.clarinet.module.ModuleCategory;
import net.minecraft.client.audio.ISoundEventListener;
import de.paxii.clarinet.module.Module;

public class ModuleAutoFish extends Module
{
    private boolean fishSound;
    private ISoundEventListener soundEventListener;
    
    public ModuleAutoFish() {
        super("AutoFish", ModuleCategory.PLAYER, -1);
        this.setRegistered(true);
        this.setDescription("Automatically fishes for you.");
        this.soundEventListener = ((soundIn, accessor) -> {
            if (soundIn.getSound().getSoundLocation().getResourcePath().equals("random/splash")) {
                this.fishSound = true;
            }
        });
    }
    
    @Override
    public void onEnable() {
        Wrapper.getMinecraft().getSoundHandler().getSndManager().addListener(this.soundEventListener);
    }
    
    @EventHandler
    public void onEntityVelocity(final EntityVelocityEvent event) {
        final Entity entity = Wrapper.getWorld().getEntityByID(event.getEntityID());
        if (entity instanceof EntityFishHook) {
            final EntityFishHook fishHook = (EntityFishHook)entity;
            if (this.fishSound && fishHook.getAngler().getEntityId() == Wrapper.getPlayer().getEntityId() && event.getVelocityPacket().getMotionX() == 0 && event.getVelocityPacket().getMotionZ() == 0) {
                new Thread(() -> {
                    for (int i = 0; i < 2; ++i) {
                        try {
                            Thread.sleep(200L);
                        }
                        catch (final InterruptedException exception) {
                            exception.printStackTrace();
                        }
                        Wrapper.getSendQueue().addToSendQueue(new CPacketPlayerTryUseItem(EnumHand.MAIN_HAND));
                    }
                    return;
                }).start();
                this.fishSound = false;
            }
        }
    }
    
    @Override
    public void onDisable() {
        Wrapper.getMinecraft().getSoundHandler().getSndManager().removeListener(this.soundEventListener);
    }
}
