// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.event;

import java.util.Iterator;
import de.paxii.clarinet.event.events.player.PlayerSendChatMessageEvent;
import net.minecraft.network.play.client.CPacketChatMessage;
import de.paxii.clarinet.event.events.player.PlayerSwingItemEvent;
import net.minecraft.network.play.client.CPacketAnimation;
import de.paxii.clarinet.event.events.game.SendPacketEvent;
import net.minecraft.network.Packet;
import java.util.ArrayList;

public class PacketPatcher
{
    private static ArrayList<Class<? extends Packet>> filterList;
    private static boolean shouldFilter;
    
    public static Packet getPatchedPacket(final Packet packetIn) {
        Packet packetOut = packetIn;
        final SendPacketEvent packageEvent = new SendPacketEvent(packetIn);
        EventManager.call(packageEvent);
        if (packageEvent.isCancelled()) {
            return null;
        }
        if (isShouldFilter()) {
            for (final Class blockedPacket : PacketPatcher.filterList) {
                if (packetIn.getClass() == blockedPacket) {
                    return null;
                }
            }
        }
        if (packetIn instanceof CPacketAnimation) {
            EventManager.call(new PlayerSwingItemEvent());
        }
        if (packetIn instanceof CPacketChatMessage) {
            final CPacketChatMessage tempPacket = packetIn;
            final String bypassPrefix = "_PASS_";
            if (tempPacket.getMessage().startsWith(bypassPrefix)) {
                final String chatMessage = tempPacket.getMessage().substring(bypassPrefix.length());
                packetOut = new CPacketChatMessage(chatMessage);
            }
            else {
                final PlayerSendChatMessageEvent messageEvent = new PlayerSendChatMessageEvent(tempPacket);
                EventManager.call(messageEvent);
                if (messageEvent.isCancelled()) {
                    return null;
                }
                packetOut = messageEvent.getPacket();
            }
        }
        return packetOut;
    }
    
    public static ArrayList<Class<? extends Packet>> getFilterList() {
        return PacketPatcher.filterList;
    }
    
    public static boolean isShouldFilter() {
        return PacketPatcher.shouldFilter;
    }
    
    public static void setShouldFilter(final boolean shouldFilter) {
        PacketPatcher.shouldFilter = shouldFilter;
    }
    
    static {
        PacketPatcher.filterList = new ArrayList<Class<? extends Packet>>();
        PacketPatcher.shouldFilter = false;
    }
}
