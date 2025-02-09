// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.util.function;

@FunctionalInterface
public interface TwoArgumentsFunction<R, T, A>
{
    R apply(final T p0, final A p1);
}
