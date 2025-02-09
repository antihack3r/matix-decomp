// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.gui.ingame.panel;

import de.paxii.clarinet.gui.ingame.panel.theme.themes.DefaultTheme;
import de.paxii.clarinet.gui.ingame.panel.element.elements.PanelModuleButton;
import net.minecraft.util.EnumActionResult;
import de.paxii.clarinet.gui.ingame.panel.theme.layout.ElementSpacing;
import de.paxii.clarinet.Wrapper;
import java.util.Iterator;
import de.paxii.clarinet.gui.ingame.panel.element.PanelElement;
import java.util.ArrayList;

public class GuiPanel
{
    private ArrayList<PanelElement> panelElements;
    private String panelName;
    private int panelX;
    private int panelY;
    private int panelWidth;
    private int panelHeight;
    private int titleHeight;
    private int scrollY;
    private int dragX;
    private int dragY;
    private boolean dragging;
    private boolean draggable;
    private boolean opened;
    private boolean collapsible;
    private boolean pinned;
    private boolean visible;
    private boolean scrollable;
    
    public GuiPanel(final String panelName, final int panelX, final int panelY) {
        this(panelName, panelX, panelY, 100, 200);
    }
    
    public GuiPanel(final String panelName, final int panelX, final int panelY, final int panelWidth, final int panelHeight) {
        this(panelName, panelX, panelY, panelWidth, panelHeight, true);
    }
    
    public GuiPanel(final String panelName, final int panelX, final int panelY, final int panelWidth, final int panelHeight, final boolean isOpened) {
        this(panelName, panelX, panelY, panelWidth, panelHeight, isOpened, true);
    }
    
    public GuiPanel(final String panelName, final int panelX, final int panelY, final int panelWidth, final int panelHeight, final boolean isOpened, final boolean isVisible) {
        this.titleHeight = 12;
        this.draggable = true;
        this.collapsible = true;
        this.visible = true;
        this.panelName = panelName;
        this.panelX = panelX;
        this.panelY = panelY;
        this.panelWidth = panelWidth;
        this.panelHeight = panelHeight;
        this.panelElements = new ArrayList<PanelElement>();
        this.setOpened(isOpened);
        this.setVisible(isVisible);
        this.addElements();
    }
    
    public void addElements() {
    }
    
    private int calculateHeight() {
        int height = 22;
        for (final PanelElement panelElement : this.getPanelElements()) {
            height += panelElement.getHeight() + panelElement.getElementSpacing().getMarginTop() + panelElement.getElementSpacing().getMarginBottom();
        }
        return height;
    }
    
    public void drawPanel(final int mouseX, final int mouseY) {
        this.setPanelHeight(this.calculateHeight());
        if (this.isDragging()) {
            this.setPanelX(mouseX + this.dragX);
            this.setPanelY(mouseY + this.dragY);
        }
        Wrapper.getClickableGui().getCurrentTheme().drawPanel(this, mouseX, mouseY);
        if (this.isOpened()) {
            int index = 19;
            for (final PanelElement panelElement : this.getPanelElements()) {
                final ElementSpacing spacing = panelElement.getElementSpacing();
                index += spacing.getMarginTop();
                panelElement.drawElement(this.getPanelX() + spacing.getMarginLeft(), this.getPanelY() + index, mouseX, mouseY);
                index += panelElement.getHeight() + spacing.getMarginBottom();
            }
        }
    }
    
    public void mouseClicked(final int mouseX, final int mouseY, final int buttonClicked) {
        if (this.isMouseOverCollapseButton(mouseX, mouseY) && buttonClicked == 0 && this.isCollapsible()) {
            this.setOpened(!this.isOpened());
            return;
        }
        if (this.isMouseOverTitle(mouseX, mouseY) && buttonClicked == 0 && this.isDraggable()) {
            this.setDragging(true);
            this.dragX = this.getPanelX() - mouseX;
            this.dragY = this.getPanelY() - mouseY;
        }
        if (this.isOpened() && this.isMouseOverPanel(mouseX, mouseY)) {
            for (final PanelElement panelElement : this.getPanelElements()) {
                panelElement.mouseClicked(mouseX, mouseY, buttonClicked);
            }
        }
    }
    
    public EnumActionResult keyPressed(final int keyCode) {
        EnumActionResult result = EnumActionResult.PASS;
        if (this.isOpened()) {
            for (final PanelElement panelElement : this.getPanelElements()) {
                final EnumActionResult actionResult = panelElement.keyPressed(keyCode);
                if (result == EnumActionResult.PASS) {
                    result = actionResult;
                }
            }
        }
        return result;
    }
    
    public void setOpened(final boolean opened) {
        this.opened = opened;
        this.getPanelElements().stream().filter(pE -> pE instanceof PanelModuleButton).forEach(pE -> {
            final PanelModuleButton panelModuleButton = (PanelModuleButton)pE;
            if (panelModuleButton.getModuleSettings() != null) {
                panelModuleButton.getModuleSettings().setVisible(false);
            }
        });
    }
    
    public boolean isMouseOverPanel(final int mouseX, final int mouseY) {
        final boolean rightX = mouseX >= this.getPanelX() && mouseX <= this.getPanelX() + this.getPanelWidth();
        final boolean rightY = mouseY >= this.getPanelY() && mouseY <= this.getPanelY() + (this.isOpened() ? this.getPanelHeight() : this.getTitleHeight());
        return rightX && rightY;
    }
    
    public boolean isMouseOverTitle(final int mouseX, final int mouseY) {
        final boolean rightX = mouseX >= this.getPanelX() && mouseX <= this.getPanelX() + this.getPanelWidth();
        final boolean rightY = mouseY >= this.getPanelY() && mouseY <= this.getPanelY() + this.getTitleHeight();
        return rightX && rightY;
    }
    
    public boolean isMouseOverCollapseButton(final int mouseX, final int mouseY) {
        if (Wrapper.getClickableGui().getCurrentTheme() instanceof DefaultTheme) {
            return mouseX >= this.getPanelX() + this.getPanelWidth() - 15 && mouseX <= this.getPanelX() + this.getPanelWidth() - 5 && mouseY >= this.getPanelY() + 2 && mouseY <= this.getPanelY() + 20;
        }
        return mouseX >= this.getPanelX() + this.getPanelWidth() - 11 && mouseX <= this.getPanelX() + this.getPanelWidth() - 2 && mouseY >= this.getPanelY() + 2 && mouseY <= this.getPanelY() + this.getTitleHeight() - 2;
    }
    
    public boolean isMouseOverAll(final int mouseX, final int mouseY) {
        if (this.isMouseOverPanel(mouseX, mouseY)) {
            for (final GuiPanel guiPanel : Wrapper.getClickableGui().getGuiPanels()) {
                if (!guiPanel.isVisible()) {
                    continue;
                }
                if (guiPanel.isMouseOverPanel(mouseX, mouseY) && Wrapper.getClickableGui().getGuiPanels().indexOf(guiPanel) > Wrapper.getClickableGui().getGuiPanels().indexOf(this)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    
    public ArrayList<PanelElement> getPanelElements() {
        return this.panelElements;
    }
    
    public String getPanelName() {
        return this.panelName;
    }
    
    public void setPanelName(final String panelName) {
        this.panelName = panelName;
    }
    
    public int getPanelX() {
        return this.panelX;
    }
    
    public int getPanelY() {
        return this.panelY;
    }
    
    public int getPanelWidth() {
        return this.panelWidth;
    }
    
    public int getPanelHeight() {
        return this.panelHeight;
    }
    
    public int getTitleHeight() {
        return this.titleHeight;
    }
    
    public int getScrollY() {
        return this.scrollY;
    }
    
    public void setPanelX(final int panelX) {
        this.panelX = panelX;
    }
    
    public void setPanelY(final int panelY) {
        this.panelY = panelY;
    }
    
    public void setPanelWidth(final int panelWidth) {
        this.panelWidth = panelWidth;
    }
    
    public void setPanelHeight(final int panelHeight) {
        this.panelHeight = panelHeight;
    }
    
    public void setTitleHeight(final int titleHeight) {
        this.titleHeight = titleHeight;
    }
    
    public void setScrollY(final int scrollY) {
        this.scrollY = scrollY;
    }
    
    public boolean isDragging() {
        return this.dragging;
    }
    
    public boolean isDraggable() {
        return this.draggable;
    }
    
    public boolean isOpened() {
        return this.opened;
    }
    
    public boolean isCollapsible() {
        return this.collapsible;
    }
    
    public boolean isPinned() {
        return this.pinned;
    }
    
    public boolean isVisible() {
        return this.visible;
    }
    
    public boolean isScrollable() {
        return this.scrollable;
    }
    
    public void setDragging(final boolean dragging) {
        this.dragging = dragging;
    }
    
    public void setDraggable(final boolean draggable) {
        this.draggable = draggable;
    }
    
    public void setCollapsible(final boolean collapsible) {
        this.collapsible = collapsible;
    }
    
    public void setPinned(final boolean pinned) {
        this.pinned = pinned;
    }
    
    public void setVisible(final boolean visible) {
        this.visible = visible;
    }
    
    public void setScrollable(final boolean scrollable) {
        this.scrollable = scrollable;
    }
}
