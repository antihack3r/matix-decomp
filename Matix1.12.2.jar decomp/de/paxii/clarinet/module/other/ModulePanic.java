// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.module.other;

import java.util.Iterator;
import de.paxii.clarinet.Wrapper;
import de.paxii.clarinet.module.ModuleCategory;
import java.util.ArrayList;
import de.paxii.clarinet.module.Module;

public class ModulePanic extends Module
{
    private ArrayList<Module> enabledModules;
    
    public ModulePanic() {
        super("Panic", ModuleCategory.OTHER);
        this.enabledModules = new ArrayList<Module>();
        this.setDescription("Turns all modules off.");
    }
    
    @Override
    public void onEnable() {
        this.enabledModules.clear();
        Wrapper.getModuleManager().getModuleList().values().forEach(module -> {
            if (module.isEnabled() && module != this) {
                this.enabledModules.add(module);
                module.setEnabled(false);
            }
        });
    }
    
    @Override
    public void onDisable() {
        for (final Module module : this.enabledModules) {
            module.setEnabled(true);
        }
    }
}
