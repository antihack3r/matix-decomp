// 
// Decompiled by Procyon v0.6.0
// 

package net.minecraft.block.properties;

import com.google.common.base.MoreObjects;

public abstract class PropertyHelper<T extends Comparable<T>> implements IProperty<T>
{
    private final Class<T> valueClass;
    private final String name;
    
    protected PropertyHelper(final String name, final Class<T> valueClass) {
        this.valueClass = valueClass;
        this.name = name;
    }
    
    @Override
    public String getName() {
        return this.name;
    }
    
    @Override
    public Class<T> getValueClass() {
        return this.valueClass;
    }
    
    @Override
    public String toString() {
        return MoreObjects.toStringHelper((Object)this).add("name", (Object)this.name).add("clazz", (Object)this.valueClass).add("values", (Object)this.getAllowedValues()).toString();
    }
    
    @Override
    public boolean equals(final Object p_equals_1_) {
        if (this == p_equals_1_) {
            return true;
        }
        if (!(p_equals_1_ instanceof PropertyHelper)) {
            return false;
        }
        final PropertyHelper<?> propertyhelper = (PropertyHelper<?>)p_equals_1_;
        return this.valueClass.equals(propertyhelper.valueClass) && this.name.equals(propertyhelper.name);
    }
    
    @Override
    public int hashCode() {
        return 31 * this.valueClass.hashCode() + this.name.hashCode();
    }
}
