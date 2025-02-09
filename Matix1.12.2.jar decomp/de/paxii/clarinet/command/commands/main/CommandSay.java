// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.command.commands.main;

import de.paxii.clarinet.command.CommandCategory;
import de.paxii.clarinet.util.chat.Chat;
import de.paxii.clarinet.command.AClientCommand;

public class CommandSay extends AClientCommand
{
    @Override
    public String getCommand() {
        return "say";
    }
    
    @Override
    public String getDescription() {
        return "Send a Message to the Server";
    }
    
    @Override
    public void runCommand(final String[] args) {
        if (args.length > 0) {
            final StringBuilder stringBuilder = new StringBuilder();
            for (final String arg : args) {
                stringBuilder.append(arg).append(" ");
            }
            final String chatMessage = stringBuilder.toString().trim();
            Chat.sendChatMessage(chatMessage);
        }
    }
    
    @Override
    public String getUsage() {
        return "say <message>";
    }
    
    @Override
    public CommandCategory getCategory() {
        return CommandCategory.MAIN;
    }
}
