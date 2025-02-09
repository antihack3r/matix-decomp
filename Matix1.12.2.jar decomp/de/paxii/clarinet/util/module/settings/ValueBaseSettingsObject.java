// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.util.module.settings;

public class ValueBaseSettingsObject
{
    private float min;
    private float max;
    private float value;
    private String name;
    private boolean shouldRound;
    
    public ValueBaseSettingsObject(final ValueBase valueBase) {
        this.name = valueBase.getName();
        this.value = valueBase.getValue();
        this.min = valueBase.getMin();
        this.max = valueBase.getMax();
        this.shouldRound = valueBase.isShouldRound();
    }
    
    public float getMin() {
        return this.min;
    }
    
    public float getMax() {
        return this.max;
    }
    
    public float getValue() {
        return this.value;
    }
    
    public void setMin(final float min) {
        this.min = min;
    }
    
    public void setMax(final float max) {
        this.max = max;
    }
    
    public void setValue(final float value) {
        this.value = value;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    public boolean isShouldRound() {
        return this.shouldRound;
    }
    
    public void setShouldRound(final boolean shouldRound) {
        this.shouldRound = shouldRound;
    }
}
