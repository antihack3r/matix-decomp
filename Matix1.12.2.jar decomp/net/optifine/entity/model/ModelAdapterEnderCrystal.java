// 
// Decompiled by Procyon v0.6.0
// 

package net.optifine.entity.model;

import net.minecraft.entity.Entity;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.src.Config;
import net.minecraft.client.renderer.entity.RenderEnderCrystal;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.Minecraft;
import net.minecraft.src.Reflector;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.model.ModelEnderCrystal;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.item.EntityEnderCrystal;

public class ModelAdapterEnderCrystal extends ModelAdapter
{
    public ModelAdapterEnderCrystal() {
        this("end_crystal");
    }
    
    protected ModelAdapterEnderCrystal(final String name) {
        super(EntityEnderCrystal.class, name, 0.5f);
    }
    
    @Override
    public ModelBase makeModel() {
        return new ModelEnderCrystal(0.0f, true);
    }
    
    @Override
    public ModelRenderer getModelRenderer(final ModelBase model, final String modelPart) {
        if (!(model instanceof ModelEnderCrystal)) {
            return null;
        }
        final ModelEnderCrystal modelendercrystal = (ModelEnderCrystal)model;
        if (modelPart.equals("cube")) {
            return (ModelRenderer)Reflector.getFieldValue(modelendercrystal, Reflector.ModelEnderCrystal_ModelRenderers, 0);
        }
        if (modelPart.equals("glass")) {
            return (ModelRenderer)Reflector.getFieldValue(modelendercrystal, Reflector.ModelEnderCrystal_ModelRenderers, 1);
        }
        return modelPart.equals("base") ? ((ModelRenderer)Reflector.getFieldValue(modelendercrystal, Reflector.ModelEnderCrystal_ModelRenderers, 2)) : null;
    }
    
    @Override
    public IEntityRenderer makeEntityRender(final ModelBase modelBase, final float shadowSize) {
        final RenderManager rendermanager = Minecraft.getMinecraft().getRenderManager();
        final Render render = rendermanager.getEntityRenderMap().get(EntityEnderCrystal.class);
        if (!(render instanceof RenderEnderCrystal)) {
            Config.warn("Not an instance of RenderEnderCrystal: " + render);
            return null;
        }
        final RenderEnderCrystal renderendercrystal = (RenderEnderCrystal)render;
        if (!Reflector.RenderEnderCrystal_modelEnderCrystal.exists()) {
            Config.warn("Field not found: RenderEnderCrystal.modelEnderCrystal");
            return null;
        }
        Reflector.setFieldValue(renderendercrystal, Reflector.RenderEnderCrystal_modelEnderCrystal, modelBase);
        renderendercrystal.shadowSize = shadowSize;
        return renderendercrystal;
    }
}
