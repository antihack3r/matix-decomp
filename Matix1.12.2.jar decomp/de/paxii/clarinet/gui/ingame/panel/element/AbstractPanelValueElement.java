// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.gui.ingame.panel.element;

public abstract class AbstractPanelValueElement<T> extends PanelElement
{
    protected T value;
    
    public void setValue(final T newValue) {
        if (this.value != newValue || !this.value.equals(newValue)) {
            this.onUpdate(newValue, this.value);
        }
        this.value = newValue;
    }
    
    public void onUpdate(final T newValue, final T oldValue) {
    }
    
    public T getValue() {
        return this.value;
    }
}
