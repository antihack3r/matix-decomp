// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.gui.ingame.panel.theme.layout;

public class ElementSpacing
{
    private int width;
    private int height;
    private int marginTop;
    private int marginRight;
    private int marginBottom;
    private int marginLeft;
    
    public ElementSpacing(final int width, final int height) {
        this(width, height, 0, 0, 0, 0);
    }
    
    public ElementSpacing(final int width, final int height, final int marginTop, final int marginRight, final int marginBottom, final int marginLeft) {
        this.width = width;
        this.height = height;
        this.marginTop = marginTop;
        this.marginRight = marginRight;
        this.marginBottom = marginBottom;
        this.marginLeft = marginLeft;
    }
    
    public int getWidth() {
        return this.width;
    }
    
    public int getHeight() {
        return this.height;
    }
    
    public int getMarginTop() {
        return this.marginTop;
    }
    
    public int getMarginRight() {
        return this.marginRight;
    }
    
    public int getMarginBottom() {
        return this.marginBottom;
    }
    
    public int getMarginLeft() {
        return this.marginLeft;
    }
    
    public void setWidth(final int width) {
        this.width = width;
    }
    
    public void setHeight(final int height) {
        this.height = height;
    }
    
    public void setMarginTop(final int marginTop) {
        this.marginTop = marginTop;
    }
    
    public void setMarginRight(final int marginRight) {
        this.marginRight = marginRight;
    }
    
    public void setMarginBottom(final int marginBottom) {
        this.marginBottom = marginBottom;
    }
    
    public void setMarginLeft(final int marginLeft) {
        this.marginLeft = marginLeft;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ElementSpacing)) {
            return false;
        }
        final ElementSpacing other = (ElementSpacing)o;
        return other.canEqual(this) && this.getWidth() == other.getWidth() && this.getHeight() == other.getHeight() && this.getMarginTop() == other.getMarginTop() && this.getMarginRight() == other.getMarginRight() && this.getMarginBottom() == other.getMarginBottom() && this.getMarginLeft() == other.getMarginLeft();
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof ElementSpacing;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * 59 + this.getWidth();
        result = result * 59 + this.getHeight();
        result = result * 59 + this.getMarginTop();
        result = result * 59 + this.getMarginRight();
        result = result * 59 + this.getMarginBottom();
        result = result * 59 + this.getMarginLeft();
        return result;
    }
    
    @Override
    public String toString() {
        return "ElementSpacing(width=" + this.getWidth() + ", height=" + this.getHeight() + ", marginTop=" + this.getMarginTop() + ", marginRight=" + this.getMarginRight() + ", marginBottom=" + this.getMarginBottom() + ", marginLeft=" + this.getMarginLeft() + ")";
    }
}
