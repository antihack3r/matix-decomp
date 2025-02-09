// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.command.commands.main;

import de.paxii.clarinet.command.CommandCategory;
import java.util.Iterator;
import net.minecraft.item.ItemStack;
import de.paxii.clarinet.util.chat.Chat;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.util.EnumHand;
import de.paxii.clarinet.Wrapper;
import de.paxii.clarinet.command.AClientCommand;

public class CommandEnchant extends AClientCommand
{
    @Override
    public String getCommand() {
        return "enchant";
    }
    
    @Override
    public String getDescription() {
        return "Sets the Enchantments for the current Item to 127. Requires Creative Mode.";
    }
    
    @Override
    public void runCommand(final String[] args) {
        if (Wrapper.getPlayer().capabilities.isCreativeMode) {
            final ItemStack currentItem = Wrapper.getPlayer().getHeldItem(EnumHand.MAIN_HAND);
            if (currentItem != null) {
                for (final Enchantment enchantment : Enchantment.REGISTRY) {
                    if (enchantment == Enchantment.getEnchantmentByID(33)) {
                        continue;
                    }
                    try {
                        currentItem.addEnchantment(enchantment, 127);
                    }
                    catch (final Exception ex) {}
                }
                Chat.printClientMessage("Your Item has been enchanted!");
            }
            else {
                Chat.printClientMessage("You need to have an Item in Hand!");
            }
        }
        else {
            Chat.printClientMessage("This Command requires you to be in Creative Mode.");
        }
    }
    
    @Override
    public String getUsage() {
        return this.getCommand();
    }
    
    @Override
    public CommandCategory getCategory() {
        return CommandCategory.MAIN;
    }
}
