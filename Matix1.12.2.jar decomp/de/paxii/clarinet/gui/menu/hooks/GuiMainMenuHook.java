// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.gui.menu.hooks;

import de.paxii.clarinet.Client;
import java.io.IOException;
import de.paxii.clarinet.gui.menu.changelog.GuiChangelog;
import de.paxii.clarinet.gui.menu.store.module.GuiModuleStore;
import net.minecraft.client.gui.GuiScreen;
import de.paxii.clarinet.Wrapper;
import de.paxii.clarinet.util.protocol.ProtocolVersion;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMainMenu;

public class GuiMainMenuHook extends GuiMainMenu
{
    private GuiButton pluginsButton;
    private GuiButton protocolButton;
    
    @Override
    public void initGui() {
        super.initGui();
        this.buttonList.add(new GuiButton(200, 2, this.height - 40, 100, 20, "AltManager"));
        this.pluginsButton = new GuiButton(201, this.width - 102, this.height - 40, 100, 20, "Plugins");
        this.protocolButton = new GuiButton(202, 2, this.height - 65, 100, 20, ProtocolVersion.getGameVersion());
        this.buttonList.add(new GuiButton(203, this.width - 102, this.height - 65, 100, 20, "Changelog"));
        this.buttonList.add(this.pluginsButton);
        if (ProtocolVersion.CompatibleVersion.values().length > 1) {
            this.buttonList.add(this.protocolButton);
        }
    }
    
    @Override
    protected void actionPerformed(final GuiButton button) throws IOException {
        super.actionPerformed(button);
        if (button.id == 200) {
            Wrapper.getMinecraft().displayGuiScreen(Wrapper.getClient().getAltManger());
        }
        if (button.id == 201) {
            Wrapper.getMinecraft().displayGuiScreen(new GuiModuleStore(this));
        }
        if (button.id == this.protocolButton.id) {
            ProtocolVersion.cycleVersion();
            this.protocolButton.displayString = ProtocolVersion.getGameVersion();
        }
        if (button.id == 203) {
            Wrapper.getMinecraft().displayGuiScreen(new GuiChangelog());
        }
    }
    
    @Override
    public void drawScreen(final int mouseX, final int mouseY, final float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
        Wrapper.getFontRenderer().drawString(Client.getClientName() + " " + Client.getClientVersion(), 2, 2, -1);
    }
}
