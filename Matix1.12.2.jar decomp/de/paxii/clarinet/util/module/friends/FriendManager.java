// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.util.module.friends;

import java.awt.Color;
import de.paxii.clarinet.Wrapper;
import de.paxii.clarinet.event.events.client.RemoveFriendEvent;
import de.paxii.clarinet.event.EventManager;
import de.paxii.clarinet.event.events.client.AddFriendEvent;
import java.util.HashMap;

public class FriendManager
{
    private HashMap<String, Integer> friendList;
    
    public FriendManager() {
        this.friendList = new HashMap<String, Integer>();
    }
    
    public boolean addFriend(final String friendName) {
        return this.addFriend(friendName, 65280);
    }
    
    public boolean addFriend(final String friendName, final int friendColor) {
        if (!this.friendList.containsKey(friendName)) {
            this.friendList.put(friendName, friendColor);
            EventManager.call(new AddFriendEvent(friendName));
            return true;
        }
        return false;
    }
    
    public boolean removeFriend(final String friendName) {
        if (this.friendList.containsKey(friendName)) {
            this.friendList.remove(friendName);
            EventManager.call(new RemoveFriendEvent(friendName));
            return true;
        }
        return false;
    }
    
    public boolean isFriend(final String friendName) {
        return this.friendList.containsKey(friendName) || friendName.equals(Wrapper.getPlayer().getName());
    }
    
    public int getFriendColor(final String friendName) {
        return this.getFriends().getOrDefault(friendName, 16711680);
    }
    
    public Color getFriendColorObject(final String friendName) {
        final int friendColor = this.getFriendColor(friendName);
        return new Color(friendColor);
    }
    
    public HashMap<String, Integer> getFriends() {
        return this.friendList;
    }
    
    public void setFriendList(final HashMap<String, Integer> friendList) {
        this.friendList = friendList;
    }
}
