// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.util.module.killaura;

import java.util.Random;

public class TimeManager
{
    private final Random utilRandom;
    private int currentRandomness;
    private long current;
    private long last;
    private int randomness;
    private boolean random;
    
    public TimeManager() {
        this.utilRandom = new Random();
        this.createNewRandomDelay();
    }
    
    public void updateTimer() {
        this.current = this.getCurrentMillis();
    }
    
    public void updateTimer(final int newRandomness) {
        this.randomness = newRandomness;
        this.updateTimer();
    }
    
    public boolean sleep(final long delay) {
        final boolean slept = this.current - this.last > delay + this.currentRandomness;
        if (slept) {
            this.createNewRandomDelay();
        }
        return slept;
    }
    
    public final void updateLast() {
        this.last = this.getCurrentMillis();
    }
    
    public long getCurrentMillis() {
        return System.nanoTime() / 1000000L;
    }
    
    private void createNewRandomDelay() {
        if (this.isRandom()) {
            this.currentRandomness = this.utilRandom.nextInt((this.randomness == 0) ? 200 : this.randomness);
        }
    }
    
    public long getCurrent() {
        return this.current;
    }
    
    public long getLast() {
        return this.last;
    }
    
    public void setCurrent(final long current) {
        this.current = current;
    }
    
    public void setLast(final long last) {
        this.last = last;
    }
    
    public int getRandomness() {
        return this.randomness;
    }
    
    public void setRandomness(final int randomness) {
        this.randomness = randomness;
    }
    
    public boolean isRandom() {
        return this.random;
    }
    
    public void setRandom(final boolean random) {
        this.random = random;
    }
}
