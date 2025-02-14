// 
// Decompiled by Procyon v0.6.0
// 

package net.minecraft.src;

import java.util.Iterator;
import java.io.IOException;
import net.minecraft.client.gui.GuiButton;
import java.util.Collection;
import net.minecraft.client.gui.GuiOptionButton;
import net.minecraft.client.resources.I18n;
import com.google.common.collect.Lists;
import java.util.List;
import net.minecraft.client.gui.GuiScreen;

public class GuiMessage extends GuiScreen
{
    private GuiScreen parentScreen;
    private String messageLine1;
    private String messageLine2;
    private final List<String> listLines2;
    protected String confirmButtonText;
    private int ticksUntilEnable;
    
    public GuiMessage(final GuiScreen p_i45_1_, final String p_i45_2_, final String p_i45_3_) {
        this.listLines2 = Lists.newArrayList();
        this.parentScreen = p_i45_1_;
        this.messageLine1 = p_i45_2_;
        this.messageLine2 = p_i45_3_;
        this.confirmButtonText = I18n.format("gui.done", new Object[0]);
    }
    
    @Override
    public void initGui() {
        this.buttonList.add(new GuiOptionButton(0, this.width / 2 - 74, this.height / 6 + 96, this.confirmButtonText));
        this.listLines2.clear();
        this.listLines2.addAll(this.fontRenderer.listFormattedStringToWidth(this.messageLine2, this.width - 50));
    }
    
    @Override
    protected void actionPerformed(final GuiButton button) throws IOException {
        Config.getMinecraft().displayGuiScreen(this.parentScreen);
    }
    
    @Override
    public void drawScreen(final int mouseX, final int mouseY, final float partialTicks) {
        this.drawDefaultBackground();
        this.drawCenteredString(this.fontRenderer, this.messageLine1, this.width / 2, 70, 16777215);
        int i = 90;
        for (final String s : this.listLines2) {
            this.drawCenteredString(this.fontRenderer, s, this.width / 2, i, 16777215);
            i += this.fontRenderer.FONT_HEIGHT;
        }
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
    
    public void setButtonDelay(final int p_setButtonDelay_1_) {
        this.ticksUntilEnable = p_setButtonDelay_1_;
        for (final GuiButton guibutton : this.buttonList) {
            guibutton.enabled = false;
        }
    }
    
    @Override
    public void updateScreen() {
        super.updateScreen();
        final int ticksUntilEnable = this.ticksUntilEnable - 1;
        this.ticksUntilEnable = ticksUntilEnable;
        if (ticksUntilEnable == 0) {
            for (final GuiButton guibutton : this.buttonList) {
                guibutton.enabled = true;
            }
        }
    }
}
