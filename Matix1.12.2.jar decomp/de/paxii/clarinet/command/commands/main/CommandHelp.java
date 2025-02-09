// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.command.commands.main;

import de.paxii.clarinet.command.CommandCategory;
import net.minecraft.util.text.ITextComponent;
import java.util.Iterator;
import de.paxii.clarinet.util.chat.ChatColor;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import java.util.SortedMap;
import de.paxii.clarinet.util.chat.Chat;
import java.util.Map;
import de.paxii.clarinet.module.Module;
import java.util.TreeMap;
import de.paxii.clarinet.Wrapper;
import de.paxii.clarinet.command.AClientCommand;

public class CommandHelp extends AClientCommand
{
    @Override
    public String getCommand() {
        return "help";
    }
    
    @Override
    public String getDescription() {
        return "Command Help";
    }
    
    @Override
    public void runCommand(final String[] args) {
        if (args.length == 0) {
            final TreeMap<String, Module> sortedModules = new TreeMap<String, Module>(Wrapper.getModuleManager().getModuleList());
            Chat.printClientMessage("--------------------------------------------");
            Chat.printClientMessage("Module commands:");
            for (final Map.Entry<String, Module> moduleEntry : sortedModules.entrySet()) {
                final Module module = moduleEntry.getValue();
                if (module.isCommand()) {
                    Chat.printClientMessage(module.getName() + ": " + module.getDescription());
                }
            }
            final TreeMap<String, AClientCommand> sortedCommands = new TreeMap<String, AClientCommand>(Wrapper.getConsole().getCommandList());
            Chat.printClientMessage("--------------------------------------------");
            Chat.printClientMessage("Commands:");
            for (final Map.Entry<String, AClientCommand> commandEntry : sortedCommands.entrySet()) {
                final AClientCommand command = commandEntry.getValue();
                Chat.printClientMessage(command.getCommand() + ": " + command.getDescription());
            }
            Chat.printClientMessage("--------------------------------------------");
        }
        else {
            final String searchString = args[0].toLowerCase();
            if (Wrapper.getConsole().getCommandList().containsKey(searchString)) {
                final AClientCommand command2 = Wrapper.getConsole().getCommandList().get(searchString);
                Chat.printClientMessage("--------------------------------------------");
                Chat.printClientMessage(command2.getCommand() + " Help:");
                Chat.printClientMessage("Description: " + command2.getDescription());
                Chat.printClientMessage("Syntax: " + command2.getUsage());
            }
            else if (Wrapper.getModuleManager().doesModuleExist(searchString)) {
                final Module module2 = Wrapper.getModuleManager().getModuleIgnoreCase(searchString);
                Chat.printClientMessage("--------------------------------------------");
                Chat.printClientMessage(module2.getName() + " Help:");
                Chat.printClientMessage("Description: " + module2.getDescription());
                if (module2.isCommand()) {
                    Chat.printClientMessage("Syntax: " + module2.getSyntax());
                }
                final ITextComponent textComponent = new TextComponentString(Chat.getPrefix() + "Documentation: ");
                final ITextComponent urlComponent = new TextComponentString("Click here");
                final Style chatStyle = new Style();
                chatStyle.setUnderlined(true);
                chatStyle.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, module2.getHelpUrl()));
                urlComponent.setStyle(chatStyle);
                textComponent.appendSibling(urlComponent);
                Chat.printChatComponent(textComponent);
                if (module2.isPlugin()) {
                    Chat.printClientMessage(ChatColor.RED + "Warning: This Module is a Plugin. Documentation URL might be incorrect!");
                }
            }
            else {
                Chat.printClientMessage(String.format("Could not find the command \"%s\"!", args[0]));
            }
        }
    }
    
    @Override
    public String getUsage() {
        return "help [command/module]";
    }
    
    @Override
    public CommandCategory getCategory() {
        return CommandCategory.MAIN;
    }
}
