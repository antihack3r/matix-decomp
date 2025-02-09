// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.module.combat;

import de.paxii.clarinet.util.settings.ClientSettings;
import de.paxii.clarinet.util.chat.Chat;
import net.minecraft.util.math.AxisAlignedBB;
import java.util.Iterator;
import java.util.List;
import net.minecraft.util.math.Vec3d;
import net.minecraft.src.Reflector;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import net.minecraft.util.EntitySelectors;
import de.paxii.clarinet.util.player.PlayerUtils;
import org.lwjgl.input.Mouse;
import de.paxii.clarinet.event.EventHandler;
import net.minecraft.entity.Entity;
import net.minecraft.util.EnumHand;
import net.minecraft.entity.player.EntityPlayer;
import de.paxii.clarinet.Wrapper;
import de.paxii.clarinet.event.events.game.IngameTickEvent;
import de.paxii.clarinet.gui.ingame.panel.element.PanelElement;
import de.paxii.clarinet.util.settings.type.ClientSettingBoolean;
import de.paxii.clarinet.util.settings.type.ClientSettingInteger;
import de.paxii.clarinet.util.module.settings.ValueBase;
import org.lwjgl.input.Keyboard;
import de.paxii.clarinet.module.ModuleCategory;
import java.util.function.Function;
import de.paxii.clarinet.gui.ingame.panel.element.elements.PanelKeyBindButton;
import net.minecraft.util.math.RayTraceResult;
import de.paxii.clarinet.util.module.killaura.TimeManager;
import de.paxii.clarinet.module.Module;

public class ModuleTriggerbot extends Module
{
    private final TimeManager timeManager;
    private RayTraceResult objectMouseOver;
    private final PanelKeyBindButton triggerKeyButton;
    private final Function<Integer, String> triggerKeyCaption;
    
    public ModuleTriggerbot() {
        super("Triggerbot", ModuleCategory.COMBAT);
        this.triggerKeyCaption = (Function<Integer, String>)(keyCode -> {
            new StringBuilder().append("Triggerkey: ");
            String str;
            if (keyCode != -1) {
                if (keyCode < -1) {
                    str = String.format("Mouse %d", keyCode + 100);
                }
                else {
                    str = Keyboard.getKeyName((int)keyCode);
                }
            }
            else {
                str = "None";
            }
            final StringBuilder sb;
            return sb.append(str).toString();
        });
        this.setCommand(true);
        this.setRegistered(true);
        this.setDescription("Automatically hits entities when you hover over them while holding the trigger key (default is left alt)");
        this.setSyntax("triggerbot <autospeed/key> <true/false/code>");
        this.getModuleValues().put("clickSpeed", new ValueBase("Triggerbot Speed", 8.2f, 1.0f, 20.0f, "Speed"));
        this.getModuleValues().put("clickRange", new ValueBase("Triggerbot Range", 4.5f, 1.0f, 6.6f, "Range"));
        this.getModuleValues().put("randomness", new ValueBase(String.format("%s Random", this.getName()), 50.0f, 1.0f, 250.0f, true, "Randomness"));
        this.getModuleSettings().put("triggerKey", new ClientSettingInteger("Trigger Key", 56));
        this.getModuleSettings().put("ignoreHitboxes", new ClientSettingBoolean("Ignore Hitboxes", false));
        this.getModuleSettings().put("autoDelay", new ClientSettingBoolean("Auto Delay", false));
        this.addPanelElement(this.triggerKeyButton = new PanelKeyBindButton((String)this.triggerKeyCaption.apply(this.getTriggerKey()), (keyCode, self) -> {
            if (keyCode == 1) {
                keyCode = -1;
            }
            this.setTriggerKey(keyCode);
            self.setCaption(this.triggerKeyCaption.apply(keyCode));
            return Boolean.valueOf(false);
        }));
        (this.timeManager = new TimeManager()).setRandom(true);
    }
    
    @Override
    public void onStartup() {
        this.triggerKeyButton.setCaption(this.triggerKeyCaption.apply(this.getTriggerKey()));
    }
    
    @EventHandler
    public void onTick(final IngameTickEvent event) {
        if (this.isKeyDown()) {
            this.getMouseOver(Wrapper.getMinecraft().getTimer().renderPartialTicks);
            this.timeManager.updateTimer((int)this.getModuleValues().get("randomness").getValue());
            Entity pointedEntity = null;
            if (this.objectMouseOver != null && this.objectMouseOver.entityHit != null) {
                pointedEntity = this.objectMouseOver.entityHit;
            }
            if (((this.getValue("ignoreHitboxes", Boolean.class) && pointedEntity == null) || this.shouldAttack(pointedEntity)) && this.isDelayComplete()) {
                if (pointedEntity != null) {
                    Wrapper.getMinecraft().playerController.attackEntity(Wrapper.getPlayer(), pointedEntity);
                }
                Wrapper.getPlayer().swingArm(EnumHand.MAIN_HAND);
                this.timeManager.updateLast();
            }
        }
    }
    
    private boolean isDelayComplete() {
        if (this.getValueOrDefault("autoDelay", Boolean.class, false)) {
            return Wrapper.getPlayer().getCooledAttackStrength(0.0f) >= 1.0f;
        }
        return this.timeManager.sleep((long)(1000.0f / this.getModuleValues().get("clickSpeed").getValue()));
    }
    
    private void setTriggerKey(final int triggerKey) {
        this.setValue("triggerKey", triggerKey);
    }
    
    private int getTriggerKey() {
        return this.getValueOrDefault("triggerKey", Integer.class, 56);
    }
    
    private boolean isKeyDown() {
        final int triggerKey = this.getTriggerKey();
        return (triggerKey > 0) ? Keyboard.isKeyDown(triggerKey) : Mouse.isButtonDown(triggerKey + 100);
    }
    
    private boolean shouldAttack(final Entity entity) {
        return !entity.isDead && Wrapper.getPlayer().getDistanceToEntity(entity) <= this.getModuleValues().get("clickRange").getValue() && PlayerUtils.canEntityBeSeen(Wrapper.getPlayer(), entity) && !Wrapper.getFriendManager().isFriend(entity.getName());
    }
    
    private void getMouseOver(final float partialTicks) {
        final Entity entity = Wrapper.getMinecraft().getRenderViewEntity();
        if (entity != null && Wrapper.getWorld() != null) {
            final double d0 = this.getModuleValues().get("clickRange").getValue();
            this.objectMouseOver = entity.rayTrace(d0, partialTicks);
            double d2 = d0;
            final Vec3d vec3d = entity.getPositionEyes(partialTicks);
            if (this.objectMouseOver != null) {
                d2 = this.objectMouseOver.hitVec.distanceTo(vec3d);
            }
            final Vec3d vec3d2 = entity.getLook(partialTicks);
            final Vec3d vec3d3 = vec3d.addVector(vec3d2.x * d0, vec3d2.y * d0, vec3d2.z * d0);
            Entity pointedEntity = null;
            Vec3d vec3d4 = null;
            final float f = 1.0f;
            final List<Entity> list = Wrapper.getWorld().getEntitiesInAABBexcluding(entity, entity.getEntityBoundingBox().expand(vec3d2.x * d0, vec3d2.y * d0, vec3d2.z * d0).expand(f, f, f), (Predicate<? super Entity>)Predicates.and((Predicate)EntitySelectors.NOT_SPECTATING, e -> e != null && e.canBeCollidedWith()));
            double d3 = d2;
            for (final Entity entity2 : list) {
                final AxisAlignedBB axisalignedbb = entity2.getEntityBoundingBox().grow(entity2.getCollisionBorderSize());
                final RayTraceResult raytraceresult = axisalignedbb.calculateIntercept(vec3d, vec3d3);
                if (axisalignedbb.contains(vec3d)) {
                    if (d3 < 0.0) {
                        continue;
                    }
                    pointedEntity = entity2;
                    vec3d4 = ((raytraceresult == null) ? vec3d : raytraceresult.hitVec);
                    d3 = 0.0;
                }
                else {
                    if (raytraceresult == null) {
                        continue;
                    }
                    final double d4 = vec3d.distanceTo(raytraceresult.hitVec);
                    if (d4 >= d3 && d3 != 0.0) {
                        continue;
                    }
                    boolean flag1 = false;
                    if (Reflector.ForgeEntity_canRiderInteract.exists()) {
                        flag1 = Reflector.callBoolean(entity2, Reflector.ForgeEntity_canRiderInteract, new Object[0]);
                    }
                    if (!flag1 && entity2.getLowestRidingEntity() == entity.getLowestRidingEntity()) {
                        if (d3 != 0.0) {
                            continue;
                        }
                        pointedEntity = entity2;
                        vec3d4 = raytraceresult.hitVec;
                    }
                    else {
                        pointedEntity = entity2;
                        vec3d4 = raytraceresult.hitVec;
                        d3 = d4;
                    }
                }
            }
            if (pointedEntity != null && (d3 < d2 || this.objectMouseOver == null)) {
                this.objectMouseOver = new RayTraceResult(pointedEntity, vec3d4);
            }
        }
    }
    
    @Override
    public void onCommand(final String[] args) {
        if (args.length >= 2) {
            if (args[0].equalsIgnoreCase("autospeed")) {
                try {
                    final boolean autoSpeed = Boolean.parseBoolean(args[1]);
                    this.setValue("autoDelay", autoSpeed);
                    Chat.printClientMessage("TriggerBot auto speed mode has been set to " + autoSpeed + ".");
                }
                catch (final Exception e) {
                    Chat.printClientMessage("Invalid argument!");
                }
            }
            else if (args[0].equalsIgnoreCase("key")) {
                try {
                    final int keyCode = Integer.parseInt(args[1]);
                    this.setValue("triggerKey", keyCode);
                    Chat.printClientMessage(String.format("TriggerBot Key has been set to %s.", (keyCode >= 0) ? Keyboard.getKeyName(keyCode) : Mouse.getButtonName(keyCode + 100)));
                }
                catch (final NumberFormatException exception) {
                    Chat.printClientMessage(String.format("\"%s\" is not a valid tigger key.", args[1]));
                    Chat.printChatMessage("Use lwjgl key codes (substract 100 for mouse keys. eg. -96 for MOUSE4)");
                }
            }
            else {
                Chat.printClientMessage("Invalid subcommand! Use \"" + ClientSettings.getValue("client.prefix", String.class) + "help triggerbot\"");
            }
        }
        else {
            Chat.printClientMessage("Too few arguments!");
        }
    }
}
