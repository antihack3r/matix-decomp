// 
// Decompiled by Procyon v0.6.0
// 

package net.minecraft.client.gui;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

public class GuiButtonImage extends GuiButton
{
    private final ResourceLocation resourceLocation;
    private final int xTexStart;
    private final int yTexStart;
    private final int yDiffText;
    
    public GuiButtonImage(final int p_i47392_1_, final int p_i47392_2_, final int p_i47392_3_, final int p_i47392_4_, final int p_i47392_5_, final int p_i47392_6_, final int p_i47392_7_, final int p_i47392_8_, final ResourceLocation p_i47392_9_) {
        super(p_i47392_1_, p_i47392_2_, p_i47392_3_, p_i47392_4_, p_i47392_5_, "");
        this.xTexStart = p_i47392_6_;
        this.yTexStart = p_i47392_7_;
        this.yDiffText = p_i47392_8_;
        this.resourceLocation = p_i47392_9_;
    }
    
    public void setPosition(final int p_191746_1_, final int p_191746_2_) {
        this.x = p_191746_1_;
        this.y = p_191746_2_;
    }
    
    @Override
    public void drawButton(final Minecraft mc, final int mouseX, final int mouseY, final float partialTicks) {
        if (this.visible) {
            this.hovered = (mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height);
            mc.getTextureManager().bindTexture(this.resourceLocation);
            GlStateManager.disableDepth();
            final int i = this.xTexStart;
            int j = this.yTexStart;
            if (this.hovered) {
                j += this.yDiffText;
            }
            this.drawTexturedModalRect(this.x, this.y, i, j, this.width, this.height);
            GlStateManager.enableDepth();
        }
    }
}
