// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.module;

import de.paxii.clarinet.event.events.Event;
import de.paxii.clarinet.util.chat.Chat;
import de.paxii.clarinet.util.chat.ChatColor;
import de.paxii.clarinet.Wrapper;
import de.paxii.clarinet.Client;
import de.paxii.clarinet.gui.ingame.panel.element.PanelElement;
import java.util.ArrayList;
import de.paxii.clarinet.util.settings.ClientSetting;
import de.paxii.clarinet.util.module.settings.ValueBase;
import java.util.HashMap;

public class Module implements Comparable<Module>
{
    private final String name;
    private ModuleCategory category;
    private int key;
    private String syntax;
    private String description;
    private String helpUrl;
    private boolean command;
    private boolean enabled;
    private boolean registered;
    private boolean displayedInGui;
    private boolean plugin;
    private int buildVersion;
    private String version;
    private HashMap<String, ValueBase> moduleValues;
    private HashMap<String, ClientSetting> moduleSettings;
    private ArrayList<PanelElement> guiPanelElements;
    
    public Module(final String name, final ModuleCategory category) {
        this.syntax = "";
        this.description = "";
        this.name = name;
        this.category = category;
        this.setKey(-1);
        this.setHelpUrl(Client.getClientURL() + "docs/modules/" + this.category.toString().toLowerCase() + "/" + this.getName().toLowerCase() + ".html");
        this.setBuildVersion(Client.getClientBuild());
        this.setVersion(Client.getClientVersion());
        this.moduleValues = new HashMap<String, ValueBase>();
        this.moduleSettings = new HashMap<String, ClientSetting>();
        this.guiPanelElements = new ArrayList<PanelElement>();
        this.setDisplayedInGui(true);
    }
    
    public Module(final String name, final ModuleCategory category, final int i) {
        this(name, category);
        this.setKey(i);
    }
    
    public final void setEnabled(final boolean enabled) {
        if (this.enabled == enabled) {
            return;
        }
        this.enabled = enabled;
        if (this.isEnabled()) {
            if (this.isRegistered()) {
                this.register();
            }
            this.onEnable();
        }
        else {
            this.onDisable();
            if (this.isRegistered()) {
                this.unregister();
            }
        }
        this.onToggle();
    }
    
    public final void setKey(final int key) {
        this.key = key;
        if (key != -1) {
            Wrapper.getModuleManager().addKey(this.name, key);
        }
        else {
            Wrapper.getModuleManager().removeKey(this.name);
        }
    }
    
    public final void toggle() {
        this.setEnabled(!this.isEnabled());
    }
    
    public void onEnable() {
    }
    
    public void onDisable() {
    }
    
    public void onToggle() {
    }
    
    public void onStartup() {
    }
    
    public void onShutdown() {
    }
    
    public void onCommand(final String[] args) {
    }
    
    @Override
    public String toString() {
        return this.getName();
    }
    
    public <T extends ClientSetting> T getSetting(final String settingName, final Class<T> type) {
        return type.cast(this.moduleSettings.get(settingName));
    }
    
    public <T> T setValue(final String settingName, final T value) {
        if (!this.getModuleSettings().containsKey(settingName)) {
            this.getModuleSettings().put(settingName, new ClientSetting(settingName, value));
        }
        final ClientSetting<T> clientSetting = this.getModuleSettings().get(settingName);
        clientSetting.setValue(value);
        return value;
    }
    
    public <T> T getValue(final String settingName, final Class<T> type) {
        return type.cast(this.moduleSettings.get(settingName).getValue());
    }
    
    public <T> T getValueOrDefault(final String settingName, final Class<T> type, final T defaultValue) {
        if (this.moduleSettings.containsKey(settingName)) {
            return type.cast(this.moduleSettings.get(settingName).getValue());
        }
        return defaultValue;
    }
    
    public ValueBase getValueBase(final String valueName) {
        return this.getModuleValues().get(valueName);
    }
    
    public void addPanelElement(final PanelElement panelElement) {
        this.guiPanelElements.add(panelElement);
    }
    
    public void removePanelElement(final PanelElement panelElement) {
        this.guiPanelElements.remove(panelElement);
    }
    
    protected void sendClientMessage(final String message) {
        Chat.printClientMessage(ChatColor.AQUA + "[" + this.getName() + "] " + ChatColor.RESET + message);
    }
    
    protected void register() {
        Wrapper.getEventManager().register(this);
    }
    
    protected void register(final Class<? extends Event> eventClass) {
        Wrapper.getEventManager().register(this, eventClass);
    }
    
    protected void unregister() {
        Wrapper.getEventManager().unregister(this);
    }
    
    protected void unregister(final Class<? extends Event> eventClass) {
        Wrapper.getEventManager().unregister(this, eventClass);
    }
    
    @Override
    public int compareTo(final Module o) {
        return this.getName().compareToIgnoreCase(o.getName());
    }
    
    public String getName() {
        return this.name;
    }
    
    public ModuleCategory getCategory() {
        return this.category;
    }
    
    public int getKey() {
        return this.key;
    }
    
    public String getSyntax() {
        return this.syntax;
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public String getHelpUrl() {
        return this.helpUrl;
    }
    
    public boolean isCommand() {
        return this.command;
    }
    
    public boolean isEnabled() {
        return this.enabled;
    }
    
    public boolean isRegistered() {
        return this.registered;
    }
    
    public boolean isDisplayedInGui() {
        return this.displayedInGui;
    }
    
    public boolean isPlugin() {
        return this.plugin;
    }
    
    public int getBuildVersion() {
        return this.buildVersion;
    }
    
    public String getVersion() {
        return this.version;
    }
    
    public HashMap<String, ValueBase> getModuleValues() {
        return this.moduleValues;
    }
    
    public HashMap<String, ClientSetting> getModuleSettings() {
        return this.moduleSettings;
    }
    
    public ArrayList<PanelElement> getGuiPanelElements() {
        return this.guiPanelElements;
    }
    
    public void setCategory(final ModuleCategory category) {
        this.category = category;
    }
    
    public void setSyntax(final String syntax) {
        this.syntax = syntax;
    }
    
    public void setDescription(final String description) {
        this.description = description;
    }
    
    public void setHelpUrl(final String helpUrl) {
        this.helpUrl = helpUrl;
    }
    
    public void setCommand(final boolean command) {
        this.command = command;
    }
    
    public void setDisplayedInGui(final boolean displayedInGui) {
        this.displayedInGui = displayedInGui;
    }
    
    public void setPlugin(final boolean plugin) {
        this.plugin = plugin;
    }
    
    public void setModuleValues(final HashMap<String, ValueBase> moduleValues) {
        this.moduleValues = moduleValues;
    }
    
    public void setModuleSettings(final HashMap<String, ClientSetting> moduleSettings) {
        this.moduleSettings = moduleSettings;
    }
    
    public void setGuiPanelElements(final ArrayList<PanelElement> guiPanelElements) {
        this.guiPanelElements = guiPanelElements;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Module)) {
            return false;
        }
        final Module other = (Module)o;
        if (!other.canEqual(this)) {
            return false;
        }
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        Label_0065: {
            if (this$name == null) {
                if (other$name == null) {
                    break Label_0065;
                }
            }
            else if (this$name.equals(other$name)) {
                break Label_0065;
            }
            return false;
        }
        final Object this$category = this.getCategory();
        final Object other$category = other.getCategory();
        Label_0102: {
            if (this$category == null) {
                if (other$category == null) {
                    break Label_0102;
                }
            }
            else if (this$category.equals(other$category)) {
                break Label_0102;
            }
            return false;
        }
        if (this.getKey() != other.getKey()) {
            return false;
        }
        final Object this$syntax = this.getSyntax();
        final Object other$syntax = other.getSyntax();
        Label_0152: {
            if (this$syntax == null) {
                if (other$syntax == null) {
                    break Label_0152;
                }
            }
            else if (this$syntax.equals(other$syntax)) {
                break Label_0152;
            }
            return false;
        }
        final Object this$description = this.getDescription();
        final Object other$description = other.getDescription();
        Label_0189: {
            if (this$description == null) {
                if (other$description == null) {
                    break Label_0189;
                }
            }
            else if (this$description.equals(other$description)) {
                break Label_0189;
            }
            return false;
        }
        final Object this$helpUrl = this.getHelpUrl();
        final Object other$helpUrl = other.getHelpUrl();
        Label_0226: {
            if (this$helpUrl == null) {
                if (other$helpUrl == null) {
                    break Label_0226;
                }
            }
            else if (this$helpUrl.equals(other$helpUrl)) {
                break Label_0226;
            }
            return false;
        }
        if (this.isCommand() != other.isCommand()) {
            return false;
        }
        if (this.isEnabled() != other.isEnabled()) {
            return false;
        }
        if (this.isRegistered() != other.isRegistered()) {
            return false;
        }
        if (this.isDisplayedInGui() != other.isDisplayedInGui()) {
            return false;
        }
        if (this.isPlugin() != other.isPlugin()) {
            return false;
        }
        if (this.getBuildVersion() != other.getBuildVersion()) {
            return false;
        }
        final Object this$version = this.getVersion();
        final Object other$version = other.getVersion();
        Label_0341: {
            if (this$version == null) {
                if (other$version == null) {
                    break Label_0341;
                }
            }
            else if (this$version.equals(other$version)) {
                break Label_0341;
            }
            return false;
        }
        final Object this$moduleValues = this.getModuleValues();
        final Object other$moduleValues = other.getModuleValues();
        Label_0378: {
            if (this$moduleValues == null) {
                if (other$moduleValues == null) {
                    break Label_0378;
                }
            }
            else if (this$moduleValues.equals(other$moduleValues)) {
                break Label_0378;
            }
            return false;
        }
        final Object this$moduleSettings = this.getModuleSettings();
        final Object other$moduleSettings = other.getModuleSettings();
        Label_0415: {
            if (this$moduleSettings == null) {
                if (other$moduleSettings == null) {
                    break Label_0415;
                }
            }
            else if (this$moduleSettings.equals(other$moduleSettings)) {
                break Label_0415;
            }
            return false;
        }
        final Object this$guiPanelElements = this.getGuiPanelElements();
        final Object other$guiPanelElements = other.getGuiPanelElements();
        if (this$guiPanelElements == null) {
            if (other$guiPanelElements == null) {
                return true;
            }
        }
        else if (this$guiPanelElements.equals(other$guiPanelElements)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof Module;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $name = this.getName();
        result = result * 59 + (($name == null) ? 43 : $name.hashCode());
        final Object $category = this.getCategory();
        result = result * 59 + (($category == null) ? 43 : $category.hashCode());
        result = result * 59 + this.getKey();
        final Object $syntax = this.getSyntax();
        result = result * 59 + (($syntax == null) ? 43 : $syntax.hashCode());
        final Object $description = this.getDescription();
        result = result * 59 + (($description == null) ? 43 : $description.hashCode());
        final Object $helpUrl = this.getHelpUrl();
        result = result * 59 + (($helpUrl == null) ? 43 : $helpUrl.hashCode());
        result = result * 59 + (this.isCommand() ? 79 : 97);
        result = result * 59 + (this.isEnabled() ? 79 : 97);
        result = result * 59 + (this.isRegistered() ? 79 : 97);
        result = result * 59 + (this.isDisplayedInGui() ? 79 : 97);
        result = result * 59 + (this.isPlugin() ? 79 : 97);
        result = result * 59 + this.getBuildVersion();
        final Object $version = this.getVersion();
        result = result * 59 + (($version == null) ? 43 : $version.hashCode());
        final Object $moduleValues = this.getModuleValues();
        result = result * 59 + (($moduleValues == null) ? 43 : $moduleValues.hashCode());
        final Object $moduleSettings = this.getModuleSettings();
        result = result * 59 + (($moduleSettings == null) ? 43 : $moduleSettings.hashCode());
        final Object $guiPanelElements = this.getGuiPanelElements();
        result = result * 59 + (($guiPanelElements == null) ? 43 : $guiPanelElements.hashCode());
        return result;
    }
    
    protected void setRegistered(final boolean registered) {
        this.registered = registered;
    }
    
    protected void setBuildVersion(final int buildVersion) {
        this.buildVersion = buildVersion;
    }
    
    protected void setVersion(final String version) {
        this.version = version;
    }
}
