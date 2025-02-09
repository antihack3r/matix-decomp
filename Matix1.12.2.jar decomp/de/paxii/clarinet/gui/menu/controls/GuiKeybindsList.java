// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.gui.menu.controls;

import java.util.Iterator;
import java.util.Map;
import de.paxii.clarinet.module.Module;
import java.util.TreeMap;
import de.paxii.clarinet.Wrapper;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiListExtended;

public class GuiKeybindsList extends GuiListExtended
{
    private final GuiScreen parentScreen;
    private final IGuiListEntry[] keyEntries;
    
    public GuiKeybindsList(final GuiScreen parentScreen) {
        super(Wrapper.getMinecraft(), Wrapper.getScaledResolution().getScaledWidth(), Wrapper.getScaledResolution().getScaledHeight(), 63, Wrapper.getScaledResolution().getScaledHeight() - 32, 20);
        this.parentScreen = parentScreen;
        this.keyEntries = new IGuiListEntry[Wrapper.getModuleManager().getModuleList().size()];
        int index = 0;
        final TreeMap<String, Module> sortedModules = new TreeMap<String, Module>(Wrapper.getModuleManager().getModuleList());
        for (final Map.Entry<String, Module> moduleEntry : sortedModules.entrySet()) {
            this.keyEntries[index] = new GuiListEntryModuleKey(moduleEntry.getValue(), parentScreen);
            ++index;
        }
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
