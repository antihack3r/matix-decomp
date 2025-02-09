// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.gui.ingame.settings;

import com.google.gson.Gson;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Arrays;
import de.paxii.clarinet.gui.ingame.panel.GuiPanelModuleSettings;
import com.google.gson.GsonBuilder;
import de.paxii.clarinet.event.events.game.StopGameEvent;
import de.paxii.clarinet.event.EventHandler;
import de.paxii.clarinet.gui.ingame.panel.GuiPanel;
import de.paxii.clarinet.gui.ingame.panel.theme.themes.LegacyTheme;
import de.paxii.clarinet.util.file.FileService;
import java.io.File;
import de.paxii.clarinet.util.settings.ClientSettings;
import de.paxii.clarinet.event.events.client.PostLoadPanelsEvent;
import de.paxii.clarinet.Wrapper;

public class PanelSettingsHandler
{
    public PanelSettingsHandler() {
        Wrapper.getEventManager().register(this);
    }
    
    @EventHandler
    public void onPanelsLoaded(final PostLoadPanelsEvent event) {
        try {
            File settingsFile = new File(ClientSettings.getClientFolderPath().getValue(), "/panels.json");
            if (settingsFile.exists()) {
                final PanelSettingsContainer panelSettingsContainer = FileService.getFileContents(settingsFile, PanelSettingsContainer.class);
                if (panelSettingsContainer != null) {
                    panelSettingsContainer.getPanelSettings().forEach((panelName, panel) -> {
                        if (Wrapper.getClickableGui().doesPanelExist(panelName)) {
                            final GuiPanel guiPanel = Wrapper.getClickableGui().getGuiPanel(panelName);
                            guiPanel.setPanelX(panel.getPosX());
                            guiPanel.setPanelY(panel.getPosY());
                            guiPanel.setOpened(panel.isOpened());
                            guiPanel.setPinned(panel.isPinned());
                            guiPanel.setVisible(panel.isVisible());
                        }
                        return;
                    });
                }
            }
            settingsFile = new File(ClientSettings.getClientFolderPath().getValue(), "/color.json");
            if (settingsFile.exists()) {
                final String colorName = FileService.getFileContents(settingsFile, String.class);
                final LegacyTheme theme = (LegacyTheme)Wrapper.getClickableGui().getTheme("Legacy");
                if (theme != null) {
                    theme.setCurrentColor(theme.getColorObject(colorName));
                }
            }
        }
        catch (final Exception e) {
            e.printStackTrace();
        }
    }
    
    @EventHandler
    public void onStopGame(final StopGameEvent event) {
        try {
            final Gson gson = new GsonBuilder().setPrettyPrinting().create();
            final File settingsFile = new File(ClientSettings.getClientFolderPath().getValue(), "/panels.json");
            if (settingsFile.exists() && !settingsFile.delete()) {
                return;
            }
            if (settingsFile.createNewFile()) {
                final GuiPanel[] guiPanels = Wrapper.getClickableGui().getGuiPanels().stream().filter(guiPanel -> !(guiPanel instanceof GuiPanelModuleSettings)).toArray(GuiPanel[]::new);
                final PanelSettingsContainer panelSettingsContainer = new PanelSettingsContainer(new ArrayList<GuiPanel>(Arrays.asList(guiPanels)));
                FileService.setFileContentsAsJson(settingsFile, panelSettingsContainer);
                try {
                    final File colorFile = new File(ClientSettings.getClientFolderPath().getValue(), "/color.json");
                    if (colorFile.exists() || colorFile.createNewFile()) {
                        final LegacyTheme legacyTheme = (LegacyTheme)Wrapper.getClickableGui().getTheme("Legacy");
                        if (legacyTheme != null) {
                            final String colorName = gson.toJson((Object)legacyTheme.getCurrentColor().getColorName());
                            FileService.setFileContentsAsJson(colorFile, colorName);
                        }
                    }
                }
                catch (final Exception e) {
                    e.printStackTrace();
                }
            }
        }
        catch (final Exception e2) {
            e2.printStackTrace();
        }
        Wrapper.getEventManager().unregister(this);
    }
}
