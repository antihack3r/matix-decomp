// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.module.player;

import net.minecraft.block.Block;
import java.util.Iterator;
import de.paxii.clarinet.event.EventHandler;
import net.minecraft.util.math.BlockPos;
import net.minecraft.client.settings.KeyBinding;
import de.paxii.clarinet.Wrapper;
import java.util.List;
import de.paxii.clarinet.event.events.game.IngameTickEvent;
import de.paxii.clarinet.util.chat.Chat;
import java.awt.AWTException;
import de.paxii.clarinet.module.ModuleCategory;
import java.awt.Robot;
import java.util.ArrayList;
import de.paxii.clarinet.module.Module;

public class ModuleAutoMine extends Module
{
    private final ArrayList<Integer> blockList;
    private boolean clickFixed;
    private Robot robot;
    
    public ModuleAutoMine() {
        super("AutoMine", ModuleCategory.PLAYER);
        try {
            this.robot = new Robot();
        }
        catch (final AWTException e) {
            e.printStackTrace();
        }
        (this.blockList = new ArrayList<Integer>()).add(56);
        this.setDisplayedInGui(false);
        this.setCommand(true);
        this.setRegistered(true);
        this.setDescription("Automatically mines specific blocks in a given distance.");
        this.setSyntax("automine <add/remove/list> <blockid>");
    }
    
    @Override
    public void onEnable() {
        Chat.printClientMessage("If the Bot does not start Mining, press your Attack Button once!");
    }
    
    @EventHandler
    public void onIngameTick(final IngameTickEvent event) {
        final BlockPos nearestBlock = this.getNearestBlock(this.blockList, 30);
        if (nearestBlock != null) {
            final float[] angles = this.getAngles(nearestBlock);
            Wrapper.getPlayer().rotationYaw = angles[0];
            Wrapper.getPlayer().rotationPitch = angles[1];
            KeyBinding.setKeyBindState(Wrapper.getGameSettings().keyBindAttack.getKeyCode(), true);
            if (!this.clickFixed) {
                this.robot.mousePress(0);
                this.robot.mouseRelease(0);
            }
            if (Wrapper.getPlayer().getDistance(nearestBlock.getX(), nearestBlock.getY(), nearestBlock.getZ()) > 4.0) {
                KeyBinding.setKeyBindState(Wrapper.getGameSettings().keyBindForward.getKeyCode(), true);
                if (Wrapper.getPlayer().onGround && Wrapper.getPlayer().isCollidedHorizontally) {
                    Wrapper.getPlayer().jump();
                }
            }
            else {
                KeyBinding.setKeyBindState(Wrapper.getGameSettings().keyBindForward.getKeyCode(), false);
            }
        }
        else {
            this.clickFixed = false;
            KeyBinding.setKeyBindState(Wrapper.getGameSettings().keyBindAttack.getKeyCode(), false);
        }
    }
    
    @Override
    public void onCommand(final String[] args) {
        if (args.length > 0) {
            if (args.length >= 2) {
                final String identifier = args[0];
                int blockID;
                try {
                    blockID = Integer.parseInt(args[1]);
                }
                catch (final Exception e) {
                    Chat.printClientMessage("Invalid BlockID!");
                    return;
                }
                if (identifier.equalsIgnoreCase("add")) {
                    if (!this.blockList.contains(blockID)) {
                        this.blockList.add(blockID);
                        Chat.printClientMessage("Block " + blockID + " has been added.");
                    }
                    else {
                        Chat.printClientMessage("Block " + blockID + " has already been added!");
                    }
                }
                else if (identifier.equalsIgnoreCase("remove")) {
                    if (this.blockList.contains(blockID)) {
                        for (int i = 0; i < this.blockList.size(); ++i) {
                            if (this.blockList.get(i) == blockID) {
                                this.blockList.remove(i);
                            }
                        }
                        Chat.printClientMessage("Block " + blockID + " has been removed.");
                    }
                    else {
                        Chat.printClientMessage("Block " + blockID + " hasn't been added yet!");
                    }
                }
                else {
                    Chat.printClientMessage("Unknown subcommand!");
                }
            }
            else if (args.length >= 1) {
                if (args[0].equalsIgnoreCase("list")) {
                    Chat.printClientMessage("AutoMine Block List:");
                    for (final Integer blockID2 : this.blockList) {
                        Chat.printClientMessage("Block: " + blockID2);
                    }
                }
                else if (args[0].equalsIgnoreCase("clear")) {
                    this.blockList.clear();
                    Chat.printClientMessage("AutoMine Block list cleared.");
                }
                else {
                    Chat.printClientMessage("Unknown subcommand!");
                }
            }
        }
        else {
            Chat.printClientMessage("Too few arguments!");
        }
    }
    
    @Override
    public void onDisable() {
        this.clickFixed = false;
    }
    
    private BlockPos getNearestBlock(final List<Integer> blockIDs, final int radius) {
        BlockPos nearestBlock = null;
        double distance = radius + 1.0;
        for (int x = -radius; x < radius; ++x) {
            for (int y = -radius; y < radius; ++y) {
                for (int z = -radius; z < radius; ++z) {
                    final BlockPos blockPos = new BlockPos(Wrapper.getPlayer().posX + x, Wrapper.getPlayer().posY + y, Wrapper.getPlayer().posZ + z);
                    final Block block = Wrapper.getWorld().getBlock(blockPos.getX(), blockPos.getY(), blockPos.getZ());
                    final int id = Block.getIdFromBlock(block);
                    if (blockIDs.contains(id)) {
                        final double distanceToBlock = Wrapper.getPlayer().getDistance(blockPos.getX(), blockPos.getY(), blockPos.getZ());
                        if (distanceToBlock < distance) {
                            nearestBlock = blockPos;
                            distance = distanceToBlock;
                        }
                    }
                }
            }
        }
        return nearestBlock;
    }
    
    private float[] getAngles(final BlockPos blockPos) {
        final double difX = blockPos.getX() + 0.5 - Wrapper.getPlayer().posX;
        final double difY = blockPos.getY() + 0.5 - (Wrapper.getPlayer().posY + Wrapper.getPlayer().getEyeHeight());
        final double difZ = blockPos.getZ() + 0.5 - Wrapper.getPlayer().posZ;
        final double helper = Math.sqrt(difX * difX + difZ * difZ);
        final float yaw = (float)(Math.atan2(difZ, difX) * 180.0 / 3.141592653589793) - 90.0f;
        final float pitch = (float)(-(Math.atan2(difY, helper) * 180.0 / 3.141592653589793));
        return new float[] { yaw, pitch };
    }
}
