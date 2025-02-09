// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.event;

public class EventPriority
{
    public static final byte HIGHEST = 0;
    public static final byte HIGH = 1;
    public static final byte NORMAL = 2;
    public static final byte LOW = 3;
    public static final byte LOWEST = 4;
    
    public static int[] getValues() {
        return new int[] { 0, 1, 2, 3, 4 };
    }
    
    public String toString(final int eventPriority) {
        switch (eventPriority) {
            case 0: {
                return "Highest";
            }
            case 1: {
                return "High";
            }
            case 2: {
                return "Normal";
            }
            case 3: {
                return "Low";
            }
            case 4: {
                return "Lowest";
            }
            default: {
                return "Normal";
            }
        }
    }
}
