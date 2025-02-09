// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.module.movement;

import de.paxii.clarinet.event.EventHandler;
import net.minecraft.item.ItemStack;
import de.paxii.clarinet.Wrapper;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import de.paxii.clarinet.event.events.player.PlayerMoveEvent;
import de.paxii.clarinet.module.ModuleCategory;
import de.paxii.clarinet.module.Module;

public class ModuleElytra extends Module
{
    public ModuleElytra() {
        super("Elytra", ModuleCategory.MOVEMENT);
        this.setRegistered(true);
        this.setDescription("Allows you to do an upwards motion with an elytra when holding the jump button.");
    }
    
    @EventHandler
    public void onMovement(final PlayerMoveEvent event) {
        final ItemStack chestItemstack = event.getPlayer().getItemStackFromSlot(EntityEquipmentSlot.CHEST);
        if (!event.getPlayer().onGround && chestItemstack != null && chestItemstack.getItem() == Items.ELYTRA && Wrapper.getGameSettings().keyBindJump.isKeyDown()) {
            event.setMotionY(event.getMotionY() + 0.05);
        }
    }
}
