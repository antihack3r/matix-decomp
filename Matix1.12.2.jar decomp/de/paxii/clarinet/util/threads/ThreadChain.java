// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.util.threads;

import java.util.ArrayList;

public class ThreadChain
{
    private final ArrayList<Thread> threadList;
    
    public ThreadChain() {
        this.threadList = new ArrayList<Thread>();
    }
    
    public ThreadChain chainRunnable(final Runnable runnable) {
        return this.chainThread(new Thread(runnable));
    }
    
    public ThreadChain chainThread(final Thread thread) {
        this.threadList.add(thread);
        return this;
    }
    
    public void next() {
        if (this.threadList.size() > 0) {
            this.threadList.get(0).start();
            this.threadList.remove(0);
        }
    }
    
    public void kickOff() {
        this.next();
    }
}
