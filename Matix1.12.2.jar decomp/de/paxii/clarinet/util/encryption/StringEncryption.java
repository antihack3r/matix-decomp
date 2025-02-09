// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.util.encryption;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import de.paxii.clarinet.util.settings.ClientSettings;
import java.util.Base64;

public class StringEncryption
{
    private final String DEFAULT_ENCODING = "UTF-8";
    private final Base64.Encoder enc;
    private final Base64.Decoder dec;
    private String encryptionKey;
    
    public StringEncryption(final String encryptionKey) {
        this.enc = Base64.getEncoder();
        this.dec = Base64.getDecoder();
        this.encryptionKey = encryptionKey;
    }
    
    public StringEncryption() {
        this.enc = Base64.getEncoder();
        this.dec = Base64.getDecoder();
    }
    
    public String encryptString(String encryptionText) {
        if (this.encryptionKey == null) {
            this.encryptionKey = ClientSettings.getValue("client.enckey", String.class);
        }
        encryptionText = this.xorMessage(encryptionText, this.encryptionKey);
        return this.base64encode(encryptionText);
    }
    
    public String decryptString(String decryptionText) {
        if (this.encryptionKey == null) {
            this.encryptionKey = ClientSettings.getValue("client.enckey", String.class);
        }
        decryptionText = this.base64decode(decryptionText);
        return this.xorMessage(decryptionText, this.encryptionKey);
    }
    
    private String base64encode(final String text) {
        try {
            return this.enc.encodeToString(text.getBytes("UTF-8"));
        }
        catch (final UnsupportedEncodingException e) {
            return null;
        }
    }
    
    private String base64decode(final String text) {
        try {
            return new String(this.dec.decode(text), "UTF-8");
        }
        catch (final IOException e) {
            return null;
        }
    }
    
    private String xorMessage(final String message, final String key) {
        try {
            if (message == null || key == null) {
                return null;
            }
            final char[] keys = key.toCharArray();
            final char[] mesg = message.toCharArray();
            final int ml = mesg.length;
            final int kl = keys.length;
            final char[] newmsg = new char[ml];
            for (int i = 0; i < ml; ++i) {
                newmsg[i] = (char)(mesg[i] ^ keys[i % kl]);
            }
            return new String(newmsg);
        }
        catch (final Exception e) {
            return null;
        }
    }
}
