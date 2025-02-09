// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.util.alt;

import de.paxii.clarinet.gui.menu.login.AltObject;
import java.util.ArrayList;

public class AltContainer
{
    private ArrayList<AltObject> altList;
    
    public AltContainer(final ArrayList<AltObject> altList) {
        this.altList = altList;
    }
    
    public ArrayList<AltObject> getAltList() {
        return this.altList;
    }
    
    public void setAltList(final ArrayList<AltObject> altList) {
        this.altList = altList;
    }
}
