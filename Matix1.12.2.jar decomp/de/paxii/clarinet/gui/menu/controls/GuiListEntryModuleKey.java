// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.gui.menu.controls;

import org.lwjgl.input.Keyboard;
import de.paxii.clarinet.Wrapper;
import de.paxii.clarinet.module.Module;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiListExtended;

public class GuiListEntryModuleKey implements GuiListExtended.IGuiListEntry
{
    private final GuiScreen parentScreen;
    private Module module;
    private int x;
    private int y;
    private int width;
    private int height;
    
    public GuiListEntryModuleKey(final Module module, final GuiScreen parentScreen) {
        this.module = module;
        this.parentScreen = parentScreen;
    }
    
    @Override
    public void updatePosition(final int p_192633_1_, final int p_192633_2_, final int p_192633_3_, final float p_192633_4_) {
    }
    
    @Override
    public void drawEntry(final int slotIndex, final int x, final int y, final int listWidth, final int slotHeight, final int mouseX, final int mouseY, final boolean isSelected, final float partialTicks) {
        final GuiKeybinds guiKeybinds = (GuiKeybinds)this.parentScreen;
        if (isSelected) {
            guiKeybinds.setSelectedButton(this);
        }
        this.x = x;
        this.y = y;
        this.width = listWidth;
        this.height = slotHeight;
        Wrapper.getFontRenderer().drawString(this.getModule().getName(), x + 5, y + 5, 16777215);
        String keyString = "NONE";
        if (this.getModule().getKey() != -1) {
            if (this.getModule().getKey() >= 0) {
                keyString = Keyboard.getKeyName(this.getModule().getKey());
            }
            else {
                keyString = "Mouse " + (this.getModule().getKey() + 101);
            }
        }
        if (guiKeybinds.isShouldListen() && guiKeybinds.getPressedButton() != null && guiKeybinds.getPressedButton().getModule().getName().equals(this.getModule().getName())) {
            keyString = "Press a key";
            Wrapper.getFontRenderer().drawString(keyString, x + listWidth - Wrapper.getFontRenderer().getStringWidth(keyString), y + 5, 16246528);
        }
        else {
            Wrapper.getFontRenderer().drawString(keyString, x + listWidth - Wrapper.getFontRenderer().getStringWidth(keyString), y + 5, 16777215);
        }
    }
    
    @Override
    public boolean mousePressed(final int slotIndex, final int mouseX, final int mouseY, final int p_148278_4_, final int p_148278_5_, final int p_148278_6_) {
        if (mouseX >= this.x && mouseX <= this.x + this.width && mouseY >= this.y && mouseY <= this.y + this.height) {
            final GuiKeybinds guiKeybinds = (GuiKeybinds)this.parentScreen;
            guiKeybinds.setPressedButton(this);
            guiKeybinds.setShouldListen(true);
            return true;
        }
        return false;
    }
    
    @Override
    public void mouseReleased(final int mouseX, final int mouseY, final int var3, final int var4, final int var5, final int var6) {
    }
    
    public Module getModule() {
        return this.module;
    }
}
