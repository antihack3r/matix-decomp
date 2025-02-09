// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.command;

public interface IClientCommand
{
    String getCommand();
    
    String getDescription();
    
    void runCommand(final String[] p0);
    
    String getUsage();
    
    CommandCategory getCategory();
}
