// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.gui.ingame.panel.theme.themes;

import de.paxii.clarinet.gui.ingame.panel.theme.layout.DefaultThemeLayout;
import java.text.DecimalFormat;
import de.paxii.clarinet.util.module.settings.ValueBase;
import net.minecraft.item.ItemStack;
import net.minecraft.client.renderer.RenderHelper;
import de.paxii.clarinet.module.render.ModuleXray;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import de.paxii.clarinet.Wrapper;
import de.paxii.clarinet.util.gui.MouseService;
import org.lwjgl.opengl.GL11;
import de.paxii.clarinet.module.Module;
import de.paxii.clarinet.util.render.GuiMethods;
import de.paxii.clarinet.util.chat.font.FontManager;
import de.paxii.clarinet.gui.ingame.panel.GuiPanel;
import net.minecraft.util.ResourceLocation;
import de.paxii.clarinet.gui.ingame.panel.theme.layout.GuiThemeLayout;
import de.paxii.clarinet.gui.ingame.panel.theme.GuiTheme;

public class DefaultTheme implements GuiTheme
{
    private static final GuiThemeLayout LAYOUT;
    private static final ResourceLocation PANEL_HEADER;
    private static final ResourceLocation MODULE_ENABLED;
    private static final ResourceLocation MODULE_ENABLED_HOVERED;
    private static final ResourceLocation MODULE_DISABLED;
    private static final ResourceLocation MODULE_DISABLED_HOVERED;
    
    @Override
    public String getName() {
        return "Default";
    }
    
    @Override
    public void drawPanel(final GuiPanel guiPanel, final int mouseX, final int mouseY) {
        for (int i = 0; i < (guiPanel.getPanelWidth() - 30) * 2; i += 12) {
            this.drawTexture(DefaultTheme.PANEL_HEADER, guiPanel.getPanelX() + 20 + i / 2, guiPanel.getPanelY(), 0, 124, 12, 45);
        }
        this.drawTexture(DefaultTheme.PANEL_HEADER, guiPanel.getPanelX(), guiPanel.getPanelY(), 0, 0, 45, 45);
        this.drawTexture(DefaultTheme.PANEL_HEADER, guiPanel.getPanelX() + guiPanel.getPanelWidth() - 20, guiPanel.getPanelY(), 0, 64, 45, 45);
        FontManager.getBigUbuntuFont().drawCenteredString(guiPanel.getPanelName(), guiPanel.getPanelX() + guiPanel.getPanelWidth() / 2, guiPanel.getPanelY() + guiPanel.getTitleHeight() / 2 - (int)FontManager.getBigUbuntuFont().getStringHeight(" ") / 2 + 1, -1);
        if (guiPanel.isCollapsible()) {
            FontManager.getSmallUbuntuFont().drawCenteredString(guiPanel.isOpened() ? "-" : "+", guiPanel.getPanelX() + guiPanel.getPanelWidth() - 10, guiPanel.getPanelY() + guiPanel.getTitleHeight() / 2 - (int)FontManager.getBigUbuntuFont().getStringHeight(" ") / 2 + 1, -1);
            GuiMethods.drawCircle(guiPanel.getPanelX() + guiPanel.getPanelWidth() - 10, guiPanel.getPanelY() + guiPanel.getTitleHeight() / 2 + 4, 5, -1);
        }
        if (guiPanel.isOpened()) {
            GuiMethods.drawGradientRect(guiPanel.getPanelX(), guiPanel.getPanelY() + 19, guiPanel.getPanelX() + guiPanel.getPanelWidth(), (double)(guiPanel.getPanelY() + guiPanel.getPanelHeight()), -1728053248, -1728053248);
        }
    }
    
    @Override
    public void drawButton(final String caption, final int buttonX, final int buttonY, final int buttonWidth, final int buttonHeight, final boolean active, final boolean buttonHovered) {
        ResourceLocation resourceLocation;
        if (active) {
            if (buttonHovered) {
                resourceLocation = DefaultTheme.MODULE_ENABLED_HOVERED;
            }
            else {
                resourceLocation = DefaultTheme.MODULE_ENABLED;
            }
        }
        else if (buttonHovered) {
            resourceLocation = DefaultTheme.MODULE_DISABLED_HOVERED;
        }
        else {
            resourceLocation = DefaultTheme.MODULE_DISABLED;
        }
        this.drawTexture(resourceLocation, buttonX, buttonY, 0, 0, 256, 256);
        FontManager.getSmallUbuntuFont().drawCenteredString(caption, buttonX + buttonWidth / 2, buttonY + buttonHeight / 2 - 7, -1);
    }
    
    @Override
    public void drawModuleButton(final Module module, final int buttonX, final int buttonY, final int buttonWidth, final int buttonHeight, final boolean buttonHovered, final boolean hasSettings, final boolean displayHelp) {
        this.drawButton(module.getName(), buttonX, buttonY, buttonWidth, buttonHeight, module.isEnabled(), buttonHovered);
        if (hasSettings) {
            GuiMethods.drawRightTri(buttonX + buttonWidth + 2, buttonY + buttonHeight / 2 + 1, 3, -1);
        }
        if (displayHelp && module.getDescription().length() > 0) {
            GL11.glPushMatrix();
            GL11.glTranslatef(0.0f, 0.0f, 255.0f);
            final int posX = MouseService.getX() + 10;
            final int posY = MouseService.getY() + 10;
            GuiMethods.drawRoundedRect(posX - 3, posY, posX + (int)FontManager.getUbuntuFont().getStringWidth(module.getDescription()), posY + Wrapper.getFontRenderer().FONT_HEIGHT + 3, -10987175, -10987175);
            FontManager.getUbuntuFont().drawString(module.getDescription(), posX, posY - 2, -1);
            GL11.glPopMatrix();
        }
    }
    
    @Override
    public void drawCheckBox(final String caption, final boolean checked, final int elementX, final int elementY, final int elementWidth, final int elementHeight, final boolean elementHovered) {
        this.drawButton(caption, elementX, elementY, elementWidth, elementHeight, checked, elementHovered);
    }
    
    @Override
    public void drawDropdown(final String currentValue, final String[] values, final int elementX, final int elementY, final int elementWidth, final int elementHeight, final int defaultElementHeight, final boolean opened, final boolean elementHovered) {
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
        GuiMethods.drawHLine((float)sliderX, (float)(sliderX + sliderWidth), (float)(sliderY + 12), -2763307);
        GuiMethods.drawHLine((float)sliderX, sliderX + dragX, (float)(sliderY + 12), -12755287);
        GuiMethods.drawFilledCircle((dragX + 3.0f > sliderWidth) ? (sliderX + sliderWidth) : (sliderX + (int)dragX + 3), sliderY + 12, 3.0f, -1);
        FontManager.getSmallUbuntuFont().drawString(valueBase.getDisplayName() + ": " + format.format(valueBase.getValue()), sliderX, sliderY - 4, -1);
        super.drawSlider(valueBase, sliderX, sliderY, sliderWidth, sliderHeight, dragX, shouldRound);
    }
    
    @Override
    public GuiThemeLayout getLayout() {
        return DefaultTheme.LAYOUT;
    }
    
    protected void drawTexture(final ResourceLocation resource, final int x, final int y, final int textureX, final int textureY, final int width, final int height) {
        GL11.glPushMatrix();
        GL11.glEnable(3042);
        GL11.glScaled(0.5, 0.5, 0.5);
        Wrapper.getMinecraft().getTextureManager().bindTexture(resource);
        GuiMethods.drawTexturedModalRect(x * 2, y * 2, textureX, textureY, width, height, 0.0);
        GL11.glDisable(3042);
        GL11.glPopMatrix();
    }
    
    protected static ResourceLocation getResource(final String fileName) {
        return new ResourceLocation("matix", String.format("textures/matix-theme-default/%s.png", fileName));
    }
    
    static {
        LAYOUT = new DefaultThemeLayout();
        PANEL_HEADER = getResource("panel-header");
        MODULE_ENABLED = getResource("module-enabled");
        MODULE_ENABLED_HOVERED = getResource("module-enabled-hovered");
        MODULE_DISABLED = getResource("module-disabled");
        MODULE_DISABLED_HOVERED = getResource("module-disabled-hovered");
    }
}
