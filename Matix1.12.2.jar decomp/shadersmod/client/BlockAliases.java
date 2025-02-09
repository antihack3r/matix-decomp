// 
// Decompiled by Procyon v0.6.0
// 

package shadersmod.client;

import net.minecraft.src.MatchBlock;
import java.util.Iterator;
import java.util.Properties;
import java.io.InputStream;
import java.io.IOException;
import net.minecraft.src.StrUtils;
import net.minecraft.src.ConnectedParser;
import java.util.List;
import java.util.ArrayList;
import net.minecraft.src.Config;
import net.minecraft.src.PropertiesOrdered;

public class BlockAliases
{
    private static BlockAlias[][] blockAliases;
    
    public static int getMappedBlockId(final int blockId, final int metadata) {
        if (BlockAliases.blockAliases == null) {
            return blockId;
        }
        if (blockId < 0 || blockId >= BlockAliases.blockAliases.length) {
            return blockId;
        }
        final BlockAlias[] ablockalias = BlockAliases.blockAliases[blockId];
        if (ablockalias == null) {
            return blockId;
        }
        for (int i = 0; i < ablockalias.length; ++i) {
            final BlockAlias blockalias = ablockalias[i];
            if (blockalias.matches(blockId, metadata)) {
                return blockalias.getBlockId();
            }
        }
        return blockId;
    }
    
    public static void update(final IShaderPack shaderPack) {
        reset();
        final String s = "/shaders/block.properties";
        try {
            final InputStream inputstream = shaderPack.getResourceAsStream(s);
            if (inputstream == null) {
                return;
            }
            final Properties properties = new PropertiesOrdered();
            properties.load(inputstream);
            inputstream.close();
            Config.dbg("[Shaders] Parsing block mappings: " + s);
            final List<List<BlockAlias>> list = new ArrayList<List<BlockAlias>>();
            final ConnectedParser connectedparser = new ConnectedParser("Shaders");
            for (final Object o : properties.keySet()) {
                final String s2 = (String)o;
                final String s3 = properties.getProperty(s2);
                final String s4 = "block.";
                if (!s2.startsWith(s4)) {
                    Config.warn("[Shaders] Invalid block ID: " + s2);
                }
                else {
                    final String s5 = StrUtils.removePrefix(s2, s4);
                    final int i = Config.parseInt(s5, -1);
                    if (i < 0) {
                        Config.warn("[Shaders] Invalid block ID: " + s2);
                    }
                    else {
                        final MatchBlock[] amatchblock = connectedparser.parseMatchBlocks(s3);
                        if (amatchblock != null && amatchblock.length >= 1) {
                            final BlockAlias blockalias = new BlockAlias(i, amatchblock);
                            addToList(list, blockalias);
                        }
                        else {
                            Config.warn("[Shaders] Invalid block ID mapping: " + s2 + "=" + s3);
                        }
                    }
                }
            }
            if (list.size() <= 0) {
                return;
            }
            BlockAliases.blockAliases = toArrays(list);
        }
        catch (final IOException var15) {
            Config.warn("[Shaders] Error reading: " + s);
        }
    }
    
    private static void addToList(final List<List<BlockAlias>> blocksAliases, final BlockAlias ba) {
        final int[] aint = ba.getMatchBlockIds();
        for (int i = 0; i < aint.length; ++i) {
            final int j = aint[i];
            while (j >= blocksAliases.size()) {
                blocksAliases.add(null);
            }
            List<BlockAlias> list = blocksAliases.get(j);
            if (list == null) {
                list = new ArrayList<BlockAlias>();
                blocksAliases.set(j, list);
            }
            list.add(ba);
        }
    }
    
    private static BlockAlias[][] toArrays(final List<List<BlockAlias>> listBlocksAliases) {
        final BlockAlias[][] ablockalias = new BlockAlias[listBlocksAliases.size()][];
        for (int i = 0; i < ablockalias.length; ++i) {
            final List<BlockAlias> list = listBlocksAliases.get(i);
            if (list != null) {
                ablockalias[i] = list.toArray(new BlockAlias[list.size()]);
            }
        }
        return ablockalias;
    }
    
    public static void reset() {
        BlockAliases.blockAliases = null;
    }
    
    static {
        BlockAliases.blockAliases = null;
    }
}
