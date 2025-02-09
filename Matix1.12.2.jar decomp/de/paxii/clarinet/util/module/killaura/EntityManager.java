// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.util.module.killaura;

import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import de.paxii.clarinet.util.player.PlayerUtils;
import java.util.Iterator;
import net.minecraft.entity.Entity;
import de.paxii.clarinet.Wrapper;
import net.minecraft.entity.EntityLivingBase;
import de.paxii.clarinet.util.module.friends.FriendManager;

public class EntityManager
{
    private final AuraManager auraManager;
    private FriendManager friends;
    
    public EntityManager(final AuraManager auraManager, final FriendManager friends) {
        this.auraManager = auraManager;
        this.friends = friends;
    }
    
    public EntityLivingBase getClosestEntity(final double range) {
        double distance = range;
        EntityLivingBase tempEntity = null;
        for (final Entity entity : Wrapper.getWorld().loadedEntityList) {
            if (!(entity instanceof EntityLivingBase)) {
                continue;
            }
            final EntityLivingBase living = (EntityLivingBase)entity;
            if (!this.canAttackEntity(living)) {
                continue;
            }
            final double curDistance = Wrapper.getPlayer().getDistanceToEntity(living);
            if (curDistance > distance) {
                continue;
            }
            distance = curDistance;
            tempEntity = living;
        }
        return tempEntity;
    }
    
    public EntityLivingBase getClosestEntityToCursor(final float angle) {
        float distance = angle;
        EntityLivingBase tempEntity = null;
        for (final Entity entity : Wrapper.getWorld().loadedEntityList) {
            if (!(entity instanceof EntityLivingBase)) {
                continue;
            }
            final EntityLivingBase living = (EntityLivingBase)entity;
            if (!this.canAttackEntity(living)) {
                continue;
            }
            final float[] angles = this.getAngles(living);
            final float yaw = this.getDistanceBetweenAngles(Wrapper.getPlayer().rotationYawHead, angles[0]);
            final float pitch = this.getDistanceBetweenAngles(Wrapper.getPlayer().rotationPitch, angles[1]);
            if (yaw > angle) {
                continue;
            }
            if (pitch > angle) {
                continue;
            }
            final float curDistance = (yaw + pitch) / 2.0f;
            if (curDistance > distance) {
                continue;
            }
            distance = curDistance;
            tempEntity = living;
        }
        return tempEntity;
    }
    
    public EntityLivingBase getEntity(final float angle, final double distance) {
        final EntityLivingBase distanceCheck = this.getClosestEntity(distance);
        final EntityLivingBase angleCheck = this.getClosestEntityToCursor(angle);
        if (this.auraManager.isLegit() && angleCheck == null) {
            return null;
        }
        return (angleCheck != null) ? angleCheck : distanceCheck;
    }
    
    public float getDistanceBetweenAngles(final float ang1, final float ang2) {
        return Math.abs(((ang1 - ang2 + 180.0f) % 360.0f + 360.0f) % 360.0f - 180.0f);
    }
    
    public boolean canAttackEntity(final EntityLivingBase entity) {
        return entity != Wrapper.getPlayer() && this.shouldAttack(entity);
    }
    
    private boolean shouldAttack(final EntityLivingBase entityLiving) {
        return entityLiving.deathTime <= 0 && entityLiving.isEntityAlive() && PlayerUtils.canEntityBeSeen(Wrapper.getPlayer(), entityLiving) && Wrapper.getPlayer().getDistanceToEntity(entityLiving) <= this.auraManager.getRange() && entityLiving.canBePushed() && (!(entityLiving instanceof EntityPlayer) || this.auraManager.isPlayer()) && (!(entityLiving instanceof EntityPlayer) || !this.friends.isFriend(entityLiving.getName())) && (!(entityLiving instanceof EntityAnimal) || this.auraManager.isAnimal()) && (!(entityLiving instanceof EntityMob) || this.auraManager.isMob()) && (!entityLiving.isInvisible() || this.auraManager.isInvisible()) && entityLiving.ticksExisted >= 20 && (!this.auraManager.isLegit() || !entityLiving.onGround || (entityLiving.posY - Wrapper.getPlayer().posY > -2.0 && Wrapper.getPlayer().posY - entityLiving.posY < 2.0));
    }
    
    public float[] getAngles(final EntityLivingBase entityLiving) {
        final double difX = entityLiving.posX - Wrapper.getPlayer().posX;
        final double difY = entityLiving.posY + entityLiving.getEyeHeight() - (Wrapper.getPlayer().posY + Wrapper.getPlayer().getEyeHeight());
        final double difZ = entityLiving.posZ - Wrapper.getPlayer().posZ;
        final double helper = Math.sqrt(difX * difX + difZ * difZ);
        final float yaw = (float)(Math.atan2(difZ, difX) * 180.0 / 3.141592653589793) - 90.0f;
        final float pitch = (float)(-(Math.atan2(difY, helper) * 180.0 / 3.141592653589793));
        return new float[] { yaw, pitch };
    }
}
