// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.command.commands.module;

import de.paxii.clarinet.command.CommandCategory;
import net.minecraft.client.gui.GuiChat;
import de.paxii.clarinet.event.events.gui.DisplayGuiScreenEvent;
import de.paxii.clarinet.event.EventHandler;
import net.minecraft.client.gui.GuiScreen;
import de.paxii.clarinet.event.events.client.PostLoadModulesEvent;
import de.paxii.clarinet.module.Module;
import org.lwjgl.input.Keyboard;
import de.paxii.clarinet.util.chat.Chat;
import de.paxii.clarinet.Wrapper;
import de.paxii.clarinet.gui.menu.controls.GuiKeybinds;
import de.paxii.clarinet.command.AClientCommand;

public class CommandKey extends AClientCommand
{
    private boolean openGui;
    private GuiKeybinds guiKeybinds;
    
    public CommandKey() {
        Wrapper.getEventManager().register(this);
    }
    
    @Override
    public String getCommand() {
        return "key";
    }
    
    @Override
    public String getDescription() {
        return "Keybind Management";
    }
    
    @Override
    public void runCommand(final String[] args) {
        if (args.length > 0) {
            if (args.length >= 2) {
                final String moduleName = args[1];
                if (args[0].equalsIgnoreCase("set")) {
                    if (!Wrapper.getModuleManager().doesModuleExist(moduleName)) {
                        Chat.printClientMessage("Unknown Module!");
                        return;
                    }
                    final Module module = Wrapper.getModuleManager().getModuleIgnoreCase(moduleName);
                    if (args.length >= 3) {
                        String keyString = args[2].toUpperCase();
                        if (keyString.equals(".")) {
                            keyString = "PERIOD";
                        }
                        if (keyString.equals(",")) {
                            keyString = "COMMA";
                        }
                        if (keyString.equals("-")) {
                            keyString = "MINUS";
                        }
                        if (keyString.equals("+")) {
                            keyString = "PLUS";
                        }
                        final int moduleKey = Keyboard.getKeyIndex(keyString);
                        module.setKey(moduleKey);
                        Chat.printClientMessage(String.format("%s Key has been set to %s.", module, Keyboard.getKeyName(moduleKey)));
                    }
                    else {
                        Chat.printClientMessage(module + " Key has been removed!");
                    }
                }
                else if (args[0].equalsIgnoreCase("remove")) {
                    if (!Wrapper.getModuleManager().doesModuleExist(moduleName)) {
                        Chat.printClientMessage("Unknown Module!");
                        return;
                    }
                    final Module module = Wrapper.getModuleManager().getModuleIgnoreCase(moduleName);
                    module.setKey(-1);
                    Chat.printClientMessage(module + " Key has been removed!");
                }
                else {
                    Chat.printClientMessage("Unknown sub-command!");
                }
            }
            else if (args[0].equalsIgnoreCase("gui")) {
                this.openGui = true;
            }
            else {
                Chat.printClientMessage("Unknown sub-command!");
            }
        }
        else {
            Chat.printClientMessage("Too few arguments!");
        }
    }
    
    @EventHandler
    public void onModulesLoaded(final PostLoadModulesEvent loadEvent) {
        this.guiKeybinds = new GuiKeybinds(null);
    }
    
    @EventHandler
    public void onDisplayGuiScreen(final DisplayGuiScreenEvent screenEvent) {
        if (this.openGui && Wrapper.getMinecraft().currentScreen instanceof GuiChat && screenEvent.getGuiScreen() == null) {
            screenEvent.setGuiScreen(this.guiKeybinds);
            this.openGui = false;
        }
    }
    
    @Override
    public String getUsage() {
        return "key <set/remove/gui> <module> [key]";
    }
    
    @Override
    public CommandCategory getCategory() {
        return CommandCategory.MODULE;
    }
}
