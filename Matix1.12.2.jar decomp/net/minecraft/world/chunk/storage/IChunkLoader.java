// 
// Decompiled by Procyon v0.6.0
// 

package net.minecraft.world.chunk.storage;

import net.minecraft.world.MinecraftException;
import javax.annotation.Nullable;
import java.io.IOException;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.World;

public interface IChunkLoader
{
    @Nullable
    Chunk loadChunk(final World p0, final int p1, final int p2) throws IOException;
    
    void saveChunk(final World p0, final Chunk p1) throws MinecraftException, IOException;
    
    void saveExtraChunkData(final World p0, final Chunk p1) throws IOException;
    
    void chunkTick();
    
    void flush();
    
    boolean isChunkGeneratedAt(final int p0, final int p1);
}
