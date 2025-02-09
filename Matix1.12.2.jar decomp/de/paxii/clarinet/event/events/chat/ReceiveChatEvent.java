// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.event.events.chat;

import de.paxii.clarinet.event.events.type.EventCancellable;

public class ReceiveChatEvent extends EventCancellable
{
    private String chatMessage;
    
    public ReceiveChatEvent(final String chatMessage) {
        this.chatMessage = chatMessage;
    }
    
    public String getChatMessage() {
        return this.chatMessage;
    }
    
    public void setChatMessage(final String chatMessage) {
        this.chatMessage = chatMessage;
    }
}
