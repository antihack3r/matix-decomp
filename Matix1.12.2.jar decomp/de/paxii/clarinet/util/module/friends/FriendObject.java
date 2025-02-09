// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.util.module.friends;

public class FriendObject
{
    private String friendName;
    private int friendColor;
    
    public FriendObject(final String friendName, final int friendColor) {
        this.friendName = friendName;
        this.friendColor = friendColor;
    }
    
    public String getFriendName() {
        return this.friendName;
    }
    
    public int getFriendColor() {
        return this.friendColor;
    }
}
