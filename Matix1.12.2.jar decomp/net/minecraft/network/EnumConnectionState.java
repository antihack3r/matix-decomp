// 
// Decompiled by Procyon v0.6.0
// 

package net.minecraft.network;

import java.util.Iterator;
import net.minecraft.network.login.client.CPacketEncryptionResponse;
import net.minecraft.network.login.client.CPacketLoginStart;
import net.minecraft.network.login.server.SPacketEnableCompression;
import net.minecraft.network.login.server.SPacketLoginSuccess;
import net.minecraft.network.login.server.SPacketEncryptionRequest;
import net.minecraft.network.status.server.SPacketPong;
import net.minecraft.network.status.client.CPacketPing;
import net.minecraft.network.status.server.SPacketServerInfo;
import net.minecraft.network.status.client.CPacketServerQuery;
import net.minecraft.network.play.client.CPacketPlayerTryUseItem;
import net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock;
import net.minecraft.network.play.client.CPacketSpectate;
import net.minecraft.network.play.client.CPacketAnimation;
import net.minecraft.network.play.client.CPacketUpdateSign;
import net.minecraft.network.play.client.CPacketCreativeInventoryAction;
import net.minecraft.network.play.client.CPacketHeldItemChange;
import net.minecraft.network.play.client.CPacketSeenAdvancements;
import net.minecraft.network.play.client.CPacketResourcePackStatus;
import net.minecraft.network.play.client.CPacketRecipeInfo;
import net.minecraft.network.play.client.CPacketInput;
import net.minecraft.network.play.client.CPacketEntityAction;
import net.minecraft.network.play.client.CPacketPlayerDigging;
import net.minecraft.network.play.client.CPacketPlayerAbilities;
import net.minecraft.network.play.client.CPacketPlaceRecipe;
import net.minecraft.network.play.client.CPacketSteerBoat;
import net.minecraft.network.play.client.CPacketVehicleMove;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.network.play.client.CPacketKeepAlive;
import net.minecraft.network.play.client.CPacketUseEntity;
import net.minecraft.network.play.client.CPacketCustomPayload;
import net.minecraft.network.play.client.CPacketCloseWindow;
import net.minecraft.network.play.client.CPacketClickWindow;
import net.minecraft.network.play.client.CPacketEnchantItem;
import net.minecraft.network.play.client.CPacketConfirmTransaction;
import net.minecraft.network.play.client.CPacketClientSettings;
import net.minecraft.network.play.client.CPacketClientStatus;
import net.minecraft.network.play.client.CPacketChatMessage;
import net.minecraft.network.play.client.CPacketTabComplete;
import net.minecraft.network.play.client.CPacketConfirmTeleport;
import net.minecraft.network.play.server.SPacketEntityEffect;
import net.minecraft.network.play.server.SPacketEntityProperties;
import net.minecraft.network.play.server.SPacketAdvancementInfo;
import net.minecraft.network.play.server.SPacketEntityTeleport;
import net.minecraft.network.play.server.SPacketCollectItem;
import net.minecraft.network.play.server.SPacketPlayerListHeaderFooter;
import net.minecraft.network.play.server.SPacketSoundEffect;
import net.minecraft.network.play.server.SPacketTitle;
import net.minecraft.network.play.server.SPacketTimeUpdate;
import net.minecraft.network.play.server.SPacketSpawnPosition;
import net.minecraft.network.play.server.SPacketUpdateScore;
import net.minecraft.network.play.server.SPacketTeams;
import net.minecraft.network.play.server.SPacketSetPassengers;
import net.minecraft.network.play.server.SPacketScoreboardObjective;
import net.minecraft.network.play.server.SPacketUpdateHealth;
import net.minecraft.network.play.server.SPacketSetExperience;
import net.minecraft.network.play.server.SPacketEntityEquipment;
import net.minecraft.network.play.server.SPacketEntityVelocity;
import net.minecraft.network.play.server.SPacketEntityAttach;
import net.minecraft.network.play.server.SPacketEntityMetadata;
import net.minecraft.network.play.server.SPacketDisplayObjective;
import net.minecraft.network.play.server.SPacketHeldItemChange;
import net.minecraft.network.play.server.SPacketCamera;
import net.minecraft.network.play.server.SPacketWorldBorder;
import net.minecraft.network.play.server.SPacketSelectAdvancementsTab;
import net.minecraft.network.play.server.SPacketEntityHeadLook;
import net.minecraft.network.play.server.SPacketRespawn;
import net.minecraft.network.play.server.SPacketResourcePackSend;
import net.minecraft.network.play.server.SPacketRemoveEntityEffect;
import net.minecraft.network.play.server.SPacketDestroyEntities;
import net.minecraft.network.play.server.SPacketRecipeBook;
import net.minecraft.network.play.server.SPacketUseBed;
import net.minecraft.network.play.server.SPacketPlayerPosLook;
import net.minecraft.network.play.server.SPacketPlayerListItem;
import net.minecraft.network.play.server.SPacketCombatEvent;
import net.minecraft.network.play.server.SPacketPlayerAbilities;
import net.minecraft.network.play.server.SPacketPlaceGhostRecipe;
import net.minecraft.network.play.server.SPacketSignEditorOpen;
import net.minecraft.network.play.server.SPacketMoveVehicle;
import net.minecraft.network.play.server.SPacketEntity;
import net.minecraft.network.play.server.SPacketMaps;
import net.minecraft.network.play.server.SPacketJoinGame;
import net.minecraft.network.play.server.SPacketParticles;
import net.minecraft.network.play.server.SPacketEffect;
import net.minecraft.network.play.server.SPacketChunkData;
import net.minecraft.network.play.server.SPacketKeepAlive;
import net.minecraft.network.play.server.SPacketChangeGameState;
import net.minecraft.network.play.server.SPacketUnloadChunk;
import net.minecraft.network.play.server.SPacketExplosion;
import net.minecraft.network.play.server.SPacketEntityStatus;
import net.minecraft.network.play.server.SPacketDisconnect;
import net.minecraft.network.play.server.SPacketCustomSound;
import net.minecraft.network.play.server.SPacketCustomPayload;
import net.minecraft.network.play.server.SPacketCooldown;
import net.minecraft.network.play.server.SPacketSetSlot;
import net.minecraft.network.play.server.SPacketWindowProperty;
import net.minecraft.network.play.server.SPacketWindowItems;
import net.minecraft.network.play.server.SPacketOpenWindow;
import net.minecraft.network.play.server.SPacketCloseWindow;
import net.minecraft.network.play.server.SPacketConfirmTransaction;
import net.minecraft.network.play.server.SPacketMultiBlockChange;
import net.minecraft.network.play.server.SPacketChat;
import net.minecraft.network.play.server.SPacketTabComplete;
import net.minecraft.network.play.server.SPacketServerDifficulty;
import net.minecraft.network.play.server.SPacketUpdateBossInfo;
import net.minecraft.network.play.server.SPacketBlockChange;
import net.minecraft.network.play.server.SPacketBlockAction;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.network.play.server.SPacketBlockBreakAnim;
import net.minecraft.network.play.server.SPacketStatistics;
import net.minecraft.network.play.server.SPacketAnimation;
import net.minecraft.network.play.server.SPacketSpawnPlayer;
import net.minecraft.network.play.server.SPacketSpawnPainting;
import net.minecraft.network.play.server.SPacketSpawnMob;
import net.minecraft.network.play.server.SPacketSpawnGlobalEntity;
import net.minecraft.network.play.server.SPacketSpawnExperienceOrb;
import net.minecraft.network.play.server.SPacketSpawnObject;
import net.minecraft.network.handshake.client.C00Handshake;
import javax.annotation.Nullable;
import org.apache.logging.log4j.LogManager;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.Maps;
import com.google.common.collect.BiMap;
import java.util.Map;

public enum EnumConnectionState
{
    HANDSHAKING(-1) {
        {
            this.registerPacket(EnumPacketDirection.SERVERBOUND, C00Handshake.class);
        }
    }, 
    PLAY(0) {
        {
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketSpawnObject.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketSpawnExperienceOrb.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketSpawnGlobalEntity.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketSpawnMob.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketSpawnPainting.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketSpawnPlayer.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketAnimation.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketStatistics.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketBlockBreakAnim.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketUpdateTileEntity.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketBlockAction.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketBlockChange.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketUpdateBossInfo.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketServerDifficulty.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketTabComplete.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketChat.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketMultiBlockChange.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketConfirmTransaction.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketCloseWindow.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketOpenWindow.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketWindowItems.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketWindowProperty.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketSetSlot.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketCooldown.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketCustomPayload.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketCustomSound.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketDisconnect.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketEntityStatus.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketExplosion.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketUnloadChunk.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketChangeGameState.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketKeepAlive.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketChunkData.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketEffect.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketParticles.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketJoinGame.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketMaps.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketEntity.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketEntity.S15PacketEntityRelMove.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketEntity.S17PacketEntityLookMove.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketEntity.S16PacketEntityLook.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketMoveVehicle.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketSignEditorOpen.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketPlaceGhostRecipe.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketPlayerAbilities.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketCombatEvent.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketPlayerListItem.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketPlayerPosLook.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketUseBed.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketRecipeBook.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketDestroyEntities.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketRemoveEntityEffect.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketResourcePackSend.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketRespawn.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketEntityHeadLook.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketSelectAdvancementsTab.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketWorldBorder.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketCamera.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketHeldItemChange.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketDisplayObjective.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketEntityMetadata.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketEntityAttach.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketEntityVelocity.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketEntityEquipment.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketSetExperience.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketUpdateHealth.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketScoreboardObjective.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketSetPassengers.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketTeams.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketUpdateScore.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketSpawnPosition.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketTimeUpdate.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketTitle.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketSoundEffect.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketPlayerListHeaderFooter.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketCollectItem.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketEntityTeleport.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketAdvancementInfo.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketEntityProperties.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketEntityEffect.class);
            this.registerPacket(EnumPacketDirection.SERVERBOUND, CPacketConfirmTeleport.class);
            this.registerPacket(EnumPacketDirection.SERVERBOUND, CPacketTabComplete.class);
            this.registerPacket(EnumPacketDirection.SERVERBOUND, CPacketChatMessage.class);
            this.registerPacket(EnumPacketDirection.SERVERBOUND, CPacketClientStatus.class);
            this.registerPacket(EnumPacketDirection.SERVERBOUND, CPacketClientSettings.class);
            this.registerPacket(EnumPacketDirection.SERVERBOUND, CPacketConfirmTransaction.class);
            this.registerPacket(EnumPacketDirection.SERVERBOUND, CPacketEnchantItem.class);
            this.registerPacket(EnumPacketDirection.SERVERBOUND, CPacketClickWindow.class);
            this.registerPacket(EnumPacketDirection.SERVERBOUND, CPacketCloseWindow.class);
            this.registerPacket(EnumPacketDirection.SERVERBOUND, CPacketCustomPayload.class);
            this.registerPacket(EnumPacketDirection.SERVERBOUND, CPacketUseEntity.class);
            this.registerPacket(EnumPacketDirection.SERVERBOUND, CPacketKeepAlive.class);
            this.registerPacket(EnumPacketDirection.SERVERBOUND, CPacketPlayer.class);
            this.registerPacket(EnumPacketDirection.SERVERBOUND, CPacketPlayer.Position.class);
            this.registerPacket(EnumPacketDirection.SERVERBOUND, CPacketPlayer.PositionRotation.class);
            this.registerPacket(EnumPacketDirection.SERVERBOUND, CPacketPlayer.Rotation.class);
            this.registerPacket(EnumPacketDirection.SERVERBOUND, CPacketVehicleMove.class);
            this.registerPacket(EnumPacketDirection.SERVERBOUND, CPacketSteerBoat.class);
            this.registerPacket(EnumPacketDirection.SERVERBOUND, CPacketPlaceRecipe.class);
            this.registerPacket(EnumPacketDirection.SERVERBOUND, CPacketPlayerAbilities.class);
            this.registerPacket(EnumPacketDirection.SERVERBOUND, CPacketPlayerDigging.class);
            this.registerPacket(EnumPacketDirection.SERVERBOUND, CPacketEntityAction.class);
            this.registerPacket(EnumPacketDirection.SERVERBOUND, CPacketInput.class);
            this.registerPacket(EnumPacketDirection.SERVERBOUND, CPacketRecipeInfo.class);
            this.registerPacket(EnumPacketDirection.SERVERBOUND, CPacketResourcePackStatus.class);
            this.registerPacket(EnumPacketDirection.SERVERBOUND, CPacketSeenAdvancements.class);
            this.registerPacket(EnumPacketDirection.SERVERBOUND, CPacketHeldItemChange.class);
            this.registerPacket(EnumPacketDirection.SERVERBOUND, CPacketCreativeInventoryAction.class);
            this.registerPacket(EnumPacketDirection.SERVERBOUND, CPacketUpdateSign.class);
            this.registerPacket(EnumPacketDirection.SERVERBOUND, CPacketAnimation.class);
            this.registerPacket(EnumPacketDirection.SERVERBOUND, CPacketSpectate.class);
            this.registerPacket(EnumPacketDirection.SERVERBOUND, CPacketPlayerTryUseItemOnBlock.class);
            this.registerPacket(EnumPacketDirection.SERVERBOUND, CPacketPlayerTryUseItem.class);
        }
    }, 
    STATUS(1) {
        {
            this.registerPacket(EnumPacketDirection.SERVERBOUND, CPacketServerQuery.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketServerInfo.class);
            this.registerPacket(EnumPacketDirection.SERVERBOUND, CPacketPing.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketPong.class);
        }
    }, 
    LOGIN(2) {
        {
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, net.minecraft.network.login.server.SPacketDisconnect.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketEncryptionRequest.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketLoginSuccess.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketEnableCompression.class);
            this.registerPacket(EnumPacketDirection.SERVERBOUND, CPacketLoginStart.class);
            this.registerPacket(EnumPacketDirection.SERVERBOUND, CPacketEncryptionResponse.class);
        }
    };
    
    private static final EnumConnectionState[] STATES_BY_ID;
    private static final Map<Class<? extends Packet<?>>, EnumConnectionState> STATES_BY_CLASS;
    private final int id;
    private final Map<EnumPacketDirection, BiMap<Integer, Class<? extends Packet<?>>>> directionMaps;
    
    private EnumConnectionState(final int protocolId) {
        this.directionMaps = Maps.newEnumMap((Class)EnumPacketDirection.class);
        this.id = protocolId;
    }
    
    protected EnumConnectionState registerPacket(final EnumPacketDirection direction, final Class<? extends Packet<?>> packetClass) {
        BiMap<Integer, Class<? extends Packet<?>>> bimap = this.directionMaps.get(direction);
        if (bimap == null) {
            bimap = (BiMap<Integer, Class<? extends Packet<?>>>)HashBiMap.create();
            this.directionMaps.put(direction, bimap);
        }
        if (bimap.containsValue((Object)packetClass)) {
            final String s = direction + " packet " + packetClass + " is already known to ID " + bimap.inverse().get((Object)packetClass);
            LogManager.getLogger().fatal(s);
            throw new IllegalArgumentException(s);
        }
        bimap.put((Object)bimap.size(), (Object)packetClass);
        return this;
    }
    
    public Integer getPacketId(final EnumPacketDirection direction, final Packet<?> packetIn) throws Exception {
        return (Integer)this.directionMaps.get(direction).inverse().get((Object)packetIn.getClass());
    }
    
    @Nullable
    public Packet<?> getPacket(final EnumPacketDirection direction, final int packetId) throws InstantiationException, IllegalAccessException {
        final Class<? extends Packet<?>> oclass = (Class<? extends Packet<?>>)this.directionMaps.get(direction).get((Object)packetId);
        return (oclass == null) ? null : ((Packet)oclass.newInstance());
    }
    
    public int getId() {
        return this.id;
    }
    
    public static EnumConnectionState getById(final int stateId) {
        return (stateId >= -1 && stateId <= 2) ? EnumConnectionState.STATES_BY_ID[stateId + 1] : null;
    }
    
    public static EnumConnectionState getFromPacket(final Packet<?> packetIn) {
        return EnumConnectionState.STATES_BY_CLASS.get(packetIn.getClass());
    }
    
    static {
        STATES_BY_ID = new EnumConnectionState[4];
        STATES_BY_CLASS = Maps.newHashMap();
        for (final EnumConnectionState enumconnectionstate : values()) {
            final int i = enumconnectionstate.getId();
            if (i < -1 || i > 2) {
                throw new Error("Invalid protocol ID " + Integer.toString(i));
            }
            EnumConnectionState.STATES_BY_ID[i + 1] = enumconnectionstate;
            for (final EnumPacketDirection enumpacketdirection : enumconnectionstate.directionMaps.keySet()) {
                for (final Class<? extends Packet<?>> oclass : enumconnectionstate.directionMaps.get(enumpacketdirection).values()) {
                    if (EnumConnectionState.STATES_BY_CLASS.containsKey(oclass) && EnumConnectionState.STATES_BY_CLASS.get(oclass) != enumconnectionstate) {
                        throw new Error("Packet " + oclass + " is already assigned to protocol " + EnumConnectionState.STATES_BY_CLASS.get(oclass) + " - can't reassign to " + enumconnectionstate);
                    }
                    try {
                        oclass.newInstance();
                    }
                    catch (final Throwable var10) {
                        throw new Error("Packet " + oclass + " fails instantiation checks! " + oclass);
                    }
                    EnumConnectionState.STATES_BY_CLASS.put(oclass, enumconnectionstate);
                }
            }
        }
    }
}
