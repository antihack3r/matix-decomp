// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.command.commands.main;

import de.paxii.clarinet.command.CommandCategory;
import de.paxii.clarinet.util.chat.Chat;
import de.paxii.clarinet.util.player.capesapi.CapesApi;
import de.paxii.clarinet.command.AClientCommand;

public class CommandReloadCapes extends AClientCommand
{
    @Override
    public String getCommand() {
        return "reloadcapes";
    }
    
    @Override
    public String getDescription() {
        return "Reload Capes";
    }
    
    @Override
    public void runCommand(final String[] args) {
        CapesApi.reset();
        Chat.printClientMessage("Capes were reloaded.");
    }
    
    @Override
    public String getUsage() {
        return null;
    }
    
    @Override
    public CommandCategory getCategory() {
        return null;
    }
}
