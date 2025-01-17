// 
// Decompiled by Procyon v0.6.0
// 

package net.optifine.entity.model;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.src.Config;
import net.minecraft.src.Reflector;
import net.minecraft.client.renderer.tileentity.TileEntitySignRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.model.ModelSign;
import net.minecraft.client.model.ModelBase;
import net.minecraft.tileentity.TileEntitySign;

public class ModelAdapterSign extends ModelAdapter
{
    public ModelAdapterSign() {
        super(TileEntitySign.class, "sign", 0.0f);
    }
    
    @Override
    public ModelBase makeModel() {
        return new ModelSign();
    }
    
    @Override
    public ModelRenderer getModelRenderer(final ModelBase model, final String modelPart) {
        if (!(model instanceof ModelSign)) {
            return null;
        }
        final ModelSign modelsign = (ModelSign)model;
        if (modelPart.equals("board")) {
            return modelsign.signBoard;
        }
        return modelPart.equals("stick") ? modelsign.signStick : null;
    }
    
    @Override
    public IEntityRenderer makeEntityRender(final ModelBase modelBase, final float shadowSize) {
        final TileEntityRendererDispatcher tileentityrendererdispatcher = TileEntityRendererDispatcher.instance;
        TileEntitySpecialRenderer tileentityspecialrenderer = tileentityrendererdispatcher.getRenderer(TileEntitySign.class);
        if (!(tileentityspecialrenderer instanceof TileEntitySignRenderer)) {
            return null;
        }
        if (tileentityspecialrenderer.getEntityClass() == null) {
            tileentityspecialrenderer = new TileEntitySignRenderer();
            tileentityspecialrenderer.setRendererDispatcher(tileentityrendererdispatcher);
        }
        if (!Reflector.TileEntitySignRenderer_model.exists()) {
            Config.warn("Field not found: TileEntitySignRenderer.model");
            return null;
        }
        Reflector.setFieldValue(tileentityspecialrenderer, Reflector.TileEntitySignRenderer_model, modelBase);
        return tileentityspecialrenderer;
    }
}
