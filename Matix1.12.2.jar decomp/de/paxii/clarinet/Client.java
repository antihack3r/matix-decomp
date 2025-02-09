// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet;

import net.minecraft.client.gui.GuiScreen;
import de.paxii.clarinet.gui.menu.hooks.GuiMainMenuHook;
import java.io.File;
import de.paxii.clarinet.util.settings.ClientSettings;
import de.paxii.clarinet.util.update.UpdateChecker;
import de.paxii.clarinet.util.encryption.StringEncryption;
import de.paxii.clarinet.gui.menu.login.GuiAltManager;
import de.paxii.clarinet.util.module.friends.FriendSettingsHandler;
import de.paxii.clarinet.gui.ingame.settings.PanelSettingsHandler;
import de.paxii.clarinet.util.module.settings.ModuleSettingsHandler;
import de.paxii.clarinet.util.settings.ClientSettingsHandler;
import de.paxii.clarinet.gui.ingame.ClientClickableGui;
import de.paxii.clarinet.command.ClientConsole;
import de.paxii.clarinet.util.module.store.ModuleStore;
import de.paxii.clarinet.util.login.mcleaks.MCLeaksManager;
import de.paxii.clarinet.util.chat.font.FontManager;
import de.paxii.clarinet.util.notifications.NotificationManager;
import de.paxii.clarinet.util.module.friends.FriendManager;
import de.paxii.clarinet.module.ModuleManager;
import de.paxii.clarinet.event.EventManager;

public class Client
{
    private static final String clientName = "Matix";
    private static final String clientVersion = "1.9.2B";
    private static final String gameVersion = "1.12.2";
    private static final int clientBuild = 19020;
    private static final String clientURL = "http://paxii.de/Matix/";
    private static Client clientInstance;
    private EventManager eventManager;
    private ModuleManager moduleManager;
    private FriendManager friendManager;
    private NotificationManager notificationManager;
    private FontManager fontManager;
    private MCLeaksManager mcLeaksManager;
    private ModuleStore moduleStore;
    private ClientConsole clientConsole;
    private ClientClickableGui clientClickableGui;
    private ClientSettingsHandler clientSettingsHandler;
    private ModuleSettingsHandler moduleSettingsHandler;
    private PanelSettingsHandler panelSettingsHandler;
    private FriendSettingsHandler friendSettingsHandler;
    private GuiAltManager altManger;
    private StringEncryption stringEncryption;
    private UpdateChecker updateChecker;
    
    public Client() {
        Client.clientInstance = this;
        final File clientFolder = new File(ClientSettings.getClientFolderPath().getValue());
        if (!clientFolder.exists()) {
            clientFolder.mkdirs();
        }
        this.eventManager = new EventManager();
        this.clientSettingsHandler = new ClientSettingsHandler();
        this.stringEncryption = new StringEncryption();
        this.moduleStore = new ModuleStore();
        this.moduleManager = new ModuleManager();
        this.friendManager = new FriendManager();
        this.notificationManager = new NotificationManager();
        this.fontManager = new FontManager();
        this.mcLeaksManager = new MCLeaksManager();
        this.clientConsole = new ClientConsole();
        this.clientClickableGui = new ClientClickableGui();
        this.moduleSettingsHandler = new ModuleSettingsHandler();
        this.panelSettingsHandler = new PanelSettingsHandler();
        this.friendSettingsHandler = new FriendSettingsHandler();
        this.altManger = new GuiAltManager(new GuiMainMenuHook());
        this.updateChecker = new UpdateChecker();
    }
    
    public EventManager getEventManager() {
        return this.eventManager;
    }
    
    public ModuleManager getModuleManager() {
        return this.moduleManager;
    }
    
    public FriendManager getFriendManager() {
        return this.friendManager;
    }
    
    public NotificationManager getNotificationManager() {
        return this.notificationManager;
    }
    
    public FontManager getFontManager() {
        return this.fontManager;
    }
    
    public MCLeaksManager getMcLeaksManager() {
        return this.mcLeaksManager;
    }
    
    public ModuleStore getModuleStore() {
        return this.moduleStore;
    }
    
    public ClientConsole getClientConsole() {
        return this.clientConsole;
    }
    
    public ClientClickableGui getClientClickableGui() {
        return this.clientClickableGui;
    }
    
    public ClientSettingsHandler getClientSettingsHandler() {
        return this.clientSettingsHandler;
    }
    
    public ModuleSettingsHandler getModuleSettingsHandler() {
        return this.moduleSettingsHandler;
    }
    
    public PanelSettingsHandler getPanelSettingsHandler() {
        return this.panelSettingsHandler;
    }
    
    public FriendSettingsHandler getFriendSettingsHandler() {
        return this.friendSettingsHandler;
    }
    
    public GuiAltManager getAltManger() {
        return this.altManger;
    }
    
    public StringEncryption getStringEncryption() {
        return this.stringEncryption;
    }
    
    public UpdateChecker getUpdateChecker() {
        return this.updateChecker;
    }
    
    public static String getClientName() {
        return "Matix";
    }
    
    public static String getClientVersion() {
        return "1.9.2B";
    }
    
    public static String getGameVersion() {
        return "1.12.2";
    }
    
    public static int getClientBuild() {
        return 19020;
    }
    
    public static String getClientURL() {
        return "http://paxii.de/Matix/";
    }
    
    public static Client getClientInstance() {
        return Client.clientInstance;
    }
}
