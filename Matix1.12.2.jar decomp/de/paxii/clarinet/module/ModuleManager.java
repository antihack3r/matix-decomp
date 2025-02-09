// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.module;

import de.paxii.clarinet.event.EventHandler;
import de.paxii.clarinet.util.settings.ClientSettings;
import de.paxii.clarinet.event.events.game.KeyPressedEvent;
import java.util.Map;
import java.util.Iterator;
import de.paxii.clarinet.util.module.store.ModuleStore;
import de.paxii.clarinet.event.EventManager;
import de.paxii.clarinet.event.events.client.PostLoadModulesEvent;
import de.paxii.clarinet.util.reflection.ReflectionHelper;
import de.paxii.clarinet.Wrapper;
import de.paxii.clarinet.module.external.ExternalModuleLoader;
import java.util.concurrent.ConcurrentHashMap;

public class ModuleManager
{
    private final ConcurrentHashMap<String, Module> moduleList;
    private final ConcurrentHashMap<String, Integer> keybindList;
    private ExternalModuleLoader externalModuleLoader;
    
    public ModuleManager() {
        this.moduleList = new ConcurrentHashMap<String, Module>();
        this.keybindList = new ConcurrentHashMap<String, Integer>();
        this.externalModuleLoader = new ExternalModuleLoader();
        Wrapper.getEventManager().register(this);
        new Thread(this::addModules).start();
    }
    
    public void reloadModules() {
        this.addModules();
    }
    
    private void addModules() {
        for (final ModuleCategory m : ModuleCategory.values()) {
            final String packageName = "de.paxii.clarinet.module." + m.toString().toLowerCase();
            System.out.println("Searching for Modules in " + packageName);
            try {
                final Class<? extends Module>[] classesInPackageBySuperType;
                final Class<? extends Module>[] moduleClasses = classesInPackageBySuperType = ReflectionHelper.getClassesInPackageBySuperType(packageName, Module.class);
                for (final Class<? extends Module> moduleClass : classesInPackageBySuperType) {
                    try {
                        final Module newModule = (Module)moduleClass.newInstance();
                        this.addModule(newModule);
                    }
                    catch (final Exception x) {
                        x.printStackTrace();
                    }
                }
            }
            catch (final Exception x2) {
                x2.printStackTrace();
            }
        }
        this.externalModuleLoader.loadModules(() -> {
            final PostLoadModulesEvent postLoadModulesEvent = EventManager.call(new PostLoadModulesEvent());
        });
    }
    
    public void addModule(final Module module) {
        for (final String blocked : ModuleStore.getModulesToDelete()) {
            if (module.getName().equals(blocked)) {
                return;
            }
        }
        if (!this.doesModuleExist(module.getName())) {
            System.out.println("Loading " + module.getName() + "...");
            this.moduleList.put(module.getName(), module);
        }
    }
    
    public void removeModule(final Module module) {
        if (this.doesModuleExist(module.getName())) {
            System.out.println("Removing " + module.getName() + "...");
            this.moduleList.remove(module.getName());
        }
    }
    
    public boolean doesModuleExist(final String moduleName) {
        for (final Map.Entry<String, Module> command : this.moduleList.entrySet()) {
            if (command.getKey().equalsIgnoreCase(moduleName)) {
                return true;
            }
        }
        return false;
    }
    
    public Module getModule(final String moduleName) {
        if (this.doesModuleExist(moduleName)) {
            return this.moduleList.get(moduleName);
        }
        return null;
    }
    
    public Module getModuleIgnoreCase(final String moduleName) {
        for (final Map.Entry<String, Module> command : this.moduleList.entrySet()) {
            if (command.getKey().equalsIgnoreCase(moduleName)) {
                return command.getValue();
            }
        }
        return null;
    }
    
    public ConcurrentHashMap<String, Module> getModulesByCategory(final ModuleCategory moduleCategory) {
        final ConcurrentHashMap<String, Module> modules = new ConcurrentHashMap<String, Module>();
        this.moduleList.forEach((k, m) -> {
            if (m.getCategory() == moduleCategory) {
                modules.put(k, m);
            }
            return;
        });
        return modules;
    }
    
    public boolean isModuleActive(final String moduleName) {
        final Module module = this.getModule(moduleName);
        return module != null && module.isEnabled();
    }
    
    @EventHandler
    private void handleKeyPress(final KeyPressedEvent event) {
        if (!ClientSettings.getValue("client.hidden", Boolean.class)) {
            for (final Map.Entry<String, Integer> entry : this.keybindList.entrySet()) {
                final String moduleName = entry.getKey();
                final int moduleKey = entry.getValue();
                if (moduleKey == event.getKey()) {
                    final Module module = this.getModule(moduleName);
                    if (module == null) {
                        continue;
                    }
                    module.toggle();
                }
            }
        }
    }
    
    public void addKey(final String moduleName, final int key) {
        if (this.keybindList.containsKey(moduleName)) {
            this.removeKey(moduleName);
        }
        this.keybindList.put(moduleName, key);
    }
    
    public void removeKey(final String moduleName) {
        this.keybindList.remove(moduleName);
    }
    
    public ConcurrentHashMap<String, Module> getModuleList() {
        return this.moduleList;
    }
    
    public ConcurrentHashMap<String, Integer> getKeybindList() {
        return this.keybindList;
    }
    
    public ExternalModuleLoader getExternalModuleLoader() {
        return this.externalModuleLoader;
    }
}
