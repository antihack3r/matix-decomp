// 
// Decompiled by Procyon v0.6.0
// 

package net.minecraft.client.multiplayer;

import org.apache.logging.log4j.LogManager;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketSeenAdvancements;
import java.util.Iterator;
import net.minecraft.client.gui.toasts.IToast;
import net.minecraft.client.gui.toasts.AdvancementToast;
import net.minecraft.util.ResourceLocation;
import net.minecraft.network.play.server.SPacketAdvancementInfo;
import com.google.common.collect.Maps;
import javax.annotation.Nullable;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.Advancement;
import java.util.Map;
import net.minecraft.advancements.AdvancementList;
import net.minecraft.client.Minecraft;
import org.apache.logging.log4j.Logger;

public class ClientAdvancementManager
{
    private static final Logger LOGGER;
    private final Minecraft mc;
    private final AdvancementList advancementList;
    private final Map<Advancement, AdvancementProgress> advancementToProgress;
    @Nullable
    private IListener listener;
    @Nullable
    private Advancement selectedTab;
    
    public ClientAdvancementManager(final Minecraft p_i47380_1_) {
        this.advancementList = new AdvancementList();
        this.advancementToProgress = Maps.newHashMap();
        this.mc = p_i47380_1_;
    }
    
    public void read(final SPacketAdvancementInfo p_192799_1_) {
        if (p_192799_1_.isFirstSync()) {
            this.advancementList.clear();
            this.advancementToProgress.clear();
        }
        this.advancementList.removeAll(p_192799_1_.getAdvancementsToRemove());
        this.advancementList.loadAdvancements(p_192799_1_.getAdvancementsToAdd());
        for (final Map.Entry<ResourceLocation, AdvancementProgress> entry : p_192799_1_.getProgressUpdates().entrySet()) {
            final Advancement advancement = this.advancementList.getAdvancement(entry.getKey());
            if (advancement != null) {
                final AdvancementProgress advancementprogress = entry.getValue();
                advancementprogress.update(advancement.getCriteria(), advancement.getRequirements());
                this.advancementToProgress.put(advancement, advancementprogress);
                if (this.listener != null) {
                    this.listener.onUpdateAdvancementProgress(advancement, advancementprogress);
                }
                if (p_192799_1_.isFirstSync() || !advancementprogress.isDone() || advancement.getDisplay() == null || !advancement.getDisplay().shouldShowToast()) {
                    continue;
                }
                this.mc.getToastGui().add(new AdvancementToast(advancement));
            }
            else {
                ClientAdvancementManager.LOGGER.warn("Server informed client about progress for unknown advancement " + entry.getKey());
            }
        }
    }
    
    public AdvancementList getAdvancementList() {
        return this.advancementList;
    }
    
    public void setSelectedTab(@Nullable final Advancement p_194230_1_, final boolean tellServer) {
        final NetHandlerPlayClient nethandlerplayclient = this.mc.getConnection();
        if (nethandlerplayclient != null && p_194230_1_ != null && tellServer) {
            nethandlerplayclient.sendPacket(CPacketSeenAdvancements.openedTab(p_194230_1_));
        }
        if (this.selectedTab != p_194230_1_) {
            this.selectedTab = p_194230_1_;
            if (this.listener != null) {
                this.listener.setSelectedTab(p_194230_1_);
            }
        }
    }
    
    public void setListener(@Nullable final IListener p_192798_1_) {
        this.listener = p_192798_1_;
        this.advancementList.setListener(p_192798_1_);
        if (p_192798_1_ != null) {
            for (final Map.Entry<Advancement, AdvancementProgress> entry : this.advancementToProgress.entrySet()) {
                p_192798_1_.onUpdateAdvancementProgress(entry.getKey(), entry.getValue());
            }
            p_192798_1_.setSelectedTab(this.selectedTab);
        }
    }
    
    static {
        LOGGER = LogManager.getLogger();
    }
    
    public interface IListener extends AdvancementList.Listener
    {
        void onUpdateAdvancementProgress(final Advancement p0, final AdvancementProgress p1);
        
        void setSelectedTab(final Advancement p0);
    }
}
