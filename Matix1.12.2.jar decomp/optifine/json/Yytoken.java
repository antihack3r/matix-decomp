// 
// Decompiled by Procyon v0.6.0
// 

package optifine.json;

public class Yytoken
{
    public static final int TYPE_VALUE = 0;
    public static final int TYPE_LEFT_BRACE = 1;
    public static final int TYPE_RIGHT_BRACE = 2;
    public static final int TYPE_LEFT_SQUARE = 3;
    public static final int TYPE_RIGHT_SQUARE = 4;
    public static final int TYPE_COMMA = 5;
    public static final int TYPE_COLON = 6;
    public static final int TYPE_EOF = -1;
    public int type;
    public Object value;
    
    public Yytoken(final int type, final Object value) {
        this.type = 0;
        this.value = null;
        this.type = type;
        this.value = value;
    }
    
    @Override
    public String toString() {
        final StringBuffer stringbuffer = new StringBuffer();
        switch (this.type) {
            case -1: {
                stringbuffer.append("END OF FILE");
                break;
            }
            case 0: {
                stringbuffer.append("VALUE(").append(this.value).append(")");
                break;
            }
            case 1: {
                stringbuffer.append("LEFT BRACE({)");
                break;
            }
            case 2: {
                stringbuffer.append("RIGHT BRACE(})");
                break;
            }
            case 3: {
                stringbuffer.append("LEFT SQUARE([)");
                break;
            }
            case 4: {
                stringbuffer.append("RIGHT SQUARE(])");
                break;
            }
            case 5: {
                stringbuffer.append("COMMA(,)");
                break;
            }
            case 6: {
                stringbuffer.append("COLON(:)");
                break;
            }
        }
        return stringbuffer.toString();
    }
}
