// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.gui.menu.store.module;

import de.paxii.clarinet.Wrapper;
import de.paxii.clarinet.util.module.store.ModuleStore;
import de.paxii.clarinet.util.render.GuiMethods;
import de.paxii.clarinet.util.module.store.ModuleEntry;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiListExtended;

public class GuiListEntryExternalModule implements GuiListExtended.IGuiListEntry
{
    private final GuiScreen parentScreen;
    private ModuleEntry moduleEntry;
    private int x;
    private int y;
    private int width;
    private int height;
    
    public GuiListEntryExternalModule(final ModuleEntry moduleEntry, final GuiScreen parentScreen) {
        this.moduleEntry = moduleEntry;
        this.parentScreen = parentScreen;
    }
    
    @Override
    public void updatePosition(final int p_192633_1_, final int p_192633_2_, final int p_192633_3_, final float p_192633_4_) {
    }
    
    @Override
    public void drawEntry(final int slotIndex, final int x, final int y, final int listWidth, final int slotHeight, final int mouseX, final int mouseY, final boolean isSelected, final float partialTicks) {
        final GuiModuleStore guiModuleStore = (GuiModuleStore)this.parentScreen;
        if (isSelected) {
            guiModuleStore.setSelectedButton(this);
        }
        this.x = x;
        this.y = y;
        this.width = listWidth;
        this.height = slotHeight;
        int background = 0;
        final String compatible = this.getModuleEntry().getCompatible();
        switch (compatible) {
            case "may": {
                background = 1726506775;
                break;
            }
            case "no": {
                background = 1725632271;
                break;
            }
        }
        if (guiModuleStore.getPressedButton() != null && guiModuleStore.getPressedButton().getModuleEntry().getModule().equals(this.getModuleEntry().getModule())) {
            GuiMethods.drawBorderedRect(this.x, this.y, this.x + this.width, this.y + this.height, 2.0f, -30720, background);
        }
        else {
            int color = -1;
            if (ModuleStore.isModuleInstalled(this.getModuleEntry().getModule())) {
                color = -16711936;
            }
            if (ModuleStore.isModuleInstalled(this.getModuleEntry().getModule()) && !ModuleStore.isModuleUptoDate(this.getModuleEntry().getModule())) {
                color = -65536;
            }
            GuiMethods.drawBorderedRect(this.x, this.y, this.x + this.width, this.y + this.height, 1.0f, color, background);
        }
        final String displayName = this.getModuleEntry().getModule() + ((this.getModuleEntry().getVersion() != null) ? (" - V" + this.getModuleEntry().getVersion()) : "");
        Wrapper.getFontRenderer().drawString(displayName, x + 5, y + 5, 16777215);
        Wrapper.getFontRenderer().drawSplitString(this.getModuleEntry().getDescription(), x + 5, y + 15, this.width - 5, 8947848);
    }
    
    @Override
    public boolean mousePressed(final int slotIndex, final int mouseX, final int mouseY, final int p_148278_4_, final int p_148278_5_, final int p_148278_6_) {
        if (mouseX >= this.x && mouseX <= this.x + this.width && mouseY >= this.y && mouseY <= this.y + this.height) {
            final GuiModuleStore guiModuleStore = (GuiModuleStore)this.parentScreen;
            guiModuleStore.setPressedButton(this);
            return true;
        }
        return false;
    }
    
    @Override
    public void mouseReleased(final int mouseX, final int mouseY, final int var3, final int var4, final int var5, final int var6) {
    }
    
    public ModuleEntry getModuleEntry() {
        return this.moduleEntry;
    }
}
