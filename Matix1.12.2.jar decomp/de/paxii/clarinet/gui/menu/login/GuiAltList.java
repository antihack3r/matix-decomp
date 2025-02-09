// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.gui.menu.login;

import java.util.function.Function;
import java.util.stream.Collector;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import net.minecraft.client.gui.GuiScreen;
import de.paxii.clarinet.Wrapper;
import java.util.ArrayList;
import net.minecraft.client.gui.GuiListExtended;

public class GuiAltList extends GuiListExtended
{
    private final ArrayList<GuiAltListEntry> entryList;
    
    public GuiAltList(final GuiAltManager altManager, final ArrayList<AltObject> alts) {
        super(Wrapper.getMinecraft(), Wrapper.getScaledResolution().getScaledWidth(), Wrapper.getScaledResolution().getScaledHeight(), 63, Wrapper.getScaledResolution().getScaledHeight() - 55, 20);
        this.entryList = alts.stream().map(alt -> new GuiAltListEntry(alt, altManager)).collect((Collector<? super Object, ?, ArrayList<GuiAltListEntry>>)Collectors.toCollection((Supplier<R>)ArrayList::new));
    }
    
    @Override
    public IGuiListEntry getListEntry(final int index) {
        return this.entryList.get(index);
    }
    
    @Override
    protected int getSize() {
        return this.entryList.size();
    }
    
    public ArrayList<AltObject> getAltObjects() {
        return this.entryList.stream().map((Function<? super Object, ?>)GuiAltListEntry::getAlt).collect((Collector<? super Object, ?, ArrayList<AltObject>>)Collectors.toCollection((Supplier<R>)ArrayList::new));
    }
    
    public ArrayList<GuiAltListEntry> getEntryList() {
        return this.entryList;
    }
}
