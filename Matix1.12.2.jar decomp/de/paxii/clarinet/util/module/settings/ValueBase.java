// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.util.module.settings;

import java.util.Iterator;
import java.util.ArrayList;

public class ValueBase implements Comparable<ValueBase>
{
    private static ArrayList<ValueBase> valueList;
    private float min;
    private float max;
    private float value;
    private String name;
    private String displayName;
    private boolean shouldRound;
    
    public ValueBase(final String name, final float current, final float min, final float max) {
        this(name, current, min, max, false);
    }
    
    public ValueBase(final String name, final float current, final float min, final float max, final String displayName) {
        this(name, current, min, max, false, displayName);
    }
    
    public ValueBase(final String name, final float current, final float min, final float max, final boolean shouldRound) {
        this(name, current, min, max, shouldRound, null);
    }
    
    public ValueBase(final String name, final float current, final float min, final float max, final boolean shouldRound, final String displayName) {
        this.name = name;
        this.value = current;
        this.min = min;
        this.max = max;
        this.shouldRound = shouldRound;
        this.displayName = displayName;
        if (!doesValueExist(this.getName())) {
            ValueBase.valueList.add(this);
        }
    }
    
    public static boolean doesValueExist(final String name) {
        for (final ValueBase vb : ValueBase.valueList) {
            if (vb.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }
    
    public static ValueBase getValueBase(final String name) {
        for (final ValueBase vb : ValueBase.valueList) {
            if (vb.getName().equals(name)) {
                return vb;
            }
        }
        return null;
    }
    
    public String getDisplayName() {
        return (this.displayName != null) ? this.displayName : this.name;
    }
    
    public void onUpdate(final float oldValue, final float newValue) {
    }
    
    public void setValue(final float value) {
        if (this.value != value) {
            this.onUpdate(this.value, value);
        }
        this.value = value;
    }
    
    @Override
    public int compareTo(final ValueBase vb) {
        return this.getName().compareToIgnoreCase(vb.getName());
    }
    
    public static ArrayList<ValueBase> getValueList() {
        return ValueBase.valueList;
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
    
    public String getName() {
        return this.name;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    public void setDisplayName(final String displayName) {
        this.displayName = displayName;
    }
    
    public boolean isShouldRound() {
        return this.shouldRound;
    }
    
    public void setShouldRound(final boolean shouldRound) {
        this.shouldRound = shouldRound;
    }
    
    static {
        ValueBase.valueList = new ArrayList<ValueBase>();
    }
}
