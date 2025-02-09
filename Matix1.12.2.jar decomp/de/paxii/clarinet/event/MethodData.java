// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.event;

import java.lang.reflect.Method;

class MethodData
{
    private final Object source;
    private final Method target;
    private final int priority;
    
    public MethodData(final Object source, final Method target, final int i) {
        this.source = source;
        this.target = target;
        this.priority = i;
    }
    
    public Object getSource() {
        return this.source;
    }
    
    public Method getTarget() {
        return this.target;
    }
    
    public int getPriority() {
        return this.priority;
    }
}
