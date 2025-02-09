// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.module.player;

import de.paxii.clarinet.event.EventHandler;
import net.minecraft.util.EnumHand;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayerDigging;
import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import de.paxii.clarinet.Wrapper;
import de.paxii.clarinet.event.events.game.IngameTickEvent;
import de.paxii.clarinet.module.ModuleCategory;
import de.paxii.clarinet.util.module.killaura.TimeManager;
import de.paxii.clarinet.module.Module;

public class ModuleNuker extends Module
{
    private final TimeManager timeManager;
    
    public ModuleNuker() {
        super("Nuker", ModuleCategory.PLAYER);
        this.setRegistered(true);
        this.setDescription("Breaks blocks around you when holding the attack button. Requires Creative.");
        this.timeManager = new TimeManager();
    }
    
    @EventHandler
    public void onTick(final IngameTickEvent e) {
        this.timeManager.updateTimer();
        if (Wrapper.getGameSettings().keyBindAttack.isKeyDown() && this.timeManager.sleep(150L)) {
            this.timeManager.updateLast();
            for (int range = 5, x = -range; x < range; ++x) {
                for (int y = -range; y < range; ++y) {
                    for (int z = -range; z < range; ++z) {
                        final BlockPos blockPos = new BlockPos(Wrapper.getPlayer().posX + x, Wrapper.getPlayer().posY + y, Wrapper.getPlayer().posZ + z);
                        if (Wrapper.getWorld() != null && Block.getIdFromBlock(Wrapper.getWorld().getBlock(blockPos.getX(), blockPos.getY(), blockPos.getZ())) != 0) {
                            Wrapper.getSendQueue().addToSendQueue(new CPacketPlayerDigging(CPacketPlayerDigging.Action.START_DESTROY_BLOCK, blockPos, Wrapper.getPlayer().getHorizontalFacing()));
                            Wrapper.getSendQueue().addToSendQueue(new CPacketPlayerDigging(CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK, blockPos, Wrapper.getPlayer().getHorizontalFacing()));
                            Wrapper.getPlayer().swingArm(EnumHand.MAIN_HAND);
                        }
                    }
                }
            }
        }
    }
}
