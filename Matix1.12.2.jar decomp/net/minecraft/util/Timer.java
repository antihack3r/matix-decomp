// 
// Decompiled by Procyon v0.6.0
// 

package net.minecraft.util;

import net.minecraft.client.Minecraft;

public class Timer
{
    public int elapsedTicks;
    public float renderPartialTicks;
    public float elapsedPartialTicks;
    private long lastSyncSysClock;
    private float tickLength;
    
    public Timer(final float tps) {
        this.tickLength = 1000.0f / tps;
        this.lastSyncSysClock = Minecraft.getSystemTime();
    }
    
    public void updateTimer() {
        final long i = Minecraft.getSystemTime();
        this.elapsedPartialTicks = (i - this.lastSyncSysClock) / this.tickLength;
        this.lastSyncSysClock = i;
        this.renderPartialTicks += this.elapsedPartialTicks;
        this.elapsedTicks = (int)this.renderPartialTicks;
        this.renderPartialTicks -= this.elapsedTicks;
    }
}
