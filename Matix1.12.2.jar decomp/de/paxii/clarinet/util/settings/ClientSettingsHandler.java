// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.util.settings;

import de.paxii.clarinet.event.EventHandler;
import java.io.IOException;
import de.paxii.clarinet.util.file.FileService;
import java.io.File;
import de.paxii.clarinet.event.EventManager;
import de.paxii.clarinet.event.events.game.StopGameEvent;
import de.paxii.clarinet.Wrapper;

public class ClientSettingsHandler
{
    public ClientSettingsHandler() {
        Wrapper.getEventManager().register(this);
        this.onStartGame();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            final StopGameEvent stopGameEvent = EventManager.call(new StopGameEvent());
        }));
    }
    
    public void onStartGame() {
        final File settingsFile = new File(ClientSettings.getClientFolderPath().getValue(), "/settings.json");
        try {
            if (!settingsFile.exists()) {
                settingsFile.createNewFile();
            }
            final ClientSettingsContainer clientSettingsContainer = FileService.getFileContents(settingsFile, ClientSettingsContainer.class);
            if (clientSettingsContainer != null) {
                clientSettingsContainer.getClientSettings().forEach((k, v) -> {
                    final ClientSetting clientSetting = new ClientSetting(k, (T)v);
                    final ClientSetting clientSetting2 = ClientSettingValueManager.patchValue(clientSetting);
                    ClientSettings.getClientSettings().put(k, clientSetting2);
                });
            }
        }
        catch (final Exception e) {
            e.printStackTrace();
        }
    }
    
    @EventHandler
    public void onStopGame(final StopGameEvent event) {
        try {
            final File settingsFile = new File(ClientSettings.getClientFolderPath().getValue(), "/settings.json");
            FileService.setFileContentsAsJson(settingsFile, ClientSettingsContainer.buildContainer(ClientSettings.getClientSettings()));
        }
        catch (final IOException e) {
            e.printStackTrace();
        }
        Wrapper.getEventManager().unregister(this);
    }
}
