// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.command.commands.main;

import de.paxii.clarinet.command.CommandCategory;
import de.paxii.clarinet.util.chat.Chat;
import de.paxii.clarinet.util.settings.ClientSettings;
import de.paxii.clarinet.util.settings.ClientSetting;
import de.paxii.clarinet.command.AClientCommand;

public class CommandSetting extends AClientCommand
{
    @Override
    public String getCommand() {
        return "setting";
    }
    
    @Override
    public String getDescription() {
        return "Allows you to edit client settings";
    }
    
    @Override
    public void runCommand(final String[] args) {
        if (args.length >= 2) {
            final String settingKey = args[1];
            if (args[0].equalsIgnoreCase("get")) {
                final ClientSetting clientSetting = ClientSettings.getClientSettings().getOrDefault(settingKey, null);
                if (clientSetting != null) {
                    Chat.printClientMessage("Client Setting \"" + clientSetting.getName() + "\" is currently set to \"" + clientSetting.getValue() + "\".");
                }
            }
            else if (args[0].equalsIgnoreCase("set")) {
                if (args.length >= 3) {
                    final ClientSetting clientSetting = ClientSettings.getClientSettings().getOrDefault(settingKey, null);
                    if (clientSetting != null) {
                        String value = args[2];
                        try {
                            final Class type = clientSetting.getValue().getClass();
                            Object settingValue;
                            if (type == Boolean.class) {
                                settingValue = Boolean.parseBoolean(value);
                            }
                            else if (type == Integer.class) {
                                settingValue = Integer.parseInt(value);
                            }
                            else if (type == Double.class) {
                                settingValue = Double.parseDouble(value);
                            }
                            else if (type == String.class) {
                                settingValue = value;
                            }
                            else {
                                if (type != Long.class) {
                                    Chat.printClientMessage("Setting Type was not recognized. Please report this.");
                                    return;
                                }
                                if (value.startsWith("0x")) {
                                    value = value.substring(2);
                                }
                                settingValue = Long.parseLong(value, 16);
                            }
                            clientSetting.setValue(settingValue);
                            Chat.printClientMessage("Client Setting \"" + clientSetting.getName() + "\" has been set to \"" + clientSetting.getValue() + "\".");
                        }
                        catch (final Exception x) {
                            x.printStackTrace();
                        }
                    }
                    else {
                        Chat.printClientMessage(String.format("No Client Setting with key \"%s\" found.", settingKey));
                    }
                }
                else {
                    Chat.printClientMessage("Too few arguments!");
                }
            }
            else {
                Chat.printClientMessage("Unknown subcommand!");
            }
        }
    }
    
    @Override
    public String getUsage() {
        return "setting <client setting key> <value>";
    }
    
    @Override
    public CommandCategory getCategory() {
        return CommandCategory.MAIN;
    }
}
