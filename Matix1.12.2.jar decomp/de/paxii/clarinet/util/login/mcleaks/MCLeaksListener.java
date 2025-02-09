// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.util.login.mcleaks;

import de.paxii.clarinet.event.EventHandler;
import java.lang.reflect.Field;
import io.netty.channel.ChannelHandler;
import io.netty.channel.Channel;
import net.minecraft.network.NetworkManager;
import net.minecraft.client.multiplayer.GuiConnecting;
import de.paxii.clarinet.Wrapper;
import de.paxii.clarinet.event.events.gui.DisplayGuiScreenEvent;

public class MCLeaksListener
{
    @EventHandler
    public void onDisplayGui(final DisplayGuiScreenEvent screenEvent) {
        if (Wrapper.getClient().getMcLeaksManager().isUsingMcLeaks() && screenEvent.getGuiScreen() instanceof GuiConnecting) {
            new Thread(() -> {
                try {
                    final Field networkManagerField = GuiConnecting.class.getDeclaredField("networkManager");
                    final Field channelField = NetworkManager.class.getDeclaredField("channel");
                    channelField.setAccessible(true);
                    networkManagerField.setAccessible(true);
                    NetworkManager networkManager;
                    for (networkManager = null; networkManager == null; networkManager = (NetworkManager)networkManagerField.get(screenEvent.getGuiScreen())) {
                        try {
                            Thread.sleep(2L);
                        }
                        catch (final InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    Wrapper.getClient().getMcLeaksManager().setNetworkManager(networkManager);
                    final Channel channel = (Channel)channelField.get(networkManager);
                    channel.pipeline().addBefore("packet_handler", "mcleaks_handler", (ChannelHandler)new MCLeaksChannelHandler());
                }
                catch (final ReflectiveOperationException e2) {
                    e2.printStackTrace();
                }
            }).start();
        }
    }
}
