// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.module.render;

import de.paxii.clarinet.event.EventHandler;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import de.paxii.clarinet.util.chat.font.TTF;
import de.paxii.clarinet.util.chat.font.FontManager;
import de.paxii.clarinet.Wrapper;
import de.paxii.clarinet.event.events.game.IngameTickEvent;
import de.paxii.clarinet.util.settings.type.ClientSettingBoolean;
import de.paxii.clarinet.module.ModuleCategory;
import de.paxii.clarinet.module.Module;

public class ModuleArraylist extends Module
{
    public ModuleArraylist() {
        super("Arraylist", ModuleCategory.RENDER);
        this.setRegistered(true);
        this.setDescription("Renders a list of enabled modules on your screen.");
        this.setEnabled(true);
        this.getModuleSettings().put("useTTF", new ClientSettingBoolean("Use TTF", false));
    }
    
    @EventHandler
    public void onTick(final IngameTickEvent event) {
        if (Wrapper.getMinecraft().gameSettings.showDebugInfo || Wrapper.getMinecraft().gameSettings.hideGUI) {
            return;
        }
        final ScaledResolution scaledResolution = Wrapper.getScaledResolution();
        final FontRenderer fontRenderer = Wrapper.getFontRenderer();
        int indexY = 2;
        final Module[] array;
        Module module = null;
        final Module[] sortedModules = array = Wrapper.getModuleManager().getModuleList().values().stream().sorted((module, otherModule) -> Integer.compare(fontRenderer.getStringWidth(otherModule.getName()), fontRenderer.getStringWidth(module.getName()))).filter(module -> module.isEnabled() && module.isDisplayedInGui() && module != this).toArray(Module[]::new);
        for (int length = array.length, i = 0; i < length; ++i) {
            module = array[i];
            if (this.getValue("useTTF", Boolean.class)) {
                FontManager.getDefaultFont().drawString(module.getName(), scaledResolution.getScaledWidth() - FontManager.getDefaultFont().getStringWidth(module.getName()) - 2.0f, (float)(indexY - 2), TTF.FontType.SHADOW_THIN, module.getCategory().getColor(), -16777216);
            }
            else {
                fontRenderer.drawStringWithShadow(module.getName(), (float)(scaledResolution.getScaledWidth() - fontRenderer.getStringWidth(module.getName()) - 2), (float)indexY, module.getCategory().getColor());
            }
            indexY += 10;
        }
    }
}
