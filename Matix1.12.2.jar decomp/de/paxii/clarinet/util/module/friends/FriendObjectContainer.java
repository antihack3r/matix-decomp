// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.util.module.friends;

import java.util.HashMap;

public class FriendObjectContainer
{
    private HashMap<String, Integer> friendList;
    
    public FriendObjectContainer(final HashMap<String, Integer> friendList) {
        this.friendList = friendList;
    }
    
    public HashMap<String, Integer> getFriendList() {
        return this.friendList;
    }
    
    public void setFriendList(final HashMap<String, Integer> friendList) {
        this.friendList = friendList;
    }
}
