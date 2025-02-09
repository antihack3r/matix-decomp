// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.util.objects;

public class IntObject
{
    private int integer;
    
    public IntObject(final int integer) {
        this.integer = integer;
    }
    
    public IntObject add(final int integer) {
        this.integer += integer;
        return this;
    }
    
    public IntObject substract(final int integer) {
        this.integer -= integer;
        return this;
    }
    
    public IntObject multiply(final int integer) {
        this.integer *= integer;
        return this;
    }
    
    public IntObject divide(final int integer) {
        this.integer /= integer;
        return this;
    }
    
    public int getInteger() {
        return this.integer;
    }
    
    public void setInteger(final int integer) {
        this.integer = integer;
    }
}
