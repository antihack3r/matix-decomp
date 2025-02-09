// 
// Decompiled by Procyon v0.6.0
// 

package optifine.json;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.Reader;

class Yylex
{
    public static final int YYEOF = -1;
    private static final int ZZ_BUFFERSIZE = 16384;
    public static final int YYINITIAL = 0;
    public static final int STRING_BEGIN = 2;
    private static final int[] ZZ_LEXSTATE;
    private static final String ZZ_CMAP_PACKED = "\t\u0000\u0001\u0007\u0001\u0007\u0002\u0000\u0001\u0007\u0012\u0000\u0001\u0007\u0001\u0000\u0001\t\b\u0000\u0001\u0006\u0001\u0019\u0001\u0002\u0001\u0004\u0001\n\n\u0003\u0001\u001a\u0006\u0000\u0004\u0001\u0001\u0005\u0001\u0001\u0014\u0000\u0001\u0017\u0001\b\u0001\u0018\u0003\u0000\u0001\u0012\u0001\u000b\u0002\u0001\u0001\u0011\u0001\f\u0005\u0000\u0001\u0013\u0001\u0000\u0001\r\u0003\u0000\u0001\u000e\u0001\u0014\u0001\u000f\u0001\u0010\u0005\u0000\u0001\u0015\u0001\u0000\u0001\u0016\uff82\u0000";
    private static final char[] ZZ_CMAP;
    private static final int[] ZZ_ACTION;
    private static final String ZZ_ACTION_PACKED_0 = "\u0002\u0000\u0002\u0001\u0001\u0002\u0001\u0003\u0001\u0004\u0003\u0001\u0001\u0005\u0001\u0006\u0001\u0007\u0001\b\u0001\t\u0001\n\u0001\u000b\u0001\f\u0001\r\u0005\u0000\u0001\f\u0001\u000e\u0001\u000f\u0001\u0010\u0001\u0011\u0001\u0012\u0001\u0013\u0001\u0014\u0001\u0000\u0001\u0015\u0001\u0000\u0001\u0015\u0004\u0000\u0001\u0016\u0001\u0017\u0002\u0000\u0001\u0018";
    private static final int[] ZZ_ROWMAP;
    private static final String ZZ_ROWMAP_PACKED_0 = "\u0000\u0000\u0000\u001b\u00006\u0000Q\u0000l\u0000\u0087\u00006\u0000?\u0000?\u0000\u00d8\u00006\u00006\u00006\u00006\u00006\u00006\u0000\u00f3\u0000\u010e\u00006\u0000\u0129\u0000\u0144\u0000\u015f\u0000\u017a\u0000\u0195\u00006\u00006\u00006\u00006\u00006\u00006\u00006\u00006\u0000\u01b0\u0000\u01cb\u0000\u01e6\u0000\u01e6\u0000\u0201\u0000\u021c\u0000\u0237\u0000\u0252\u00006\u00006\u0000\u026d\u0000\u0288\u00006";
    private static final int[] ZZ_TRANS;
    private static final int ZZ_UNKNOWN_ERROR = 0;
    private static final int ZZ_NO_MATCH = 1;
    private static final int ZZ_PUSHBACK_2BIG = 2;
    private static final String[] ZZ_ERROR_MSG;
    private static final int[] ZZ_ATTRIBUTE;
    private static final String ZZ_ATTRIBUTE_PACKED_0 = "\u0002\u0000\u0001\t\u0003\u0001\u0001\t\u0003\u0001\u0006\t\u0002\u0001\u0001\t\u0005\u0000\b\t\u0001\u0000\u0001\u0001\u0001\u0000\u0001\u0001\u0004\u0000\u0002\t\u0002\u0000\u0001\t";
    private Reader zzReader;
    private int zzState;
    private int zzLexicalState;
    private char[] zzBuffer;
    private int zzMarkedPos;
    private int zzCurrentPos;
    private int zzStartRead;
    private int zzEndRead;
    private int yyline;
    private int yychar;
    private int yycolumn;
    private boolean zzAtBOL;
    private boolean zzAtEOF;
    private StringBuffer sb;
    
    private static int[] zzUnpackAction() {
        final int[] aint = new int[45];
        final int i = 0;
        zzUnpackAction("\u0002\u0000\u0002\u0001\u0001\u0002\u0001\u0003\u0001\u0004\u0003\u0001\u0001\u0005\u0001\u0006\u0001\u0007\u0001\b\u0001\t\u0001\n\u0001\u000b\u0001\f\u0001\r\u0005\u0000\u0001\f\u0001\u000e\u0001\u000f\u0001\u0010\u0001\u0011\u0001\u0012\u0001\u0013\u0001\u0014\u0001\u0000\u0001\u0015\u0001\u0000\u0001\u0015\u0004\u0000\u0001\u0016\u0001\u0017\u0002\u0000\u0001\u0018", i, aint);
        return aint;
    }
    
    private static int zzUnpackAction(final String packed, final int offset, final int[] result) {
        int i = 0;
        int j = offset;
        final int k = packed.length();
        while (i < k) {
            int l = packed.charAt(i++);
            final int i2 = packed.charAt(i++);
            do {
                result[j++] = i2;
            } while (l > 0);
            --l;
        }
        return j;
    }
    
    private static int[] zzUnpackRowMap() {
        final int[] aint = new int[45];
        final int i = 0;
        zzUnpackRowMap("\u0000\u0000\u0000\u001b\u00006\u0000Q\u0000l\u0000\u0087\u00006\u0000?\u0000?\u0000\u00d8\u00006\u00006\u00006\u00006\u00006\u00006\u0000\u00f3\u0000\u010e\u00006\u0000\u0129\u0000\u0144\u0000\u015f\u0000\u017a\u0000\u0195\u00006\u00006\u00006\u00006\u00006\u00006\u00006\u00006\u0000\u01b0\u0000\u01cb\u0000\u01e6\u0000\u01e6\u0000\u0201\u0000\u021c\u0000\u0237\u0000\u0252\u00006\u00006\u0000\u026d\u0000\u0288\u00006", i, aint);
        return aint;
    }
    
    private static int zzUnpackRowMap(final String packed, final int offset, final int[] result) {
        int i = 0;
        int j = offset;
        int l;
        for (int k = packed.length(); i < k; l = packed.charAt(i++) << 16, result[j++] = (l | packed.charAt(i++))) {}
        return j;
    }
    
    private static int[] zzUnpackAttribute() {
        final int[] aint = new int[45];
        final int i = 0;
        zzUnpackAttribute("\u0002\u0000\u0001\t\u0003\u0001\u0001\t\u0003\u0001\u0006\t\u0002\u0001\u0001\t\u0005\u0000\b\t\u0001\u0000\u0001\u0001\u0001\u0000\u0001\u0001\u0004\u0000\u0002\t\u0002\u0000\u0001\t", i, aint);
        return aint;
    }
    
    private static int zzUnpackAttribute(final String packed, final int offset, final int[] result) {
        int i = 0;
        int j = offset;
        final int k = packed.length();
        while (i < k) {
            int l = packed.charAt(i++);
            final int i2 = packed.charAt(i++);
            do {
                result[j++] = i2;
            } while (l > 0);
            --l;
        }
        return j;
    }
    
    int getPosition() {
        return this.yychar;
    }
    
    Yylex(final Reader in) {
        this.zzLexicalState = 0;
        this.zzBuffer = new char[16384];
        this.zzAtBOL = true;
        this.sb = new StringBuffer();
        this.zzReader = in;
    }
    
    Yylex(final InputStream in) {
        this(new InputStreamReader(in));
    }
    
    private static char[] zzUnpackCMap(final String packed) {
        final char[] achar = new char[65536];
        int i = 0;
        int j = 0;
        while (i < 90) {
            int k = packed.charAt(i++);
            final char c0 = packed.charAt(i++);
            do {
                achar[j++] = c0;
            } while (k > 0);
            --k;
        }
        return achar;
    }
    
    private boolean zzRefill() throws IOException {
        if (this.zzStartRead > 0) {
            System.arraycopy(this.zzBuffer, this.zzStartRead, this.zzBuffer, 0, this.zzEndRead - this.zzStartRead);
            this.zzEndRead -= this.zzStartRead;
            this.zzCurrentPos -= this.zzStartRead;
            this.zzMarkedPos -= this.zzStartRead;
            this.zzStartRead = 0;
        }
        if (this.zzCurrentPos >= this.zzBuffer.length) {
            final char[] achar = new char[this.zzCurrentPos * 2];
            System.arraycopy(this.zzBuffer, 0, achar, 0, this.zzBuffer.length);
            this.zzBuffer = achar;
        }
        final int j = this.zzReader.read(this.zzBuffer, this.zzEndRead, this.zzBuffer.length - this.zzEndRead);
        if (j > 0) {
            this.zzEndRead += j;
            return false;
        }
        if (j != 0) {
            return true;
        }
        final int i = this.zzReader.read();
        if (i == -1) {
            return true;
        }
        this.zzBuffer[this.zzEndRead++] = (char)i;
        return false;
    }
    
    public final void yyclose() throws IOException {
        this.zzAtEOF = true;
        this.zzEndRead = this.zzStartRead;
        if (this.zzReader != null) {
            this.zzReader.close();
        }
    }
    
    public final void yyreset(final Reader reader) {
        this.zzReader = reader;
        this.zzAtBOL = true;
        this.zzAtEOF = false;
        final int n = 0;
        this.zzStartRead = n;
        this.zzEndRead = n;
        final int n2 = 0;
        this.zzMarkedPos = n2;
        this.zzCurrentPos = n2;
        final int yyline = 0;
        this.yycolumn = yyline;
        this.yychar = yyline;
        this.yyline = yyline;
        this.zzLexicalState = 0;
    }
    
    public final int yystate() {
        return this.zzLexicalState;
    }
    
    public final void yybegin(final int newState) {
        this.zzLexicalState = newState;
    }
    
    public final String yytext() {
        return new String(this.zzBuffer, this.zzStartRead, this.zzMarkedPos - this.zzStartRead);
    }
    
    public final char yycharat(final int pos) {
        return this.zzBuffer[this.zzStartRead + pos];
    }
    
    public final int yylength() {
        return this.zzMarkedPos - this.zzStartRead;
    }
    
    private void zzScanError(final int errorCode) {
        String s;
        try {
            s = Yylex.ZZ_ERROR_MSG[errorCode];
        }
        catch (final ArrayIndexOutOfBoundsException var4) {
            s = Yylex.ZZ_ERROR_MSG[0];
        }
        throw new Error(s);
    }
    
    public void yypushback(final int number) {
        if (number > this.yylength()) {
            this.zzScanError(2);
        }
        this.zzMarkedPos -= number;
    }
    
    public Yytoken yylex() throws IOException, ParseException {
        int l = this.zzEndRead;
        char[] achar = this.zzBuffer;
        final char[] achar2 = Yylex.ZZ_CMAP;
        final int[] aint = Yylex.ZZ_TRANS;
        final int[] aint2 = Yylex.ZZ_ROWMAP;
        final int[] aint3 = Yylex.ZZ_ATTRIBUTE;
        while (true) {
            int k = this.zzMarkedPos;
            this.yychar += k - this.zzStartRead;
            int j = -1;
            final int n = k;
            this.zzStartRead = n;
            this.zzCurrentPos = n;
            int j2 = n;
            this.zzState = Yylex.ZZ_LEXSTATE[this.zzLexicalState];
            int i;
            while (true) {
                if (j2 < l) {
                    i = achar[j2++];
                }
                else {
                    if (this.zzAtEOF) {
                        i = -1;
                        break;
                    }
                    this.zzCurrentPos = j2;
                    this.zzMarkedPos = k;
                    final boolean flag = this.zzRefill();
                    j2 = this.zzCurrentPos;
                    k = this.zzMarkedPos;
                    achar = this.zzBuffer;
                    l = this.zzEndRead;
                    if (flag) {
                        i = -1;
                        break;
                    }
                    i = achar[j2++];
                }
                final int k2 = aint[aint2[this.zzState] + achar2[i]];
                if (k2 == -1) {
                    break;
                }
                this.zzState = k2;
                final int i2 = aint3[this.zzState];
                if ((i2 & 0x1) != 0x1) {
                    continue;
                }
                j = this.zzState;
                k = j2;
                if ((i2 & 0x8) == 0x8) {
                    break;
                }
            }
            this.zzMarkedPos = k;
            switch ((j < 0) ? j : Yylex.ZZ_ACTION[j]) {
                case 1: {
                    throw new ParseException(this.yychar, 0, new Character(this.yycharat(0)));
                }
                case 2: {
                    final Long olong = Long.valueOf(this.yytext());
                    return new Yytoken(0, olong);
                }
                case 3:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48: {
                    continue;
                }
                case 4: {
                    this.sb.delete(0, this.sb.length());
                    this.yybegin(2);
                    continue;
                }
                case 5: {
                    return new Yytoken(1, null);
                }
                case 6: {
                    return new Yytoken(2, null);
                }
                case 7: {
                    return new Yytoken(3, null);
                }
                case 8: {
                    return new Yytoken(4, null);
                }
                case 9: {
                    return new Yytoken(5, null);
                }
                case 10: {
                    return new Yytoken(6, null);
                }
                case 11: {
                    this.sb.append(this.yytext());
                    continue;
                }
                case 12: {
                    this.sb.append('\\');
                    continue;
                }
                case 13: {
                    this.yybegin(0);
                    return new Yytoken(0, this.sb.toString());
                }
                case 14: {
                    this.sb.append('\"');
                    continue;
                }
                case 15: {
                    this.sb.append('/');
                    continue;
                }
                case 16: {
                    this.sb.append('\b');
                    continue;
                }
                case 17: {
                    this.sb.append('\f');
                    continue;
                }
                case 18: {
                    this.sb.append('\n');
                    continue;
                }
                case 19: {
                    this.sb.append('\r');
                    continue;
                }
                case 20: {
                    this.sb.append('\t');
                    continue;
                }
                case 21: {
                    final Double d0 = Double.valueOf(this.yytext());
                    return new Yytoken(0, d0);
                }
                case 22: {
                    return new Yytoken(0, null);
                }
                case 23: {
                    final Boolean obool = Boolean.valueOf(this.yytext());
                    return new Yytoken(0, obool);
                }
                case 24: {
                    try {
                        final int l2 = Integer.parseInt(this.yytext().substring(2), 16);
                        this.sb.append((char)l2);
                        continue;
                    }
                    catch (final Exception exception) {
                        throw new ParseException(this.yychar, 2, exception);
                    }
                    break;
                }
            }
            if (i == -1 && this.zzStartRead == this.zzCurrentPos) {
                this.zzAtEOF = true;
                return null;
            }
            this.zzScanError(1);
        }
    }
    
    static {
        ZZ_LEXSTATE = new int[] { 0, 0, 1, 1 };
        ZZ_CMAP = zzUnpackCMap("\t\u0000\u0001\u0007\u0001\u0007\u0002\u0000\u0001\u0007\u0012\u0000\u0001\u0007\u0001\u0000\u0001\t\b\u0000\u0001\u0006\u0001\u0019\u0001\u0002\u0001\u0004\u0001\n\n\u0003\u0001\u001a\u0006\u0000\u0004\u0001\u0001\u0005\u0001\u0001\u0014\u0000\u0001\u0017\u0001\b\u0001\u0018\u0003\u0000\u0001\u0012\u0001\u000b\u0002\u0001\u0001\u0011\u0001\f\u0005\u0000\u0001\u0013\u0001\u0000\u0001\r\u0003\u0000\u0001\u000e\u0001\u0014\u0001\u000f\u0001\u0010\u0005\u0000\u0001\u0015\u0001\u0000\u0001\u0016\uff82\u0000");
        ZZ_ACTION = zzUnpackAction();
        ZZ_ROWMAP = zzUnpackRowMap();
        ZZ_TRANS = new int[] { 2, 2, 3, 4, 2, 2, 2, 5, 2, 6, 2, 2, 7, 8, 2, 9, 2, 2, 2, 2, 2, 10, 11, 12, 13, 14, 15, 16, 16, 16, 16, 16, 16, 16, 16, 17, 18, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 4, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 4, 19, 20, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 20, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 5, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 21, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 22, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 23, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 16, 16, 16, 16, 16, 16, 16, 16, -1, -1, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, -1, -1, -1, -1, -1, -1, -1, -1, 24, 25, 26, 27, 28, 29, 30, 31, 32, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 33, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 34, 35, -1, -1, 34, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 36, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 37, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 38, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 39, -1, 39, -1, 39, -1, -1, -1, -1, -1, 39, 39, -1, -1, -1, -1, 39, 39, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 33, -1, 20, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 20, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 35, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 38, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 40, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 41, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 42, -1, 42, -1, 42, -1, -1, -1, -1, -1, 42, 42, -1, -1, -1, -1, 42, 42, -1, -1, -1, -1, -1, -1, -1, -1, -1, 43, -1, 43, -1, 43, -1, -1, -1, -1, -1, 43, 43, -1, -1, -1, -1, 43, 43, -1, -1, -1, -1, -1, -1, -1, -1, -1, 44, -1, 44, -1, 44, -1, -1, -1, -1, -1, 44, 44, -1, -1, -1, -1, 44, 44, -1, -1, -1, -1, -1, -1, -1, -1 };
        ZZ_ERROR_MSG = new String[] { "Unkown internal scanner error", "Error: could not match input", "Error: pushback value was too large" };
        ZZ_ATTRIBUTE = zzUnpackAttribute();
    }
}
