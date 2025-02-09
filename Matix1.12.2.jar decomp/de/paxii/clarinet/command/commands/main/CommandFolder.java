// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.command.commands.main;

import de.paxii.clarinet.command.CommandCategory;
import java.awt.Desktop;
import java.net.URI;
import java.io.IOException;
import de.paxii.clarinet.util.chat.Chat;
import java.io.File;
import de.paxii.clarinet.util.settings.ClientSettings;
import de.paxii.clarinet.command.AClientCommand;

public class CommandFolder extends AClientCommand
{
    @Override
    public String getCommand() {
        return "folder";
    }
    
    @Override
    public String getDescription() {
        return "Opens the Matix Base Folder";
    }
    
    @Override
    public void runCommand(final String[] args) {
        String relativePath = ClientSettings.getClientFolderPath().getValue();
        if (relativePath.startsWith("./")) {
            relativePath = relativePath.substring(2);
        }
        final URI uri = new File(relativePath).toURI();
        try {
            this.openWebLink(uri);
            Chat.printClientMessage("Opened \"" + relativePath + "\".");
        }
        catch (final IOException ioException) {
            Chat.printClientMessage("Couldn't open \"" + relativePath + "\".");
        }
    }
    
    private void openWebLink(final URI url) throws IOException {
        Desktop.getDesktop().browse(url);
    }
    
    @Override
    public String getUsage() {
        return "folder";
    }
    
    @Override
    public CommandCategory getCategory() {
        return CommandCategory.MAIN;
    }
}
