// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.util.module.killaura;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import de.paxii.clarinet.Wrapper;
import net.minecraft.entity.Entity;
import de.paxii.clarinet.util.settings.type.ClientSettingBoolean;
import de.paxii.clarinet.util.chat.Chat;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.ArrayList;
import de.paxii.clarinet.util.module.settings.ValueBase;
import de.paxii.clarinet.module.Module;

public class AuraManager
{
    private final Module module;
    private final ValueBase valueRange;
    private final ValueBase valueDelay;
    private final ValueBase valueAngle;
    private final TimeManager timeManager;
    private final ArrayList<Float> pitchMap;
    private final ArrayList<Float> yawMap;
    private final CameraObject cameraObject;
    private final Random random;
    private Map<Integer, Long> attackMap;
    
    public AuraManager(final Module module) {
        this.module = module;
        this.attackMap = new HashMap<Integer, Long>();
        this.cameraObject = new CameraObject();
        this.pitchMap = new ArrayList<Float>();
        this.yawMap = new ArrayList<Float>();
        this.random = new Random();
        this.timeManager = new TimeManager();
        this.valueRange = new ValueBase(module.getName() + " Range", 4.1f, 1.0f, 6.0f, "Range");
        this.valueDelay = new ValueBase(module.getName() + " Speed", 2.0f, 1.0f, 15.0f, "Speed") {
            @Override
            public void onUpdate(final float oldValue, final float newValue) {
                if (AuraManager.this.isAutoSpeed()) {
                    Chat.printClientMessage(String.format("Auto Speed is currently enabled for %s. Speed is not taken into account.", module.getName()));
                }
            }
        };
        this.valueAngle = new ValueBase(module.getName() + " Angle", 80.0f, 1.0f, 180.0f, "Angle");
        this.module.getModuleValues().put("valueRange", this.valueRange);
        this.module.getModuleValues().put("valueDelay", this.valueDelay);
        this.module.getModuleValues().put("valueAngle", this.valueAngle);
        this.module.getModuleSettings().put("animal", new ClientSettingBoolean("Animals", false));
        this.module.getModuleSettings().put("mob", new ClientSettingBoolean("Mobs", true));
        this.module.getModuleSettings().put("player", new ClientSettingBoolean("Players", true));
        this.module.getModuleSettings().put("silent", new ClientSettingBoolean("Silent", true));
        this.module.getModuleSettings().put("legit", new ClientSettingBoolean("Legit", true));
        this.module.getModuleSettings().put("invisible", new ClientSettingBoolean("Invisible", false));
        this.module.getModuleSettings().put("autoDelay", new ClientSettingBoolean("Auto Delay", false));
        for (float f = -10.0f; f <= 10.0f; f += 2.0f) {
            this.yawMap.add(f);
        }
        for (float f = -10.0f; f <= 50.0f; f += 5.0f) {
            this.pitchMap.add(f);
        }
    }
    
    public void addToAttackMap(final int entityId, final long last) {
        this.attackMap.put(entityId, last);
    }
    
    private long getLast(final Entity entity) {
        if (this.attackMap.get(entity.getEntityId()) == null) {
            return 0L;
        }
        return this.attackMap.get(entity.getEntityId());
    }
    
    public long getDelay() {
        return (long)(1000.0f / this.valueDelay.getValue());
    }
    
    public void setDelay(final float delay) {
        this.valueDelay.setValue(delay);
    }
    
    public boolean isDelayComplete(final TimeManager timeManager) {
        if (this.isAutoSpeed()) {
            return Wrapper.getPlayer().getCooledAttackStrength(0.0f) >= 1.0f;
        }
        return timeManager.sleep(this.getDelay());
    }
    
    public double getRange() {
        return this.valueRange.getValue();
    }
    
    public void setRange(final float range) {
        this.valueRange.setValue(range);
    }
    
    public float getAngle() {
        return this.valueAngle.getValue();
    }
    
    public Map<Integer, Long> getAttackMap() {
        return this.attackMap;
    }
    
    public void setAttackMap(final Map<Integer, Long> attackMap) {
        this.attackMap = attackMap;
    }
    
    public long convertToMilliseconds(final double delay) {
        return 1000L / (long)delay;
    }
    
    public void saveCamera(final EntityPlayer p) {
        this.cameraObject.saveCamera(p);
    }
    
    public void restoreCamera(final EntityPlayer p) {
        this.cameraObject.restoreCamera(p);
    }
    
    public float getRandomPitchModifier() {
        final int randomIndex = this.random.nextInt(this.pitchMap.size() - 1);
        return this.pitchMap.get(randomIndex);
    }
    
    public float getRandomYawModifier() {
        final int randomIndex = this.random.nextInt(this.yawMap.size() - 1);
        return this.yawMap.get(randomIndex);
    }
    
    public boolean isSilent() {
        return this.module.getValue("silent", Boolean.class);
    }
    
    public void setSilent(final boolean silent) {
        this.module.setValue("silent", silent);
    }
    
    public boolean isMob() {
        return this.module.getValue("mob", Boolean.class);
    }
    
    public void setMob(final boolean mob) {
        this.module.setValue("silent", mob);
    }
    
    public boolean isAnimal() {
        return this.module.getValue("animal", Boolean.class);
    }
    
    public void setAnimal(final boolean animal) {
        this.module.setValue("animal", animal);
    }
    
    public boolean isPlayer() {
        return this.module.getValue("player", Boolean.class);
    }
    
    public void setPlayer(final boolean player) {
        this.module.setValue("player", player);
    }
    
    public boolean isLegit() {
        return this.module.getValue("legit", Boolean.class);
    }
    
    public void setLegit(final boolean legit) {
        this.module.setValue("legit", legit);
    }
    
    public boolean isInvisible() {
        return this.module.getValue("invisible", Boolean.class);
    }
    
    public void setInvisible(final boolean invisible) {
        this.module.setValue("invisible", invisible);
    }
    
    public boolean isAutoSpeed() {
        return this.module.getValueOrDefault("autoDelay", Boolean.class, false);
    }
    
    public void setAutoSpeed(final boolean autoSpeed) {
        this.module.setValue("autoDelay", autoSpeed);
    }
    
    public void setAngles(final EntityLivingBase entityLiving, final EntityManager entityManager) {
        final float yaw = entityManager.getAngles(entityLiving)[0];
        final float pitch = entityManager.getAngles(entityLiving)[1];
        if (this.isLegit()) {
            this.timeManager.updateTimer();
            if (this.timeManager.sleep(200L)) {
                this.setPitch(pitch + (this.random.nextFloat() - 0.5f) * 50.0f);
                this.setYaw(yaw + (this.random.nextFloat() - 0.5f) * 10.0f);
                this.timeManager.updateLast();
            }
            else {
                this.setPitch(pitch);
                this.setYaw(yaw);
            }
        }
        else {
            this.setPitch(pitch);
            this.setYaw(yaw);
        }
    }
    
    private void setYaw(final float yaw) {
        Wrapper.getPlayer().rotationYaw = yaw;
        Wrapper.getPlayer().rotationYawHead = yaw;
    }
    
    private void setPitch(final float pitch) {
        Wrapper.getPlayer().rotationPitch = pitch;
    }
}
