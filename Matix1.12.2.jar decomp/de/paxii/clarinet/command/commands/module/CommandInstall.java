// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.command.commands.module;

import de.paxii.clarinet.command.CommandCategory;
import org.lwjgl.opengl.Display;
import org.lwjgl.input.Mouse;
import net.minecraft.client.gui.GuiScreen;
import de.paxii.clarinet.gui.menu.store.module.GuiModuleStore;
import de.paxii.clarinet.Wrapper;
import de.paxii.clarinet.command.AClientCommand;

public class CommandInstall extends AClientCommand
{
    @Override
    public String getCommand() {
        return "install";
    }
    
    @Override
    public String getDescription() {
        return "Opens the plugins menu.";
    }
    
    @Override
    public void runCommand(final String[] args) {
        new Thread(() -> {
            try {
                Thread.sleep(200L);
            }
            catch (final InterruptedException e) {
                e.printStackTrace();
            }
            finally {
                Wrapper.getMinecraft().displayGuiScreen(new GuiModuleStore(null));
                Mouse.setCursorPosition(-999, -999);
                Mouse.setCursorPosition(Display.getWidth() / 2, Display.getHeight() / 2);
            }
        }).start();
    }
    
    @Override
    public String getUsage() {
        return "install";
    }
    
    @Override
    public CommandCategory getCategory() {
        return CommandCategory.MODULE;
    }
}
