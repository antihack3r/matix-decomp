// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.module.render;

import net.minecraft.util.math.RayTraceResult;
import net.minecraft.item.ItemStack;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;
import net.minecraft.client.renderer.GlStateManager;
import de.paxii.clarinet.util.render.GL11Helper;
import org.lwjgl.opengl.GL11;
import net.minecraft.util.math.MathHelper;
import net.minecraft.item.Item;
import de.paxii.clarinet.Wrapper;
import de.paxii.clarinet.event.EventHandler;
import de.paxii.clarinet.event.events.game.RenderTickEvent;
import de.paxii.clarinet.module.ModuleCategory;
import de.paxii.clarinet.module.Module;

public class ModuleProjectiles extends Module
{
    public ModuleProjectiles() {
        super("Projectiles", ModuleCategory.RENDER, -1);
        this.setRegistered(true);
        this.setDescription("Predicts where projectiles will land. (probably not going to be correct)");
    }
    
    @EventHandler
    public void onRenderTick(final RenderTickEvent event) {
        this.draw();
    }
    
    private void draw() {
        final ItemStack is = Wrapper.getPlayer().inventory.getCurrentItem();
        if (is != null) {
            final int id = Item.getIdFromItem(is.getItem());
            boolean drawPrediction = true;
            float var5 = 3.2f;
            float var6 = 0.0f;
            if (id == 261) {
                var5 = 5.4f;
            }
            else if (id == 368) {
                var5 = 1.5f;
            }
            else if (id == 332) {
                var5 = 1.5f;
            }
            else if (id == 346) {
                var5 = 1.5f;
            }
            else if (id == 344) {
                var5 = 1.5f;
            }
            else if ((is.getItemDamage() & 0x4000) > 0) {
                var5 = 0.5f;
                var6 = -20.0f;
            }
            else {
                drawPrediction = false;
            }
            if (drawPrediction) {
                final float var7 = (float)Wrapper.getPlayer().posX;
                final float var8 = (float)Wrapper.getPlayer().getEntityBoundingBox().minY + 1.5f;
                final float var9 = (float)Wrapper.getPlayer().posZ;
                final float var10 = Wrapper.getPlayer().rotationYaw;
                final float var11 = Wrapper.getPlayer().rotationPitch;
                final float var12 = var10 / 180.0f * 3.1415927f;
                final float var13 = MathHelper.sin(var12);
                final float var14 = MathHelper.cos(var12);
                final float var15 = var11 / 180.0f * 3.1415927f;
                final float var16 = MathHelper.cos(var15);
                final float var17 = (var11 + var6) / 180.0f * 3.1415927f;
                final float var18 = MathHelper.sin(var17);
                float var19 = var7 - var14 * 0.16f;
                float var20 = var8 - 0.1f;
                float var21 = var9 - var13 * 0.16f;
                float var22 = -var13 * var16 * 0.4f;
                float var23 = -var18 * 0.4f;
                float var24 = var14 * var16 * 0.4f;
                final float var25 = MathHelper.sqrt_double(var22 * var22 + var23 * var23 + var24 * var24);
                var22 /= var25;
                var23 /= var25;
                var24 /= var25;
                var22 *= var5;
                var23 *= var5;
                var24 *= var5;
                float var26 = var19;
                float var27 = var20;
                float var28 = var21;
                GL11.glPushMatrix();
                GL11.glColor4f(255.0f, 0.0f, 0.0f, 1.0f);
                GL11Helper.enableDefaults();
                GL11.glDisable(2929);
                GL11.glDepthMask(false);
                GlStateManager.depthMask(false);
                GL11.glBegin(1);
                int var29 = 0;
                boolean var30 = true;
                while (var30) {
                    ++var29;
                    var26 += var22;
                    var27 += var23;
                    var28 += var24;
                    final Vec3d var31 = new Vec3d(var19, var20, var21);
                    final Vec3d var32 = new Vec3d(var26, var27, var28);
                    final RayTraceResult var33 = Wrapper.getWorld().rayTraceBlocks(var31, var32);
                    if (var33 != null) {
                        var19 = (float)var33.hitVec.x;
                        var20 = (float)var33.hitVec.y;
                        var21 = (float)var33.hitVec.z;
                        var30 = false;
                    }
                    else if (var29 > 200) {
                        var30 = false;
                    }
                    else {
                        this.drawLine3D(var19 - var7, var20 - var8, var21 - var9, var26 - var7, var27 - var8, var28 - var9);
                        final AxisAlignedBB var34 = new AxisAlignedBB(var19 - 0.125f, var20, var21 - 0.125f, var19 + 0.125f, var20 + 0.25f, var21 + 0.125f);
                        float var35 = 0.0f;
                        for (int var36 = 0; var36 < 5; ++var36) {
                            final float var37 = (float)(var34.minY + (var34.maxY - var34.minY) * var36 / 5.0);
                            final float var38 = (float)(var34.minY + (var34.maxY - var34.minY) * (var36 + 1) / 5.0);
                            final AxisAlignedBB var39 = new AxisAlignedBB(var34.minX, var37, var34.minZ, var34.maxX, var38, var34.maxZ);
                            if (Wrapper.getWorld().isAABBInMaterial(var39, Material.WATER)) {
                                var35 += 0.2f;
                            }
                        }
                        final float var40 = var35 * 2.0f - 1.0f;
                        var23 += 0.04f * var40;
                        float var37 = 0.92f;
                        if (var35 > 0.0f) {
                            var37 *= 0.9f;
                            var23 *= 0.8f;
                        }
                        var22 *= var37;
                        var23 *= var37;
                        var24 *= var37;
                        var19 = var26;
                        var20 = var27;
                        var21 = var28;
                    }
                }
                GL11.glEnd();
                GlStateManager.depthMask(true);
                GL11.glDepthMask(true);
                GL11.glEnable(2929);
                GL11Helper.disableDefaults();
                GL11.glPopMatrix();
            }
        }
    }
    
    private void drawLine3D(final float var1, final float var2, final float var3, final float var4, final float var5, final float var6) {
        GL11.glVertex3d((double)var1, (double)var2, (double)var3);
        GL11.glVertex3d((double)var4, (double)var5, (double)var6);
    }
}
