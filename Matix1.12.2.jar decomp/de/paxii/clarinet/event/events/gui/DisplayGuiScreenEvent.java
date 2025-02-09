// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.event.events.gui;

import net.minecraft.client.gui.GuiScreen;
import de.paxii.clarinet.event.events.type.EventCancellable;

public class DisplayGuiScreenEvent extends EventCancellable
{
    private GuiScreen guiScreen;
    
    public DisplayGuiScreenEvent(final GuiScreen guiScreen) {
        this.guiScreen = guiScreen;
    }
    
    public GuiScreen getGuiScreen() {
        return this.guiScreen;
    }
    
    public void setGuiScreen(final GuiScreen guiScreen) {
        this.guiScreen = guiScreen;
    }
}
