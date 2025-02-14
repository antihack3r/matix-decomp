// 
// Decompiled by Procyon v0.6.0
// 

package net.minecraft.world;

import net.minecraft.world.storage.WorldSavedData;
import net.minecraft.village.VillageCollection;
import net.minecraft.world.border.WorldBorder;
import net.minecraft.world.border.IBorderListener;
import net.minecraft.world.storage.WorldInfo;
import net.minecraft.world.storage.DerivedWorldInfo;
import net.minecraft.profiler.Profiler;
import net.minecraft.world.storage.ISaveHandler;
import net.minecraft.server.MinecraftServer;

public class WorldServerMulti extends WorldServer
{
    private final WorldServer delegate;
    
    public WorldServerMulti(final MinecraftServer server, final ISaveHandler saveHandlerIn, final int dimensionId, final WorldServer delegate, final Profiler profilerIn) {
        super(server, saveHandlerIn, new DerivedWorldInfo(delegate.getWorldInfo()), dimensionId, profilerIn);
        this.delegate = delegate;
        delegate.getWorldBorder().addListener(new IBorderListener() {
            @Override
            public void onSizeChanged(final WorldBorder border, final double newSize) {
                WorldServerMulti.this.getWorldBorder().setTransition(newSize);
            }
            
            @Override
            public void onTransitionStarted(final WorldBorder border, final double oldSize, final double newSize, final long time) {
                WorldServerMulti.this.getWorldBorder().setTransition(oldSize, newSize, time);
            }
            
            @Override
            public void onCenterChanged(final WorldBorder border, final double x, final double z) {
                WorldServerMulti.this.getWorldBorder().setCenter(x, z);
            }
            
            @Override
            public void onWarningTimeChanged(final WorldBorder border, final int newTime) {
                WorldServerMulti.this.getWorldBorder().setWarningTime(newTime);
            }
            
            @Override
            public void onWarningDistanceChanged(final WorldBorder border, final int newDistance) {
                WorldServerMulti.this.getWorldBorder().setWarningDistance(newDistance);
            }
            
            @Override
            public void onDamageAmountChanged(final WorldBorder border, final double newAmount) {
                WorldServerMulti.this.getWorldBorder().setDamageAmount(newAmount);
            }
            
            @Override
            public void onDamageBufferChanged(final WorldBorder border, final double newSize) {
                WorldServerMulti.this.getWorldBorder().setDamageBuffer(newSize);
            }
        });
    }
    
    @Override
    protected void saveLevel() throws MinecraftException {
    }
    
    @Override
    public World init() {
        this.mapStorage = this.delegate.getMapStorage();
        this.worldScoreboard = this.delegate.getScoreboard();
        this.lootTable = this.delegate.getLootTableManager();
        this.advancementManager = this.delegate.getAdvancementManager();
        final String s = VillageCollection.fileNameForProvider(this.provider);
        final VillageCollection villagecollection = (VillageCollection)this.mapStorage.getOrLoadData(VillageCollection.class, s);
        if (villagecollection == null) {
            this.villageCollection = new VillageCollection(this);
            this.mapStorage.setData(s, this.villageCollection);
        }
        else {
            (this.villageCollection = villagecollection).setWorldsForAll(this);
        }
        return this;
    }
    
    public void saveAdditionalData() {
        this.provider.onWorldSave();
    }
}
