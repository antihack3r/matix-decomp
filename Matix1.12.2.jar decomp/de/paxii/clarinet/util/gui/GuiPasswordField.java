// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.util.gui;

import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiTextField;

public class GuiPasswordField extends GuiTextField
{
    public GuiPasswordField(final int componentId, final FontRenderer fontrendererObj, final int x, final int y, final int par5Width, final int par6Height) {
        super(componentId, fontrendererObj, x, y, par5Width, par6Height);
    }
    
    @Override
    public void drawTextBox() {
        if (this.getVisible()) {
            if (this.getEnableBackgroundDrawing()) {
                Gui.drawRect(this.x - 1, this.y - 1, this.x + this.width + 1, this.y + this.height + 1, -6250336);
                Gui.drawRect(this.x, this.y, this.x + this.width, this.y + this.height, -16777216);
            }
            final int var1 = this.isEnabled ? this.enabledColor : this.disabledColor;
            final int var2 = this.cursorPosition - this.lineScrollOffset;
            int var3 = this.selectionEnd - this.lineScrollOffset;
            final String text = this.text.substring(this.lineScrollOffset);
            String hidden = "";
            for (int i = 0; i < text.length(); ++i) {
                hidden += "*";
            }
            final String var4 = this.fontRenderer.trimStringToWidth(hidden, this.getWidth());
            final boolean var5 = var2 >= 0 && var2 <= var4.length();
            final boolean var6 = this.isFocused && this.cursorCounter / 6 % 2 == 0 && var5;
            final int var7 = this.enableBackgroundDrawing ? (this.x + 4) : this.x;
            final int var8 = this.enableBackgroundDrawing ? (this.y + (this.height - 8) / 2) : this.y;
            int var9 = var7;
            if (var3 > var4.length()) {
                var3 = var4.length();
            }
            if (var4.length() > 0) {
                final String var10 = var5 ? var4.substring(0, var2) : var4;
                var9 = this.fontRenderer.drawStringWithShadow(var10, (float)var7, (float)var8, var1);
            }
            final boolean var11 = this.cursorPosition < this.text.length() || this.text.length() >= this.getMaxStringLength();
            int var12 = var9;
            if (!var5) {
                var12 = ((var2 > 0) ? (var7 + this.width) : var7);
            }
            else if (var11) {
                var12 = var9 - 1;
                --var9;
            }
            if (var4.length() > 0 && var5 && var2 < var4.length()) {
                this.fontRenderer.drawStringWithShadow(var4.substring(var2), (float)var9, (float)var8, var1);
            }
            if (var6) {
                if (var11) {
                    Gui.drawRect(var12, var8 - 1, var12 + 1, var8 + 1 + this.fontRenderer.FONT_HEIGHT, -3092272);
                }
                else {
                    this.fontRenderer.drawStringWithShadow("_", (float)var12, (float)var8, var1);
                }
            }
            if (var3 != var2) {
                final int var13 = var7 + this.fontRenderer.getStringWidth(var4.substring(0, var3));
                this.drawSelectionBox(var12, var8 - 1, var13 - 1, var8 + 1 + this.fontRenderer.FONT_HEIGHT);
            }
        }
    }
}
