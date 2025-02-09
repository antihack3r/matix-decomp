// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.module.render;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.Entity;
import de.paxii.clarinet.event.events.entity.PostRenderEntityEvent;
import de.paxii.clarinet.event.EventHandler;
import de.paxii.clarinet.Wrapper;
import net.minecraft.client.renderer.GlStateManager;
import de.paxii.clarinet.event.events.entity.PreRenderEntityEvent;
import de.paxii.clarinet.module.ModuleCategory;
import de.paxii.clarinet.module.Module;

public class ModuleWallhack extends Module
{
    public ModuleWallhack() {
        super("Wallhack", ModuleCategory.RENDER, -1);
        this.setRegistered(true);
        this.setDescription("Renders entities through walls.");
    }
    
    @EventHandler
    public void preRender(final PreRenderEntityEvent event) {
        if (this.useWallhack(event.getRenderedEntity())) {
            GlStateManager.disableDepth();
            GlStateManager.depthMask(false);
            Wrapper.getRenderer().disableLightmap();
        }
    }
    
    @EventHandler
    public void postRender(final PostRenderEntityEvent event) {
        if (this.useWallhack(event.getRenderedEntity())) {
            Wrapper.getRenderer().enableLightmap();
            GlStateManager.enableDepth();
            GlStateManager.depthMask(true);
        }
    }
    
    private boolean useWallhack(final Entity entityIn) {
        return entityIn instanceof EntityLivingBase || entityIn instanceof EntityItem;
    }
}
