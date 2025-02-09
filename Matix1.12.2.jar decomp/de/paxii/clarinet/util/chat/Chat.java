// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.util.chat;

import net.minecraft.util.text.TextFormatting;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketChatMessage;
import net.minecraft.util.text.ITextComponent;
import de.paxii.clarinet.Wrapper;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import java.util.regex.Pattern;

public class Chat
{
    private static final String prefix;
    private static final Pattern URL_PATTERN;
    
    public static void printChatMessage(final String chatMessage) {
        final ITextComponent textComponent = new TextComponentString("");
        final String[] messageParts = chatMessage.split(" ");
        int pathIndex = 0;
        for (final String messagePart : messageParts) {
            final ITextComponent append = new TextComponentString(messagePart);
            final Style chatStyle = new Style();
            if (Chat.URL_PATTERN.matcher(ChatColor.stripColor(messagePart)).matches()) {
                chatStyle.setUnderlined(true);
                chatStyle.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, ChatColor.stripColor(messagePart)));
            }
            final String currentPath = chatMessage.substring(0, chatMessage.indexOf(messagePart, pathIndex));
            final String lastColor = ChatColor.getLastColors(currentPath);
            if (lastColor.length() >= 2) {
                final char formattingChar = lastColor.charAt(1);
                formatChatStyle(chatStyle, formattingChar);
            }
            append.setStyle(chatStyle);
            textComponent.appendSibling(append);
            textComponent.appendText(" ");
            pathIndex += messagePart.length() - 1;
        }
        Wrapper.getMinecraft().getIngameGUI().getChatGUI().printChatMessage(textComponent);
    }
    
    public static void printChatComponent(final ITextComponent textComponent) {
        Wrapper.getMinecraft().getIngameGUI().getChatGUI().printChatMessage(textComponent);
    }
    
    public static void printClientMessage(final String chatMessage) {
        printChatMessage(Chat.prefix + chatMessage);
    }
    
    public static void sendChatMessage(final String chatMessage) {
        Wrapper.getSendQueue().addToSendQueue(new CPacketChatMessage("_PASS_" + chatMessage));
    }
    
    private static TextFormatting getTextFormattingByValue(final char value) {
        for (final TextFormatting textFormatting : TextFormatting.values()) {
            if (value == textFormatting.getFormattingCode()) {
                return textFormatting;
            }
        }
        return null;
    }
    
    private static void formatChatStyle(final Style chatStyle, final char formattingChar) {
        switch (formattingChar) {
            case 'k': {
                chatStyle.setObfuscated(true);
                break;
            }
            case 'm': {
                chatStyle.setStrikethrough(true);
                break;
            }
            case 'l': {
                chatStyle.setBold(true);
                break;
            }
            case 'n': {
                chatStyle.setUnderlined(true);
                break;
            }
            case 'o': {
                chatStyle.setItalic(true);
                break;
            }
            case 'r': {
                chatStyle.setObfuscated(false);
                chatStyle.setStrikethrough(false);
                chatStyle.setBold(false);
                chatStyle.setUnderlined(false);
                chatStyle.setItalic(false);
                chatStyle.setColor(TextFormatting.RESET);
                break;
            }
            default: {
                final TextFormatting textFormatting = getTextFormattingByValue(formattingChar);
                chatStyle.setColor((textFormatting != null) ? textFormatting : TextFormatting.RESET);
                break;
            }
        }
    }
    
    public static String getPrefix() {
        return Chat.prefix;
    }
    
    static {
        prefix = ChatColor.AQUA + "[C] " + ChatColor.WHITE;
        URL_PATTERN = Pattern.compile("^(https?)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]");
    }
}
