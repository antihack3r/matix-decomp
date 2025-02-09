// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.event.events.player;

import net.minecraft.network.play.client.CPacketChatMessage;
import de.paxii.clarinet.event.events.type.EventCancellable;

public class PlayerSendChatMessageEvent extends EventCancellable
{
    public static final String BYPASS_PREFIX = "_PASS_";
    private CPacketChatMessage chatPacket;
    
    public PlayerSendChatMessageEvent(final CPacketChatMessage chatPacket) {
        this.chatPacket = chatPacket;
    }
    
    public String getChatMessage() {
        return this.chatPacket.getMessage();
    }
    
    public void setChatMessage(final String newMessage) {
        this.chatPacket = new CPacketChatMessage(newMessage);
    }
    
    public CPacketChatMessage getPacket() {
        return this.chatPacket;
    }
}
