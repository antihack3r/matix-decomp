// 
// Decompiled by Procyon v0.6.0
// 

package net.minecraft.client.gui.chat;

import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.ChatType;
import net.minecraft.client.Minecraft;

public class OverlayChatListener implements IChatListener
{
    private final Minecraft mc;
    
    public OverlayChatListener(final Minecraft p_i47394_1_) {
        this.mc = p_i47394_1_;
    }
    
    @Override
    public void say(final ChatType p_192576_1_, final ITextComponent p_192576_2_) {
        this.mc.ingameGUI.setOverlayMessage(p_192576_2_, false);
    }
}
