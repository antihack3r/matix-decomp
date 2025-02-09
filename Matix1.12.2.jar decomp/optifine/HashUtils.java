// 
// Decompiled by Procyon v0.6.0
// 

package optifine;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashUtils
{
    public static String getHashMd5(final String data) {
        return getHash(data, "MD5");
    }
    
    public static String getHashSha1(final String data) {
        return getHash(data, "SHA-1");
    }
    
    public static String getHashSha256(final String data) {
        return getHash(data, "SHA-256");
    }
    
    public static String getHash(final String data, final String digest) {
        try {
            final byte[] abyte = getHash(data.getBytes("UTF-8"), digest);
            return toHexString(abyte);
        }
        catch (final Exception exception) {
            throw new RuntimeException(exception.getMessage(), exception);
        }
    }
    
    public static String toHexString(final byte[] data) {
        final StringBuffer stringbuffer = new StringBuffer();
        for (int i = 0; i < data.length; ++i) {
            stringbuffer.append(Integer.toHexString((data[i] & 0xFF) | 0x100).substring(1, 3));
        }
        return stringbuffer.toString();
    }
    
    public static byte[] getHashMd5(final byte[] data) throws NoSuchAlgorithmException {
        return getHash(data, "MD5");
    }
    
    public static byte[] getHashSha1(final byte[] data) throws NoSuchAlgorithmException {
        return getHash(data, "SHA-1");
    }
    
    public static byte[] getHashSha256(final byte[] data) throws NoSuchAlgorithmException {
        return getHash(data, "SHA-256");
    }
    
    public static byte[] getHash(final byte[] data, final String digest) throws NoSuchAlgorithmException {
        final MessageDigest messagedigest = MessageDigest.getInstance(digest);
        final byte[] abyte = messagedigest.digest(data);
        return abyte;
    }
}
