// 
// Decompiled by Procyon v0.6.0
// 

package optifine;

import java.io.IOException;
import java.util.zip.ZipEntry;
import java.io.InputStream;
import java.util.zip.ZipFile;

public class ZipResourceProvider implements IResourceProvider
{
    private ZipFile zipFile;
    
    public ZipResourceProvider(final ZipFile zipFile) {
        this.zipFile = null;
        this.zipFile = zipFile;
    }
    
    @Override
    public InputStream getResourceStream(String path) throws IOException {
        path = Utils.removePrefix(path, "/");
        final ZipEntry zipentry = this.zipFile.getEntry(path);
        if (zipentry == null) {
            return null;
        }
        final InputStream inputstream = this.zipFile.getInputStream(zipentry);
        return inputstream;
    }
}
