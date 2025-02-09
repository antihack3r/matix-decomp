// 
// Decompiled by Procyon v0.6.0
// 

package net.minecraft.client.resources;

import java.io.File;
import java.io.FileNotFoundException;

public class ResourcePackFileNotFoundException extends FileNotFoundException
{
    public ResourcePackFileNotFoundException(final File resourcePack, final String fileName) {
        super(String.format("'%s' in ResourcePack '%s'", fileName, resourcePack));
    }
}
