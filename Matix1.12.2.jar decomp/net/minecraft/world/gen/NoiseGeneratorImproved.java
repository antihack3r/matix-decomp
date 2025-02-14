// 
// Decompiled by Procyon v0.6.0
// 

package net.minecraft.world.gen;

import java.util.Random;

public class NoiseGeneratorImproved extends NoiseGenerator
{
    private final int[] permutations;
    public double xCoord;
    public double yCoord;
    public double zCoord;
    private static final double[] GRAD_X;
    private static final double[] GRAD_Y;
    private static final double[] GRAD_Z;
    private static final double[] GRAD_2X;
    private static final double[] GRAD_2Z;
    
    public NoiseGeneratorImproved() {
        this(new Random());
    }
    
    public NoiseGeneratorImproved(final Random p_i45469_1_) {
        this.permutations = new int[512];
        this.xCoord = p_i45469_1_.nextDouble() * 256.0;
        this.yCoord = p_i45469_1_.nextDouble() * 256.0;
        this.zCoord = p_i45469_1_.nextDouble() * 256.0;
        for (int i = 0; i < 256; this.permutations[i] = i++) {}
        for (int l = 0; l < 256; ++l) {
            final int j = p_i45469_1_.nextInt(256 - l) + l;
            final int k = this.permutations[l];
            this.permutations[l] = this.permutations[j];
            this.permutations[j] = k;
            this.permutations[l + 256] = this.permutations[l];
        }
    }
    
    public final double lerp(final double p_76311_1_, final double p_76311_3_, final double p_76311_5_) {
        return p_76311_3_ + p_76311_1_ * (p_76311_5_ - p_76311_3_);
    }
    
    public final double grad2(final int p_76309_1_, final double p_76309_2_, final double p_76309_4_) {
        final int i = p_76309_1_ & 0xF;
        return NoiseGeneratorImproved.GRAD_2X[i] * p_76309_2_ + NoiseGeneratorImproved.GRAD_2Z[i] * p_76309_4_;
    }
    
    public final double grad(final int p_76310_1_, final double p_76310_2_, final double p_76310_4_, final double p_76310_6_) {
        final int i = p_76310_1_ & 0xF;
        return NoiseGeneratorImproved.GRAD_X[i] * p_76310_2_ + NoiseGeneratorImproved.GRAD_Y[i] * p_76310_4_ + NoiseGeneratorImproved.GRAD_Z[i] * p_76310_6_;
    }
    
    public void populateNoiseArray(final double[] noiseArray, final double xOffset, final double yOffset, final double zOffset, final int xSize, final int ySize, final int zSize, final double xScale, final double yScale, final double zScale, final double noiseScale) {
        if (ySize == 1) {
            int i5 = 0;
            int j5 = 0;
            int k = 0;
            int k2 = 0;
            double d14 = 0.0;
            double d15 = 0.0;
            int l5 = 0;
            final double d16 = 1.0 / noiseScale;
            for (int j6 = 0; j6 < xSize; ++j6) {
                double d17 = xOffset + j6 * xScale + this.xCoord;
                int i6 = (int)d17;
                if (d17 < i6) {
                    --i6;
                }
                final int k3 = i6 & 0xFF;
                d17 -= i6;
                final double d18 = d17 * d17 * d17 * (d17 * (d17 * 6.0 - 15.0) + 10.0);
                for (int j7 = 0; j7 < zSize; ++j7) {
                    double d19 = zOffset + j7 * zScale + this.zCoord;
                    int k4 = (int)d19;
                    if (d19 < k4) {
                        --k4;
                    }
                    final int l6 = k4 & 0xFF;
                    d19 -= k4;
                    final double d20 = d19 * d19 * d19 * (d19 * (d19 * 6.0 - 15.0) + 10.0);
                    i5 = this.permutations[k3] + 0;
                    j5 = this.permutations[i5] + l6;
                    k = this.permutations[k3 + 1] + 0;
                    k2 = this.permutations[k] + l6;
                    d14 = this.lerp(d18, this.grad2(this.permutations[j5], d17, d19), this.grad(this.permutations[k2], d17 - 1.0, 0.0, d19));
                    d15 = this.lerp(d18, this.grad(this.permutations[j5 + 1], d17, 0.0, d19 - 1.0), this.grad(this.permutations[k2 + 1], d17 - 1.0, 0.0, d19 - 1.0));
                    final double d21 = this.lerp(d20, d14, d15);
                    final int n;
                    final int i7 = n = l5++;
                    noiseArray[n] += d21 * d16;
                }
            }
        }
        else {
            int m = 0;
            final double d22 = 1.0 / noiseScale;
            int k5 = -1;
            int l7 = 0;
            int i8 = 0;
            int j8 = 0;
            int k6 = 0;
            int l8 = 0;
            int i9 = 0;
            double d23 = 0.0;
            double d24 = 0.0;
            double d25 = 0.0;
            double d26 = 0.0;
            for (int l9 = 0; l9 < xSize; ++l9) {
                double d27 = xOffset + l9 * xScale + this.xCoord;
                int i10 = (int)d27;
                if (d27 < i10) {
                    --i10;
                }
                final int j9 = i10 & 0xFF;
                d27 -= i10;
                final double d28 = d27 * d27 * d27 * (d27 * (d27 * 6.0 - 15.0) + 10.0);
                for (int k7 = 0; k7 < zSize; ++k7) {
                    double d29 = zOffset + k7 * zScale + this.zCoord;
                    int l10 = (int)d29;
                    if (d29 < l10) {
                        --l10;
                    }
                    final int i11 = l10 & 0xFF;
                    d29 -= l10;
                    final double d30 = d29 * d29 * d29 * (d29 * (d29 * 6.0 - 15.0) + 10.0);
                    for (int j10 = 0; j10 < ySize; ++j10) {
                        double d31 = yOffset + j10 * yScale + this.yCoord;
                        int k8 = (int)d31;
                        if (d31 < k8) {
                            --k8;
                        }
                        final int l11 = k8 & 0xFF;
                        d31 -= k8;
                        final double d32 = d31 * d31 * d31 * (d31 * (d31 * 6.0 - 15.0) + 10.0);
                        if (j10 == 0 || l11 != k5) {
                            k5 = l11;
                            l7 = this.permutations[j9] + l11;
                            i8 = this.permutations[l7] + i11;
                            j8 = this.permutations[l7 + 1] + i11;
                            k6 = this.permutations[j9 + 1] + l11;
                            l8 = this.permutations[k6] + i11;
                            i9 = this.permutations[k6 + 1] + i11;
                            d23 = this.lerp(d28, this.grad(this.permutations[i8], d27, d31, d29), this.grad(this.permutations[l8], d27 - 1.0, d31, d29));
                            d24 = this.lerp(d28, this.grad(this.permutations[j8], d27, d31 - 1.0, d29), this.grad(this.permutations[i9], d27 - 1.0, d31 - 1.0, d29));
                            d25 = this.lerp(d28, this.grad(this.permutations[i8 + 1], d27, d31, d29 - 1.0), this.grad(this.permutations[l8 + 1], d27 - 1.0, d31, d29 - 1.0));
                            d26 = this.lerp(d28, this.grad(this.permutations[j8 + 1], d27, d31 - 1.0, d29 - 1.0), this.grad(this.permutations[i9 + 1], d27 - 1.0, d31 - 1.0, d29 - 1.0));
                        }
                        final double d33 = this.lerp(d32, d23, d24);
                        final double d34 = this.lerp(d32, d25, d26);
                        final double d35 = this.lerp(d30, d33, d34);
                        final int n2;
                        final int j11 = n2 = m++;
                        noiseArray[n2] += d35 * d22;
                    }
                }
            }
        }
    }
    
    static {
        GRAD_X = new double[] { 1.0, -1.0, 1.0, -1.0, 1.0, -1.0, 1.0, -1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, -1.0, 0.0 };
        GRAD_Y = new double[] { 1.0, 1.0, -1.0, -1.0, 0.0, 0.0, 0.0, 0.0, 1.0, -1.0, 1.0, -1.0, 1.0, -1.0, 1.0, -1.0 };
        GRAD_Z = new double[] { 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, -1.0, -1.0, 1.0, 1.0, -1.0, -1.0, 0.0, 1.0, 0.0, -1.0 };
        GRAD_2X = new double[] { 1.0, -1.0, 1.0, -1.0, 1.0, -1.0, 1.0, -1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, -1.0, 0.0 };
        GRAD_2Z = new double[] { 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, -1.0, -1.0, 1.0, 1.0, -1.0, -1.0, 0.0, 1.0, 0.0, -1.0 };
    }
}
