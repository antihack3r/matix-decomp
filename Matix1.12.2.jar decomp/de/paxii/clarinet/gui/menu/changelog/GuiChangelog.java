// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.gui.menu.changelog;

import java.util.LinkedHashMap;
import java.io.IOException;
import java.util.Arrays;
import net.minecraft.client.gui.GuiButton;
import de.paxii.clarinet.util.web.JsonFetcher;
import de.paxii.clarinet.Client;
import net.minecraft.client.settings.GameSettings;
import de.paxii.clarinet.Wrapper;
import net.minecraft.client.gui.GuiOptionsRowList;
import net.minecraft.client.gui.GuiScreen;

public class GuiChangelog extends GuiScreen
{
    private GuiScreen parentScreen;
    private Changelog changelog;
    private int posY;
    private GuiOptionsRowList guiOptionsRowList;
    
    public GuiChangelog() {
        this.parentScreen = Wrapper.getMinecraft().currentScreen;
    }
    
    @Override
    public void initGui() {
        this.guiOptionsRowList = new GuiOptionsRowList(Wrapper.getMinecraft(), Wrapper.getScaledResolution().getScaledWidth(), Wrapper.getScaledResolution().getScaledHeight(), 20, Wrapper.getScaledResolution().getScaledHeight() - 30, 20, new GameSettings.Options[0]);
        this.changelog = JsonFetcher.get(Client.getClientURL() + "changelog.json", Changelog.class);
        this.buttonList.add(new GuiButton(1, 10, this.height - 25, 100, 20, "Back"));
    }
    
    @Override
    public void drawScreen(final int mouseX, final int mouseY, final float partialTicks) {
        this.drawDefaultBackground();
        this.guiOptionsRowList.drawScreen(mouseX, mouseY, partialTicks);
        this.drawCenteredString(this.fontRenderer, String.format("%s Changelog (running %s)", Client.getClientName(), Client.getClientVersion()), this.width / 2, 6, 16777215);
        this.posY = 30;
        if (this.changelog != null) {
            this.changelog.getChangelog().forEach((version, entry) -> {
                this.drawString(version, 16777215);
                Arrays.stream(entry.getAdded()).forEach(label -> this.drawString(label, 65280, "+ ", 10));
                Arrays.stream(entry.getRemoved()).forEach(label -> this.drawString(label, 16711680, "- ", 10));
                Arrays.stream(entry.getFixed()).forEach(label -> this.drawString(label, 16746496, "* ", 10));
                Arrays.stream(entry.getNotes()).forEach(label -> this.drawString(label, 30719, "# ", 10));
                return;
            });
        }
        else {
            this.drawString("is null y0", 16711680);
        }
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
    
    @Override
    protected void actionPerformed(final GuiButton button) throws IOException {
        if (button.id == 1) {
            Wrapper.getMinecraft().displayGuiScreen(this.parentScreen);
        }
        super.actionPerformed(button);
    }
    
    private void drawString(final String label, final int color) {
        this.drawString(label, color, "", 0);
    }
    
    private void drawString(String label, final int color, final String prefix, final int posX) {
        if (!this.shouldDraw()) {
            return;
        }
        final String prefixOffset = prefix.replaceAll(".", " ");
        this.fontRenderer.drawString(prefix, 10 + posX, this.posY, color);
        while (label.length() > 0) {
            if (!this.shouldDraw()) {
                return;
            }
            final int firstLineFeed = label.contains("\n") ? label.indexOf("\n") : label.length();
            final String line = label.substring(0, firstLineFeed);
            this.fontRenderer.drawString(prefixOffset + line, 10 + posX, this.posY, color);
            this.posY += 10;
            label = label.substring((label.length() >= firstLineFeed + 1) ? (firstLineFeed + 1) : label.length());
        }
    }
    
    private boolean shouldDraw() {
        return this.posY < this.height - 40;
    }
    
    private class Changelog
    {
        private LinkedHashMap<String, Entry> changelog;
        
        public Changelog(final LinkedHashMap<String, Entry> changelog) {
            this.changelog = changelog;
        }
        
        public LinkedHashMap<String, Entry> getChangelog() {
            return this.changelog;
        }
    }
    
    private class Entry
    {
        private String[] added;
        private String[] removed;
        private String[] fixed;
        private String[] notes;
        
        public Entry(final String[] added, final String[] removed, final String[] fixed, final String[] notes) {
            this.added = added;
            this.removed = removed;
            this.fixed = fixed;
            this.notes = notes;
        }
        
        public String[] getAdded() {
            return this.added;
        }
        
        public String[] getRemoved() {
            return this.removed;
        }
        
        public String[] getFixed() {
            return this.fixed;
        }
        
        public String[] getNotes() {
            return this.notes;
        }
    }
}
