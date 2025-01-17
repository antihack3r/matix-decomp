// 
// Decompiled by Procyon v0.6.0
// 

package net.minecraft.inventory;

import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.EntityPlayer;

public class ContainerDispenser extends Container
{
    private final IInventory dispenserInventory;
    
    public ContainerDispenser(final IInventory playerInventory, final IInventory dispenserInventoryIn) {
        this.dispenserInventory = dispenserInventoryIn;
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                this.addSlotToContainer(new Slot(dispenserInventoryIn, j + i * 3, 62 + j * 18, 17 + i * 18));
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
    public boolean canInteractWith(final EntityPlayer playerIn) {
        return this.dispenserInventory.isUsableByPlayer(playerIn);
    }
    
    @Override
    public ItemStack transferStackInSlot(final EntityPlayer playerIn, final int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        final Slot slot = this.inventorySlots.get(index);
        if (slot != null && slot.getHasStack()) {
            final ItemStack itemstack2 = slot.getStack();
            itemstack = itemstack2.copy();
            if (index < 9) {
                if (!this.mergeItemStack(itemstack2, 9, 45, true)) {
                    return ItemStack.EMPTY;
                }
            }
            else if (!this.mergeItemStack(itemstack2, 0, 9, false)) {
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
            slot.onTake(playerIn, itemstack2);
        }
        return itemstack;
    }
}
