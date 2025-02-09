// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.module.render;

import de.paxii.clarinet.event.EventHandler;
import net.minecraft.entity.Entity;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.client.renderer.GlStateManager;
import de.paxii.clarinet.util.render.GL11Helper;
import org.lwjgl.opengl.GL11;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import de.paxii.clarinet.event.events.entity.PostRenderEntityEvent;
import de.paxii.clarinet.module.ModuleCategory;
import de.paxii.clarinet.module.Module;

public class ModuleMobESP extends Module
{
    public ModuleMobESP() {
        super("MobESP", ModuleCategory.RENDER, -1);
        this.setRegistered(true);
        this.setDescription("Draws a purple box around mobs and animals.");
    }
    
    @EventHandler
    public void renderESP(final PostRenderEntityEvent event) {
        final Entity entity = event.getRenderedEntity();
        if (!(entity instanceof EntityPlayer) && entity instanceof EntityLiving) {
            GL11.glPushMatrix();
            GL11Helper.enableDefaults();
            GlStateManager.depthMask(false);
            GlStateManager.disableTexture2D();
            GlStateManager.disableLighting();
            GlStateManager.disableCull();
            GlStateManager.disableBlend();
            GL11.glLineWidth(1.0f);
            final AxisAlignedBB var11 = entity.getEntityBoundingBox();
            final AxisAlignedBB var12 = new AxisAlignedBB(var11.minX - entity.posX + event.getX() - 0.2, var11.minY - entity.posY + event.getY(), var11.minZ - entity.posZ + event.getZ() - 0.2, var11.maxX - entity.posX + event.getX() + 0.2, var11.maxY - entity.posY + event.getY() + 0.2, var11.maxZ - entity.posZ + event.getZ() + 0.2);
            RenderGlobal.drawOutlinedBoundingBox(var12, 201, 4, 155, 255);
            GL11Helper.disableDefaults();
            GlStateManager.enableTexture2D();
            GlStateManager.enableLighting();
            GlStateManager.enableCull();
            GlStateManager.disableBlend();
            GlStateManager.depthMask(true);
            GL11.glPopMatrix();
        }
    }
}
