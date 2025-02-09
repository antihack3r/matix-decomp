// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.module.render;

import de.paxii.clarinet.util.function.TwoBooleansFunction;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.util.math.AxisAlignedBB;
import de.paxii.clarinet.util.render.GL11Helper;
import org.lwjgl.opengl.GL11;
import de.paxii.clarinet.Wrapper;
import de.paxii.clarinet.event.events.game.RenderTickEvent;
import java.util.regex.Matcher;
import de.paxii.clarinet.util.chat.ChatColor;
import de.paxii.clarinet.event.events.chat.ReceiveChatEvent;
import de.paxii.clarinet.event.EventHandler;
import net.minecraft.util.EnumFacing;
import de.paxii.clarinet.event.events.player.PlayerSendChatMessageEvent;
import de.paxii.clarinet.module.ModuleCategory;
import net.minecraft.util.math.BlockPos;
import java.util.regex.Pattern;
import de.paxii.clarinet.module.Module;

public class ModuleWorldEditESP extends Module
{
    private static final Pattern worldEditPattern;
    private final BlockPos[] blockPos;
    
    public ModuleWorldEditESP() {
        super("WorldEditESP", ModuleCategory.RENDER, -1);
        this.blockPos = new BlockPos[] { null, null };
        this.setRegistered(true);
        this.setDescription("Draws a box around your current worldedit selection.");
    }
    
    @EventHandler
    public void onChatMessage(final PlayerSendChatMessageEvent event) {
        if (event.getChatMessage().startsWith("//")) {
            final String weCommand = event.getChatMessage().substring(2);
            if (weCommand.startsWith("desel") || weCommand.startsWith("sel")) {
                this.blockPos[0] = null;
                this.blockPos[1] = null;
            }
            if (weCommand.startsWith("expand")) {
                final String[] split = weCommand.split(" ");
                String expandDirection = "";
                double expandAmount = 0.0;
                double reverseAmount = 0.0;
                try {
                    if (split.length == 2) {
                        expandDirection = split[1];
                        if (expandDirection.equalsIgnoreCase("vert")) {
                            expandDirection = "up";
                            expandAmount = 256.0;
                            reverseAmount = 256.0;
                        }
                    }
                    else if (split.length == 3) {
                        expandAmount = Double.parseDouble(split[1]);
                        expandDirection = split[2];
                    }
                    else if (split.length > 3) {
                        expandAmount = Double.parseDouble(split[1]);
                        reverseAmount = Double.parseDouble(split[2]);
                        expandDirection = split[3];
                    }
                }
                catch (final NumberFormatException exception) {
                    this.sendClientMessage("Invalid expand syntax detected.");
                }
                if (expandDirection.length() != 0 && expandAmount != 0.0) {
                    final EnumFacing enumFacing = EnumFacing.byName(expandDirection);
                    if (enumFacing == null) {
                        this.sendClientMessage("Could not determine expand direction. Please use full words (north, east, vert is fine though)");
                        return;
                    }
                    this.expandSelection(enumFacing, (int)expandAmount, (int)reverseAmount);
                }
            }
        }
    }
    
    @EventHandler
    public void onReceiveChat(final ReceiveChatEvent event) {
        final String message = ChatColor.stripColor(event.getChatMessage());
        final Matcher matcher = ModuleWorldEditESP.worldEditPattern.matcher(message);
        if (matcher.find()) {
            final int index = matcher.group(1).equals("First") ? 0 : 1;
            final double posX = Double.parseDouble(matcher.group(2));
            final double posY = Double.parseDouble(matcher.group(3));
            final double posZ = Double.parseDouble(matcher.group(4));
            this.blockPos[index] = new BlockPos(posX, posY, posZ);
        }
    }
    
    @EventHandler
    public void onGlobalRender(final RenderTickEvent event) {
        final BlockPos block1 = this.blockPos[0];
        final BlockPos block2 = this.blockPos[1];
        final EntityPlayerSP entityPlayer = Wrapper.getPlayer();
        final float partialTicks = Wrapper.getTimer().renderPartialTicks;
        final double positionX = entityPlayer.lastTickPosX + (entityPlayer.posX - entityPlayer.lastTickPosX) * partialTicks;
        final double positionY = entityPlayer.lastTickPosY + (entityPlayer.posY - entityPlayer.lastTickPosY) * partialTicks;
        final double positionZ = entityPlayer.lastTickPosZ + (entityPlayer.posZ - entityPlayer.lastTickPosZ) * partialTicks;
        GL11.glPushMatrix();
        GL11Helper.enableDefaults();
        GL11.glLineWidth(1.5f);
        if (block1 != null) {
            final double renderX = block1.getX() - positionX;
            final double renderY = block1.getY() - positionY;
            final double renderZ = block1.getZ() - positionZ;
            final AxisAlignedBB blockBox0 = new AxisAlignedBB(renderX, renderY, renderZ, renderX + 1.0, renderY + 1.0, renderZ + 1.0);
            RenderGlobal.drawOutlinedBoundingBox(blockBox0, -343801);
        }
        if (block2 != null) {
            final double renderX = block2.getX() - positionX;
            final double renderY = block2.getY() - positionY;
            final double renderZ = block2.getZ() - positionZ;
            final AxisAlignedBB blockBox2 = new AxisAlignedBB(renderX, renderY, renderZ, renderX + 1.0, renderY + 1.0, renderZ + 1.0);
            RenderGlobal.drawOutlinedBoundingBox(blockBox2, -16254393);
        }
        if (block1 != null && block2 != null) {
            final double renderX = Math.min(block1.getX(), block2.getX()) - positionX;
            final double renderY = Math.min(block1.getY(), block2.getY()) - positionY;
            final double renderZ = Math.min(block1.getZ(), block2.getZ()) - positionZ;
            final double deltaX = Math.max(block1.getX(), block2.getX()) - Math.min(block1.getX(), block2.getX()) + 1;
            final double deltaY = Math.max(block1.getY(), block2.getY()) - Math.min(block1.getY(), block2.getY()) + 1;
            final double deltaZ = Math.max(block1.getZ(), block2.getZ()) - Math.min(block1.getZ(), block2.getZ()) + 1;
            final AxisAlignedBB draw = new AxisAlignedBB(renderX, renderY, renderZ, renderX + deltaX, renderY + deltaY, renderZ + deltaZ);
            RenderGlobal.drawOutlinedBoundingBox(draw, 16711935);
        }
        GL11Helper.disableDefaults();
        GL11.glPopMatrix();
    }
    
    private void expandSelection(final EnumFacing expandDir, final int expandAmount, final int reverseAmount) {
        if (this.blockPos[0] == null || this.blockPos[1] == null) {
            return;
        }
        boolean blockCondition = true;
        boolean reverseCondition = false;
        final TwoBooleansFunction<Integer> findIndex = (condition, reverse) -> condition ? (reverse ? 1 : 0) : (reverse ? 0 : 1);
        if (expandDir.getFrontOffsetX() != 0) {
            blockCondition = (this.blockPos[0].getX() >= this.blockPos[1].getX());
            reverseCondition = (expandDir.getFrontOffsetX() < 0);
        }
        else if (expandDir.getFrontOffsetY() != 0) {
            blockCondition = (this.blockPos[0].getY() >= this.blockPos[1].getY());
            reverseCondition = (expandDir.getFrontOffsetY() < 0);
        }
        else if (expandDir.getFrontOffsetZ() != 0) {
            blockCondition = (this.blockPos[0].getZ() >= this.blockPos[1].getZ());
            reverseCondition = (expandDir.getFrontOffsetZ() < 0);
        }
        final int blockIndex = findIndex.apply(blockCondition, reverseCondition);
        final int reverseIndex = (blockIndex == 0) ? 1 : 0;
        final BlockPos firstBlock = this.blockPos[blockIndex].offset(expandDir, expandAmount);
        final BlockPos secondBlock = this.blockPos[reverseIndex].offset(expandDir.getOpposite(), reverseAmount);
        this.blockPos[blockIndex] = this.getCorrectedBlockPos(firstBlock);
        this.blockPos[reverseIndex] = this.getCorrectedBlockPos(secondBlock);
    }
    
    private BlockPos getCorrectedBlockPos(BlockPos blockPos) {
        if (blockPos.getY() < 0) {
            blockPos = blockPos.down(blockPos.getY());
        }
        if (blockPos.getY() > Wrapper.getWorld().getHeight()) {
            blockPos = blockPos.down(blockPos.getY() - Wrapper.getWorld().getHeight());
        }
        return blockPos;
    }
    
    static {
        worldEditPattern = Pattern.compile("(First|Second) position set to \\((-?\\d+\\.\\d), (-?\\d+\\.\\d), (-?\\d+\\.\\d)\\).*");
    }
}
