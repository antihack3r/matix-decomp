// 
// Decompiled by Procyon v0.6.0
// 

package optifine.json;

public class ParseException extends Exception
{
    private static final long serialVersionUID = -7880698968187728548L;
    public static final int ERROR_UNEXPECTED_CHAR = 0;
    public static final int ERROR_UNEXPECTED_TOKEN = 1;
    public static final int ERROR_UNEXPECTED_EXCEPTION = 2;
    private int errorType;
    private Object unexpectedObject;
    private int position;
    
    public ParseException(final int errorType) {
        this(-1, errorType, null);
    }
    
    public ParseException(final int errorType, final Object unexpectedObject) {
        this(-1, errorType, unexpectedObject);
    }
    
    public ParseException(final int position, final int errorType, final Object unexpectedObject) {
        this.position = position;
        this.errorType = errorType;
        this.unexpectedObject = unexpectedObject;
    }
    
    public int getErrorType() {
        return this.errorType;
    }
    
    public void setErrorType(final int errorType) {
        this.errorType = errorType;
    }
    
    public int getPosition() {
        return this.position;
    }
    
    public void setPosition(final int position) {
        this.position = position;
    }
    
    public Object getUnexpectedObject() {
        return this.unexpectedObject;
    }
    
    public void setUnexpectedObject(final Object unexpectedObject) {
        this.unexpectedObject = unexpectedObject;
    }
    
    @Override
    public String toString() {
        final StringBuffer stringbuffer = new StringBuffer();
        switch (this.errorType) {
            case 0: {
                stringbuffer.append("Unexpected character (").append(this.unexpectedObject).append(") at position ").append(this.position).append(".");
                break;
            }
            case 1: {
                stringbuffer.append("Unexpected token ").append(this.unexpectedObject).append(" at position ").append(this.position).append(".");
                break;
            }
            case 2: {
                stringbuffer.append("Unexpected exception at position ").append(this.position).append(": ").append(this.unexpectedObject);
                break;
            }
            default: {
                stringbuffer.append("Unkown error at position ").append(this.position).append(".");
                break;
            }
        }
        return stringbuffer.toString();
    }
}
