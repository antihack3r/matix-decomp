// 
// Decompiled by Procyon v0.6.0
// 

package net.minecraft.util.registry;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class RegistryDefaulted<K, V> extends RegistrySimple<K, V>
{
    private final V defaultObject;
    
    public RegistryDefaulted(final V defaultObjectIn) {
        this.defaultObject = defaultObjectIn;
    }
    
    @Nonnull
    @Override
    public V getObject(@Nullable final K name) {
        final V v = super.getObject(name);
        final V v2 = (v == null) ? this.defaultObject : v;
        if (v2 == null) {
            $$$reportNull$$$0(0);
        }
        return v2;
    }
    
    private static /* synthetic */ void $$$reportNull$$$0(final int n) {
        throw new IllegalStateException(String.format("@Nonnull method %s.%s must not return null", "net/minecraft/util/registry/RegistryDefaulted", "getObject"));
    }
}
