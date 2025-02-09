// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.module.render;

import de.paxii.clarinet.util.settings.ClientSetting;
import de.paxii.clarinet.util.settings.type.ClientSettingInteger;
import de.paxii.clarinet.gui.ingame.panel.GuiPanel;
import java.util.Iterator;
import de.paxii.clarinet.util.chat.Chat;
import de.paxii.clarinet.event.EventHandler;
import de.paxii.clarinet.event.events.game.IngameTickEvent;
import de.paxii.clarinet.Wrapper;
import java.awt.Color;
import de.paxii.clarinet.util.module.settings.ValueBase;
import de.paxii.clarinet.module.ModuleCategory;
import java.util.concurrent.ConcurrentLinkedQueue;
import de.paxii.clarinet.module.Module;

public class ModuleXray extends Module
{
    private static boolean isActive;
    private static ConcurrentLinkedQueue<Integer> blockList;
    private static int xrayOpacity;
    private boolean editedAmbientOcclusion;
    
    public ModuleXray() {
        super("Xray", ModuleCategory.RENDER, 45);
        this.getModuleValues().put("opacity", new ValueBase("Xray Opacity", 30.0f, 0.0f, 100.0f, true, "Opacity") {
            @Override
            public void onUpdate(final float oldValue, final float newValue) {
                final int opacity = (int)(newValue * 255.0f / 100.0f);
                final Color color = new Color(255, 255, 255, opacity);
                ModuleXray.xrayOpacity = color.getRGB();
                if (ModuleXray.this.isEnabled()) {
                    Wrapper.getMinecraft().renderGlobal.loadRenderers();
                }
            }
        });
        this.setCommand(true);
        this.setRegistered(true);
        this.setDescription("Allows you to see Blocks through walls.");
        this.setSyntax("xray <add/remove/list/gui> <blockID>");
    }
    
    @Override
    public void onStartup() {
        if (this.getModuleSettings().isEmpty()) {
            for (final int blockId : new int[] { 54, 56, 14, 15, 16, 21, 129 }) {
                ModuleXray.blockList.add(blockId);
            }
        }
        else {
            this.getModuleSettings().forEach((blockIdentifier, value) -> {
                try {
                    final int blockID = this.getValue(blockIdentifier, Integer.class);
                    ModuleXray.blockList.add(blockID);
                }
                catch (final NullPointerException e) {
                    e.printStackTrace();
                }
            });
        }
    }
    
    @Override
    public void onEnable() {
        if (Wrapper.getGameSettings().ambientOcclusion == 0) {
            Wrapper.getGameSettings().ambientOcclusion = 1;
            this.editedAmbientOcclusion = true;
        }
    }
    
    @EventHandler
    public void onIngameTick(final IngameTickEvent event) {
        for (int i = 0; i < Wrapper.getWorld().provider.getLightBrightnessTable().length; ++i) {
            Wrapper.getWorld().provider.getLightBrightnessTable()[i] = 1.0f;
        }
    }
    
    @Override
    public void onToggle() {
        ModuleXray.isActive = !ModuleXray.isActive;
        Wrapper.getMinecraft().renderGlobal.loadRenderers();
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
                    if (!ModuleXray.blockList.contains(blockID)) {
                        ModuleXray.blockList.add(blockID);
                        Chat.printClientMessage("Block " + blockID + " has been added.");
                        if (this.isEnabled()) {
                            Wrapper.getMinecraft().renderGlobal.loadRenderers();
                        }
                    }
                    else {
                        Chat.printClientMessage("Block " + blockID + " has already been added!");
                    }
                }
                else if (identifier.equalsIgnoreCase("remove")) {
                    try {
                        if (ModuleXray.blockList.contains(blockID)) {
                            this.removeBlock(blockID);
                            Chat.printClientMessage("Block " + blockID + " has been removed.");
                            if (this.isEnabled()) {
                                Wrapper.getMinecraft().renderGlobal.loadRenderers();
                            }
                        }
                        else {
                            Chat.printClientMessage("Block " + blockID + " hasn't been added yet!");
                        }
                    }
                    catch (final Exception x) {
                        x.printStackTrace();
                    }
                }
                else {
                    Chat.printClientMessage("Unknown subcommand!");
                }
            }
            else if (args[0].equalsIgnoreCase("list")) {
                Chat.printClientMessage("Xray Block List:");
                for (final int id : ModuleXray.blockList) {
                    Chat.printClientMessage("Block: " + id);
                }
            }
            else if (args[0].equalsIgnoreCase("clear")) {
                ModuleXray.blockList.clear();
                Chat.printClientMessage("Xray list cleared.");
                if (this.isEnabled()) {
                    Wrapper.getMinecraft().renderGlobal.loadRenderers();
                }
            }
            else if (args[0].equalsIgnoreCase("gui")) {
                final GuiPanel guiPanel = Wrapper.getClickableGui().getGuiPanel("Xray Blocks");
                guiPanel.setVisible(!guiPanel.isVisible());
                Chat.printClientMessage("Xray Block Panel is now " + (guiPanel.isVisible() ? "visible in" : "hidden from") + " the clickable gui.");
            }
            else {
                Chat.printClientMessage("Unknown subcommand!");
            }
        }
        else {
            Chat.printClientMessage("Too few arguments!");
        }
    }
    
    public void removeBlock(final int blockID) {
        ModuleXray.blockList.removeIf(id -> id == blockID);
    }
    
    @Override
    public void onDisable() {
        Wrapper.getWorld().provider.generateLightBrightnessTable();
        if (this.editedAmbientOcclusion) {
            Wrapper.getGameSettings().ambientOcclusion = 0;
        }
    }
    
    @Override
    public void onShutdown() {
        this.getModuleSettings().clear();
        for (final int blockID : ModuleXray.blockList) {
            this.getModuleSettings().put(String.valueOf(blockID), new ClientSettingInteger(String.valueOf(blockID), blockID));
        }
    }
    
    public static boolean isActive() {
        return ModuleXray.isActive;
    }
    
    public static ConcurrentLinkedQueue<Integer> getBlockList() {
        return ModuleXray.blockList;
    }
    
    public static int getXrayOpacity() {
        return ModuleXray.xrayOpacity;
    }
    
    static {
        ModuleXray.blockList = new ConcurrentLinkedQueue<Integer>();
        ModuleXray.xrayOpacity = 1157627903;
    }
}
