// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.command;

public enum CommandCategory
{
    MAIN("Main"), 
    MODULE("Module");
    
    private String name;
    
    private CommandCategory(final String name) {
        this.name = name;
    }
    
    public String getCategoryName() {
        return this.name;
    }
    
    @Override
    public String toString() {
        return this.name;
    }
    
    public String getName() {
        return this.name;
    }
}
