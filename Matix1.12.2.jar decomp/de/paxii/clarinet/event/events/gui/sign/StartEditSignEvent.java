// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.event.events.gui.sign;

import java.util.Arrays;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.tileentity.TileEntitySign;
import de.paxii.clarinet.event.events.Event;

public class StartEditSignEvent implements Event
{
    private TileEntitySign tileEntitySign;
    private ITextComponent[] signText;
    private int editLine;
    private boolean closeGui;
    
    public StartEditSignEvent(final TileEntitySign tileEntitySign) {
        this.tileEntitySign = tileEntitySign;
        this.signText = tileEntitySign.signText;
    }
    
    public TileEntitySign getTileEntitySign() {
        return this.tileEntitySign;
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
    
    public void setTileEntitySign(final TileEntitySign tileEntitySign) {
        this.tileEntitySign = tileEntitySign;
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
        if (!(o instanceof StartEditSignEvent)) {
            return false;
        }
        final StartEditSignEvent other = (StartEditSignEvent)o;
        if (!other.canEqual(this)) {
            return false;
        }
        final Object this$tileEntitySign = this.getTileEntitySign();
        final Object other$tileEntitySign = other.getTileEntitySign();
        if (this$tileEntitySign == null) {
            if (other$tileEntitySign == null) {
                return Arrays.deepEquals(this.getSignText(), other.getSignText()) && this.getEditLine() == other.getEditLine() && this.isCloseGui() == other.isCloseGui();
            }
        }
        else if (this$tileEntitySign.equals(other$tileEntitySign)) {
            return Arrays.deepEquals(this.getSignText(), other.getSignText()) && this.getEditLine() == other.getEditLine() && this.isCloseGui() == other.isCloseGui();
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof StartEditSignEvent;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $tileEntitySign = this.getTileEntitySign();
        result = result * 59 + (($tileEntitySign == null) ? 43 : $tileEntitySign.hashCode());
        result = result * 59 + Arrays.deepHashCode(this.getSignText());
        result = result * 59 + this.getEditLine();
        result = result * 59 + (this.isCloseGui() ? 79 : 97);
        return result;
    }
    
    @Override
    public String toString() {
        return "StartEditSignEvent(tileEntitySign=" + this.getTileEntitySign() + ", signText=" + Arrays.deepToString(this.getSignText()) + ", editLine=" + this.getEditLine() + ", closeGui=" + this.isCloseGui() + ")";
    }
}
