// 
// Decompiled by Procyon v0.6.0
// 

package net.minecraft.client.particle;

import net.minecraft.init.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class Barrier extends Particle
{
    protected Barrier(final World worldIn, final double p_i46286_2_, final double p_i46286_4_, final double p_i46286_6_, final Item p_i46286_8_) {
        super(worldIn, p_i46286_2_, p_i46286_4_, p_i46286_6_, 0.0, 0.0, 0.0);
        this.setParticleTexture(Minecraft.getMinecraft().getRenderItem().getItemModelMesher().getParticleIcon(p_i46286_8_));
        this.particleRed = 1.0f;
        this.particleGreen = 1.0f;
        this.particleBlue = 1.0f;
        this.motionX = 0.0;
        this.motionY = 0.0;
        this.motionZ = 0.0;
        this.particleGravity = 0.0f;
        this.particleMaxAge = 80;
    }
    
    @Override
    public int getFXLayer() {
        return 1;
    }
    
    @Override
    public void renderParticle(final BufferBuilder buffer, final Entity entityIn, final float partialTicks, final float rotationX, final float rotationZ, final float rotationYZ, final float rotationXY, final float rotationXZ) {
        final float f = this.particleTexture.getMinU();
        final float f2 = this.particleTexture.getMaxU();
        final float f3 = this.particleTexture.getMinV();
        final float f4 = this.particleTexture.getMaxV();
        final float f5 = 0.5f;
        final float f6 = (float)(this.prevPosX + (this.posX - this.prevPosX) * partialTicks - Barrier.interpPosX);
        final float f7 = (float)(this.prevPosY + (this.posY - this.prevPosY) * partialTicks - Barrier.interpPosY);
        final float f8 = (float)(this.prevPosZ + (this.posZ - this.prevPosZ) * partialTicks - Barrier.interpPosZ);
        final int i = this.getBrightnessForRender(partialTicks);
        final int j = i >> 16 & 0xFFFF;
        final int k = i & 0xFFFF;
        buffer.pos(f6 - rotationX * 0.5f - rotationXY * 0.5f, f7 - rotationZ * 0.5f, f8 - rotationYZ * 0.5f - rotationXZ * 0.5f).tex(f2, f4).color(this.particleRed, this.particleGreen, this.particleBlue, 1.0f).lightmap(j, k).endVertex();
        buffer.pos(f6 - rotationX * 0.5f + rotationXY * 0.5f, f7 + rotationZ * 0.5f, f8 - rotationYZ * 0.5f + rotationXZ * 0.5f).tex(f2, f3).color(this.particleRed, this.particleGreen, this.particleBlue, 1.0f).lightmap(j, k).endVertex();
        buffer.pos(f6 + rotationX * 0.5f + rotationXY * 0.5f, f7 + rotationZ * 0.5f, f8 + rotationYZ * 0.5f + rotationXZ * 0.5f).tex(f, f3).color(this.particleRed, this.particleGreen, this.particleBlue, 1.0f).lightmap(j, k).endVertex();
        buffer.pos(f6 + rotationX * 0.5f - rotationXY * 0.5f, f7 - rotationZ * 0.5f, f8 + rotationYZ * 0.5f - rotationXZ * 0.5f).tex(f, f4).color(this.particleRed, this.particleGreen, this.particleBlue, 1.0f).lightmap(j, k).endVertex();
    }
    
    public static class Factory implements IParticleFactory
    {
        @Override
        public Particle createParticle(final int particleID, final World worldIn, final double xCoordIn, final double yCoordIn, final double zCoordIn, final double xSpeedIn, final double ySpeedIn, final double zSpeedIn, final int... p_178902_15_) {
            return new Barrier(worldIn, xCoordIn, yCoordIn, zCoordIn, Item.getItemFromBlock(Blocks.BARRIER));
        }
    }
}
