// 
// Decompiled by Procyon v0.6.0
// 

package net.minecraft.client.entity;

import net.minecraft.util.math.BlockPos;
import net.minecraft.client.Minecraft;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.DamageSource;
import com.mojang.authlib.GameProfile;
import net.minecraft.world.World;

public class EntityOtherPlayerMP extends AbstractClientPlayer
{
    private int otherPlayerMPPosRotationIncrements;
    private double otherPlayerMPX;
    private double otherPlayerMPY;
    private double otherPlayerMPZ;
    private double otherPlayerMPYaw;
    private double otherPlayerMPPitch;
    
    public EntityOtherPlayerMP(final World worldIn, final GameProfile gameProfileIn) {
        super(worldIn, gameProfileIn);
        this.stepHeight = 1.0f;
        this.noClip = true;
        this.renderOffsetY = 0.25f;
    }
    
    @Override
    public boolean isInRangeToRenderDist(final double distance) {
        double d0 = this.getEntityBoundingBox().getAverageEdgeLength() * 10.0;
        if (Double.isNaN(d0)) {
            d0 = 1.0;
        }
        d0 = d0 * 64.0 * getRenderDistanceWeight();
        return distance < d0 * d0;
    }
    
    @Override
    public boolean attackEntityFrom(final DamageSource source, final float amount) {
        return true;
    }
    
    @Override
    public void setPositionAndRotationDirect(final double x, final double y, final double z, final float yaw, final float pitch, final int posRotationIncrements, final boolean teleport) {
        this.otherPlayerMPX = x;
        this.otherPlayerMPY = y;
        this.otherPlayerMPZ = z;
        this.otherPlayerMPYaw = yaw;
        this.otherPlayerMPPitch = pitch;
        this.otherPlayerMPPosRotationIncrements = posRotationIncrements;
    }
    
    @Override
    public void onUpdate() {
        this.renderOffsetY = 0.0f;
        super.onUpdate();
        this.prevLimbSwingAmount = this.limbSwingAmount;
        final double d0 = this.posX - this.prevPosX;
        final double d2 = this.posZ - this.prevPosZ;
        float f = MathHelper.sqrt(d0 * d0 + d2 * d2) * 4.0f;
        if (f > 1.0f) {
            f = 1.0f;
        }
        this.limbSwingAmount += (f - this.limbSwingAmount) * 0.4f;
        this.limbSwing += this.limbSwingAmount;
    }
    
    @Override
    public void onLivingUpdate() {
        if (this.otherPlayerMPPosRotationIncrements > 0) {
            final double d0 = this.posX + (this.otherPlayerMPX - this.posX) / this.otherPlayerMPPosRotationIncrements;
            final double d2 = this.posY + (this.otherPlayerMPY - this.posY) / this.otherPlayerMPPosRotationIncrements;
            final double d3 = this.posZ + (this.otherPlayerMPZ - this.posZ) / this.otherPlayerMPPosRotationIncrements;
            double d4;
            for (d4 = this.otherPlayerMPYaw - this.rotationYaw; d4 < -180.0; d4 += 360.0) {}
            while (d4 >= 180.0) {
                d4 -= 360.0;
            }
            this.rotationYaw += (float)(d4 / this.otherPlayerMPPosRotationIncrements);
            this.rotationPitch += (float)((this.otherPlayerMPPitch - this.rotationPitch) / this.otherPlayerMPPosRotationIncrements);
            --this.otherPlayerMPPosRotationIncrements;
            this.setPosition(d0, d2, d3);
            this.setRotation(this.rotationYaw, this.rotationPitch);
        }
        this.prevCameraYaw = this.cameraYaw;
        this.updateArmSwingProgress();
        float f1 = MathHelper.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
        float f2 = (float)Math.atan(-this.motionY * 0.20000000298023224) * 15.0f;
        if (f1 > 0.1f) {
            f1 = 0.1f;
        }
        if (!this.onGround || this.getHealth() <= 0.0f) {
            f1 = 0.0f;
        }
        if (this.onGround || this.getHealth() <= 0.0f) {
            f2 = 0.0f;
        }
        this.cameraYaw += (f1 - this.cameraYaw) * 0.4f;
        this.cameraPitch += (f2 - this.cameraPitch) * 0.8f;
        this.world.profiler.startSection("push");
        this.collideWithNearbyEntities();
        this.world.profiler.endSection();
    }
    
    @Override
    public void sendMessage(final ITextComponent component) {
        Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(component);
    }
    
    @Override
    public boolean canUseCommand(final int permLevel, final String commandName) {
        return false;
    }
    
    @Override
    public BlockPos getPosition() {
        return new BlockPos(this.posX + 0.5, this.posY + 0.5, this.posZ + 0.5);
    }
}
