// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.module.render;

import de.paxii.clarinet.gui.ingame.panel.GuiPanel;
import java.util.Iterator;
import de.paxii.clarinet.util.chat.Chat;
import de.paxii.clarinet.gui.ingame.panel.theme.GuiTheme;
import de.paxii.clarinet.event.EventHandler;
import de.paxii.clarinet.gui.ingame.ClientClickableGui;
import de.paxii.clarinet.event.events.game.IngameTickEvent;
import net.minecraft.client.gui.GuiScreen;
import de.paxii.clarinet.Wrapper;
import de.paxii.clarinet.module.ModuleCategory;
import de.paxii.clarinet.module.Module;

public class ModuleClickGui extends Module
{
    public ModuleClickGui() {
        super("ClickGui", ModuleCategory.RENDER, 54);
        this.setDisplayedInGui(false);
        this.setCommand(true);
        this.setDescription("GUI Related Commands");
        this.setSyntax("clickgui theme <Name/List>");
        Wrapper.getEventManager().register(this);
    }
    
    @Override
    public void onEnable() {
        Wrapper.getMinecraft().displayGuiScreen(Wrapper.getClickableGui());
    }
    
    @EventHandler
    public void onTick(final IngameTickEvent event) {
        if (!(Wrapper.getMinecraft().currentScreen instanceof ClientClickableGui)) {
            if (this.isEnabled()) {
                this.setEnabled(false);
            }
            else {
                Wrapper.getClickableGui().getGuiPanels().forEach(guiPanel -> {
                    if (guiPanel.isPinned()) {
                        guiPanel.drawPanel(0, 0);
                    }
                });
            }
        }
    }
    
    @Override
    public void onCommand(final String[] args) {
        if (args.length >= 2) {
            if (args[0].equalsIgnoreCase("theme")) {
                if (args[1].equalsIgnoreCase("list")) {
                    String themes = "";
                    for (final GuiTheme clientTheme : Wrapper.getClickableGui().getPanelThemes()) {
                        themes = themes + ", " + clientTheme.getName();
                    }
                    if (themes.length() > 2) {
                        themes = themes.substring(2);
                    }
                    Chat.printClientMessage("Available Themes: " + themes);
                }
                else {
                    final String themeName = args[1];
                    if (Wrapper.getClickableGui().doesThemeExist(themeName)) {
                        final GuiTheme clientTheme2 = Wrapper.getClickableGui().getTheme(themeName);
                        Wrapper.getClickableGui().setCurrentTheme(clientTheme2);
                        Chat.printClientMessage("The GUI Theme was set to " + clientTheme2.getName() + ".");
                    }
                    else {
                        Chat.printClientMessage("There is no such Theme!");
                    }
                }
            }
            else {
                Chat.printClientMessage("Unknown subcommand!");
            }
        }
        else {
            Chat.printClientMessage("Too few Arguments!");
        }
    }
}
