// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.command;

public abstract class AClientCommand implements IClientCommand, Comparable<AClientCommand>
{
    @Override
    public int compareTo(final AClientCommand command) {
        return this.getCommand().compareToIgnoreCase(command.getCommand());
    }
}
