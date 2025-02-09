// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.gui.menu.login;

import de.paxii.clarinet.util.login.YggdrasilLoginBridge;
import de.paxii.clarinet.util.notifications.NotificationPriority;
import java.io.IOException;
import de.paxii.clarinet.Wrapper;
import java.util.ArrayList;
import net.minecraft.client.gui.GuiButton;
import de.paxii.clarinet.util.gui.GuiPasswordField;
import net.minecraft.client.gui.GuiTextField;
import java.util.List;
import net.minecraft.client.gui.GuiScreen;

public class GuiDirectLogin extends GuiScreen
{
    protected final GuiAltManager altManager;
    protected String displayTitle;
    protected List<GuiTextField> textFieldList;
    protected GuiTextField userNameField;
    protected GuiPasswordField passwordField;
    protected int buttonWidth;
    protected GuiButton loginButton;
    protected GuiButton cancelButton;
    
    public GuiDirectLogin(final GuiAltManager altManager) {
        this.displayTitle = "Login";
        this.buttonWidth = 200;
        this.altManager = altManager;
        this.textFieldList = new ArrayList<GuiTextField>(3);
    }
    
    @Override
    public void initGui() {
        final int startX = this.width / 2 - 100;
        this.textFieldList.add(this.userNameField = new GuiTextField(3, Wrapper.getFontRenderer(), startX, this.height / 2 - 50, this.buttonWidth, 20));
        this.textFieldList.add(this.passwordField = new GuiPasswordField(5, Wrapper.getFontRenderer(), startX, this.height / 2 - 10, this.buttonWidth, 20));
        this.buttonList.add(this.cancelButton = new GuiButton(0, this.width / 2 - 105, this.height - 25, 100, 20, "Cancel"));
        this.buttonList.add(this.loginButton = new GuiButton(1, this.width / 2 + 5, this.height - 25, 100, 20, "Login"));
    }
    
    @Override
    public void drawScreen(final int mouseX, final int mouseY, final float partialTicks) {
        this.drawDefaultBackground();
        this.drawCenteredString(Wrapper.getFontRenderer(), this.displayTitle, this.width / 2, 5, 16777215);
        this.textFieldList.forEach(GuiTextField::drawTextBox);
        this.drawTextFieldLabels();
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
    
    protected void drawTextFieldLabels() {
        this.drawCenteredString(Wrapper.getFontRenderer(), "Username:", this.width / 2, this.height / 2 - 65, 16777215);
        this.drawCenteredString(Wrapper.getFontRenderer(), "Password:", this.width / 2, this.height / 2 - 25, 16777215);
    }
    
    @Override
    protected void keyTyped(final char typedChar, final int keyCode) throws IOException {
        if (keyCode == 15) {
            this.cycleSelection();
        }
        this.textFieldList.forEach(textField -> textField.textboxKeyTyped(typedChar, keyCode));
    }
    
    protected void cycleSelection() {
        if (this.userNameField.isFocused()) {
            this.setTextFieldFocused(this.passwordField);
        }
        else if (this.passwordField.isFocused()) {
            this.setTextFieldFocused(this.userNameField);
        }
        else {
            this.setTextFieldFocused(this.userNameField);
        }
    }
    
    protected void setTextFieldFocused(final GuiTextField textField) {
        textField.setFocused(true);
        this.textFieldList.stream().filter(textFieldInList -> textFieldInList != textField).forEach(textFieldInList -> textFieldInList.setFocused(false));
    }
    
    @Override
    protected void actionPerformed(final GuiButton button) throws IOException {
        if (button == this.cancelButton) {
            Wrapper.getMinecraft().displayGuiScreen(this.altManager);
        }
        else if (button == this.loginButton) {
            final String userName = this.userNameField.getText();
            final String password = this.passwordField.getText();
            if (userName.length() == 0 && password.length() == 0) {
                Wrapper.getClient().getNotificationManager().addNotification("Invalid Credentials", NotificationPriority.DANGER);
            }
            else {
                final AltObject altObject = new AltObject(userName.contains("@") ? "" : userName, userName.contains("@") ? userName : "", password);
                if (YggdrasilLoginBridge.loginWithAlt(altObject) != null) {
                    Wrapper.getMinecraft().displayGuiScreen(this.altManager);
                }
                else {
                    Wrapper.getClient().getNotificationManager().addNotification("Invalid Credentials", NotificationPriority.DANGER);
                }
            }
        }
    }
    
    @Override
    protected void mouseClicked(final int mouseX, final int mouseY, final int mouseButton) throws IOException {
        this.textFieldList.forEach(textField -> textField.mouseClicked(mouseX, mouseY, mouseButton));
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }
}
