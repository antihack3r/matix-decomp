// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.module.player;

import de.paxii.clarinet.event.EventHandler;
import net.minecraft.entity.Entity;
import de.paxii.clarinet.util.chat.Chat;
import net.minecraft.entity.player.EntityPlayer;
import de.paxii.clarinet.Wrapper;
import de.paxii.clarinet.event.events.game.KeyPressedEvent;
import de.paxii.clarinet.module.ModuleCategory;
import de.paxii.clarinet.util.module.killaura.TimeManager;
import de.paxii.clarinet.module.Module;

public class ModuleMiddleClickFriends extends Module
{
    private final TimeManager timeManager;
    
    public ModuleMiddleClickFriends() {
        super("MiddleClick", ModuleCategory.PLAYER, -1);
        this.setRegistered(true);
        this.setDescription("Adds friends when you click the middle mouse button while aiming at them.");
        this.timeManager = new TimeManager();
    }
    
    @EventHandler
    public void onKeyPress(final KeyPressedEvent event) {
        if (event.getKey() == -98) {
            final Entity pointedEntity = Wrapper.getMinecraft().pointedEntity;
            if (pointedEntity != null && pointedEntity instanceof EntityPlayer) {
                final String userName = pointedEntity.getName();
                if (!Wrapper.getFriendManager().isFriend(userName)) {
                    Wrapper.getFriendManager().addFriend(userName);
                    Chat.printClientMessage("Added Friend " + userName + ".");
                }
                else {
                    Wrapper.getFriendManager().removeFriend(userName);
                    Chat.printClientMessage("Removed Friend " + userName + ".");
                }
            }
        }
    }
}
