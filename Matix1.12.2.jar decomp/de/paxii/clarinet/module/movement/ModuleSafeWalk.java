// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.module.movement;

import de.paxii.clarinet.event.EventHandler;
import net.minecraft.entity.Entity;
import de.paxii.clarinet.Wrapper;
import de.paxii.clarinet.event.events.entity.EntityMoveEvent;
import de.paxii.clarinet.module.ModuleCategory;
import de.paxii.clarinet.module.Module;

public class ModuleSafeWalk extends Module
{
    public ModuleSafeWalk() {
        super("Safewalk", ModuleCategory.MOVEMENT);
        this.setRegistered(true);
        this.setDescription("Prevents you from falling off of edges. (like you're sneaking)");
    }
    
    @EventHandler
    public void onMoveEntity(final EntityMoveEvent event) {
        double var13 = event.getX();
        final double var14 = event.getY();
        double var15 = event.getZ();
        final boolean var16 = Wrapper.getPlayer().onGround;
        if (var16) {
            final double var17 = 0.05;
            while (event.getX() != 0.0 && Wrapper.getWorld().getCollidingBoundingBoxes(Wrapper.getPlayer(), Wrapper.getPlayer().getEntityBoundingBox().offset(event.getX(), -1.0, 0.0)).isEmpty()) {
                if (event.getX() < var17 && event.getX() >= -var17) {
                    event.setX(0.0);
                }
                else if (event.getX() > 0.0) {
                    event.setX(event.getX() - var17);
                }
                else {
                    event.setX(event.getX() + var17);
                }
                var13 = event.getX();
            }
            while (event.getZ() != 0.0 && Wrapper.getWorld().getCollidingBoundingBoxes(Wrapper.getPlayer(), Wrapper.getPlayer().getEntityBoundingBox().offset(0.0, -1.0, event.getZ())).isEmpty()) {
                if (event.getZ() < var17 && event.getZ() >= -var17) {
                    event.setZ(0.0);
                }
                else if (event.getZ() > 0.0) {
                    event.setZ(event.getZ() - var17);
                }
                else {
                    event.setZ(event.getZ() + var17);
                }
                var15 = event.getZ();
            }
            while (event.getX() != 0.0 && event.getZ() != 0.0 && Wrapper.getWorld().getCollidingBoundingBoxes(Wrapper.getPlayer(), Wrapper.getPlayer().getEntityBoundingBox().offset(event.getX(), -1.0, event.getZ())).isEmpty()) {
                if (event.getX() < var17 && event.getX() >= -var17) {
                    event.setX(0.0);
                }
                else if (event.getX() > 0.0) {
                    event.setX(event.getX() - var17);
                }
                else {
                    event.setX(event.getX() + var17);
                }
                var13 = event.getX();
                if (event.getZ() < var17 && event.getZ() >= -var17) {
                    event.setZ(0.0);
                }
                else if (event.getZ() > 0.0) {
                    event.setZ(event.getZ() - var17);
                }
                else {
                    event.setZ(event.getZ() + var17);
                }
                var15 = event.getZ();
            }
        }
    }
}
