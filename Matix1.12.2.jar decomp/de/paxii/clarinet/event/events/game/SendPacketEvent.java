// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.event.events.game;

import net.minecraft.network.Packet;
import de.paxii.clarinet.event.events.type.EventCancellable;

public class SendPacketEvent extends EventCancellable
{
    private Packet packet;
    
    public SendPacketEvent(final Packet packet) {
        this.packet = packet;
    }
    
    public Packet getPacket() {
        return this.packet;
    }
    
    public void setPacket(final Packet packet) {
        this.packet = packet;
    }
}
