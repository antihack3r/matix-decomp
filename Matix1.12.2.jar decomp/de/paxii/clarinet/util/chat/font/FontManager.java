// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.util.chat.font;

import java.io.InputStream;
import de.paxii.clarinet.event.EventHandler;
import de.paxii.clarinet.event.events.game.StartGameEvent;
import de.paxii.clarinet.Wrapper;

public class FontManager
{
    private static TTF defaultFont;
    private static TTF smallFont;
    private static TTF bigUbuntuFont;
    private static TTF ubuntuFont;
    private static TTF smallUbuntuFont;
    
    public FontManager() {
        Wrapper.getEventManager().register(this);
    }
    
    @EventHandler
    private void loadFonts(final StartGameEvent event) {
        FontManager.defaultFont = new TTF("Verdana", 16.0f);
        FontManager.smallFont = new TTF("Verdana", 8.0f);
        FontManager.bigUbuntuFont = new TTF(this.getFontStream("Ubuntu/Ubuntu-Bold.ttf"), 20.0f);
        FontManager.ubuntuFont = new TTF(this.getFontStream("Ubuntu/Ubuntu-Regular.ttf"), 18.0f);
        FontManager.smallUbuntuFont = new TTF(this.getFontStream("Ubuntu/Ubuntu-Bold.ttf"), 18.0f);
    }
    
    private InputStream getFontStream(final String fontName) {
        return FontManager.class.getResourceAsStream(String.format("/assets/matix/fonts/%s", fontName));
    }
    
    public static TTF getDefaultFont() {
        return FontManager.defaultFont;
    }
    
    public static TTF getSmallFont() {
        return FontManager.smallFont;
    }
    
    public static TTF getBigUbuntuFont() {
        return FontManager.bigUbuntuFont;
    }
    
    public static TTF getUbuntuFont() {
        return FontManager.ubuntuFont;
    }
    
    public static TTF getSmallUbuntuFont() {
        return FontManager.smallUbuntuFont;
    }
}
