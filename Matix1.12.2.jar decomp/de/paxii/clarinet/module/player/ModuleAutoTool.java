// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.module.player;

import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.item.Item;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.block.Block;
import de.paxii.clarinet.util.player.PlayerUtils;
import net.minecraft.item.ItemSword;
import net.minecraft.block.material.Material;
import de.paxii.clarinet.Wrapper;
import de.paxii.clarinet.event.events.player.PlayerAttackEntityEvent;
import de.paxii.clarinet.event.EventHandler;
import net.minecraft.util.math.BlockPos;
import de.paxii.clarinet.event.events.player.PlayerClickBlockEvent;
import de.paxii.clarinet.module.ModuleCategory;
import de.paxii.clarinet.module.Module;

public class ModuleAutoTool extends Module
{
    public ModuleAutoTool() {
        super("AutoTool", ModuleCategory.PLAYER, -1);
        this.setRegistered(true);
        this.setDescription("Automatically uses the best tool currently in the hotbar.");
    }
    
    @EventHandler
    public void onClickBlock(final PlayerClickBlockEvent event) {
        final BlockPos blockPos = event.getBlockPos();
        this.autoTool(blockPos.getX(), blockPos.getY(), blockPos.getZ());
    }
    
    @EventHandler
    public void onAttackEntity(final PlayerAttackEntityEvent event) {
        this.bestSword(event.getTarget());
    }
    
    private void autoTool(final int i, final int j, final int k) {
        final Block b = Wrapper.getWorld().getBlock(i, j, k);
        if (b.getMaterial() != Material.AIR) {
            float s = 0.1f;
            int currentItem = Wrapper.getPlayer().inventory.currentItem;
            for (int inventoryIndex = 36; inventoryIndex < 45; ++inventoryIndex) {
                final ItemStack is = Wrapper.getPlayer().openContainer.getSlot(inventoryIndex).getStack();
                if (is != null) {
                    if (!Wrapper.getPlayer().capabilities.isCreativeMode || !(is.getItem() instanceof ItemSword)) {
                        final float strength = PlayerUtils.getPlayerStrVsBlock(b, is);
                        if (strength > s) {
                            s = strength;
                            currentItem = inventoryIndex - 36;
                        }
                    }
                }
            }
            Wrapper.getPlayer().inventory.currentItem = currentItem;
        }
    }
    
    private void bestSword(final Entity targetEntity) {
        int bestSlot = 0;
        float damage = 1.0f;
        for (int inventoryIndex = 36; inventoryIndex < 45; ++inventoryIndex) {
            if (Wrapper.getPlayer().inventoryContainer.inventorySlots.toArray()[inventoryIndex] != null && targetEntity != null) {
                final ItemStack curSlot = Wrapper.getPlayer().inventoryContainer.getSlot(inventoryIndex).getStack();
                if (curSlot != null) {
                    final float itemDamage = 1.0f + (curSlot.hasEffect() ? this.getEnchantDamageVsEntity(curSlot, targetEntity) : this.getSwordQuality(curSlot));
                    if (itemDamage > damage) {
                        bestSlot = inventoryIndex - 36;
                        damage = itemDamage;
                    }
                }
            }
        }
        if (damage > 1.0f) {
            Wrapper.getPlayer().inventory.currentItem = bestSlot;
        }
    }
    
    private int getSwordQuality(final ItemStack itemStack) {
        final Item item = itemStack.getItem();
        if (item == Item.getItemById(276)) {
            return 5;
        }
        if (item == Item.getItemById(283)) {
            return 4;
        }
        if (item == Item.getItemById(267)) {
            return 3;
        }
        if (item == Item.getItemById(272)) {
            return 2;
        }
        if (item == Item.getItemById(268)) {
            return 1;
        }
        return 0;
    }
    
    private int getEnchantDamageVsEntity(final ItemStack i, final Entity e) {
        if (e instanceof EntityZombie || e instanceof EntitySkeleton) {
            return EnchantmentHelper.getEnchantmentLevel(Enchantment.getEnchantmentByID(16), i) + EnchantmentHelper.getEnchantmentLevel(Enchantment.getEnchantmentByID(17), i);
        }
        if (e instanceof EntitySpider) {
            return EnchantmentHelper.getEnchantmentLevel(Enchantment.getEnchantmentByID(16), i) + EnchantmentHelper.getEnchantmentLevel(Enchantment.getEnchantmentByID(18), i);
        }
        return EnchantmentHelper.getEnchantmentLevel(Enchantment.getEnchantmentByID(16), i);
    }
}
