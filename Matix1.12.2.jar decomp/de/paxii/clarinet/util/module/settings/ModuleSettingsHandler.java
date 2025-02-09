// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.util.module.settings;

import de.paxii.clarinet.util.settings.ClientSettings;
import java.util.Iterator;
import de.paxii.clarinet.event.events.game.LoadWorldEvent;
import de.paxii.clarinet.event.events.game.StopGameEvent;
import de.paxii.clarinet.event.EventHandler;
import com.google.gson.JsonSyntaxException;
import java.io.IOException;
import de.paxii.clarinet.util.file.FileService;
import de.paxii.clarinet.event.events.client.PostLoadModulesEvent;
import de.paxii.clarinet.Wrapper;
import java.io.File;
import de.paxii.clarinet.module.Module;
import java.util.ArrayList;

public class ModuleSettingsHandler
{
    private ArrayList<Module> enabledModules;
    private static final File SETTINGS_FOLDER;
    private static final File OLD_SETTINGS_FILE;
    
    public ModuleSettingsHandler() {
        this.enabledModules = new ArrayList<Module>();
        Wrapper.getEventManager().register(this);
    }
    
    @EventHandler
    public void onPostLoadModules(final PostLoadModulesEvent event) {
        new Thread(() -> {
            if (!ModuleSettingsHandler.SETTINGS_FOLDER.exists()) {
                ModuleSettingsHandler.SETTINGS_FOLDER.mkdirs();
                if (ModuleSettingsHandler.OLD_SETTINGS_FILE.exists()) {
                    this.loadModuleSettingsLegacy();
                }
            }
            else {
                Wrapper.getModuleManager().getModuleList().values().forEach(module -> {
                    final File moduleSettingsFile = getModuleSettingsFile(module.getName());
                    if (!(!moduleSettingsFile.exists())) {
                        try {
                            final ModuleSettingsObject moduleSettings = FileService.getFileContents(moduleSettingsFile, ModuleSettingsObject.class);
                            if (moduleSettings != null) {
                                if (moduleSettings.getModuleKey() < 0) {
                                    moduleSettings.setModuleKey(-1);
                                }
                                moduleSettings.restoreToModule(module);
                                if (moduleSettings.isEnabled()) {
                                    this.enabledModules.add(module);
                                }
                            }
                            module.onStartup();
                        }
                        catch (final IOException e) {
                            e.printStackTrace();
                        }
                        catch (final JsonSyntaxException e2) {
                            e2.printStackTrace();
                            moduleSettingsFile.delete();
                        }
                        catch (final Throwable t) {
                            t.printStackTrace();
                        }
                    }
                });
            }
        }).start();
    }
    
    @EventHandler
    public void onStopGame(final StopGameEvent event) {
        Wrapper.getModuleManager().getModuleList().values().forEach(module -> {
            final File settingsFile = getModuleSettingsFile(module.getName());
            try {
                settingsFile.delete();
                module.onShutdown();
                if (settingsFile.createNewFile()) {
                    FileService.setFileContentsAsJson(settingsFile, new ModuleSettingsObject(module));
                }
            }
            catch (final Exception e) {
                e.printStackTrace();
            }
            return;
        });
        Wrapper.getEventManager().unregister(this);
    }
    
    @EventHandler
    private void onLoadWorld(final LoadWorldEvent event) {
        this.enabledModules.forEach(module -> {
            if (!module.isEnabled()) {
                module.setEnabled(true);
            }
            return;
        });
        this.enabledModules.clear();
    }
    
    public static File getModuleSettingsFile(final String moduleName) {
        return new File(ModuleSettingsHandler.SETTINGS_FOLDER, String.format("%s.json", moduleName.replaceAll("[\\s|\\.]", "")));
    }
    
    @Deprecated
    public void loadModuleSettingsLegacy() {
        try {
            if (ModuleSettingsHandler.OLD_SETTINGS_FILE.exists()) {
                final ModuleSettingsContainer moduleSettingsContainer = FileService.getFileContents(ModuleSettingsHandler.OLD_SETTINGS_FILE, ModuleSettingsContainer.class);
                if (moduleSettingsContainer != null) {
                    Wrapper.getModuleManager().getModuleList().values().forEach(module -> {
                        moduleSettingsContainer.getModuleSettings().iterator();
                        final Iterator iterator;
                        while (iterator.hasNext()) {
                            final ModuleSettingsObject moduleSettings = iterator.next();
                            if (moduleSettings.getModuleName().equals(module.getName())) {
                                moduleSettings.restoreToModule(module);
                                if (moduleSettings.isEnabled()) {
                                    this.enabledModules.add(module);
                                    break;
                                }
                                else {
                                    break;
                                }
                            }
                        }
                        try {
                            module.onStartup();
                        }
                        catch (final Throwable t) {
                            t.printStackTrace();
                        }
                    });
                }
            }
        }
        catch (final Exception e) {
            e.printStackTrace();
        }
    }
    
    static {
        SETTINGS_FOLDER = new File(ClientSettings.getClientFolderPath().getValue(), "/settings");
        OLD_SETTINGS_FILE = new File(ClientSettings.getClientFolderPath().getValue(), "/modules.json");
    }
}
