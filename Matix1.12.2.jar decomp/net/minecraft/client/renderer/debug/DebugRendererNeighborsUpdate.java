// 
// Decompiled by Procyon v0.6.0
// 

package net.minecraft.client.renderer.debug;

import java.util.Iterator;
import java.util.Set;
import net.minecraft.world.World;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.util.math.AxisAlignedBB;
import com.google.common.collect.Sets;
import net.minecraft.client.renderer.GlStateManager;
import java.util.Comparator;
import com.google.common.collect.Maps;
import com.google.common.collect.Ordering;
import net.minecraft.util.math.BlockPos;
import java.util.Map;
import net.minecraft.client.Minecraft;

public class DebugRendererNeighborsUpdate implements DebugRenderer.IDebugRenderer
{
    private final Minecraft minecraft;
    private final Map<Long, Map<BlockPos, Integer>> lastUpdate;
    
    DebugRendererNeighborsUpdate(final Minecraft p_i47365_1_) {
        this.lastUpdate = Maps.newTreeMap((Comparator)Ordering.natural().reverse());
        this.minecraft = p_i47365_1_;
    }
    
    public void addUpdate(final long p_191553_1_, final BlockPos p_191553_3_) {
        Map<BlockPos, Integer> map = this.lastUpdate.get(p_191553_1_);
        if (map == null) {
            map = Maps.newHashMap();
            this.lastUpdate.put(p_191553_1_, map);
        }
        Integer integer = map.get(p_191553_3_);
        if (integer == null) {
            integer = 0;
        }
        map.put(p_191553_3_, integer + 1);
    }
    
    @Override
    public void render(final float partialTicks, final long finishTimeNano) {
        final long i = this.minecraft.world.getTotalWorldTime();
        final EntityPlayer entityplayer = this.minecraft.player;
        final double d0 = entityplayer.lastTickPosX + (entityplayer.posX - entityplayer.lastTickPosX) * partialTicks;
        final double d2 = entityplayer.lastTickPosY + (entityplayer.posY - entityplayer.lastTickPosY) * partialTicks;
        final double d3 = entityplayer.lastTickPosZ + (entityplayer.posZ - entityplayer.lastTickPosZ) * partialTicks;
        final World world = this.minecraft.player.world;
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.glLineWidth(2.0f);
        GlStateManager.disableTexture2D();
        GlStateManager.depthMask(false);
        final int j = 200;
        final double d4 = 0.0025;
        final Set<BlockPos> set = Sets.newHashSet();
        final Map<BlockPos, Integer> map = Maps.newHashMap();
        final Iterator<Map.Entry<Long, Map<BlockPos, Integer>>> iterator = this.lastUpdate.entrySet().iterator();
        while (iterator.hasNext()) {
            final Map.Entry<Long, Map<BlockPos, Integer>> entry = iterator.next();
            final Long olong = entry.getKey();
            final Map<BlockPos, Integer> map2 = entry.getValue();
            final long k = i - olong;
            if (k > 200L) {
                iterator.remove();
            }
            else {
                for (final Map.Entry<BlockPos, Integer> entry2 : map2.entrySet()) {
                    final BlockPos blockpos = entry2.getKey();
                    final Integer integer = entry2.getValue();
                    if (set.add(blockpos)) {
                        RenderGlobal.drawSelectionBoundingBox(new AxisAlignedBB(BlockPos.ORIGIN).grow(0.002).shrink(0.0025 * k).offset(blockpos.getX(), blockpos.getY(), blockpos.getZ()).offset(-d0, -d2, -d3), 1.0f, 1.0f, 1.0f, 1.0f);
                        map.put(blockpos, integer);
                    }
                }
            }
        }
        for (final Map.Entry<BlockPos, Integer> entry3 : map.entrySet()) {
            final BlockPos blockpos2 = entry3.getKey();
            final Integer integer2 = entry3.getValue();
            DebugRenderer.renderDebugText(String.valueOf(integer2), blockpos2.getX(), blockpos2.getY(), blockpos2.getZ(), partialTicks, -1);
        }
        GlStateManager.depthMask(true);
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }
}
