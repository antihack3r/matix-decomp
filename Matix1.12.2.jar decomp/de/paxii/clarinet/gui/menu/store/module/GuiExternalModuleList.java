// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.gui.menu.store.module;

import java.util.Iterator;
import de.paxii.clarinet.util.module.store.ModuleEntry;
import java.util.Map;
import de.paxii.clarinet.util.module.store.ModuleStore;
import de.paxii.clarinet.Wrapper;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiListExtended;

public class GuiExternalModuleList extends GuiListExtended
{
    private final IGuiListEntry[] keyEntries;
    
    public GuiExternalModuleList(final GuiScreen parentScreen) {
        super(Wrapper.getMinecraft(), Wrapper.getScaledResolution().getScaledWidth(), Wrapper.getScaledResolution().getScaledHeight(), 63, Wrapper.getScaledResolution().getScaledHeight() - 32, 20);
        this.keyEntries = new IGuiListEntry[ModuleStore.listModules().size()];
        int index = 0;
        for (final Map.Entry<String, ModuleEntry> moduleEntry : ModuleStore.listModules().entrySet()) {
            this.keyEntries[index] = new GuiListEntryExternalModule(moduleEntry.getValue(), parentScreen);
            ++index;
        }
        this.slotHeight = 75;
    }
    
    @Override
    public IGuiListEntry getListEntry(final int index) {
        return this.keyEntries[index];
    }
    
    @Override
    protected int getSize() {
        return this.keyEntries.length;
    }
}
