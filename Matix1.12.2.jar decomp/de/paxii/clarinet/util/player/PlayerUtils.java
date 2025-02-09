// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.util.player;

import net.minecraft.block.properties.IProperty;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.entity.Entity;
import java.util.Iterator;
import de.paxii.clarinet.Wrapper;
import de.paxii.clarinet.util.chat.ChatColor;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.block.Block;

public class PlayerUtils
{
    public static float getPlayerStrVsBlock(final Block block, final ItemStack itemStack) {
        float var2 = 1.0f;
        if (itemStack != null && block != null) {
            var2 *= itemStack.getStrVsBlock(block.getBlockState().getBaseState());
        }
        return var2;
    }
    
    public static EntityPlayer getPlayerByName(String playerName) {
        playerName = ChatColor.stripColor(playerName);
        for (final EntityPlayer entityPlayer : Wrapper.getWorld().playerEntities) {
            if (ChatColor.stripColor(entityPlayer.getName()).equals(playerName)) {
                return entityPlayer;
            }
        }
        return null;
    }
    
    public static boolean canEntityBeSeen(final Entity entityA, final Entity entityB) {
        final RayTraceResult traceResult = Wrapper.getWorld().rayTraceBlocks(new Vec3d(entityA.posX, entityA.posY + entityA.getEyeHeight(), entityA.posZ), new Vec3d(entityB.posX, entityB.posY, entityB.posZ), false, true, false);
        if (traceResult != null && traceResult.typeOfHit == RayTraceResult.Type.BLOCK) {
            final Block block = Wrapper.getWorld().getBlockState(traceResult.getBlockPos()).getBlock();
            return block.getBlockState().getProperties().stream().filter(property -> property.getName().equals("half")).count() == 1L;
        }
        return traceResult == null;
    }
}
