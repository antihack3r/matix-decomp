// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.event.events.game;

import net.minecraft.world.World;
import de.paxii.clarinet.event.events.Event;

public class LoadWorldEvent implements Event
{
    private final World world;
    
    public LoadWorldEvent(final World world) {
        this.world = world;
    }
    
    public World getWorld() {
        return this.world;
    }
}
