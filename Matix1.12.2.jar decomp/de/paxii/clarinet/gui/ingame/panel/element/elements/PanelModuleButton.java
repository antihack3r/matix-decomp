// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.gui.ingame.panel.element.elements;

import de.paxii.clarinet.gui.ingame.panel.theme.layout.ElementSpacing;
import de.paxii.clarinet.event.events.player.PlayerSendChatMessageEvent;
import net.minecraft.network.play.client.CPacketChatMessage;
import de.paxii.clarinet.util.settings.ClientSettings;
import de.paxii.clarinet.Wrapper;
import de.paxii.clarinet.util.module.settings.ValueBase;
import java.util.function.Function;
import org.lwjgl.input.Keyboard;
import java.util.Comparator;
import java.util.Collection;
import de.paxii.clarinet.util.settings.ClientSetting;
import java.util.ArrayList;
import de.paxii.clarinet.util.module.killaura.TimeManager;
import de.paxii.clarinet.gui.ingame.panel.GuiPanelModuleSettings;
import de.paxii.clarinet.module.Module;
import de.paxii.clarinet.gui.ingame.panel.GuiPanel;
import de.paxii.clarinet.gui.ingame.panel.element.PanelElement;

public class PanelModuleButton extends PanelElement
{
    private final GuiPanel guiPanel;
    private final Module module;
    private GuiPanelModuleSettings moduleSettings;
    private TimeManager timeManager;
    private boolean wasHovered;
    
    public PanelModuleButton(final Module module, final GuiPanel guiPanel) {
        super(90, 12);
        this.guiPanel = guiPanel;
        this.module = module;
        this.timeManager = new TimeManager();
        this.moduleSettings = new GuiPanelModuleSettings(this.module, 0, 0) {
            @Override
            public void addElements() {
                final Module module = PanelModuleButton.this.module;
                final ArrayList<ClientSetting> settings = new ArrayList<ClientSetting>(module.getModuleSettings().values());
                settings.sort(null);
                settings.forEach(setting -> {
                    if (setting.getValue().getClass().getName().equals("java.lang.Boolean")) {
                        try {
                            this.getPanelElements().add(new PanelCheckBox(setting.getName(), (boolean)setting.getValue()) {
                                final /* synthetic */ ClientSetting val$setting;
                                
                                @Override
                                public void onUpdate(final Boolean newValue, final Boolean oldValue) {
                                    this.val$setting.setValue(newValue);
                                }
                            });
                        }
                        catch (final ClassCastException exception) {
                            exception.printStackTrace();
                        }
                    }
                    return;
                });
                this.getPanelElements().addAll(module.getGuiPanelElements());
                module.getModuleValues().forEach((valueName, value) -> {
                    final PanelSlider panelSlider = new PanelSlider(value, value.isShouldRound());
                    this.getPanelElements().add(panelSlider);
                    return;
                });
                final Function<Integer, String> caption = (Function<Integer, String>)(keyCode -> "Keybind: " + ((keyCode != -1) ? Keyboard.getKeyName((int)keyCode) : "None"));
                this.getPanelElements().add(new PanelKeyBindButton((String)caption.apply(module.getKey()), (keyCode, self) -> {
                    if (keyCode == 1 || keyCode < 0) {
                        keyCode = -1;
                    }
                    module.setKey(keyCode);
                    self.setCaption(caption.apply(keyCode));
                    return Boolean.valueOf(false);
                }));
            }
        };
        Wrapper.getClickableGui().getGuiPanels().add(this.moduleSettings);
    }
    
    @Override
    public void drawElement(final int elementX, final int elementY, final int mouseX, final int mouseY) {
        final boolean buttonHovered = this.isMouseOverButton(mouseX, mouseY);
        final boolean hasSettings = this.moduleSettings.getPanelElements().size() > 0;
        boolean displayHelp = false;
        this.timeManager.updateTimer();
        if (buttonHovered) {
            if (!this.wasHovered) {
                this.timeManager.updateLast();
                this.wasHovered = true;
            }
        }
        else {
            this.wasHovered = false;
        }
        if (this.timeManager.sleep(1000L) && this.wasHovered) {
            displayHelp = true;
        }
        this.moduleSettings.setPanelX(elementX + this.getWidth() + this.getElementSpacing().getMarginRight());
        this.moduleSettings.setPanelY(elementY);
        Wrapper.getClickableGui().getCurrentTheme().drawModuleButton(this.module, elementX, elementY, this.getWidth(), this.getHeight(), buttonHovered, hasSettings, displayHelp);
        super.drawElement(elementX, elementY, mouseX, mouseY);
    }
    
    @Override
    public void mouseClicked(final int mouseX, final int mouseY, final int clickedButton) {
        if (this.isMouseOverButton(mouseX, mouseY)) {
            if (clickedButton == 0) {
                this.module.toggle();
            }
            else if (clickedButton == 1) {
                this.guiPanel.getPanelElements().stream().filter(panelElement -> panelElement instanceof PanelModuleButton && panelElement != this).forEach(panelElement -> ((PanelModuleButton)panelElement).getModuleSettings().setVisible(false));
                this.moduleSettings.setOpened(true);
                if (this.getModuleSettings().getPanelElements().size() > 0) {
                    Wrapper.getClickableGui().moveOverAll(this.moduleSettings);
                    this.moduleSettings.setVisible(!this.moduleSettings.isVisible());
                }
                else {
                    this.moduleSettings.setVisible(false);
                }
            }
            else if (clickedButton == 2) {
                Wrapper.getConsole().onChatMessage(new PlayerSendChatMessageEvent(new CPacketChatMessage(ClientSettings.getValue("client.prefix", String.class) + "help " + this.getModule().getName().toLowerCase())));
            }
        }
    }
    
    @Override
    public ElementSpacing getElementSpacing() {
        return Wrapper.getClickableGui().getCurrentTheme().getLayout().getModuleButtonLayout();
    }
    
    public Module getModule() {
        return this.module;
    }
    
    public GuiPanelModuleSettings getModuleSettings() {
        return this.moduleSettings;
    }
}
