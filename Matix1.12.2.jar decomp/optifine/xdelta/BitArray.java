// 
// Decompiled by Procyon v0.6.0
// 

package optifine.xdelta;

public class BitArray
{
    int[] implArray;
    int size;
    static final int INT_SIZE = 32;
    
    public BitArray(final int size) {
        final int i = size / 32 + 1;
        this.implArray = new int[i];
    }
    
    public void set(final int pos, final boolean value) {
        final int i = pos / 32;
        final int j = 1 << (pos & 0x1F);
        if (value) {
            final int[] implArray = this.implArray;
            final int n = i;
            implArray[n] |= j;
        }
        else {
            final int[] implArray2 = this.implArray;
            final int n2 = i;
            implArray2[n2] &= ~j;
        }
    }
    
    public boolean get(final int pos) {
        final int i = pos / 32;
        final int j = 1 << (pos & 0x1F);
        return (this.implArray[i] & j) != 0x0;
    }
}
