// 
// Decompiled by Procyon v0.6.0
// 

package net.minecraft.inventory;

import net.minecraft.item.ItemStack;
import net.minecraft.init.Blocks;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ContainerWorkbench extends Container
{
    public InventoryCrafting craftMatrix;
    public InventoryCraftResult craftResult;
    private final World world;
    private final BlockPos pos;
    private final EntityPlayer player;
    
    public ContainerWorkbench(final InventoryPlayer playerInventory, final World worldIn, final BlockPos posIn) {
        this.craftMatrix = new InventoryCrafting(this, 3, 3);
        this.craftResult = new InventoryCraftResult();
        this.world = worldIn;
        this.pos = posIn;
        this.player = playerInventory.player;
        this.addSlotToContainer(new SlotCrafting(playerInventory.player, this.craftMatrix, this.craftResult, 0, 124, 35));
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                this.addSlotToContainer(new Slot(this.craftMatrix, j + i * 3, 30 + j * 18, 17 + i * 18));
            }
        }
        for (int k = 0; k < 3; ++k) {
            for (int i2 = 0; i2 < 9; ++i2) {
                this.addSlotToContainer(new Slot(playerInventory, i2 + k * 9 + 9, 8 + i2 * 18, 84 + k * 18));
            }
        }
        for (int l = 0; l < 9; ++l) {
            this.addSlotToContainer(new Slot(playerInventory, l, 8 + l * 18, 142));
        }
    }
    
    @Override
    public void onCraftMatrixChanged(final IInventory inventoryIn) {
        this.slotChangedCraftingGrid(this.world, this.player, this.craftMatrix, this.craftResult);
    }
    
    @Override
    public void onContainerClosed(final EntityPlayer playerIn) {
        super.onContainerClosed(playerIn);
        if (!this.world.isRemote) {
            this.clearContainer(playerIn, this.world, this.craftMatrix);
        }
    }
    
    @Override
    public boolean canInteractWith(final EntityPlayer playerIn) {
        return this.world.getBlockState(this.pos).getBlock() == Blocks.CRAFTING_TABLE && playerIn.getDistanceSq(this.pos.getX() + 0.5, this.pos.getY() + 0.5, this.pos.getZ() + 0.5) <= 64.0;
    }
    
    @Override
    public ItemStack transferStackInSlot(final EntityPlayer playerIn, final int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        final Slot slot = this.inventorySlots.get(index);
        if (slot != null && slot.getHasStack()) {
            final ItemStack itemstack2 = slot.getStack();
            itemstack = itemstack2.copy();
            if (index == 0) {
                itemstack2.getItem().onCreated(itemstack2, this.world, playerIn);
                if (!this.mergeItemStack(itemstack2, 10, 46, true)) {
                    return ItemStack.EMPTY;
                }
                slot.onSlotChange(itemstack2, itemstack);
            }
            else if (index >= 10 && index < 37) {
                if (!this.mergeItemStack(itemstack2, 37, 46, false)) {
                    return ItemStack.EMPTY;
                }
            }
            else if (index >= 37 && index < 46) {
                if (!this.mergeItemStack(itemstack2, 10, 37, false)) {
                    return ItemStack.EMPTY;
                }
            }
            else if (!this.mergeItemStack(itemstack2, 10, 46, false)) {
                return ItemStack.EMPTY;
            }
            if (itemstack2.isEmpty()) {
                slot.putStack(ItemStack.EMPTY);
            }
            else {
                slot.onSlotChanged();
            }
            if (itemstack2.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }
            final ItemStack itemstack3 = slot.onTake(playerIn, itemstack2);
            if (index == 0) {
                playerIn.dropItem(itemstack3, false);
            }
        }
        return itemstack;
    }
    
    @Override
    public boolean canMergeSlot(final ItemStack stack, final Slot slotIn) {
        return slotIn.inventory != this.craftResult && super.canMergeSlot(stack, slotIn);
    }
}
