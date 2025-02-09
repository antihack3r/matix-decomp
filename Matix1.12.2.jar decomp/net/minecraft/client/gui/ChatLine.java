// 
// Decompiled by Procyon v0.6.0
// 

package net.minecraft.client.gui;

import net.minecraft.util.text.ITextComponent;

public class ChatLine
{
    private final int updateCounterCreated;
    private final ITextComponent lineString;
    private final int chatLineID;
    
    public ChatLine(final int updateCounterCreatedIn, final ITextComponent lineStringIn, final int chatLineIDIn) {
        this.lineString = lineStringIn;
        this.updateCounterCreated = updateCounterCreatedIn;
        this.chatLineID = chatLineIDIn;
    }
    
    public ITextComponent getChatComponent() {
        return this.lineString;
    }
    
    public int getUpdatedCounter() {
        return this.updateCounterCreated;
    }
    
    public int getChatLineID() {
        return this.chatLineID;
    }
}
