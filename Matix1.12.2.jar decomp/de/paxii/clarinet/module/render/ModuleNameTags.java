// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.module.render;

import java.util.Iterator;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.item.Item;
import net.minecraft.enchantment.Enchantment;
import java.util.Map;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.world.World;
import java.util.Collection;
import net.minecraft.item.ItemStack;
import java.util.ArrayList;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.client.renderer.entity.RenderEntityItem;
import net.minecraft.client.renderer.BufferBuilder;
import org.lwjgl.opengl.GL11;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.GlStateManager;
import de.paxii.clarinet.util.chat.ChatColor;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import de.paxii.clarinet.Wrapper;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.entity.Entity;
import de.paxii.clarinet.util.module.settings.ValueBase;
import de.paxii.clarinet.util.settings.type.ClientSettingBoolean;
import de.paxii.clarinet.module.ModuleCategory;
import de.paxii.clarinet.module.Module;

public class ModuleNameTags extends Module
{
    private static ModuleNameTags instance;
    private static boolean isActive;
    
    public ModuleNameTags() {
        super("NameTags", ModuleCategory.RENDER);
        (ModuleNameTags.instance = this).setDescription("Renders bigger nametags alongside the health of other players");
        this.getModuleSettings().put("displayHealth", new ClientSettingBoolean("Health", true));
        this.getModuleSettings().put("displayArmor", new ClientSettingBoolean("Armor", false));
        this.getModuleSettings().put("opacity", new ClientSettingBoolean("Opacity", true));
        this.getModuleValues().put("scale", new ValueBase("NameTag Scale", 3.0f, 1.0f, 10.0f, "Scale"));
    }
    
    public static void drawHealthTags(final Entity entity, final FontRenderer fontRenderer, String nameTag, final float posX, final float posY, final float posZ, int yOffset, final float playerViewY, final float playerViewX, final boolean thirdPersonView, final boolean isSneaking) {
        final float distance = Wrapper.getPlayer().getDistanceToEntity(entity);
        final double scale = (distance > 10.0f) ? (distance / ModuleNameTags.instance.getModuleValues().get("scale").getValue()) : 1.0;
        final float alpha = ModuleNameTags.instance.getValueOrDefault("opacity", Boolean.class, true) ? 0.25f : 1.0f;
        yOffset -= (int)(scale / 2.0);
        if (entity instanceof EntityOtherPlayerMP && ModuleNameTags.instance.getValueOrDefault("displayHealth", Boolean.class, true)) {
            final int health = (int)((EntityOtherPlayerMP)entity).getHealth();
            nameTag = nameTag + " | HP: " + ((health >= 10) ? ChatColor.GREEN : ChatColor.RED) + health;
        }
        GlStateManager.pushMatrix();
        GlStateManager.translate(posX, posY, posZ);
        GlStateManager.glNormal3f(0.0f, 1.0f, 0.0f);
        GlStateManager.rotate(-playerViewY, 0.0f, 1.0f, 0.0f);
        GlStateManager.rotate((thirdPersonView ? -1 : 1) * playerViewX, 1.0f, 0.0f, 0.0f);
        GlStateManager.scale(-0.025f, -0.025f, 0.025f);
        GlStateManager.disableLighting();
        GlStateManager.depthMask(false);
        GlStateManager.disableDepth();
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        final int i = fontRenderer.getStringWidth(nameTag) / 2;
        GlStateManager.disableTexture2D();
        final Tessellator tessellator = Tessellator.getInstance();
        final BufferBuilder vertexbuffer = tessellator.getBuffer();
        vertexbuffer.begin(7, DefaultVertexFormats.POSITION_COLOR);
        vertexbuffer.pos(-i - 1, -1 + yOffset, 0.0).color(0.0f, 0.0f, 0.0f, alpha).endVertex();
        vertexbuffer.pos(-i - 1, 8 + yOffset, 0.0).color(0.0f, 0.0f, 0.0f, alpha).endVertex();
        vertexbuffer.pos(i + 1, 8 + yOffset, 0.0).color(0.0f, 0.0f, 0.0f, alpha).endVertex();
        vertexbuffer.pos(i + 1, -1 + yOffset, 0.0).color(0.0f, 0.0f, 0.0f, alpha).endVertex();
        GL11.glScaled(scale, scale, scale);
        tessellator.draw();
        GlStateManager.enableTexture2D();
        fontRenderer.drawString(nameTag, -fontRenderer.getStringWidth(nameTag) / 2, yOffset, -1);
        GlStateManager.enableDepth();
        GlStateManager.depthMask(true);
        GlStateManager.enableLighting();
        GlStateManager.disableBlend();
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        GlStateManager.popMatrix();
        if (entity instanceof EntityOtherPlayerMP && ModuleNameTags.instance.getValueOrDefault("displayArmor", Boolean.class, false)) {
            drawArmor((EntityOtherPlayerMP)entity, fontRenderer, posX, posY, posZ, playerViewY, playerViewX, thirdPersonView);
        }
    }
    
    private static void drawArmor(final EntityOtherPlayerMP entityPlayer, final FontRenderer fontRenderer, final float posX, final float posY, final float posZ, final float playerViewY, final float playerViewX, final boolean thirdPersonView) {
        final RenderEntityItem renderEntity = Wrapper.getMinecraft().getRenderManager().getEntityRenderMap().get(EntityItem.class);
        GL11.glPushMatrix();
        GlStateManager.translate(posX, posY, posZ);
        GlStateManager.rotate(-playerViewY, 0.0f, 1.0f, 0.0f);
        GlStateManager.disableLighting();
        GlStateManager.depthMask(false);
        GlStateManager.disableDepth();
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        final ArrayList<ItemStack> itemList = new ArrayList<ItemStack>();
        itemList.addAll(entityPlayer.inventory.armorInventory);
        itemList.add(entityPlayer.getHeldItemMainhand());
        itemList.add(entityPlayer.getHeldItemOffhand());
        int xIndex = 0;
        for (final ItemStack itemStack : itemList) {
            int yIndex = 0;
            if (itemStack != null) {
                final EntityItem entityItem = new EntityItem(Wrapper.getWorld(), posX, posY, posZ, itemStack);
                entityItem.hoverStart = 0.0f;
                GlStateManager.scale(-0.01f, -0.01f, 0.01f);
                for (final Map.Entry<Enchantment, Integer> enchantmentEntry : EnchantmentHelper.getEnchantments(itemStack).entrySet()) {
                    String displayName = enchantmentEntry.getKey().getTranslatedName(enchantmentEntry.getValue());
                    displayName = displayName.replaceAll("enchantment.level.[0-9]+", "");
                    displayName = displayName.substring(0, 4).toLowerCase() + ": " + ChatColor.GREEN + enchantmentEntry.getValue();
                    fontRenderer.drawString(displayName, 75 - xIndex * 50, -90 - (5 + 10 * yIndex), -1);
                    ++yIndex;
                }
                if (Item.getIdFromItem(itemStack.getItem()) != 0) {
                    GlStateManager.scale(2.0, 2.0, 2.0);
                    fontRenderer.drawString(itemStack.getStackSize() + "x", (80 - xIndex * 50 + 15) / 2, -10, -1);
                    GlStateManager.scale(0.5, 0.5, 0.5);
                }
                GlStateManager.scale(-100.0f, -100.0f, 100.0f);
                renderEntity.doRender(entityItem, -1.0 + xIndex * 0.5, 0.15, 0.0, Wrapper.getPlayer().rotationYaw, 0.0f);
                RenderHelper.disableStandardItemLighting();
            }
            ++xIndex;
        }
        GlStateManager.enableDepth();
        GlStateManager.depthMask(false);
        GlStateManager.enableLighting();
        GlStateManager.disableBlend();
        GL11.glPopMatrix();
    }
    
    @Override
    public void onEnable() {
        ModuleNameTags.isActive = true;
    }
    
    @Override
    public void onDisable() {
        ModuleNameTags.isActive = false;
    }
    
    public static boolean isActive() {
        return ModuleNameTags.isActive;
    }
}
