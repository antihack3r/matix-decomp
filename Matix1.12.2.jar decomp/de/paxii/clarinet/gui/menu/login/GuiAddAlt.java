// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.gui.menu.login;

import java.io.IOException;
import java.util.ArrayList;
import net.minecraft.client.gui.GuiScreen;
import de.paxii.clarinet.util.notifications.NotificationPriority;
import net.minecraft.client.gui.GuiButton;
import de.paxii.clarinet.Wrapper;
import net.minecraft.client.gui.GuiTextField;

public class GuiAddAlt extends GuiDirectLogin
{
    protected GuiTextField emailField;
    
    public GuiAddAlt(final GuiAltManager altManager) {
        super(altManager);
        this.displayTitle = "Add Alt";
    }
    
    @Override
    public void initGui() {
        super.initGui();
        final int startX = this.width / 2 - 100;
        this.passwordField.x = startX;
        this.passwordField.y = this.height / 2 + 30;
        this.textFieldList.add(this.emailField = new GuiTextField(4, Wrapper.getFontRenderer(), startX, this.height / 2 - 10, this.buttonWidth, 20));
        this.loginButton.displayString = "Save";
    }
    
    @Override
    protected void drawTextFieldLabels() {
        this.drawCenteredString(Wrapper.getFontRenderer(), "Username:", this.width / 2, this.height / 2 - 65, 16777215);
        this.drawCenteredString(Wrapper.getFontRenderer(), "Email:", this.width / 2, this.height / 2 - 25, 16777215);
        this.drawCenteredString(Wrapper.getFontRenderer(), "Password:", this.width / 2, this.height / 2 + 15, 16777215);
    }
    
    @Override
    protected void cycleSelection() {
        if (this.userNameField.isFocused()) {
            this.setTextFieldFocused(this.emailField);
        }
        else if (this.emailField.isFocused()) {
            this.setTextFieldFocused(this.passwordField);
        }
        else {
            super.cycleSelection();
        }
    }
    
    @Override
    protected void actionPerformed(final GuiButton button) throws IOException {
        if (button == this.loginButton) {
            final String userName = this.userNameField.getText();
            final String email = this.emailField.getText();
            final String password = this.passwordField.getText();
            if (email.length() > 0 && password.length() == 0) {
                Wrapper.getClient().getNotificationManager().addNotification("Invalid Password", NotificationPriority.DANGER);
            }
            else if (userName.length() == 0 && email.length() == 0 && password.length() == 0) {
                Wrapper.getClient().getNotificationManager().addNotification("Invalid Credentials", NotificationPriority.DANGER);
            }
            else if (email.length() > 0 && !email.contains("@")) {
                Wrapper.getClient().getNotificationManager().addNotification("Invalid Email", NotificationPriority.DANGER);
            }
            else {
                final ArrayList<AltObject> altObjects = this.altManager.guiAltList.getAltObjects();
                altObjects.add(new AltObject(userName, email, password));
                this.altManager.setupGuiAltList(altObjects);
                this.altManager.initGui();
                Wrapper.getMinecraft().displayGuiScreen(this.altManager);
            }
        }
        else {
            super.actionPerformed(button);
        }
    }
}
