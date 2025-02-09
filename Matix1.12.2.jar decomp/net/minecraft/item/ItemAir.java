// 
// Decompiled by Procyon v0.6.0
// 

package net.minecraft.item;

import net.minecraft.client.util.ITooltipFlag;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.world.World;
import net.minecraft.block.Block;

public class ItemAir extends Item
{
    private final Block block;
    
    public ItemAir(final Block blockIn) {
        this.block = blockIn;
    }
    
    @Override
    public String getUnlocalizedName(final ItemStack stack) {
        return this.block.getUnlocalizedName();
    }
    
    @Override
    public String getUnlocalizedName() {
        return this.block.getUnlocalizedName();
    }
    
    @Override
    public void addInformation(final ItemStack stack, @Nullable final World worldIn, final List<String> tooltip, final ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        this.block.addInformation(stack, worldIn, tooltip, flagIn);
    }
}
