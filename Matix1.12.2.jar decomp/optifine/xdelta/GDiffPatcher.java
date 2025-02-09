// 
// Decompiled by Procyon v0.6.0
// 

package optifine.xdelta;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.OutputStream;
import java.io.InputStream;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.RandomAccessFile;
import java.io.File;

public class GDiffPatcher
{
    public GDiffPatcher(final File sourceFile, final File patchFile, final File outputFile) throws IOException, PatchException {
        final RandomAccessFileSeekableSource randomaccessfileseekablesource = new RandomAccessFileSeekableSource(new RandomAccessFile(sourceFile, "r"));
        final InputStream inputstream = new FileInputStream(patchFile);
        final OutputStream outputstream = new FileOutputStream(outputFile);
        try {
            runPatch(randomaccessfileseekablesource, inputstream, outputstream);
        }
        catch (final IOException ioexception) {
            throw ioexception;
        }
        catch (final PatchException patchexception) {
            throw patchexception;
        }
        finally {
            randomaccessfileseekablesource.close();
            inputstream.close();
            outputstream.close();
        }
    }
    
    public GDiffPatcher(final byte[] source, final InputStream patch, final OutputStream output) throws IOException, PatchException {
        this(new ByteArraySeekableSource(source), patch, output);
    }
    
    public GDiffPatcher(final SeekableSource source, final InputStream patch, final OutputStream out) throws IOException, PatchException {
        runPatch(source, patch, out);
    }
    
    private static void runPatch(final SeekableSource source, final InputStream patch, final OutputStream out) throws IOException, PatchException {
        final DataOutputStream dataoutputstream = new DataOutputStream(out);
        final DataInputStream datainputstream = new DataInputStream(patch);
        try {
            final byte[] abyte = new byte[256];
            int i = 0;
            if (datainputstream.readUnsignedByte() == 209 && datainputstream.readUnsignedByte() == 255 && datainputstream.readUnsignedByte() == 209 && datainputstream.readUnsignedByte() == 255 && datainputstream.readUnsignedByte() == 4) {
                i += 5;
                while (datainputstream.available() > 0) {
                    final int j = datainputstream.readUnsignedByte();
                    int k = 0;
                    int l = 0;
                    switch (j) {
                        case 0: {
                            continue;
                        }
                        case 1: {
                            append(1, datainputstream, dataoutputstream);
                            i += 2;
                            continue;
                        }
                        case 2: {
                            append(2, datainputstream, dataoutputstream);
                            i += 3;
                            continue;
                        }
                        case 246: {
                            append(246, datainputstream, dataoutputstream);
                            i += 247;
                            continue;
                        }
                        case 247: {
                            k = datainputstream.readUnsignedShort();
                            append(k, datainputstream, dataoutputstream);
                            i += k + 3;
                            continue;
                        }
                        case 248: {
                            k = datainputstream.readInt();
                            append(k, datainputstream, dataoutputstream);
                            i += k + 5;
                            continue;
                        }
                        case 249: {
                            l = datainputstream.readUnsignedShort();
                            k = datainputstream.readUnsignedByte();
                            copy(l, k, source, dataoutputstream);
                            i += 4;
                            continue;
                        }
                        case 250: {
                            l = datainputstream.readUnsignedShort();
                            k = datainputstream.readUnsignedShort();
                            copy(l, k, source, dataoutputstream);
                            i += 5;
                            continue;
                        }
                        case 251: {
                            l = datainputstream.readUnsignedShort();
                            k = datainputstream.readInt();
                            copy(l, k, source, dataoutputstream);
                            i += 7;
                            continue;
                        }
                        case 252: {
                            l = datainputstream.readInt();
                            k = datainputstream.readUnsignedByte();
                            copy(l, k, source, dataoutputstream);
                            i += 8;
                            continue;
                        }
                        case 253: {
                            l = datainputstream.readInt();
                            k = datainputstream.readUnsignedShort();
                            copy(l, k, source, dataoutputstream);
                            i += 7;
                            continue;
                        }
                        case 254: {
                            l = datainputstream.readInt();
                            k = datainputstream.readInt();
                            copy(l, k, source, dataoutputstream);
                            i += 9;
                            continue;
                        }
                        case 255: {
                            final long i2 = datainputstream.readLong();
                            k = datainputstream.readInt();
                            copy(i2, k, source, dataoutputstream);
                            i += 13;
                            continue;
                        }
                        default: {
                            append(j, datainputstream, dataoutputstream);
                            i += j + 1;
                            continue;
                        }
                    }
                }
                return;
            }
            System.err.println("magic string not found, aborting!");
        }
        catch (final PatchException patchexception) {
            throw patchexception;
        }
        finally {
            dataoutputstream.flush();
        }
    }
    
    protected static void copy(final long offset, int length, final SeekableSource source, final OutputStream output) throws IOException, PatchException {
        if (offset + length > source.length()) {
            throw new PatchException("truncated source file, aborting");
        }
        final byte[] abyte = new byte[256];
        source.seek(offset);
        while (length > 0) {
            final int i = (length > 256) ? 256 : length;
            final int j = source.read(abyte, 0, i);
            output.write(abyte, 0, j);
            length -= j;
        }
    }
    
    protected static void append(int length, final InputStream patch, final OutputStream output) throws IOException {
        final byte[] abyte = new byte[256];
        while (length > 0) {
            final int i = (length > 256) ? 256 : length;
            final int j = patch.read(abyte, 0, i);
            output.write(abyte, 0, j);
            length -= j;
        }
    }
    
    public static void main(final String[] argv) {
        if (argv.length != 3) {
            System.err.println("usage GDiffPatch source patch output");
            System.err.println("aborting..");
        }
        else {
            try {
                final File file1 = new File(argv[0]);
                final File file2 = new File(argv[1]);
                final File file3 = new File(argv[2]);
                if (file1.length() > 2147483647L || file2.length() > 2147483647L) {
                    System.err.println("source or patch is too large, max length is 2147483647");
                    System.err.println("aborting..");
                    return;
                }
                new GDiffPatcher(file1, file2, file3);
                System.out.println("finished patching file");
            }
            catch (final Exception exception) {
                System.err.println("error while patching: " + exception);
            }
        }
    }
}
