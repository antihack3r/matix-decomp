// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.gui.menu.chat;

import java.util.Iterator;
import de.paxii.clarinet.module.Module;
import de.paxii.clarinet.command.AClientCommand;
import java.util.Map;
import de.paxii.clarinet.Wrapper;
import de.paxii.clarinet.util.settings.ClientSettings;
import net.minecraft.client.gui.GuiChat;

public class GuiChatConsole extends GuiChat
{
    @Override
    public void drawScreen(final int mouseX, final int mouseY, final float partialTicks) {
        final String clientPrefix = ClientSettings.getValue("client.prefix", String.class);
        if (this.inputField.getText().startsWith(clientPrefix)) {
            String commandName = this.inputField.getText().substring(clientPrefix.length()).toLowerCase();
            String prediction = "help";
            if (commandName.length() > 0) {
                if (commandName.contains(" ") && !commandName.startsWith(" ")) {
                    final String[] commandSplit = commandName.split(" ");
                    commandName = commandSplit[0].toLowerCase();
                }
                for (final Map.Entry<String, AClientCommand> entry : Wrapper.getConsole().getCommandList().entrySet()) {
                    if (entry.getKey().toLowerCase().startsWith(commandName)) {
                        prediction = entry.getKey().toLowerCase();
                        break;
                    }
                }
                if (prediction.equals("help")) {
                    for (final Map.Entry<String, Module> entry2 : Wrapper.getModuleManager().getModuleList().entrySet()) {
                        if (entry2.getValue().isCommand() && entry2.getKey().toLowerCase().startsWith(commandName)) {
                            prediction = entry2.getKey().toLowerCase();
                            break;
                        }
                    }
                }
                if (prediction.equals("help")) {
                    prediction = "";
                }
            }
            Wrapper.getFontRenderer().drawString(clientPrefix + prediction, 4, this.height - 12, 11184810);
        }
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
}
