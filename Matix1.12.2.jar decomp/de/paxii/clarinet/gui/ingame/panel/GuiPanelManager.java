// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.gui.ingame.panel;

import net.minecraft.client.gui.ScaledResolution;
import de.paxii.clarinet.event.EventManager;
import de.paxii.clarinet.event.events.client.PostLoadPanelsEvent;
import de.paxii.clarinet.gui.ingame.panel.element.PanelElement;
import java.util.Iterator;
import java.util.List;
import de.paxii.clarinet.gui.ingame.panel.element.elements.PanelBlockRow;
import java.util.concurrent.atomic.AtomicBoolean;
import net.minecraft.block.material.Material;
import net.minecraft.block.Block;
import de.paxii.clarinet.gui.ingame.panel.element.elements.PanelBlockButton;
import de.paxii.clarinet.util.objects.IntObject;
import de.paxii.clarinet.gui.ingame.panel.element.elements.PanelColorButton;
import de.paxii.clarinet.gui.ingame.panel.theme.themes.LegacyTheme;
import de.paxii.clarinet.gui.ingame.panel.element.elements.PanelModuleButton;
import java.util.Map;
import de.paxii.clarinet.module.Module;
import java.util.TreeMap;
import de.paxii.clarinet.Wrapper;
import de.paxii.clarinet.module.ModuleCategory;
import java.util.Collection;
import java.util.ArrayList;
import de.paxii.clarinet.gui.ingame.ClientClickableGui;

public class GuiPanelManager
{
    public void loadPanels(final ClientClickableGui clickableGui) {
        int xIndex = 5;
        int yIndex = 5;
        final ArrayList<GuiPanel> savedPanels = new ArrayList<GuiPanel>();
        if (!clickableGui.getGuiPanels().isEmpty()) {
            savedPanels.addAll(clickableGui.getGuiPanels());
            clickableGui.getGuiPanels().clear();
        }
        for (final ModuleCategory moduleCategory : ModuleCategory.values()) {
            final TreeMap<String, Module> sortedKeys = new TreeMap<String, Module>(Wrapper.getModuleManager().getModulesByCategory(moduleCategory));
            clickableGui.getGuiPanels().add(new GuiPanel(moduleCategory.toString(), xIndex, yIndex) {
                @Override
                public void addElements() {
                    sortedKeys.forEach((moduleName, module) -> {
                        if (module.isDisplayedInGui()) {
                            this.getPanelElements().add(new PanelModuleButton(module, this));
                        }
                    });
                }
            });
            xIndex += 110;
            final ScaledResolution scaledResolution = Wrapper.getScaledResolution();
            if (xIndex >= scaledResolution.getScaledWidth()) {
                xIndex = 5;
                yIndex += clickableGui.getGuiPanels().get(0).getPanelHeight() + 20;
            }
        }
        if (clickableGui.getCurrentTheme().getName().equals("Legacy")) {
            clickableGui.getGuiPanels().add(new GuiPanel("Gui Color", xIndex, yIndex, 100, 200) {
                @Override
                public void addElements() {
                    final LegacyTheme defaultTheme = (LegacyTheme)clickableGui.getCurrentTheme();
                    defaultTheme.getColorObjects().forEach(colorObject -> this.getPanelElements().add(new PanelColorButton(colorObject)));
                }
            });
        }
        xIndex = 5;
        yIndex = clickableGui.getGuiPanel("Player").getPanelY() + clickableGui.getGuiPanel("Player").getPanelHeight() + 20;
        clickableGui.getGuiPanels().add(new GuiPanel("Xray Blocks", xIndex, yIndex, 310, 200, false, true) {
            @Override
            public void addElements() {
                final IntObject blockIndex = new IntObject(0);
                final List<PanelBlockButton> blockButtons = new ArrayList<PanelBlockButton>();
                final Iterator<Block> it = Block.REGISTRY.iterator();
                while (it.hasNext()) {
                    final Block block = it.next();
                    if (block.getMaterial() != Material.AIR) {
                        final AtomicBoolean atomicBoolean = new AtomicBoolean(false);
                        this.getPanelElements().forEach(panelElement -> {
                            if (panelElement instanceof PanelBlockRow) {
                                final PanelBlockRow currentRow = (PanelBlockRow)panelElement;
                                currentRow.getBlockButtons().forEach(blockButton -> {
                                    if (block.getUnlocalizedName().equals(blockButton.getIBlockState().getBlock().getUnlocalizedName())) {
                                        atomicBoolean.set(true);
                                    }
                                });
                            }
                            return;
                        });
                        if (atomicBoolean.get()) {
                            continue;
                        }
                        blockButtons.add(new PanelBlockButton(block.getDefaultState()));
                        blockIndex.add(1);
                        if (blockIndex.getInteger() != 15 && it.hasNext()) {
                            continue;
                        }
                        this.getPanelElements().add(new PanelBlockRow((PanelBlockButton[])blockButtons.toArray(new PanelBlockButton[blockButtons.size()])));
                        blockButtons.clear();
                        blockIndex.setInteger(0);
                    }
                }
            }
        });
        savedPanels.forEach(savedPanel -> clickableGui.getGuiPanels().forEach(guiPanel -> {
            if (savedPanel.getPanelName().equals(guiPanel.getPanelName())) {
                guiPanel.setPanelX(savedPanel.getPanelX());
                guiPanel.setPanelY(savedPanel.getPanelY());
                guiPanel.setOpened(savedPanel.isOpened());
                guiPanel.setPinned(savedPanel.isPinned());
            }
        }));
        EventManager.call(new PostLoadPanelsEvent());
    }
}
