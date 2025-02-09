// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.module.movement;

import de.paxii.clarinet.module.ModuleCategory;
import de.paxii.clarinet.module.Module;

public class ModuleNoItemSlowdown extends Module
{
    public ModuleNoItemSlowdown() {
        super("NoItemSlowdown", ModuleCategory.MOVEMENT);
        this.setDescription("Allows you to run while using items or eating food.");
    }
}
