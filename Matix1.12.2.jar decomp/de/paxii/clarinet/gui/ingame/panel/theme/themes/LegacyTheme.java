// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.gui.ingame.panel.theme.themes;

import de.paxii.clarinet.gui.ingame.panel.theme.layout.LegacyThemeLayout;
import java.text.DecimalFormat;
import de.paxii.clarinet.util.module.settings.ValueBase;
import java.util.Iterator;
import net.minecraft.item.ItemStack;
import net.minecraft.client.renderer.RenderHelper;
import de.paxii.clarinet.module.render.ModuleXray;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import de.paxii.clarinet.util.gui.MouseService;
import org.lwjgl.opengl.GL11;
import de.paxii.clarinet.module.Module;
import de.paxii.clarinet.Wrapper;
import de.paxii.clarinet.util.render.GuiMethods;
import de.paxii.clarinet.gui.ingame.panel.GuiPanel;
import java.util.ArrayList;
import de.paxii.clarinet.gui.ingame.panel.theme.layout.GuiThemeLayout;
import de.paxii.clarinet.gui.ingame.panel.theme.GuiTheme;

public class LegacyTheme implements GuiTheme
{
    private static final GuiThemeLayout LAYOUT;
    private ArrayList<DefaultThemeColorObject> colorObjects;
    private DefaultThemeColorObject currentColor;
    
    public LegacyTheme() {
        (this.colorObjects = new ArrayList<DefaultThemeColorObject>()).add(new DefaultThemeColorObject("Blue", -1438682113, -1440274381, -863202407, -858599727, -868256769, -864707329, -1));
        this.colorObjects.add(new DefaultThemeColorObject("Red", -1426128896, -1440274381, -863202407, -858599727, -855703552, -855674768, -1));
        this.colorObjects.add(new DefaultThemeColorObject("Yellow", -1426066944, -1440274381, -863202407, -858599727, -855641600, -855639366, -1));
        this.colorObjects.add(new DefaultThemeColorObject("Green", -1435042048, -1440274381, -863202407, -858599727, -864616704, -859832441, -1));
        this.colorObjects.add(new DefaultThemeColorObject("Purple", -1431820903, -1440274381, -863202407, -858599727, -861395559, -861903206, -1));
        this.colorObjects.add(new DefaultThemeColorObject("Ocker", -1431010470, -1440274381, -863202407, -858599727, -860585126, -860711298, -1));
        this.colorObjects.add(new DefaultThemeColorObject("Orange", -1426089905, -1440274381, -863202407, -858599727, -855664561, -855657084, -1));
        this.colorObjects.add(new DefaultThemeColorObject("Turquoise", -1439369238, -1440274381, -863202407, -858599727, -868943894, -860032274, -1));
        this.currentColor = this.colorObjects.get(0);
    }
    
    @Override
    public String getName() {
        return "Legacy";
    }
    
    @Override
    public void drawPanel(final GuiPanel guiPanel, final int mouseX, final int mouseY) {
        if (guiPanel.isOpened()) {
            GuiMethods.drawRoundedRect(guiPanel.getPanelX(), guiPanel.getPanelY(), guiPanel.getPanelX() + guiPanel.getPanelWidth(), guiPanel.getPanelY() + guiPanel.getPanelHeight(), this.currentColor.getPanelBackground(), this.currentColor.getPanelBackground());
            GuiMethods.drawRoundedRect(guiPanel.getPanelX(), guiPanel.getPanelY(), guiPanel.getPanelX() + guiPanel.getPanelWidth(), guiPanel.getPanelY() + guiPanel.getTitleHeight(), this.currentColor.getPanelTitle(), this.currentColor.getPanelTitle());
            if (guiPanel.isCollapsible()) {
                GuiMethods.drawRoundedRect(guiPanel.getPanelX() + guiPanel.getPanelWidth() - 11, guiPanel.getPanelY() + 2, guiPanel.getPanelX() + guiPanel.getPanelWidth() - 2, guiPanel.getPanelY() + guiPanel.getTitleHeight() - 2, guiPanel.isMouseOverCollapseButton(mouseX, mouseY) ? this.currentColor.getButtonDisabledBackgroundHovered() : this.currentColor.getButtonDisabledBackground(), guiPanel.isMouseOverCollapseButton(mouseX, mouseY) ? this.currentColor.getButtonDisabledBackgroundHovered() : this.currentColor.getButtonDisabledBackground());
            }
            Wrapper.getFontRenderer().drawString(guiPanel.getPanelName(), guiPanel.getPanelX() + 4, guiPanel.getPanelY() + 2, this.currentColor.getTextColor());
        }
        else {
            GuiMethods.drawRoundedRect(guiPanel.getPanelX(), guiPanel.getPanelY(), guiPanel.getPanelX() + guiPanel.getPanelWidth(), guiPanel.getPanelY() + guiPanel.getTitleHeight(), this.currentColor.getPanelTitle(), this.currentColor.getPanelTitle());
            if (guiPanel.isCollapsible()) {
                GuiMethods.drawRoundedRect(guiPanel.getPanelX() + guiPanel.getPanelWidth() - 11, guiPanel.getPanelY() + 2, guiPanel.getPanelX() + guiPanel.getPanelWidth() - 2, guiPanel.getPanelY() + guiPanel.getTitleHeight() - 2, guiPanel.isMouseOverCollapseButton(mouseX, mouseY) ? this.currentColor.getButtonDisabledBackgroundHovered() : this.currentColor.getButtonDisabledBackground(), guiPanel.isMouseOverCollapseButton(mouseX, mouseY) ? this.currentColor.getButtonDisabledBackgroundHovered() : this.currentColor.getButtonDisabledBackground());
            }
            Wrapper.getFontRenderer().drawString(guiPanel.getPanelName(), guiPanel.getPanelX() + 4, guiPanel.getPanelY() + 2, this.currentColor.getTextColor());
        }
    }
    
    @Override
    public void drawButton(final String caption, final int buttonX, final int buttonY, final int buttonWidth, final int buttonHeight, final boolean active, final boolean buttonHovered) {
        final int buttonColor = active ? (buttonHovered ? this.currentColor.getButtonEnabledBackgroundHovered() : this.currentColor.getButtonEnabledBackground()) : (buttonHovered ? this.currentColor.getButtonEnabledBackgroundHovered() : this.currentColor.getButtonDisabledBackground());
        GuiMethods.drawRoundedRect(buttonX, buttonY, buttonX + buttonWidth, buttonY + buttonHeight - 1, buttonColor, buttonColor);
        Wrapper.getFontRenderer().drawString(caption, buttonX + 5, buttonY + 2, this.currentColor.getTextColor());
    }
    
    @Override
    public void drawModuleButton(final Module module, final int buttonX, final int buttonY, final int buttonWidth, final int buttonHeight, final boolean buttonHovered, final boolean hasSettings, final boolean displayHelp) {
        final int buttonColor = module.isEnabled() ? (buttonHovered ? this.currentColor.getButtonEnabledBackgroundHovered() : this.currentColor.getButtonEnabledBackground()) : (buttonHovered ? this.currentColor.getButtonDisabledBackgroundHovered() : this.currentColor.getButtonDisabledBackground());
        GuiMethods.drawRoundedRect(buttonX, buttonY, buttonX + buttonWidth, buttonY + buttonHeight - 1, buttonColor, buttonColor);
        if (hasSettings) {
            GuiMethods.drawRightTri(buttonX + buttonWidth - 3, buttonY + buttonHeight / 2, 3, -1);
        }
        Wrapper.getFontRenderer().drawString(module.getName(), buttonX + 5, buttonY + 2, this.currentColor.getTextColor());
        if (displayHelp && module.getDescription().length() > 0) {
            GL11.glPushMatrix();
            GL11.glTranslatef(0.0f, 0.0f, 255.0f);
            final int posX = MouseService.getX() + 10;
            final int posY = MouseService.getY() + 10;
            GuiMethods.drawRoundedRect(posX - 3, posY, posX + Wrapper.getFontRenderer().getStringWidth(module.getDescription()) + 3, posY + Wrapper.getFontRenderer().FONT_HEIGHT + 3, this.currentColor.getPanelBackground(), this.currentColor.getPanelBackground());
            Wrapper.getFontRenderer().drawString(module.getDescription(), posX, posY + 2, -1);
            GL11.glPopMatrix();
        }
    }
    
    @Override
    public void drawCheckBox(final String caption, final boolean checked, final int elementX, final int elementY, final int elementWidth, final int elementHeight, final boolean elementHovered) {
        final int buttonColor = checked ? (elementHovered ? this.currentColor.getButtonEnabledBackgroundHovered() : this.currentColor.getButtonEnabledBackground()) : (elementHovered ? this.currentColor.getButtonDisabledBackgroundHovered() : this.currentColor.getButtonDisabledBackground());
        GuiMethods.drawRoundedRect(elementX, elementY, elementX + elementWidth, elementY + elementHeight - 1, buttonColor, buttonColor);
        Wrapper.getFontRenderer().drawString(caption, elementX + 5, elementY + 2, this.currentColor.getTextColor());
    }
    
    @Override
    public void drawDropdown(final String currentValue, final String[] values, final int elementX, final int elementY, final int elementWidth, final int elementHeight, final int defaultElementHeight, final boolean opened, final boolean buttonHovered) {
        GuiMethods.drawBorderedRect(elementX, elementY, elementX + elementWidth, elementY + elementHeight, 1, 1711276032, 855638016);
        if (opened) {
            GuiMethods.drawUpTri(elementX + elementWidth - 5, elementY + 5, 3, -16777216);
        }
        else {
            GuiMethods.drawDownTri(elementX + elementWidth - 5, elementY + 8, 3, -16777216);
        }
        Wrapper.getFontRenderer().drawString(currentValue, elementX + 5, elementY + 3, -1);
        if (opened) {
            int indexY = elementY + defaultElementHeight;
            for (final String value : values) {
                final int lowerY = indexY + defaultElementHeight;
                GuiMethods.drawBorderedRect(elementX, indexY - 1, elementX + elementWidth, indexY + defaultElementHeight, 1, -16777216, 0);
                Wrapper.getFontRenderer().drawString(value, elementX + 5, indexY + defaultElementHeight / 2 - 4, currentValue.equals(value) ? 16711680 : 16777215);
                indexY = lowerY;
            }
        }
    }
    
    @Override
    public void drawBlockButton(final IBlockState iBlockState, final int buttonX, final int buttonY, final int buttonWidth, final int buttonHeight, final boolean buttonHovered) {
        final int blockID = Block.getIdFromBlock(iBlockState.getBlock());
        final boolean isEnabled = ModuleXray.getBlockList().contains(blockID);
        RenderHelper.enableGUIStandardItemLighting();
        Wrapper.getMinecraft().getRenderItem().renderItemAndEffectIntoGUI(new ItemStack(iBlockState.getBlock(), 1), buttonX + 2, buttonY + 2);
        RenderHelper.disableStandardItemLighting();
        GL11.glPushMatrix();
        GL11.glTranslatef(0.0f, 0.0f, 255.0f);
        GL11.glScalef(0.5f, 0.5f, 0.5f);
        Wrapper.getFontRenderer().drawSplitString(iBlockState.getBlock().getLocalizedName(), buttonX * 2 + 2, buttonY * 2 + 2, 38, -1);
        GL11.glPopMatrix();
        GuiMethods.drawRoundedRect(buttonX, buttonY, buttonX + buttonWidth, buttonY + buttonHeight, isEnabled ? -16711936 : -65536, 0);
    }
    
    @Override
    public void drawColorButton(final String colorName, final int buttonX, final int buttonY, final int buttonWidth, final int buttonHeight, final boolean buttonHovered) {
        final int buttonColor = this.getCurrentColor().getColorName().equals(colorName) ? (buttonHovered ? this.currentColor.getButtonEnabledBackgroundHovered() : this.currentColor.getButtonEnabledBackground()) : (buttonHovered ? this.currentColor.getButtonDisabledBackgroundHovered() : this.currentColor.getButtonDisabledBackground());
        GuiMethods.drawRoundedRect(buttonX, buttonY, buttonX + buttonWidth, buttonY + buttonHeight - 1, buttonColor, buttonColor);
        Wrapper.getFontRenderer().drawString(colorName, buttonX + 5, buttonY + 2, this.currentColor.getTextColor());
    }
    
    public DefaultThemeColorObject getColorObject(final String colorName) {
        for (final DefaultThemeColorObject colorObject : this.getColorObjects()) {
            if (colorObject.getColorName().equalsIgnoreCase(colorName)) {
                return colorObject;
            }
        }
        return null;
    }
    
    @Override
    public void drawSlider(final ValueBase valueBase, final int sliderX, final int sliderY, final int sliderWidth, final int sliderHeight, final float dragX, final boolean shouldRound) {
        final DecimalFormat format = new DecimalFormat(shouldRound ? "0" : "0.0");
        GuiMethods.drawHLine((float)sliderX, (float)(sliderX + sliderWidth), (float)(sliderY + 12), this.currentColor.getButtonDisabledBackground());
        GuiMethods.drawHLine((float)sliderX, sliderX + dragX, (float)(sliderY + 12), this.currentColor.getButtonEnabledBackground());
        GuiMethods.drawFilledCircle((dragX + 3.0f > sliderWidth) ? (sliderX + sliderWidth) : (sliderX + (int)dragX + 3), sliderY + 12, 3.0f, this.currentColor.buttonDisabledBackgroundHovered);
        Wrapper.getFontRenderer().drawString(valueBase.getDisplayName() + ": " + format.format(valueBase.getValue()), sliderX, sliderY, this.currentColor.getTextColor());
        super.drawSlider(valueBase, sliderX, sliderY, sliderWidth, sliderHeight, dragX, shouldRound);
    }
    
    @Override
    public GuiThemeLayout getLayout() {
        return LegacyTheme.LAYOUT;
    }
    
    public ArrayList<DefaultThemeColorObject> getColorObjects() {
        return this.colorObjects;
    }
    
    public DefaultThemeColorObject getCurrentColor() {
        return this.currentColor;
    }
    
    public void setCurrentColor(final DefaultThemeColorObject currentColor) {
        this.currentColor = currentColor;
    }
    
    static {
        LAYOUT = new LegacyThemeLayout();
    }
    
    public class DefaultThemeColorObject
    {
        private String colorName;
        private int panelTitle;
        private int panelBackground;
        private int buttonDisabledBackground;
        private int buttonDisabledBackgroundHovered;
        private int buttonEnabledBackground;
        private int buttonEnabledBackgroundHovered;
        private int textColor;
        
        public DefaultThemeColorObject(final String colorName, final int panelTitle, final int panelBackground, final int buttonDisabledBackground, final int buttonDisabledBackgroundHovered, final int buttonEnabledBackground, final int buttonEnabledBackgroundHovered, final int textColor) {
            this.panelTitle = -1438682113;
            this.panelBackground = -1440274381;
            this.buttonDisabledBackground = -863202407;
            this.buttonDisabledBackgroundHovered = -858599727;
            this.buttonEnabledBackground = -868256769;
            this.buttonEnabledBackgroundHovered = -864707329;
            this.textColor = -1;
            this.colorName = colorName;
            this.panelTitle = panelTitle;
            this.panelBackground = panelBackground;
            this.buttonDisabledBackground = buttonDisabledBackground;
            this.buttonDisabledBackgroundHovered = buttonDisabledBackgroundHovered;
            this.buttonEnabledBackground = buttonEnabledBackground;
            this.buttonEnabledBackgroundHovered = buttonEnabledBackgroundHovered;
        }
        
        public String getColorName() {
            return this.colorName;
        }
        
        public int getPanelTitle() {
            return this.panelTitle;
        }
        
        public int getPanelBackground() {
            return this.panelBackground;
        }
        
        public int getButtonDisabledBackground() {
            return this.buttonDisabledBackground;
        }
        
        public int getButtonDisabledBackgroundHovered() {
            return this.buttonDisabledBackgroundHovered;
        }
        
        public int getButtonEnabledBackground() {
            return this.buttonEnabledBackground;
        }
        
        public int getButtonEnabledBackgroundHovered() {
            return this.buttonEnabledBackgroundHovered;
        }
        
        public int getTextColor() {
            return this.textColor;
        }
    }
}
