// 
// Decompiled by Procyon v0.6.0
// 

package optifine;

import java.lang.reflect.Array;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import javax.swing.JOptionPane;
import java.awt.Component;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.Writer;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.File;

public class Utils
{
    public static final String MAC_OS_HOME_PREFIX = "Library/Application Support";
    private static final char[] hexTable;
    
    public static File getWorkingDirectory() {
        return getWorkingDirectory("minecraft");
    }
    
    public static File getWorkingDirectory(final String applicationName) {
        final String s = System.getProperty("user.home", ".");
        File file1 = null;
        switch (getPlatform().ordinal()) {
            case 1:
            case 2: {
                file1 = new File(s, '.' + applicationName + '/');
                break;
            }
            case 3: {
                final String s2 = System.getenv("APPDATA");
                if (s2 != null) {
                    file1 = new File(s2, "." + applicationName + '/');
                    break;
                }
                file1 = new File(s, '.' + applicationName + '/');
                break;
            }
            case 4: {
                file1 = new File(s, "Library/Application Support/" + applicationName);
                break;
            }
            default: {
                file1 = new File(s, applicationName + '/');
                break;
            }
        }
        if (!file1.exists() && !file1.mkdirs()) {
            throw new RuntimeException("The working directory could not be created: " + file1);
        }
        return file1;
    }
    
    public static OS getPlatform() {
        final String s = System.getProperty("os.name").toLowerCase();
        if (s.contains("win")) {
            return OS.WINDOWS;
        }
        if (s.contains("mac")) {
            return OS.MACOS;
        }
        if (s.contains("solaris")) {
            return OS.SOLARIS;
        }
        if (s.contains("sunos")) {
            return OS.SOLARIS;
        }
        if (s.contains("linux")) {
            return OS.LINUX;
        }
        return s.contains("unix") ? OS.LINUX : OS.UNKNOWN;
    }
    
    public static int find(final byte[] buf, final byte[] pattern) {
        return find(buf, 0, pattern);
    }
    
    public static int find(final byte[] buf, final int index, final byte[] pattern) {
        for (int i = index; i < buf.length - pattern.length; ++i) {
            boolean flag = true;
            for (int j = 0; j < pattern.length; ++j) {
                if (pattern[j] != buf[i + j]) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                return i;
            }
        }
        return -1;
    }
    
    public static byte[] readAll(final InputStream is) throws IOException {
        final ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
        final byte[] abyte = new byte[1024];
        while (true) {
            final int i = is.read(abyte);
            if (i < 0) {
                break;
            }
            bytearrayoutputstream.write(abyte, 0, i);
        }
        is.close();
        final byte[] abyte2 = bytearrayoutputstream.toByteArray();
        return abyte2;
    }
    
    public static void dbg(final String str) {
        System.out.println(str);
    }
    
    public static String[] tokenize(final String str, final String delim) {
        final List list = new ArrayList();
        final StringTokenizer stringtokenizer = new StringTokenizer(str, delim);
        while (stringtokenizer.hasMoreTokens()) {
            final String s = stringtokenizer.nextToken();
            list.add(s);
        }
        final String[] astring = list.toArray(new String[list.size()]);
        return astring;
    }
    
    public static String getExceptionStackTrace(final Throwable e) {
        final StringWriter stringwriter = new StringWriter();
        final PrintWriter printwriter = new PrintWriter(stringwriter);
        e.printStackTrace(printwriter);
        printwriter.close();
        try {
            stringwriter.close();
        }
        catch (final IOException ex) {}
        return stringwriter.getBuffer().toString();
    }
    
    public static void copyFile(final File fileSrc, final File fileDest) throws IOException {
        if (!fileSrc.getCanonicalPath().equals(fileDest.getCanonicalPath())) {
            final FileInputStream fileinputstream = new FileInputStream(fileSrc);
            final FileOutputStream fileoutputstream = new FileOutputStream(fileDest);
            copyAll(fileinputstream, fileoutputstream);
            fileoutputstream.flush();
            fileinputstream.close();
            fileoutputstream.close();
        }
    }
    
    public static void copyAll(final InputStream is, final OutputStream os) throws IOException {
        final byte[] abyte = new byte[1024];
        while (true) {
            final int i = is.read(abyte);
            if (i < 0) {
                break;
            }
            os.write(abyte, 0, i);
        }
    }
    
    public static void showMessage(final String msg) {
        JOptionPane.showMessageDialog(null, msg, "OptiFine", 1);
    }
    
    public static void showErrorMessage(final String msg) {
        JOptionPane.showMessageDialog(null, msg, "Error", 0);
    }
    
    public static String readFile(final File file) throws IOException {
        return readFile(file, "ASCII");
    }
    
    public static String readFile(final File file, final String encoding) throws IOException {
        final FileInputStream fileinputstream = new FileInputStream(file);
        return readText(fileinputstream, encoding);
    }
    
    public static String readText(final InputStream in, final String encoding) throws IOException {
        final InputStreamReader inputstreamreader = new InputStreamReader(in, encoding);
        final BufferedReader bufferedreader = new BufferedReader(inputstreamreader);
        final StringBuffer stringbuffer = new StringBuffer();
        while (true) {
            final String s = bufferedreader.readLine();
            if (s == null) {
                break;
            }
            stringbuffer.append(s);
            stringbuffer.append("\n");
        }
        bufferedreader.close();
        inputstreamreader.close();
        in.close();
        return stringbuffer.toString();
    }
    
    public static String[] readLines(final InputStream in, final String encoding) throws IOException {
        final String s = readText(in, encoding);
        final String[] astring = tokenize(s, "\n\r");
        return astring;
    }
    
    public static void centerWindow(final Component c, final Component par) {
        if (c != null) {
            final Rectangle rectangle = c.getBounds();
            Rectangle rectangle2;
            if (par != null && par.isVisible()) {
                rectangle2 = par.getBounds();
            }
            else {
                final Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
                rectangle2 = new Rectangle(0, 0, dimension.width, dimension.height);
            }
            int j = rectangle2.x + (rectangle2.width - rectangle.width) / 2;
            int i = rectangle2.y + (rectangle2.height - rectangle.height) / 2;
            if (j < 0) {
                j = 0;
            }
            if (i < 0) {
                i = 0;
            }
            c.setBounds(j, i, rectangle.width, rectangle.height);
        }
    }
    
    public static String byteArrayToHexString(final byte[] bytes) {
        if (bytes == null) {
            return "";
        }
        final StringBuffer stringbuffer = new StringBuffer();
        for (int i = 0; i < bytes.length; ++i) {
            final byte b0 = bytes[i];
            stringbuffer.append(Utils.hexTable[b0 >> 4 & 0xF]);
            stringbuffer.append(Utils.hexTable[b0 & 0xF]);
        }
        return stringbuffer.toString();
    }
    
    public static String arrayToCommaSeparatedString(final Object[] arr) {
        if (arr == null) {
            return "";
        }
        final StringBuffer stringbuffer = new StringBuffer();
        for (int i = 0; i < arr.length; ++i) {
            final Object object = arr[i];
            if (i > 0) {
                stringbuffer.append(", ");
            }
            if (object == null) {
                stringbuffer.append("null");
            }
            else if (!object.getClass().isArray()) {
                stringbuffer.append(arr[i]);
            }
            else {
                stringbuffer.append("[");
                if (object instanceof Object[]) {
                    final Object[] valObjArr = (Object[])object;
                    stringbuffer.append(arrayToCommaSeparatedString(valObjArr));
                }
                else {
                    for (int j = 0; j < Array.getLength(object); ++j) {
                        if (j > 0) {
                            stringbuffer.append(", ");
                        }
                        stringbuffer.append(Array.get(object, j));
                    }
                }
                stringbuffer.append("]");
            }
        }
        return stringbuffer.toString();
    }
    
    public static String removePrefix(String str, final String prefix) {
        if (str != null && prefix != null) {
            if (str.startsWith(prefix)) {
                str = str.substring(prefix.length());
            }
            return str;
        }
        return str;
    }
    
    public static String ensurePrefix(String str, final String prefix) {
        if (str != null && prefix != null) {
            if (!str.startsWith(prefix)) {
                str = prefix + str;
            }
            return str;
        }
        return str;
    }
    
    public static boolean equals(final Object o1, final Object o2) {
        return o1 == o2 || (o1 != null && o1.equals(o2));
    }
    
    static {
        hexTable = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
    }
    
    public enum OS
    {
        LINUX, 
        SOLARIS, 
        WINDOWS, 
        MACOS, 
        UNKNOWN;
    }
}
