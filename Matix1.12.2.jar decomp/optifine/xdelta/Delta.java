// 
// Decompiled by Procyon v0.6.0
// 

package optifine.xdelta;

import java.io.DataOutputStream;
import java.io.OutputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.RandomAccessFile;
import java.io.File;
import java.io.IOException;
import java.io.PushbackInputStream;
import java.io.BufferedInputStream;
import java.io.InputStream;

public class Delta
{
    public static final int S = 16;
    public static final boolean debug = false;
    public static final int buff_size = 1024;
    
    public static void computeDelta(final SeekableSource source, final InputStream targetIS, final int targetLength, final DiffWriter output) throws IOException, DeltaException {
        final int i = (int)source.length();
        final Checksum checksum = new Checksum();
        checksum.generateChecksums(new SeekableSourceInputStream(source), i);
        source.seek(0L);
        final PushbackInputStream pushbackinputstream = new PushbackInputStream(new BufferedInputStream(targetIS), 1024);
        boolean flag = false;
        final byte[] abyte = new byte[16];
        long j = 0L;
        final byte[] abyte2 = { 0 };
        final byte[] abyte3 = new byte[16];
        if (targetLength > 16 && i > 16) {
            int i2;
            final int l3 = i2 = pushbackinputstream.read(abyte, 0, 16);
            long i3;
            j = (i3 = Checksum.queryChecksum(abyte, 16));
            boolean flag2 = false;
            while (!flag) {
                final int j2 = checksum.findChecksumIndex(j);
                if (j2 != -1) {
                    boolean flag3 = true;
                    final int k1 = j2 * 16;
                    int l4 = 15;
                    source.seek(k1);
                    if (!flag2 && source.read(abyte3, 0, 16) != -1) {
                        for (int i4 = 0; i4 < 16; ++i4) {
                            if (abyte3[i4] != abyte[i4]) {
                                flag3 = false;
                            }
                        }
                    }
                    else {
                        flag2 = true;
                    }
                    if (flag3 & !flag2) {
                        final long l5 = System.currentTimeMillis();
                        boolean flag4 = true;
                        final byte[] abyte4 = new byte[1024];
                        final byte[] abyte5 = new byte[1024];
                        int j3 = 0;
                        int k2 = 0;
                        final int l6 = 0;
                        while (true) {
                            j3 = source.read(abyte4, 0, 1024);
                            if (j3 == -1) {
                                flag2 = true;
                                break;
                            }
                            k2 = pushbackinputstream.read(abyte5, 0, j3);
                            if (k2 == -1) {
                                break;
                            }
                            final int i5 = Math.min(j3, k2);
                            int j4 = 0;
                            do {
                                ++i2;
                                ++l4;
                                flag4 = (abyte4[j4] == abyte5[j4]);
                                ++j4;
                                if (!flag4) {
                                    abyte2[0] = abyte5[j4 - 1];
                                    if (k2 == -1) {
                                        continue;
                                    }
                                    pushbackinputstream.unread(abyte5, j4, k2 - j4);
                                }
                            } while (j4 < i5 && flag4);
                            abyte2[0] = abyte5[j4 - 1];
                            if (!flag4) {
                                break;
                            }
                            if (targetLength - i2 <= 0) {
                                break;
                            }
                        }
                        output.addCopy(k1, l4);
                        if (targetLength - i2 <= 15) {
                            abyte[0] = abyte2[0];
                            final int i6 = targetLength - i2;
                            pushbackinputstream.read(abyte, 1, i6);
                            i2 += i6;
                            for (int k3 = 0; k3 < i6 + 1; ++k3) {
                                output.addData(abyte[k3]);
                            }
                            flag = true;
                            continue;
                        }
                        abyte[0] = abyte2[0];
                        pushbackinputstream.read(abyte, 1, 15);
                        i2 += 15;
                        j = (i3 = Checksum.queryChecksum(abyte, 16));
                        continue;
                    }
                }
                if (targetLength - i2 > 0) {
                    pushbackinputstream.read(abyte2, 0, 1);
                    ++i2;
                    output.addData(abyte[0]);
                    i3 = Checksum.incrementChecksum(i3, abyte[0], abyte2[0]);
                    for (int k4 = 0; k4 < 15; ++k4) {
                        abyte[k4] = abyte[k4 + 1];
                    }
                    abyte[15] = abyte2[0];
                    j = Checksum.queryChecksum(abyte, 16);
                }
                else {
                    for (int j5 = 0; j5 < 16; ++j5) {
                        output.addData(abyte[j5]);
                    }
                    flag = true;
                }
            }
        }
        else {
            int m;
            while ((m = pushbackinputstream.read(abyte)) >= 0) {
                for (int l7 = 0; l7 < m; ++l7) {
                    output.addData(abyte[l7]);
                }
            }
        }
    }
    
    public static void computeDelta(final byte[] source, final InputStream targetIS, final int targetLength, final DiffWriter output) throws IOException, DeltaException {
        computeDelta(new ByteArraySeekableSource(source), targetIS, targetLength, output);
    }
    
    public static void computeDelta(final File sourceFile, final File targetFile, final DiffWriter output) throws IOException, DeltaException {
        final int i = (int)targetFile.length();
        final SeekableSource seekablesource = new RandomAccessFileSeekableSource(new RandomAccessFile(sourceFile, "r"));
        final InputStream inputstream = new FileInputStream(targetFile);
        try {
            computeDelta(seekablesource, inputstream, i, output);
        }
        catch (final IOException ioexception) {
            throw ioexception;
        }
        catch (final DeltaException deltaexception) {
            throw deltaexception;
        }
        finally {
            output.flush();
            seekablesource.close();
            inputstream.close();
            output.close();
        }
    }
    
    public static void main(final String[] argv) {
        new Delta();
        if (argv.length != 3) {
            System.err.println("usage Delta [-d] source target [output]");
            System.err.println("either -d or an output filename must be specified.");
            System.err.println("aborting..");
        }
        else {
            try {
                DiffWriter diffwriter = null;
                File file1 = null;
                File file2 = null;
                if (argv[0].equals("-d")) {
                    file1 = new File(argv[1]);
                    file2 = new File(argv[2]);
                    diffwriter = new DebugDiffWriter();
                }
                else {
                    file1 = new File(argv[0]);
                    file2 = new File(argv[1]);
                    diffwriter = new GDiffWriter(new DataOutputStream(new BufferedOutputStream(new FileOutputStream(new File(argv[2])))));
                }
                if (file1.length() > 2147483647L || file2.length() > 2147483647L) {
                    System.err.println("source or target is too large, max length is 2147483647");
                    System.err.println("aborting..");
                    return;
                }
                computeDelta(file1, file2, diffwriter);
                diffwriter.flush();
                diffwriter.close();
            }
            catch (final Exception exception) {
                System.err.println("error while generating delta: " + exception);
            }
        }
    }
}
