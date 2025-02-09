// 
// Decompiled by Procyon v0.6.0
// 

package net.minecraft.block;

import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Container;
import net.minecraft.world.IBlockAccess;
import net.minecraft.block.material.EnumPushReaction;
import java.util.Iterator;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.util.NonNullList;
import net.minecraft.client.util.ITooltipFlag;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.inventory.IInventory;
import net.minecraft.stats.StatList;
import net.minecraft.util.EnumHand;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntityShulkerBox;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraft.block.properties.IProperty;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.util.EnumFacing;
import net.minecraft.block.properties.PropertyEnum;

public class BlockShulkerBox extends BlockContainer
{
    public static final PropertyEnum<EnumFacing> FACING;
    private final EnumDyeColor color;
    
    public BlockShulkerBox(final EnumDyeColor colorIn) {
        super(Material.ROCK, MapColor.AIR);
        this.color = colorIn;
        this.setCreativeTab(CreativeTabs.DECORATIONS);
        this.setDefaultState(this.blockState.getBaseState().withProperty(BlockShulkerBox.FACING, EnumFacing.UP));
    }
    
    @Override
    public TileEntity createNewTileEntity(final World worldIn, final int meta) {
        return new TileEntityShulkerBox(this.color);
    }
    
    @Override
    public boolean isOpaqueCube(final IBlockState state) {
        return false;
    }
    
    @Override
    public boolean causesSuffocation(final IBlockState state) {
        return true;
    }
    
    @Override
    public boolean isFullCube(final IBlockState state) {
        return false;
    }
    
    @Override
    public boolean hasCustomBreakingProgress(final IBlockState state) {
        return true;
    }
    
    @Override
    public EnumBlockRenderType getRenderType(final IBlockState state) {
        return EnumBlockRenderType.ENTITYBLOCK_ANIMATED;
    }
    
    @Override
    public boolean onBlockActivated(final World worldIn, final BlockPos pos, final IBlockState state, final EntityPlayer playerIn, final EnumHand hand, final EnumFacing facing, final float hitX, final float hitY, final float hitZ) {
        if (worldIn.isRemote) {
            return true;
        }
        if (playerIn.isSpectator()) {
            return true;
        }
        final TileEntity tileentity = worldIn.getTileEntity(pos);
        if (tileentity instanceof TileEntityShulkerBox) {
            final EnumFacing enumfacing = state.getValue(BlockShulkerBox.FACING);
            boolean flag;
            if (((TileEntityShulkerBox)tileentity).getAnimationStatus() == TileEntityShulkerBox.AnimationStatus.CLOSED) {
                final AxisAlignedBB axisalignedbb = BlockShulkerBox.FULL_BLOCK_AABB.expand(0.5f * enumfacing.getFrontOffsetX(), 0.5f * enumfacing.getFrontOffsetY(), 0.5f * enumfacing.getFrontOffsetZ()).contract(enumfacing.getFrontOffsetX(), enumfacing.getFrontOffsetY(), enumfacing.getFrontOffsetZ());
                flag = !worldIn.collidesWithAnyBlock(axisalignedbb.offset(pos.offset(enumfacing)));
            }
            else {
                flag = true;
            }
            if (flag) {
                playerIn.addStat(StatList.OPEN_SHULKER_BOX);
                playerIn.displayGUIChest((IInventory)tileentity);
            }
            return true;
        }
        return false;
    }
    
    @Override
    public IBlockState getStateForPlacement(final World worldIn, final BlockPos pos, final EnumFacing facing, final float hitX, final float hitY, final float hitZ, final int meta, final EntityLivingBase placer) {
        return this.getDefaultState().withProperty(BlockShulkerBox.FACING, facing);
    }
    
    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, (IProperty<?>[])new IProperty[] { BlockShulkerBox.FACING });
    }
    
    @Override
    public int getMetaFromState(final IBlockState state) {
        return state.getValue(BlockShulkerBox.FACING).getIndex();
    }
    
    @Override
    public IBlockState getStateFromMeta(final int meta) {
        final EnumFacing enumfacing = EnumFacing.getFront(meta);
        return this.getDefaultState().withProperty(BlockShulkerBox.FACING, enumfacing);
    }
    
    @Override
    public void onBlockHarvested(final World worldIn, final BlockPos pos, final IBlockState state, final EntityPlayer player) {
        if (worldIn.getTileEntity(pos) instanceof TileEntityShulkerBox) {
            final TileEntityShulkerBox tileentityshulkerbox = (TileEntityShulkerBox)worldIn.getTileEntity(pos);
            tileentityshulkerbox.setDestroyedByCreativePlayer(player.capabilities.isCreativeMode);
            tileentityshulkerbox.fillWithLoot(player);
        }
    }
    
    @Override
    public void dropBlockAsItemWithChance(final World worldIn, final BlockPos pos, final IBlockState state, final float chance, final int fortune) {
    }
    
    @Override
    public void onBlockPlacedBy(final World worldIn, final BlockPos pos, final IBlockState state, final EntityLivingBase placer, final ItemStack stack) {
        if (stack.hasDisplayName()) {
            final TileEntity tileentity = worldIn.getTileEntity(pos);
            if (tileentity instanceof TileEntityShulkerBox) {
                ((TileEntityShulkerBox)tileentity).setCustomName(stack.getDisplayName());
            }
        }
    }
    
    @Override
    public void breakBlock(final World worldIn, final BlockPos pos, final IBlockState state) {
        final TileEntity tileentity = worldIn.getTileEntity(pos);
        if (tileentity instanceof TileEntityShulkerBox) {
            final TileEntityShulkerBox tileentityshulkerbox = (TileEntityShulkerBox)tileentity;
            if (!tileentityshulkerbox.isCleared() && tileentityshulkerbox.shouldDrop()) {
                final ItemStack itemstack = new ItemStack(Item.getItemFromBlock(this));
                final NBTTagCompound nbttagcompound = new NBTTagCompound();
                final NBTTagCompound nbttagcompound2 = new NBTTagCompound();
                nbttagcompound.setTag("BlockEntityTag", ((TileEntityShulkerBox)tileentity).saveToNbt(nbttagcompound2));
                itemstack.setTagCompound(nbttagcompound);
                if (tileentityshulkerbox.hasCustomName()) {
                    itemstack.setStackDisplayName(tileentityshulkerbox.getName());
                    tileentityshulkerbox.setCustomName("");
                }
                Block.spawnAsEntity(worldIn, pos, itemstack);
            }
            worldIn.updateComparatorOutputLevel(pos, state.getBlock());
        }
        super.breakBlock(worldIn, pos, state);
    }
    
    @Override
    public void addInformation(final ItemStack stack, @Nullable final World player, final List<String> tooltip, final ITooltipFlag advanced) {
        super.addInformation(stack, player, tooltip, advanced);
        final NBTTagCompound nbttagcompound = stack.getTagCompound();
        if (nbttagcompound != null && nbttagcompound.hasKey("BlockEntityTag", 10)) {
            final NBTTagCompound nbttagcompound2 = nbttagcompound.getCompoundTag("BlockEntityTag");
            if (nbttagcompound2.hasKey("LootTable", 8)) {
                tooltip.add("???????");
            }
            if (nbttagcompound2.hasKey("Items", 9)) {
                final NonNullList<ItemStack> nonnulllist = NonNullList.withSize(27, ItemStack.EMPTY);
                ItemStackHelper.loadAllItems(nbttagcompound2, nonnulllist);
                int i = 0;
                int j = 0;
                for (final ItemStack itemstack : nonnulllist) {
                    if (!itemstack.isEmpty()) {
                        ++j;
                        if (i > 4) {
                            continue;
                        }
                        ++i;
                        tooltip.add(String.format("%s x%d", itemstack.getDisplayName(), itemstack.getCount()));
                    }
                }
                if (j - i > 0) {
                    tooltip.add(String.format(TextFormatting.ITALIC + I18n.translateToLocal("container.shulkerBox.more"), j - i));
                }
            }
        }
    }
    
    @Override
    public EnumPushReaction getMobilityFlag(final IBlockState state) {
        return EnumPushReaction.DESTROY;
    }
    
    @Override
    public AxisAlignedBB getBoundingBox(final IBlockState state, final IBlockAccess source, final BlockPos pos) {
        final TileEntity tileentity = source.getTileEntity(pos);
        return (tileentity instanceof TileEntityShulkerBox) ? ((TileEntityShulkerBox)tileentity).getBoundingBox(state) : BlockShulkerBox.FULL_BLOCK_AABB;
    }
    
    @Override
    public boolean hasComparatorInputOverride(final IBlockState state) {
        return true;
    }
    
    @Override
    public int getComparatorInputOverride(final IBlockState blockState, final World worldIn, final BlockPos pos) {
        return Container.calcRedstoneFromInventory((IInventory)worldIn.getTileEntity(pos));
    }
    
    @Override
    public ItemStack getItem(final World worldIn, final BlockPos pos, final IBlockState state) {
        final ItemStack itemstack = super.getItem(worldIn, pos, state);
        final TileEntityShulkerBox tileentityshulkerbox = (TileEntityShulkerBox)worldIn.getTileEntity(pos);
        final NBTTagCompound nbttagcompound = tileentityshulkerbox.saveToNbt(new NBTTagCompound());
        if (!nbttagcompound.hasNoTags()) {
            itemstack.setTagInfo("BlockEntityTag", nbttagcompound);
        }
        return itemstack;
    }
    
    public static EnumDyeColor getColorFromItem(final Item itemIn) {
        return getColorFromBlock(Block.getBlockFromItem(itemIn));
    }
    
    public static EnumDyeColor getColorFromBlock(final Block blockIn) {
        return (blockIn instanceof BlockShulkerBox) ? ((BlockShulkerBox)blockIn).getColor() : EnumDyeColor.PURPLE;
    }
    
    public static Block getBlockByColor(final EnumDyeColor colorIn) {
        switch (colorIn) {
            case WHITE: {
                return Blocks.WHITE_SHULKER_BOX;
            }
            case ORANGE: {
                return Blocks.ORANGE_SHULKER_BOX;
            }
            case MAGENTA: {
                return Blocks.MAGENTA_SHULKER_BOX;
            }
            case LIGHT_BLUE: {
                return Blocks.LIGHT_BLUE_SHULKER_BOX;
            }
            case YELLOW: {
                return Blocks.YELLOW_SHULKER_BOX;
            }
            case LIME: {
                return Blocks.LIME_SHULKER_BOX;
            }
            case PINK: {
                return Blocks.PINK_SHULKER_BOX;
            }
            case GRAY: {
                return Blocks.GRAY_SHULKER_BOX;
            }
            case SILVER: {
                return Blocks.SILVER_SHULKER_BOX;
            }
            case CYAN: {
                return Blocks.CYAN_SHULKER_BOX;
            }
            default: {
                return Blocks.PURPLE_SHULKER_BOX;
            }
            case BLUE: {
                return Blocks.BLUE_SHULKER_BOX;
            }
            case BROWN: {
                return Blocks.BROWN_SHULKER_BOX;
            }
            case GREEN: {
                return Blocks.GREEN_SHULKER_BOX;
            }
            case RED: {
                return Blocks.RED_SHULKER_BOX;
            }
            case BLACK: {
                return Blocks.BLACK_SHULKER_BOX;
            }
        }
    }
    
    public EnumDyeColor getColor() {
        return this.color;
    }
    
    public static ItemStack getColoredItemStack(final EnumDyeColor colorIn) {
        return new ItemStack(getBlockByColor(colorIn));
    }
    
    @Override
    public IBlockState withRotation(final IBlockState state, final Rotation rot) {
        return state.withProperty(BlockShulkerBox.FACING, rot.rotate(state.getValue(BlockShulkerBox.FACING)));
    }
    
    @Override
    public IBlockState withMirror(final IBlockState state, final Mirror mirrorIn) {
        return state.withRotation(mirrorIn.toRotation(state.getValue(BlockShulkerBox.FACING)));
    }
    
    @Override
    public BlockFaceShape getBlockFaceShape(final IBlockAccess p_193383_1_, IBlockState p_193383_2_, final BlockPos p_193383_3_, final EnumFacing p_193383_4_) {
        p_193383_2_ = this.getActualState(p_193383_2_, p_193383_1_, p_193383_3_);
        final EnumFacing enumfacing = p_193383_2_.getValue(BlockShulkerBox.FACING);
        final TileEntityShulkerBox.AnimationStatus tileentityshulkerbox$animationstatus = ((TileEntityShulkerBox)p_193383_1_.getTileEntity(p_193383_3_)).getAnimationStatus();
        return (tileentityshulkerbox$animationstatus != TileEntityShulkerBox.AnimationStatus.CLOSED && (tileentityshulkerbox$animationstatus != TileEntityShulkerBox.AnimationStatus.OPENED || (enumfacing != p_193383_4_.getOpposite() && enumfacing != p_193383_4_))) ? BlockFaceShape.UNDEFINED : BlockFaceShape.SOLID;
    }
    
    static {
        FACING = PropertyDirection.create("facing");
    }
}
