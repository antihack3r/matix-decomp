// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.command;

import de.paxii.clarinet.event.events.game.StopGameEvent;
import net.minecraft.client.gui.GuiScreen;
import de.paxii.clarinet.gui.menu.chat.GuiChatConsole;
import net.minecraft.client.gui.GuiSleepMP;
import net.minecraft.client.gui.GuiChat;
import de.paxii.clarinet.event.events.gui.DisplayGuiScreenEvent;
import de.paxii.clarinet.event.EventHandler;
import de.paxii.clarinet.module.Module;
import java.util.Iterator;
import de.paxii.clarinet.util.chat.Chat;
import java.util.Map;
import de.paxii.clarinet.util.settings.ClientSettings;
import de.paxii.clarinet.event.events.player.PlayerSendChatMessageEvent;
import de.paxii.clarinet.util.reflection.ReflectionHelper;
import de.paxii.clarinet.Wrapper;
import java.util.TreeMap;

public class ClientConsole
{
    private TreeMap<String, AClientCommand> commandList;
    
    public ClientConsole() {
        this.commandList = new TreeMap<String, AClientCommand>();
        this.addCommands();
        Wrapper.getEventManager().register(this);
    }
    
    public void reloadCommands() {
        this.addCommands();
    }
    
    private void addCommands() {
        for (final CommandCategory ca : CommandCategory.values()) {
            final String packageName = "de.paxii.clarinet.command.commands." + ca.toString().toLowerCase();
            System.out.println("Searching for Commands in " + packageName);
            try {
                final Class<? extends AClientCommand>[] classesInPackageBySuperType;
                final Class<? extends AClientCommand>[] commandClasses = classesInPackageBySuperType = ReflectionHelper.getClassesInPackageBySuperType(packageName, AClientCommand.class);
                for (final Class<? extends AClientCommand> commandClass : classesInPackageBySuperType) {
                    try {
                        final AClientCommand newCommand = (AClientCommand)commandClass.newInstance();
                        this.addCommand(newCommand);
                    }
                    catch (final Exception x) {
                        x.printStackTrace();
                    }
                }
            }
            catch (final Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public void addCommand(final AClientCommand command) {
        if (!this.commandList.containsKey(command.getCommand())) {
            System.out.println("Loading " + command.getCommand() + "...");
            this.commandList.put(command.getCommand(), command);
        }
    }
    
    public void removeCommand(final IClientCommand command) {
        if (this.commandList.containsKey(command.getCommand())) {
            this.commandList.remove(command.getCommand());
        }
    }
    
    @EventHandler
    public void onChatMessage(final PlayerSendChatMessageEvent event) {
        final String chatMessage = event.getChatMessage();
        final String clientPrefix = ClientSettings.getValue("client.prefix", String.class);
        if (chatMessage.startsWith(clientPrefix)) {
            event.setCancelled(true);
            String userCommand;
            final String userMessage = userCommand = chatMessage.substring(clientPrefix.length());
            String[] args;
            if (userCommand.contains(" ")) {
                userCommand = userCommand.split(" ")[0];
                args = this.getArguments(userCommand, userMessage);
            }
            else {
                args = new String[0];
            }
            if (args.length == 1 && args[0].startsWith(" ")) {
                args = new String[0];
            }
            if (this.commandList.containsKey(userCommand)) {
                for (final Map.Entry<String, AClientCommand> clientCommand : this.commandList.entrySet()) {
                    if (clientCommand.getKey().equalsIgnoreCase(userCommand)) {
                        clientCommand.getValue().runCommand(args);
                    }
                }
            }
            else if (Wrapper.getModuleManager().doesModuleExist(userCommand)) {
                final Module module = Wrapper.getModuleManager().getModuleIgnoreCase(userCommand);
                if (module.isCommand()) {
                    module.onCommand(args);
                }
                else {
                    Chat.printChatMessage(module.getName() + " does not have a command!");
                }
            }
            else {
                Chat.printClientMessage("Unknown command! Type \"" + ClientSettings.getValue("client.prefix", String.class) + "help\" for a list of commands.");
            }
        }
    }
    
    @EventHandler(priority = 0)
    public void onDisplayGuiScreen(final DisplayGuiScreenEvent event) {
        if (event.getGuiScreen() instanceof GuiChat && !(event.getGuiScreen() instanceof GuiSleepMP)) {
            final GuiChat guiChat = (GuiChat)event.getGuiScreen();
            final GuiChatConsole guiChatConsole = new GuiChatConsole();
            guiChatConsole.setDefaultInputFieldText(guiChat.getDefaultInputFieldText());
            event.setGuiScreen(guiChatConsole);
        }
    }
    
    private String[] getArguments(final String userCommand, final String userMessage) {
        String argsWithoutWhiteSpace;
        for (argsWithoutWhiteSpace = userMessage.replaceFirst(userCommand, ""); argsWithoutWhiteSpace.startsWith(" "); argsWithoutWhiteSpace = argsWithoutWhiteSpace.substring(1)) {}
        return argsWithoutWhiteSpace.split(" ");
    }
    
    @EventHandler
    public void onShutdown(final StopGameEvent event) {
        Wrapper.getEventManager().unregister(this);
    }
    
    public TreeMap<String, AClientCommand> getCommandList() {
        return this.commandList;
    }
}
