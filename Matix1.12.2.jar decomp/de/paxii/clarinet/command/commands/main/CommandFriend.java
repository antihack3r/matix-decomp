// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.command.commands.main;

import de.paxii.clarinet.command.CommandCategory;
import java.util.Iterator;
import java.util.Map;
import de.paxii.clarinet.util.chat.Chat;
import de.paxii.clarinet.Wrapper;
import de.paxii.clarinet.command.AClientCommand;

public class CommandFriend extends AClientCommand
{
    @Override
    public String getCommand() {
        return "friend";
    }
    
    @Override
    public String getDescription() {
        return "Manage friends";
    }
    
    @Override
    public void runCommand(final String[] args) {
        if (args.length > 0) {
            if (args.length >= 2) {
                if (args[0].equalsIgnoreCase("add")) {
                    final String friendName = args[1];
                    String colorString = "";
                    if (args.length >= 3) {
                        colorString = args[2];
                    }
                    int friendColor = -1;
                    try {
                        if (colorString.startsWith("0x")) {
                            colorString = colorString.substring(2);
                        }
                        friendColor = Integer.parseInt(colorString, 16);
                    }
                    catch (final Exception ex) {}
                    if (friendColor != -1) {
                        Wrapper.getFriendManager().addFriend(friendName, friendColor);
                        Chat.printClientMessage("Added Friend " + friendName + " (0x" + Integer.toHexString(friendColor) + ").");
                    }
                    else {
                        Wrapper.getFriendManager().addFriend(friendName);
                        Chat.printClientMessage("Added Friend " + friendName + ".");
                    }
                }
                else if (args[0].equalsIgnoreCase("remove")) {
                    final String friendName = args[1];
                    Wrapper.getFriendManager().removeFriend(friendName);
                    Chat.printClientMessage("Removed Friend " + friendName + ".");
                }
                else {
                    Chat.printClientMessage("Unknown subcommand");
                }
            }
            else if (args.length >= 1) {
                if (args[0].equalsIgnoreCase("list")) {
                    Chat.printClientMessage("Friend list:");
                    for (final Map.Entry<String, Integer> friend : Wrapper.getFriendManager().getFriends().entrySet()) {
                        Chat.printClientMessage("Friend: " + friend.getKey() + " (0x" + Integer.toHexString(friend.getValue()) + ").");
                    }
                }
                else if (args[0].equalsIgnoreCase("clear")) {
                    Wrapper.getFriendManager().getFriends().clear();
                    Chat.printClientMessage("Friend list has been cleared!");
                }
            }
        }
        else {
            Chat.printClientMessage("Too few arguments!");
        }
    }
    
    @Override
    public String getUsage() {
        return "friend [add/remove] [friend]";
    }
    
    @Override
    public CommandCategory getCategory() {
        return CommandCategory.MAIN;
    }
}
