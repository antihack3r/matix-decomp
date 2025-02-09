// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.util.module.store;

import de.paxii.clarinet.event.events.game.StopGameEvent;
import de.paxii.clarinet.event.EventHandler;
import java.util.concurrent.atomic.AtomicBoolean;
import de.paxii.clarinet.event.events.client.PostLoadModulesEvent;
import de.paxii.clarinet.util.file.FileService;
import com.google.gson.reflect.TypeToken;
import de.paxii.clarinet.util.web.JsonFetcher;
import de.paxii.clarinet.module.Module;
import de.paxii.clarinet.util.notifications.NotificationPriority;
import de.paxii.clarinet.command.ClientConsole;
import de.paxii.clarinet.util.settings.ClientSettings;
import net.minecraft.network.play.client.CPacketChatMessage;
import de.paxii.clarinet.event.events.player.PlayerSendChatMessageEvent;
import de.paxii.clarinet.util.chat.Chat;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import java.net.URL;
import de.paxii.clarinet.Client;
import java.io.File;
import de.paxii.clarinet.Wrapper;
import java.util.ArrayList;
import java.util.TreeMap;

public class ModuleStore
{
    public static final int COMPATIBLE_MAY = 1726506775;
    public static final int COMPATIBLE_NO = 1725632271;
    private static final String moduleUrl;
    private static final TreeMap<String, ModuleEntry> moduleList;
    private static ArrayList<String> modulesToDelete;
    
    public ModuleStore() {
        Wrapper.getEventManager().register(this);
        this.removeModules();
    }
    
    public static void downloadModule(final String moduleName) {
        try {
            if (isModuleInstalled(moduleName)) {
                removeModule(moduleName);
            }
            final ModuleEntry moduleEntry = ModuleStore.moduleList.get(moduleName);
            final File moduleFile = new File(getModuleFolder(), String.format("%s.jar", moduleName + "-" + moduleEntry.getVersion()));
            final URL downloadUrl = new URL(String.format(ModuleStore.moduleUrl, Client.getGameVersion(), moduleName, moduleName + "-" + moduleEntry.getVersion()));
            if (moduleFile.exists() && !moduleFile.delete()) {
                return;
            }
            new Thread(() -> {
                try {
                    FileUtils.copyURLToFile(downloadUrl, moduleFile);
                }
                catch (final IOException e2) {
                    e2.printStackTrace();
                }
                finally {
                    Chat.printClientMessage(String.format("Module %s has been downloaded. Reloading Client...", moduleName));
                    Wrapper.getConsole();
                    new(de.paxii.clarinet.event.events.player.PlayerSendChatMessageEvent.class)();
                    new CPacketChatMessage(ClientSettings.getValue("client.prefix", String.class) + "reload");
                    final CPacketChatMessage chatPacket;
                    new PlayerSendChatMessageEvent(chatPacket);
                    final PlayerSendChatMessageEvent event;
                    final ClientConsole clientConsole;
                    clientConsole.onChatMessage(event);
                }
            }).start();
        }
        catch (final IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void removeModule(final String moduleName) {
        try {
            String removedVersion = moduleName;
            if (Wrapper.getModuleManager().doesModuleExist(moduleName)) {
                final Module module = Wrapper.getModuleManager().getModule(moduleName);
                removedVersion = moduleName + "-" + module.getVersion();
                module.setEnabled(false);
                module.onShutdown();
                Wrapper.getModuleManager().removeModule(module);
                Wrapper.getClickableGui().loadPanels();
            }
            if (!ModuleStore.modulesToDelete.contains(removedVersion)) {
                ModuleStore.modulesToDelete.add(removedVersion);
            }
            Wrapper.getClient().getNotificationManager().addNotification("Module was successfully removed. Please restart the Client.", NotificationPriority.GOOD);
        }
        catch (final Exception e) {
            Wrapper.getClient().getNotificationManager().addNotification("There was an error when uninstalling the plugin.", NotificationPriority.GOOD);
            e.printStackTrace();
        }
    }
    
    public static TreeMap<String, ModuleEntry> listModules() {
        return ModuleStore.moduleList;
    }
    
    public static void fetchModules() {
        final String listPath = Client.getClientURL() + "modules/modules.php?version=" + Client.getGameVersion();
        final ModuleResponse moduleResponse = JsonFetcher.get(listPath, ModuleResponse.class);
        if (moduleResponse != null) {
            final ModuleEntry moduleEntry;
            moduleResponse.getModuleList().forEach(moduleEntry -> {
                final ModuleEntry moduleEntry2 = ModuleStore.moduleList.put(moduleEntry.getModule(), moduleEntry);
            });
        }
        else if (ModuleStore.moduleList.size() == 0) {
            final ModuleEntry moduleEntry = new ModuleEntry();
            moduleEntry.setModule("Error");
            moduleEntry.setDescription("Could not get list of external Modules!");
            moduleEntry.setCompatible("no");
            ModuleStore.moduleList.put("Error", moduleEntry);
        }
    }
    
    public static boolean isModuleInstalled(final String moduleName) {
        return ModuleStore.moduleList.containsKey(moduleName) && Wrapper.getModuleManager().getModuleList().containsKey(moduleName);
    }
    
    public static boolean isModuleUptoDate(final String moduleName) {
        return isModuleInstalled(moduleName) && listModules().get(moduleName).getBuild() <= Wrapper.getModuleManager().getModule(moduleName).getBuildVersion();
    }
    
    private void removeModules() {
        final File modulesToDelete = new File(ClientSettings.getClientFolderPath().getValue(), "modulesToDelete.json");
        if (modulesToDelete.exists()) {
            try {
                final ArrayList<String> modulesToRemove = FileService.getFileContents(modulesToDelete, new TypeToken<ArrayList<String>>() {}.getType());
                if (modulesToRemove != null) {
                    modulesToRemove.forEach(moduleName -> {
                        new File(getModuleFolder(), String.format("%s.jar", moduleName));
                        final File file;
                        final File moduleFile = file;
                        moduleFile.delete();
                        if (moduleName.contains("-")) {
                            final String oldModuleName = moduleName.split("-")[0];
                            new File(getModuleFolder(), String.format("%s.jar", oldModuleName));
                            final File file2;
                            final File oldModuleFile = file2;
                            if (oldModuleFile.exists()) {
                                oldModuleFile.delete();
                            }
                        }
                        return;
                    });
                }
                modulesToDelete.delete();
            }
            catch (final IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    @EventHandler
    public void onPostLoadModules(final PostLoadModulesEvent event) {
        fetchModules();
        final AtomicBoolean updateAvailable = new AtomicBoolean(false);
        ModuleStore.moduleList.forEach((moduleName, moduleEntry) -> {
            if (isModuleInstalled(moduleName) && !isModuleUptoDate(moduleName)) {
                updateAvailable.set(true);
            }
            return;
        });
        if (updateAvailable.get()) {
            Wrapper.getClient().getNotificationManager().addNotification("There are Updates available for installed Plugins!", NotificationPriority.DANGER, 10000L);
        }
    }
    
    @EventHandler
    public void onShutdown(final StopGameEvent event) {
        final File modulesToDelete = new File(ClientSettings.getClientFolderPath().getValue(), "modulesToDelete.json");
        try {
            if (ModuleStore.modulesToDelete.size() > 0) {
                modulesToDelete.createNewFile();
                FileService.setFileContentsAsJson(modulesToDelete, ModuleStore.modulesToDelete);
            }
        }
        catch (final IOException e) {
            e.printStackTrace();
        }
    }
    
    public static File getModuleFolder() {
        return new File(ClientSettings.getClientFolderPath().getValue() + "/modules/" + Client.getGameVersion() + "/");
    }
    
    public static ArrayList<String> getModulesToDelete() {
        return ModuleStore.modulesToDelete;
    }
    
    static {
        moduleUrl = Client.getClientURL() + "modules/files/%s/%s/%s.jar";
        moduleList = new TreeMap<String, ModuleEntry>();
        ModuleStore.modulesToDelete = new ArrayList<String>();
    }
    
    class ModuleResponse
    {
        private ArrayList<ModuleEntry> moduleList;
        
        public ModuleResponse(final ArrayList<ModuleEntry> moduleList) {
            this.moduleList = moduleList;
        }
        
        public ArrayList<ModuleEntry> getModuleList() {
            return this.moduleList;
        }
        
        public void setModuleList(final ArrayList<ModuleEntry> moduleList) {
            this.moduleList = moduleList;
        }
        
        @Override
        public boolean equals(final Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof ModuleResponse)) {
                return false;
            }
            final ModuleResponse other = (ModuleResponse)o;
            if (!other.canEqual(this)) {
                return false;
            }
            final Object this$moduleList = this.getModuleList();
            final Object other$moduleList = other.getModuleList();
            if (this$moduleList == null) {
                if (other$moduleList == null) {
                    return true;
                }
            }
            else if (this$moduleList.equals(other$moduleList)) {
                return true;
            }
            return false;
        }
        
        protected boolean canEqual(final Object other) {
            return other instanceof ModuleResponse;
        }
        
        @Override
        public int hashCode() {
            final int PRIME = 59;
            int result = 1;
            final Object $moduleList = this.getModuleList();
            result = result * 59 + (($moduleList == null) ? 43 : $moduleList.hashCode());
            return result;
        }
        
        @Override
        public String toString() {
            return "ModuleStore.ModuleResponse(moduleList=" + this.getModuleList() + ")";
        }
    }
}
