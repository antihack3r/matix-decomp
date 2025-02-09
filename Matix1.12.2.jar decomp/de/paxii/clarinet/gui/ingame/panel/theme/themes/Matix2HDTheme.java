// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.gui.ingame.panel.theme.themes;

import de.paxii.clarinet.gui.ingame.panel.theme.layout.Matix2HDThemeLayout;
import java.text.DecimalFormat;
import de.paxii.clarinet.util.module.settings.ValueBase;
import net.minecraft.item.ItemStack;
import de.paxii.clarinet.Wrapper;
import net.minecraft.client.renderer.RenderHelper;
import de.paxii.clarinet.module.render.ModuleXray;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import de.paxii.clarinet.util.gui.MouseService;
import org.lwjgl.opengl.GL11;
import de.paxii.clarinet.module.Module;
import de.paxii.clarinet.util.chat.font.FontManager;
import de.paxii.clarinet.util.render.GuiMethods;
import de.paxii.clarinet.gui.ingame.panel.GuiPanelModuleSettings;
import de.paxii.clarinet.gui.ingame.panel.GuiPanel;
import de.paxii.clarinet.gui.ingame.panel.theme.layout.GuiThemeLayout;
import de.paxii.clarinet.gui.ingame.panel.theme.GuiTheme;

public class Matix2HDTheme implements GuiTheme
{
    private static final GuiThemeLayout LAYOUT;
    
    @Override
    public String getName() {
        return "Matix2HD";
    }
    
    @Override
    public void drawPanel(final GuiPanel guiPanel, final int mouseX, final int mouseY) {
        int backgroundStart = -10987175;
        int backgroundEnd = -1722263207;
        final int enabledButton = -5723992;
        if (guiPanel.getPanelName().equalsIgnoreCase("player")) {
            backgroundStart = -15764961;
            backgroundEnd = -1727040993;
        }
        else if (guiPanel.getPanelName().equalsIgnoreCase("movement")) {
            backgroundStart = -10024082;
            backgroundEnd = -1721300114;
        }
        else if (guiPanel.getPanelName().equalsIgnoreCase("combat")) {
            backgroundStart = -9436150;
            backgroundEnd = -1720712182;
        }
        else if (guiPanel.getPanelName().equalsIgnoreCase("render")) {
            backgroundStart = -45824;
            backgroundEnd = -1711321856;
        }
        else if (guiPanel.getPanelName().equalsIgnoreCase("world")) {
            backgroundStart = -16300176;
            backgroundEnd = -1727576208;
        }
        else if (guiPanel instanceof GuiPanelModuleSettings) {
            backgroundEnd = backgroundStart;
        }
        GuiMethods.drawGradientRect(guiPanel.getPanelX(), guiPanel.getPanelY(), guiPanel.getPanelX() + guiPanel.getPanelWidth(), (double)(guiPanel.getPanelY() + (guiPanel.isOpened() ? guiPanel.getPanelHeight() : guiPanel.getTitleHeight())), backgroundStart, backgroundEnd);
        if (guiPanel.isCollapsible()) {
            GuiMethods.drawRoundedRect(guiPanel.getPanelX() + guiPanel.getPanelWidth() - 11, guiPanel.getPanelY() + 2, guiPanel.getPanelX() + guiPanel.getPanelWidth() - 2, guiPanel.getPanelY() + guiPanel.getTitleHeight() - 2, enabledButton, enabledButton);
        }
        FontManager.getDefaultFont().drawString(guiPanel.getPanelName(), guiPanel.getPanelX() + 4, guiPanel.getPanelY() - 2, -1);
    }
    
    @Override
    public void drawButton(final String caption, final int buttonX, final int buttonY, final int buttonWidth, final int buttonHeight, final boolean active, final boolean buttonHovered) {
        int buttonColor = 1426063360;
        final int textColor = -1;
        if (active) {
            buttonColor = -10445252;
            if (buttonHovered) {
                buttonColor = -5338063;
            }
        }
        else if (buttonHovered) {
            buttonColor = 855638016;
        }
        GuiMethods.drawRect(buttonX, buttonY, buttonX + buttonWidth, buttonY + buttonHeight - 1, buttonColor);
        FontManager.getDefaultFont().drawString(caption, buttonX + 3, buttonY - 2, textColor);
    }
    
    @Override
    public void drawModuleButton(final Module module, final int buttonX, final int buttonY, final int buttonWidth, final int buttonHeight, final boolean buttonHovered, final boolean hasSettings, final boolean displayHelp) {
        int buttonColor = 1426063360;
        final int textColor = -1;
        if (module.isEnabled()) {
            buttonColor = -10445252;
            if (buttonHovered) {
                buttonColor = -5338063;
            }
        }
        else if (buttonHovered) {
            buttonColor = 855638016;
        }
        GuiMethods.drawRect(buttonX, buttonY, buttonX + buttonWidth, buttonY + buttonHeight - 1, buttonColor);
        if (hasSettings) {
            GuiMethods.drawRightTri(buttonX + buttonWidth - 3, buttonY + buttonHeight / 2, 3, -1);
        }
        FontManager.getDefaultFont().drawString(module.getName(), buttonX + 3, buttonY - 2, textColor);
        if (displayHelp && module.getDescription().length() > 0) {
            GL11.glPushMatrix();
            GL11.glTranslatef(0.0f, 0.0f, 255.0f);
            final int posX = MouseService.getX() + 10;
            final int posY = MouseService.getY() + 10;
            GuiMethods.drawRect(posX - 3, posY, posX + (int)FontManager.getDefaultFont().getStringWidth(module.getDescription()) + 3, posY + (int)FontManager.getDefaultFont().getStringHeight(module.getDescription()) + 3, -10987175);
            FontManager.getDefaultFont().drawString(module.getDescription(), posX, posY - 2, -1);
            GL11.glPopMatrix();
        }
    }
    
    @Override
    public void drawCheckBox(final String caption, final boolean checked, final int elementX, final int elementY, final int elementWidth, final int elementHeight, final boolean elementHovered) {
        int buttonColor = 1426063360;
        final int textColor = -1;
        if (checked) {
            buttonColor = -10445252;
            if (elementHovered) {
                buttonColor = -5338063;
            }
        }
        else if (elementHovered) {
            buttonColor = 855638016;
        }
        GuiMethods.drawRect(elementX, elementY, elementX + elementWidth, elementY + elementHeight - 1, buttonColor);
        FontManager.getDefaultFont().drawString(caption, elementX + 3, elementY - 2, textColor);
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
        FontManager.getDefaultFont().drawString(currentValue, elementX + 5, elementY - 1, -1);
        if (opened) {
            int indexY = elementY + defaultElementHeight;
            for (final String value : values) {
                final int lowerY = indexY + defaultElementHeight;
                GuiMethods.drawBorderedRect(elementX, indexY - 1, elementX + elementWidth, indexY + defaultElementHeight, 1, -13421773, 0);
                FontManager.getDefaultFont().drawString(value, elementX + 5, indexY - 2, currentValue.equals(value) ? -65536 : -1);
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
    }
    
    @Override
    public void drawSlider(final ValueBase valueBase, final int sliderX, final int sliderY, final int sliderWidth, final int sliderHeight, final float dragX, final boolean shouldRound) {
        final DecimalFormat format = new DecimalFormat(shouldRound ? "0" : "0.0");
        GuiMethods.drawHLine((float)sliderX, (float)(sliderX + sliderWidth), (float)(sliderY + 12), -5592406);
        GuiMethods.drawHLine((float)sliderX, sliderX + dragX, (float)(sliderY + 12), -16742145);
        GuiMethods.drawFilledCircle((dragX + 3.0f > sliderWidth) ? (sliderX + sliderWidth) : (sliderX + (int)dragX + 3), sliderY + 12, 3.0f, -6710887);
        FontManager.getDefaultFont().drawString(valueBase.getDisplayName() + ": " + format.format(valueBase.getValue()), sliderX, sliderY - 2, -1);
        super.drawSlider(valueBase, sliderX, sliderY, sliderWidth, sliderHeight, dragX, shouldRound);
    }
    
    @Override
    public GuiThemeLayout getLayout() {
        return Matix2HDTheme.LAYOUT;
    }
    
    static {
        LAYOUT = new Matix2HDThemeLayout();
    }
}
