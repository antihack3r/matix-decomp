// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.event.events.gui.sign;

import java.util.Arrays;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraft.util.text.ITextComponent;
import de.paxii.clarinet.event.events.Event;

public class StopEditSignEvent implements Event
{
    private ITextComponent[] signText;
    private int editLine;
    private boolean closeGui;
    
    public StopEditSignEvent(final TileEntitySign tileEntitySign) {
        this.signText = tileEntitySign.signText;
    }
    
    public ITextComponent[] getSignText() {
        return this.signText;
    }
    
    public int getEditLine() {
        return this.editLine;
    }
    
    public boolean isCloseGui() {
        return this.closeGui;
    }
    
    public void setSignText(final ITextComponent[] signText) {
        this.signText = signText;
    }
    
    public void setEditLine(final int editLine) {
        this.editLine = editLine;
    }
    
    public void setCloseGui(final boolean closeGui) {
        this.closeGui = closeGui;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof StopEditSignEvent)) {
            return false;
        }
        final StopEditSignEvent other = (StopEditSignEvent)o;
        return other.canEqual(this) && Arrays.deepEquals(this.getSignText(), other.getSignText()) && this.getEditLine() == other.getEditLine() && this.isCloseGui() == other.isCloseGui();
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof StopEditSignEvent;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * 59 + Arrays.deepHashCode(this.getSignText());
        result = result * 59 + this.getEditLine();
        result = result * 59 + (this.isCloseGui() ? 79 : 97);
        return result;
    }
    
    @Override
    public String toString() {
        return "StopEditSignEvent(signText=" + Arrays.deepToString(this.getSignText()) + ", editLine=" + this.getEditLine() + ", closeGui=" + this.isCloseGui() + ")";
    }
}
