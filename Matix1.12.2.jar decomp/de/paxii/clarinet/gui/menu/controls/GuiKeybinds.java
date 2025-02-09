// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.gui.menu.controls;

import de.paxii.clarinet.Client;
import java.io.IOException;
import de.paxii.clarinet.Wrapper;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

public class GuiKeybinds extends GuiScreen
{
    private GuiKeybindsList keybindsList;
    private GuiScreen parentScreen;
    boolean shouldListen;
    private GuiListEntryModuleKey selectedButton;
    private GuiListEntryModuleKey pressedButton;
    private int pressedKey;
    private GuiButton doneButton;
    
    public GuiKeybinds(final GuiScreen parentScreen) {
        this.parentScreen = parentScreen;
    }
    
    @Override
    public void initGui() {
        this.keybindsList = new GuiKeybindsList(this);
        this.doneButton = new GuiButton(0, this.width / 2 - 50, this.height - 25, 100, 20, "Done");
        this.buttonList.add(this.doneButton);
    }
    
    @Override
    protected void keyTyped(final char typedChar, final int keyCode) throws IOException {
        if (keyCode == 1) {
            if (this.isShouldListen()) {
                this.getPressedButton().getModule().setKey(-1);
                this.setShouldListen(false);
                this.setPressedButton(null);
            }
            else {
                Wrapper.getMinecraft().displayGuiScreen(this.parentScreen);
            }
        }
        if (this.isShouldListen()) {
            if (this.getPressedButton() != null) {
                this.getPressedButton().getModule().setKey(keyCode);
            }
            this.setShouldListen(false);
            this.pressedKey = keyCode;
        }
    }
    
    @Override
    public void handleMouseInput() throws IOException {
        this.keybindsList.handleMouseInput();
        super.handleMouseInput();
    }
    
    @Override
    protected void actionPerformed(final GuiButton button) throws IOException {
        if (button.id == this.doneButton.id) {
            Wrapper.getMinecraft().displayGuiScreen(this.parentScreen);
        }
    }
    
    @Override
    protected void mouseClicked(final int mouseX, final int mouseY, final int mouseButton) throws IOException {
        this.keyTyped(' ', mouseButton - 100);
        this.keybindsList.mouseClicked(mouseX, mouseY, mouseButton);
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }
    
    @Override
    protected void mouseReleased(final int mouseX, final int mouseY, final int state) {
        this.keybindsList.mouseReleased(mouseX, mouseY, state);
        super.mouseReleased(mouseX, mouseY, state);
    }
    
    @Override
    public void drawScreen(final int mouseX, final int mouseY, final float partialTicks) {
        this.drawDefaultBackground();
        if (this.keybindsList != null) {
            this.keybindsList.drawScreen(mouseX, mouseY, partialTicks);
        }
        this.drawCenteredString(this.fontRenderer, Client.getClientName() + " Keys", this.width / 2, 8, 16777215);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
    
    public void setParentScreen(final GuiScreen parentScreen) {
        this.parentScreen = parentScreen;
    }
    
    public boolean isShouldListen() {
        return this.shouldListen;
    }
    
    public void setShouldListen(final boolean shouldListen) {
        this.shouldListen = shouldListen;
    }
    
    public GuiListEntryModuleKey getSelectedButton() {
        return this.selectedButton;
    }
    
    public void setSelectedButton(final GuiListEntryModuleKey selectedButton) {
        this.selectedButton = selectedButton;
    }
    
    public GuiListEntryModuleKey getPressedButton() {
        return this.pressedButton;
    }
    
    public void setPressedButton(final GuiListEntryModuleKey pressedButton) {
        this.pressedButton = pressedButton;
    }
    
    public int getPressedKey() {
        return this.pressedKey;
    }
}
