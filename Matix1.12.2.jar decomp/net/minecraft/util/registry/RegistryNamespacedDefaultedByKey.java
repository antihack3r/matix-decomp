// 
// Decompiled by Procyon v0.6.0
// 

package net.minecraft.util.registry;

import java.util.Random;
import javax.annotation.Nullable;
import javax.annotation.Nonnull;
import org.apache.commons.lang3.Validate;

public class RegistryNamespacedDefaultedByKey<K, V> extends RegistryNamespaced<K, V>
{
    private final K defaultValueKey;
    private V defaultValue;
    
    public RegistryNamespacedDefaultedByKey(final K defaultValueKeyIn) {
        this.defaultValueKey = defaultValueKeyIn;
    }
    
    @Override
    public void register(final int id, final K key, final V value) {
        if (this.defaultValueKey.equals(key)) {
            this.defaultValue = value;
        }
        super.register(id, key, value);
    }
    
    public void validateKey() {
        Validate.notNull((Object)this.defaultValue, "Missing default of DefaultedMappedRegistry: " + this.defaultValueKey, new Object[0]);
    }
    
    @Override
    public int getIDForObject(final V value) {
        final int i = super.getIDForObject(value);
        return (i == -1) ? super.getIDForObject(this.defaultValue) : i;
    }
    
    @Nonnull
    @Override
    public K getNameForObject(final V value) {
        final K k = super.getNameForObject(value);
        final K i = (k == null) ? this.defaultValueKey : k;
        if (i == null) {
            $$$reportNull$$$0(0);
        }
        return i;
    }
    
    @Nonnull
    @Override
    public V getObject(@Nullable final K name) {
        final V v = super.getObject(name);
        final V v2 = (v == null) ? this.defaultValue : v;
        if (v2 == null) {
            $$$reportNull$$$0(1);
        }
        return v2;
    }
    
    @Nonnull
    @Override
    public V getObjectById(final int id) {
        final V v = super.getObjectById(id);
        final V v2 = (v == null) ? this.defaultValue : v;
        if (v2 == null) {
            $$$reportNull$$$0(2);
        }
        return v2;
    }
    
    @Nonnull
    @Override
    public V getRandomObject(final Random random) {
        final V v = super.getRandomObject(random);
        final V v2 = (v == null) ? this.defaultValue : v;
        if (v2 == null) {
            $$$reportNull$$$0(3);
        }
        return v2;
    }
    
    private static /* synthetic */ void $$$reportNull$$$0(final int n) {
        final String format = "@Nonnull method %s.%s must not return null";
        final Object[] args = { "net/minecraft/util/registry/RegistryNamespacedDefaultedByKey", null };
        switch (n) {
            default: {
                args[1] = "getNameForObject";
                break;
            }
            case 1: {
                args[1] = "getObject";
                break;
            }
            case 2: {
                args[1] = "getObjectById";
                break;
            }
            case 3: {
                args[1] = "getRandomObject";
                break;
            }
        }
        throw new IllegalStateException(String.format(format, args));
    }
}
