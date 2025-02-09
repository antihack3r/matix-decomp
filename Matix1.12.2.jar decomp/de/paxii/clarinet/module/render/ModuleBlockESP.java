// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.module.render;

import de.paxii.clarinet.util.settings.ClientSetting;
import de.paxii.clarinet.util.settings.type.ClientSettingInteger;
import de.paxii.clarinet.event.events.game.QuitServerEvent;
import java.util.Iterator;
import java.util.Map;
import de.paxii.clarinet.util.chat.Chat;
import net.minecraft.block.Block;
import de.paxii.clarinet.event.events.block.BlockBreakEvent;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.GlStateManager;
import de.paxii.clarinet.util.render.GL11Helper;
import org.lwjgl.opengl.GL11;
import de.paxii.clarinet.Wrapper;
import java.awt.Color;
import de.paxii.clarinet.event.events.game.RenderTickEvent;
import de.paxii.clarinet.event.events.game.LoadWorldEvent;
import de.paxii.clarinet.event.EventHandler;
import de.paxii.clarinet.event.events.game.IngameTickEvent;
import de.paxii.clarinet.util.module.settings.ValueBase;
import de.paxii.clarinet.module.ModuleCategory;
import net.minecraft.util.math.BlockPos;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentHashMap;
import de.paxii.clarinet.module.Module;

public class ModuleBlockESP extends Module
{
    private final ConcurrentHashMap<Integer, Integer> searchBlocks;
    private ConcurrentLinkedQueue<SearchBlock> renderBlocks;
    private BlockPos updatePosition;
    
    public ModuleBlockESP() {
        super("BlockESP", ModuleCategory.RENDER);
        this.getModuleValues().put("renderDistance", new ValueBase("Block ESP Distance", 50.0f, 1.0f, 500.0f, true, "Distance") {
            @Override
            public void onUpdate(final float oldValue, final float newValue) {
                ModuleBlockESP.this.renderBlocks.clear();
                ModuleBlockESP.this.searchBlocks();
            }
        });
        this.searchBlocks = new ConcurrentHashMap<Integer, Integer>();
        this.renderBlocks = new ConcurrentLinkedQueue<SearchBlock>();
        this.setCommand(true);
        this.setRegistered(true);
        this.setDescription("Draws a Box around Blocks");
        this.setSyntax("blockesp <add/remove/list> <blockID> <color (0xFFFFFF)>");
    }
    
    @Override
    public void onStartup() {
        this.getModuleSettings().forEach((blockIdentifier, value) -> {
            try {
                final int blockID = Integer.parseInt(blockIdentifier);
                final int blockColor = this.getValue(blockIdentifier, Integer.class);
                this.searchBlocks.put(blockID, blockColor);
            }
            catch (final NumberFormatException e) {
                e.printStackTrace();
            }
        });
    }
    
    @EventHandler
    public void onTick(final IngameTickEvent event) {
        this.updateRenderBlocks();
    }
    
    @EventHandler
    public void onWorldLoaded(final LoadWorldEvent event) {
        this.updatePosition = null;
        this.updateRenderBlocks();
    }
    
    @EventHandler
    public void onRender(final RenderTickEvent event) {
        this.renderBlocks.forEach(renderBlock -> {
            final int renderColor = this.searchBlocks.get(renderBlock.getBlockID());
            final Color decode = new Color(renderColor);
            final BlockPos blockPos = renderBlock.getBlockPos();
            final double x = blockPos.getX() - (Wrapper.getPlayer().lastTickPosX + (Wrapper.getPlayer().posX - Wrapper.getPlayer().lastTickPosX) * Wrapper.getTimer().renderPartialTicks);
            final double y = blockPos.getY() - (Wrapper.getPlayer().lastTickPosY + (Wrapper.getPlayer().posY - Wrapper.getPlayer().lastTickPosY) * Wrapper.getTimer().renderPartialTicks);
            final double z = blockPos.getZ() - (Wrapper.getPlayer().lastTickPosZ + (Wrapper.getPlayer().posZ - Wrapper.getPlayer().lastTickPosZ) * Wrapper.getTimer().renderPartialTicks);
            GL11.glPushMatrix();
            GL11Helper.enableDefaults();
            GlStateManager.depthMask(false);
            GlStateManager.disableTexture2D();
            GlStateManager.disableLighting();
            GlStateManager.disableCull();
            GlStateManager.disableBlend();
            GL11.glLineWidth(1.0f);
            RenderGlobal.drawBoundingBox(x, y, z, x + 1.0, y + 1.0, z + 1.0, decode.getRed() / 255.0f, decode.getGreen() / 255.0f, decode.getBlue() / 255.0f, 1.0f);
            GL11Helper.disableDefaults();
            GlStateManager.enableTexture2D();
            GlStateManager.enableLighting();
            GlStateManager.enableCull();
            GlStateManager.disableBlend();
            GlStateManager.depthMask(true);
            GL11.glPopMatrix();
        });
    }
    
    @EventHandler
    public void onBlockBreak(final BlockBreakEvent blockBreakEvent) {
        final int blockID = Block.getIdFromBlock(blockBreakEvent.getBlock());
        if (this.searchBlocks.containsKey(blockID)) {
            this.renderBlocks.forEach(searchBlock -> {
                if (searchBlock.getBlockPos().equals(blockBreakEvent.getBlockPos())) {
                    this.renderBlocks.remove(searchBlock);
                }
            });
        }
    }
    
    private void updateRenderBlocks() {
        if (this.updatePosition == null) {
            this.renderBlocks.clear();
            this.searchBlocks();
            this.updatePosition = Wrapper.getPlayer().getPosition();
        }
        else {
            final double distance = Wrapper.getPlayer().getDistance(this.updatePosition.getX(), this.updatePosition.getY(), this.updatePosition.getZ());
            if (distance > this.getModuleValues().get("renderDistance").getValue() / 5.0f) {
                this.renderBlocks.clear();
                this.searchBlocks();
                this.updatePosition = Wrapper.getPlayer().getPosition();
            }
        }
    }
    
    private void searchBlocks() {
        if (!this.searchBlocks.isEmpty()) {
            new Thread(() -> {
                final int renderDistance = (int)this.getModuleValues().get("renderDistance").getValue();
                for (int y = 0; y < 256; ++y) {
                    for (int x = 0; x < renderDistance; ++x) {
                        for (int z = 0; z < renderDistance; ++z) {
                            final int blockX = (int)Wrapper.getPlayer().posX - renderDistance / 2 + x;
                            final int blockZ = (int)Wrapper.getPlayer().posZ - renderDistance / 2 + z;
                            final BlockPos blockPos = new BlockPos(blockX, y, blockZ);
                            final Block block = Wrapper.getWorld().getBlockState(blockPos).getBlock();
                            final int blockID = Block.getIdFromBlock(block);
                            if (this.searchBlocks.containsKey(blockID)) {
                                this.renderBlocks.add(new SearchBlock(blockID, blockPos));
                            }
                        }
                    }
                }
            }).start();
        }
    }
    
    @Override
    public void onCommand(final String[] args) {
        if (args.length > 0) {
            if (args.length >= 2) {
                final String identifier = args[0];
                int blockColor = 16777215;
                int blockID;
                try {
                    blockID = Integer.parseInt(args[1]);
                }
                catch (final Exception e) {
                    Chat.printClientMessage("Invalid BlockID!");
                    return;
                }
                if (identifier.equalsIgnoreCase("add")) {
                    if (args.length >= 3) {
                        try {
                            String colorString = args[2];
                            if (colorString.startsWith("0x")) {
                                colorString = colorString.substring(2);
                            }
                            blockColor = Integer.parseInt(colorString, 16);
                        }
                        catch (final Exception e) {
                            e.printStackTrace();
                            Chat.printClientMessage("Invalid Block Color!");
                            return;
                        }
                    }
                    if (!this.searchBlocks.containsKey(blockID)) {
                        this.searchBlocks.put(blockID, blockColor);
                        this.renderBlocks.clear();
                        this.searchBlocks();
                        Chat.printClientMessage("Block " + blockID + " (0x" + Integer.toHexString(blockColor) + ") has been added.");
                    }
                    else {
                        Chat.printClientMessage("Block " + blockID + " has already been added!");
                    }
                }
                else if (identifier.equalsIgnoreCase("remove")) {
                    if (this.searchBlocks.containsKey(blockID)) {
                        this.searchBlocks.remove(blockID);
                        this.renderBlocks.clear();
                        this.searchBlocks();
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
                    Chat.printClientMessage("Block ESP List:");
                    for (final Map.Entry<Integer, Integer> searchBlock : this.searchBlocks.entrySet()) {
                        Chat.printClientMessage("Block: " + searchBlock.getKey() + " (0x" + Integer.toHexString(searchBlock.getValue()) + ")");
                    }
                }
                else if (args[0].equalsIgnoreCase("clear")) {
                    this.searchBlocks.clear();
                    this.renderBlocks.clear();
                    this.searchBlocks();
                    Chat.printClientMessage("Block ESP list cleared.");
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
    
    @EventHandler
    public void onServerQuit(final QuitServerEvent quitServerEvent) {
        this.setEnabled(false);
    }
    
    @Override
    public void onDisable() {
        this.renderBlocks.clear();
        this.updatePosition = null;
    }
    
    @Override
    public void onShutdown() {
        this.getModuleSettings().clear();
        for (final Map.Entry<Integer, Integer> searchBlock : this.searchBlocks.entrySet()) {
            this.getModuleSettings().put(String.valueOf(searchBlock.getKey()), new ClientSettingInteger(String.valueOf(searchBlock.getKey()), searchBlock.getValue()));
        }
    }
    
    private class SearchBlock
    {
        private int blockID;
        private BlockPos blockPos;
        
        SearchBlock(final int blockID, final BlockPos blockPos) {
            this.blockID = blockID;
            this.blockPos = blockPos;
        }
        
        public int getBlockID() {
            return this.blockID;
        }
        
        public BlockPos getBlockPos() {
            return this.blockPos;
        }
    }
}
