// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.gui.menu.login;

import de.paxii.clarinet.Wrapper;
import de.paxii.clarinet.util.render.GuiMethods;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiListExtended;

public class GuiAltListEntry implements GuiListExtended.IGuiListEntry
{
    private final GuiScreen parentScreen;
    private AltObject alt;
    private int x;
    private int y;
    private int width;
    private int height;
    
    public GuiAltListEntry(final AltObject alt, final GuiScreen parentScreen) {
        this.alt = alt;
        this.parentScreen = parentScreen;
    }
    
    @Override
    public void updatePosition(final int p_192633_1_, final int p_192633_2_, final int p_192633_3_, final float p_192633_4_) {
    }
    
    @Override
    public void drawEntry(final int slotIndex, final int x, final int y, final int listWidth, final int slotHeight, final int mouseX, final int mouseY, final boolean isSelected, final float partialTicks) {
        final GuiAltManager altManager = (GuiAltManager)this.parentScreen;
        this.x = x;
        this.y = y;
        this.width = listWidth;
        this.height = slotHeight;
        if (altManager.getPressedSlot() == this) {
            GuiMethods.drawBorderedRect(x, y, x + listWidth, y + slotHeight, 1.0f, -1, 0);
        }
        Wrapper.getFontRenderer().drawString(this.alt.getUserName(), x + 5, y + 5, 16777215);
        if (this.alt.getEmail().length() > 0) {
            Wrapper.getFontRenderer().drawString(", " + this.alt.getEmail(), x + Wrapper.getFontRenderer().getStringWidth(this.alt.getUserName()) + 5, y + 5, 16777215);
        }
    }
    
    @Override
    public boolean mousePressed(final int slotIndex, final int mouseX, final int mouseY, final int p_148278_4_, final int p_148278_5_, final int p_148278_6_) {
        if (mouseX >= this.x && mouseX <= this.x + this.width && mouseY >= this.y && mouseY <= this.y + this.height) {
            final GuiAltManager altManager = (GuiAltManager)this.parentScreen;
            altManager.setPressedSlot(this);
            return true;
        }
        return false;
    }
    
    @Override
    public void mouseReleased(final int mouseX, final int mouseY, final int var3, final int var4, final int var5, final int var6) {
    }
    
    public AltObject getAlt() {
        return this.alt;
    }
}
