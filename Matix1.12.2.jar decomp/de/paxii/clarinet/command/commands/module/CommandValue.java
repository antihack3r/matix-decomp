// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.command.commands.module;

import de.paxii.clarinet.command.CommandCategory;
import de.paxii.clarinet.module.Module;
import de.paxii.clarinet.util.module.settings.ValueBase;
import java.text.NumberFormat;
import de.paxii.clarinet.util.chat.Chat;
import de.paxii.clarinet.Wrapper;
import de.paxii.clarinet.command.AClientCommand;

public class CommandValue extends AClientCommand
{
    @Override
    public String getCommand() {
        return "value";
    }
    
    @Override
    public String getDescription() {
        return "Allows you to list and modify values of installed modules.";
    }
    
    @Override
    public void runCommand(final String[] args) {
        if (args.length >= 2) {
            if (Wrapper.getModuleManager().doesModuleExist(args[1])) {
                final Module module = Wrapper.getModuleManager().getModuleIgnoreCase(args[1]);
                if (args[0].equals("list")) {
                    Chat.printClientMessage(String.format("Values of Module \"%s\":", module.getName()));
                    module.getModuleValues().values().forEach(value -> {
                        final String valueString = NumberFormat.getNumberInstance().format(value.getValue());
                        Chat.printClientMessage(value.getName() + ": " + valueString);
                    });
                }
                else if (args.length >= 3) {
                    final ValueBase[] output = { null };
                    module.getModuleValues().forEach((valueKey, valueBase) -> {
                        if (valueKey.equalsIgnoreCase(args[2]) || valueBase.getName().equalsIgnoreCase(args[2]) || valueBase.getDisplayName().equalsIgnoreCase(args[2])) {
                            output[0] = valueBase;
                        }
                        return;
                    });
                    if (output[0] != null) {
                        final NumberFormat numberFormat = NumberFormat.getNumberInstance();
                        if (args[0].equalsIgnoreCase("get")) {
                            Chat.printClientMessage(String.format("Value \"%s\" of Module \"%s\".", output[0].getDisplayName(), module.getName()));
                            Chat.printClientMessage("Current Value: " + numberFormat.format(output[0].getValue()));
                            Chat.printClientMessage("Min Value: " + numberFormat.format(output[0].getMin()));
                            Chat.printClientMessage("Max Value: " + numberFormat.format(output[0].getMax()));
                        }
                        else if (args[0].equalsIgnoreCase("set")) {
                            if (args.length >= 4) {
                                try {
                                    final float newValue = Float.parseFloat(args[3]);
                                    output[0].setValue(newValue);
                                    Chat.printClientMessage(String.format("Value \"%s\" of Module \"%s\" was set to \"%s\".", output[0].getDisplayName(), module.getName(), numberFormat.format(newValue)));
                                }
                                catch (final NumberFormatException e) {
                                    Chat.printClientMessage(String.format("\"%s\" is not a valid value.", args[3]));
                                }
                            }
                            else {
                                Chat.printClientMessage("Too few arguments.");
                            }
                        }
                        else {
                            Chat.printClientMessage("Unkown subcommand.");
                        }
                    }
                    else {
                        Chat.printClientMessage(String.format("Value \"%s\" was not found in Module \"%s\".", args[2], module.getName()));
                    }
                }
                else {
                    Chat.printClientMessage("Too few arguments.");
                }
            }
            else {
                Chat.printClientMessage(String.format("Module \"%s\" is not installed.", args[1]));
            }
        }
        else {
            Chat.printClientMessage("Too few arguments.");
        }
    }
    
    @Override
    public String getUsage() {
        return "value <get/set/list> <module> <value name> <new value>";
    }
    
    @Override
    public CommandCategory getCategory() {
        return CommandCategory.MAIN;
    }
}
