// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.gui.ingame;

import net.minecraft.util.EnumActionResult;
import java.util.Iterator;
import de.paxii.clarinet.gui.ingame.panel.element.PanelElement;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;
import de.paxii.clarinet.util.settings.ClientSetting;
import de.paxii.clarinet.util.settings.type.ClientSettingString;
import de.paxii.clarinet.util.settings.ClientSettings;
import de.paxii.clarinet.gui.ingame.panel.theme.themes.LegacyTheme;
import de.paxii.clarinet.gui.ingame.panel.theme.themes.Matix2HDTheme;
import de.paxii.clarinet.gui.ingame.panel.theme.themes.DefaultTheme;
import de.paxii.clarinet.event.EventHandler;
import de.paxii.clarinet.event.events.client.PostLoadModulesEvent;
import de.paxii.clarinet.Wrapper;
import de.paxii.clarinet.gui.ingame.panel.GuiPanel;
import java.util.ArrayList;
import de.paxii.clarinet.gui.ingame.panel.theme.GuiTheme;
import de.paxii.clarinet.gui.ingame.panel.GuiPanelManager;
import net.minecraft.client.gui.GuiScreen;

public class ClientClickableGui extends GuiScreen
{
    private final GuiPanelManager panelManager;
    private GuiTheme currentTheme;
    private ArrayList<GuiPanel> guiPanels;
    private ArrayList<GuiTheme> panelThemes;
    
    public ClientClickableGui() {
        this.guiPanels = new ArrayList<GuiPanel>();
        this.panelThemes = new ArrayList<GuiTheme>();
        this.panelManager = new GuiPanelManager();
        Wrapper.getEventManager().register(this);
    }
    
    @EventHandler
    private void onModulesLoad(final PostLoadModulesEvent event) {
        this.loadThemes();
        this.loadPanels();
    }
    
    private void loadThemes() {
        this.panelThemes.add(new DefaultTheme());
        this.panelThemes.add(new Matix2HDTheme());
        this.panelThemes.add(new LegacyTheme());
        final GuiTheme clientTheme = this.getTheme(ClientSettings.getValue("client.guitheme", String.class));
        if (clientTheme != null) {
            this.setCurrentTheme(clientTheme);
        }
        else {
            this.setCurrentTheme(this.panelThemes.get(0));
        }
    }
    
    public void setCurrentTheme(final GuiTheme clientTheme) {
        final GuiPanel colorPanel = this.getGuiPanel("Gui Color");
        if (colorPanel != null) {
            if (clientTheme instanceof LegacyTheme) {
                colorPanel.setVisible(true);
            }
            else {
                colorPanel.setVisible(false);
            }
        }
        this.currentTheme = clientTheme;
        ClientSettings.put(new ClientSettingString("client.guitheme", this.getCurrentTheme().getName()));
    }
    
    public void loadPanels() {
        new Thread(() -> {
            System.out.println("loading Panels...");
            this.reloadPanels();
            final AtomicBoolean error = new AtomicBoolean();
            this.getGuiPanels().forEach(guiPanel -> {
                if (guiPanel.isVisible() && guiPanel.getPanelElements().size() == 0) {
                    error.set(true);
                }
                return;
            });
            if (error.get()) {
                this.reloadPanels();
            }
        }).start();
    }
    
    private void reloadPanels() {
        this.panelManager.loadPanels(this);
    }
    
    @Override
    public void drawScreen(final int mouseX, final int mouseY, final float partialTicks) {
        this.getGuiPanels().forEach(guiPanel -> {
            if (guiPanel.isVisible()) {
                guiPanel.drawPanel(mouseX, mouseY);
            }
            return;
        });
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
    
    @Override
    protected void mouseClicked(final int mouseX, final int mouseY, final int buttonClicked) throws IOException {
        for (int i = 0; i < this.getGuiPanels().size(); ++i) {
            final GuiPanel guiPanel = this.getGuiPanels().get(i);
            if (guiPanel.isVisible()) {
                if (guiPanel.isMouseOverAll(mouseX, mouseY)) {
                    if (this.getGuiPanels().indexOf(guiPanel) == this.getGuiPanels().size() - 1) {
                        guiPanel.mouseClicked(mouseX, mouseY, buttonClicked);
                    }
                    this.moveOverAll(guiPanel);
                }
            }
        }
        super.mouseClicked(mouseX, mouseY, buttonClicked);
    }
    
    public void moveOverAll(final GuiPanel guiPanel) {
        this.getGuiPanels().remove(guiPanel);
        this.getGuiPanels().add(guiPanel);
    }
    
    public void mouseReleased(final int mouseX, final int mouseY, final int buttonReleased) {
        if (buttonReleased == 0) {
            for (final GuiPanel guiPanel : this.getGuiPanels()) {
                guiPanel.setDragging(false);
                for (final PanelElement panelElement : guiPanel.getPanelElements()) {
                    panelElement.mouseMovedOrUp(mouseX, mouseY, buttonReleased);
                }
            }
        }
        super.mouseReleased(mouseX, mouseY, buttonReleased);
    }
    
    @Override
    protected void keyTyped(final char typedChar, final int keyCode) throws IOException {
        EnumActionResult result = EnumActionResult.PASS;
        for (final GuiPanel guiPanel : this.getGuiPanels()) {
            final EnumActionResult actionResult = guiPanel.keyPressed(keyCode);
            if (result == EnumActionResult.PASS) {
                result = actionResult;
            }
        }
        if (result == EnumActionResult.PASS) {
            super.keyTyped(typedChar, keyCode);
        }
    }
    
    public boolean doesPanelExist(final String panelName) {
        for (final GuiPanel guiPanel : this.getGuiPanels()) {
            if (guiPanel.getPanelName().equals(panelName)) {
                return true;
            }
        }
        return false;
    }
    
    public GuiPanel getGuiPanel(final String panelName) {
        for (final GuiPanel guiPanel : this.getGuiPanels()) {
            if (guiPanel.getPanelName().equals(panelName)) {
                return guiPanel;
            }
        }
        return null;
    }
    
    public boolean doesThemeExist(final String themeName) {
        for (final GuiTheme theme : this.getPanelThemes()) {
            if (theme.getName().equalsIgnoreCase(themeName)) {
                return true;
            }
        }
        return false;
    }
    
    public GuiTheme getTheme(final String themeName) {
        for (final GuiTheme theme : this.getPanelThemes()) {
            if (theme.getName().equalsIgnoreCase(themeName)) {
                return theme;
            }
        }
        return null;
    }
    
    public GuiTheme getCurrentTheme() {
        return this.currentTheme;
    }
    
    public ArrayList<GuiPanel> getGuiPanels() {
        return this.guiPanels;
    }
    
    public ArrayList<GuiTheme> getPanelThemes() {
        return this.panelThemes;
    }
}
