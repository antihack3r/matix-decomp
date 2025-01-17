// 
// Decompiled by Procyon v0.6.0
// 

package net.minecraft.entity.ai;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.village.Village;
import net.minecraft.world.World;
import net.minecraft.entity.passive.EntityVillager;

public class EntityAIVillagerMate extends EntityAIBase
{
    private final EntityVillager villager;
    private EntityVillager mate;
    private final World world;
    private int matingTimeout;
    Village village;
    
    public EntityAIVillagerMate(final EntityVillager villagerIn) {
        this.villager = villagerIn;
        this.world = villagerIn.world;
        this.setMutexBits(3);
    }
    
    @Override
    public boolean shouldExecute() {
        if (this.villager.getGrowingAge() != 0) {
            return false;
        }
        if (this.villager.getRNG().nextInt(500) != 0) {
            return false;
        }
        this.village = this.world.getVillageCollection().getNearestVillage(new BlockPos(this.villager), 0);
        if (this.village == null) {
            return false;
        }
        if (!this.checkSufficientDoorsPresentForNewVillager() || !this.villager.getIsWillingToMate(true)) {
            return false;
        }
        final Entity entity = this.world.findNearestEntityWithinAABB(EntityVillager.class, this.villager.getEntityBoundingBox().grow(8.0, 3.0, 8.0), this.villager);
        if (entity == null) {
            return false;
        }
        this.mate = (EntityVillager)entity;
        return this.mate.getGrowingAge() == 0 && this.mate.getIsWillingToMate(true);
    }
    
    @Override
    public void startExecuting() {
        this.matingTimeout = 300;
        this.villager.setMating(true);
    }
    
    @Override
    public void resetTask() {
        this.village = null;
        this.mate = null;
        this.villager.setMating(false);
    }
    
    @Override
    public boolean shouldContinueExecuting() {
        return this.matingTimeout >= 0 && this.checkSufficientDoorsPresentForNewVillager() && this.villager.getGrowingAge() == 0 && this.villager.getIsWillingToMate(false);
    }
    
    @Override
    public void updateTask() {
        --this.matingTimeout;
        this.villager.getLookHelper().setLookPositionWithEntity(this.mate, 10.0f, 30.0f);
        if (this.villager.getDistanceSqToEntity(this.mate) > 2.25) {
            this.villager.getNavigator().tryMoveToEntityLiving(this.mate, 0.25);
        }
        else if (this.matingTimeout == 0 && this.mate.isMating()) {
            this.giveBirth();
        }
        if (this.villager.getRNG().nextInt(35) == 0) {
            this.world.setEntityState(this.villager, (byte)12);
        }
    }
    
    private boolean checkSufficientDoorsPresentForNewVillager() {
        if (!this.village.isMatingSeason()) {
            return false;
        }
        final int i = (int)((float)this.village.getNumVillageDoors() * 0.35);
        return this.village.getNumVillagers() < i;
    }
    
    private void giveBirth() {
        final EntityVillager entityvillager = this.villager.createChild(this.mate);
        this.mate.setGrowingAge(6000);
        this.villager.setGrowingAge(6000);
        this.mate.setIsWillingToMate(false);
        this.villager.setIsWillingToMate(false);
        entityvillager.setGrowingAge(-24000);
        entityvillager.setLocationAndAngles(this.villager.posX, this.villager.posY, this.villager.posZ, 0.0f, 0.0f);
        this.world.spawnEntity(entityvillager);
        this.world.setEntityState(entityvillager, (byte)12);
    }
}
