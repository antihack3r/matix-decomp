// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.gui.menu.login;

import java.util.function.Function;
import java.util.stream.Collector;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import de.paxii.clarinet.util.file.FileService;
import de.paxii.clarinet.util.alt.AltContainer;
import de.paxii.clarinet.util.notifications.NotificationPriority;
import de.paxii.clarinet.util.login.YggdrasilLoginBridge;
import java.util.ArrayList;
import net.minecraft.client.gui.GuiButton;
import de.paxii.clarinet.event.EventHandler;
import net.minecraft.client.gui.GuiMainMenu;
import de.paxii.clarinet.event.events.Event;
import de.paxii.clarinet.event.events.gui.DisplayGuiScreenEvent;
import de.paxii.clarinet.Wrapper;
import java.io.IOException;
import de.paxii.clarinet.util.settings.ClientSettings;
import java.io.File;
import de.paxii.clarinet.gui.menu.hooks.GuiMainMenuHook;
import net.minecraft.client.gui.GuiScreen;

public class GuiAltManager extends GuiScreen
{
    private final GuiScreen parentScreen;
    private final GuiMainMenuHook mainMenuHook;
    private final File altFile;
    GuiAltList guiAltList;
    private GuiAltListEntry pressedSlot;
    
    public GuiAltManager(final GuiScreen parentScreen) {
        this.parentScreen = parentScreen;
        if (this.parentScreen instanceof GuiMainMenuHook) {
            this.mainMenuHook = (GuiMainMenuHook)this.parentScreen;
        }
        else {
            this.mainMenuHook = new GuiMainMenuHook();
        }
        this.altFile = new File(ClientSettings.getClientFolderPath().getValue(), "alts.json");
        try {
            this.altFile.createNewFile();
        }
        catch (final IOException e) {
            e.printStackTrace();
        }
        this.setupGuiAltList(this.loadAlts());
        Wrapper.getEventManager().register(this, DisplayGuiScreenEvent.class);
    }
    
    @EventHandler
    public void onDisplayGuiScreen(final DisplayGuiScreenEvent event) {
        if (ClientSettings.getValue("client.hidden", Boolean.class)) {
            return;
        }
        if (event.getGuiScreen() instanceof GuiMainMenu || (event.getGuiScreen() == null && Wrapper.getWorld() == null)) {
            event.setGuiScreen(this.mainMenuHook);
        }
    }
    
    @Override
    public void initGui() {
        this.setupGuiAltList(this.guiAltList.getAltObjects());
        this.buttonList.clear();
        this.buttonList.add(new GuiButton(0, this.width / 2 - 50, this.height - 25, 100, 20, "Delete"));
        this.buttonList.add(new GuiButton(1, this.width / 2 - 160, this.height - 25, 100, 20, "Add"));
        this.buttonList.add(new GuiButton(2, this.width / 2 + 60, this.height - 25, 100, 20, "Done"));
        this.buttonList.add(new GuiButton(3, this.width / 2 - 50, this.height - 50, 100, 20, "Login"));
        this.buttonList.add(new GuiButton(4, this.width / 2 + 60, this.height - 50, 100, 20, "Direct"));
        this.buttonList.add(new GuiButton(5, this.width / 2 - 160, this.height - 50, 100, 20, "MCLeaks.net"));
    }
    
    public void setupGuiAltList(final ArrayList<AltObject> altObjects) {
        this.guiAltList = new GuiAltList(this, altObjects);
    }
    
    @Override
    protected void keyTyped(final char typedChar, final int keyCode) throws IOException {
        if (keyCode == 1) {
            Wrapper.getMinecraft().displayGuiScreen(this.parentScreen);
        }
    }
    
    @Override
    public void drawScreen(final int mouseX, final int mouseY, final float partialTicks) {
        this.drawDefaultBackground();
        if (this.guiAltList != null) {
            this.guiAltList.drawScreen(mouseX, mouseY, partialTicks);
        }
        this.drawCenteredString(this.fontRenderer, "AltManager", this.width / 2, 8, 16777215);
        this.drawString(Wrapper.getFontRenderer(), "Username: ", 5, 8, 16777215);
        this.drawString(Wrapper.getFontRenderer(), Wrapper.getMinecraft().getSession().getUsername(), 5 + Wrapper.getFontRenderer().getStringWidth("Username: "), 8, 65280);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
    
    @Override
    protected void actionPerformed(final GuiButton button) throws IOException {
        if (button.id == 0 && this.pressedSlot != null) {
            this.guiAltList.getEntryList().remove(this.getPressedSlot());
        }
        else if (button.id == 1) {
            Wrapper.getMinecraft().displayGuiScreen(new GuiAddAlt(this));
        }
        else if (button.id == 2) {
            Wrapper.getMinecraft().displayGuiScreen(this.parentScreen);
        }
        else if (button.id == 3 && this.pressedSlot != null) {
            if (YggdrasilLoginBridge.loginWithAlt(this.getPressedSlot().getAlt()) == null) {
                Wrapper.getClient().getNotificationManager().addNotification("Invalid Credentials!", NotificationPriority.DANGER);
            }
        }
        else if (button.id == 4) {
            Wrapper.getMinecraft().displayGuiScreen(new GuiDirectLogin(this));
        }
        else if (button.id == 5) {
            Wrapper.getMinecraft().displayGuiScreen(new GuiMcLeaksLogin(this));
        }
    }
    
    @Override
    public void handleMouseInput() throws IOException {
        super.handleMouseInput();
        this.guiAltList.handleMouseInput();
    }
    
    @Override
    protected void mouseClicked(final int mouseX, final int mouseY, final int mouseButton) throws IOException {
        this.guiAltList.mouseClicked(mouseX, mouseY, mouseButton);
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }
    
    @Override
    protected void mouseReleased(final int mouseX, final int mouseY, final int state) {
        this.guiAltList.mouseReleased(mouseX, mouseY, state);
        super.mouseReleased(mouseX, mouseY, state);
    }
    
    @Override
    public void onGuiClosed() {
        this.saveAlts();
    }
    
    private ArrayList<AltObject> loadAlts() {
        ArrayList<AltObject> altObjects = new ArrayList<AltObject>(10);
        try {
            final AltContainer altContainer = FileService.getFileContents(this.altFile, AltContainer.class);
            if (altContainer != null) {
                altObjects = altContainer.getAltList().stream().sorted().peek(alt -> alt.setPassword(Wrapper.getStringEncryption().decryptString(alt.getPassword()))).collect((Collector<? super Object, ?, ArrayList<AltObject>>)Collectors.toCollection((Supplier<R>)ArrayList::new));
            }
        }
        catch (final Exception e) {
            e.printStackTrace();
        }
        return altObjects;
    }
    
    private void saveAlts() {
        try {
            if (this.altFile.exists() && !this.altFile.delete()) {
                return;
            }
            final AltContainer altContainer = new AltContainer(this.guiAltList.getAltObjects().stream().map((Function<? super Object, ?>)AltObject::copy).peek(alt -> alt.setPassword(Wrapper.getStringEncryption().encryptString(alt.getPassword()))).collect((Collector<? super Object, ?, ArrayList<AltObject>>)Collectors.toCollection((Supplier<R>)ArrayList::new)));
            FileService.setFileContentsAsJson(this.altFile, altContainer);
        }
        catch (final IOException e) {
            e.printStackTrace();
        }
    }
    
    public GuiAltListEntry getPressedSlot() {
        return this.pressedSlot;
    }
    
    public void setPressedSlot(final GuiAltListEntry pressedSlot) {
        this.pressedSlot = pressedSlot;
    }
}
