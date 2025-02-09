// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.module.player;

import de.paxii.clarinet.event.EventHandler;
import de.paxii.clarinet.Wrapper;
import net.minecraft.entity.player.EnumPlayerModelParts;
import de.paxii.clarinet.event.events.player.PreMotionUpdateEvent;
import de.paxii.clarinet.module.ModuleCategory;
import java.util.Random;
import de.paxii.clarinet.module.Module;

public class ModuleSkinDerp extends Module
{
    private final Random random;
    
    public ModuleSkinDerp() {
        super("SkinDerp", ModuleCategory.PLAYER);
        this.setRegistered(true);
        this.setDescription("Rapidly enables and disables layers of your skin.");
        this.random = new Random();
    }
    
    @EventHandler
    public void preMotion(final PreMotionUpdateEvent event) {
        for (int i = 0; i < 2; ++i) {
            final int maxSize = EnumPlayerModelParts.values().length - 1;
            final EnumPlayerModelParts modelPart = EnumPlayerModelParts.values()[this.random.nextInt(maxSize) + 1];
            Wrapper.getGameSettings().setModelPartEnabled(modelPart, this.random.nextBoolean());
        }
    }
    
    @Override
    public void onDisable() {
        for (final EnumPlayerModelParts modelPart : EnumPlayerModelParts.values()) {
            Wrapper.getMinecraft().gameSettings.setModelPartEnabled(modelPart, true);
        }
    }
}
