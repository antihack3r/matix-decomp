// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.util.login.mcleaks;

import java.security.PublicKey;
import javax.crypto.SecretKey;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import net.minecraft.network.login.client.CPacketEncryptionResponse;
import java.net.InetSocketAddress;
import java.math.BigInteger;
import net.minecraft.util.CryptManager;
import net.minecraft.network.NetworkManager;
import io.netty.channel.ChannelHandler;
import net.minecraft.network.login.server.SPacketEncryptionRequest;
import de.paxii.clarinet.Wrapper;
import java.util.List;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.network.Packet;
import io.netty.handler.codec.MessageToMessageDecoder;

public class MCLeaksChannelHandler extends MessageToMessageDecoder<Packet>
{
    protected void decode(final ChannelHandlerContext channelHandlerContext, final Packet packet, final List<Object> list) throws Exception {
        if (Wrapper.getClient().getMcLeaksManager().isUsingMcLeaks() && packet instanceof SPacketEncryptionRequest) {
            this.handleEncryptionPacket(Wrapper.getClient().getMcLeaksManager().getNetworkManager(), (SPacketEncryptionRequest)packet);
            channelHandlerContext.pipeline().remove((ChannelHandler)this);
        }
        else {
            list.add(packet);
        }
    }
    
    private void handleEncryptionPacket(final NetworkManager networkManager, final SPacketEncryptionRequest encryptionRequest) {
        try {
            final SecretKey secretkey = CryptManager.createNewSharedKey();
            final String serverId = encryptionRequest.getServerId();
            final PublicKey publickey = encryptionRequest.getPublicKey();
            final String encryptionKey = new BigInteger(CryptManager.getServerIdHash(serverId, publickey, secretkey)).toString(16);
            final InetSocketAddress remoteAddress = (InetSocketAddress)networkManager.getRemoteAddress();
            final MCLeaksManager mcLeaksManager = Wrapper.getClient().getMcLeaksManager();
            MCLeaksLoginBridge.joinServer(mcLeaksManager.getMcLeaksSession(), mcLeaksManager.getMcLeaksName(), remoteAddress.getHostName() + ":" + remoteAddress.getPort(), encryptionKey, () -> networkManager.sendPacket(new CPacketEncryptionResponse(secretkey, publickey, encryptionRequest.getVerifyToken()), (GenericFutureListener<? extends Future<? super Void>>)(p_operationComplete_1_ -> networkManager.enableEncryption(secretkey)), (GenericFutureListener<? extends Future<? super Void>>[])new GenericFutureListener[0]));
        }
        catch (final Exception x) {
            x.printStackTrace();
        }
    }
}
