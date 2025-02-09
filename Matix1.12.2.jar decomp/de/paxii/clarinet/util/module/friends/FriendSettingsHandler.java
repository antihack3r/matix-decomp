// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.util.module.friends;

import de.paxii.clarinet.event.events.game.StopGameEvent;
import de.paxii.clarinet.event.EventHandler;
import de.paxii.clarinet.util.file.FileService;
import java.io.File;
import de.paxii.clarinet.util.settings.ClientSettings;
import de.paxii.clarinet.event.events.game.StartGameEvent;
import de.paxii.clarinet.Wrapper;

public class FriendSettingsHandler
{
    public FriendSettingsHandler() {
        Wrapper.getEventManager().register(this);
    }
    
    @EventHandler
    public void onStartGame(final StartGameEvent event) {
        try {
            final File settingsFile = new File(ClientSettings.getClientFolderPath().getValue(), "/friends.json");
            if (settingsFile.exists()) {
                final FriendObjectContainer friendObjectContainer = FileService.getFileContents(settingsFile, FriendObjectContainer.class);
                if (friendObjectContainer != null && friendObjectContainer.getFriendList() != null) {
                    Wrapper.getFriendManager().setFriendList(friendObjectContainer.getFriendList());
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
            final File settingsFile = new File(ClientSettings.getClientFolderPath().getValue(), "/friends.json");
            if (settingsFile.exists() && !settingsFile.delete()) {
                return;
            }
            if (settingsFile.createNewFile()) {
                final FriendObjectContainer friendObjectContainer = new FriendObjectContainer(Wrapper.getFriendManager().getFriends());
                FileService.setFileContentsAsJson(settingsFile, friendObjectContainer);
            }
        }
        catch (final Exception e) {
            e.printStackTrace();
        }
        Wrapper.getEventManager().unregister(this);
    }
}
