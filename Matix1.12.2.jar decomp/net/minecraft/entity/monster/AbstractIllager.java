// 
// Decompiled by Procyon v0.6.0
// 

package net.minecraft.entity.monster;

import net.minecraft.entity.Entity;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.world.World;
import net.minecraft.network.datasync.DataParameter;

public abstract class AbstractIllager extends EntityMob
{
    protected static final DataParameter<Byte> AGGRESSIVE;
    
    public AbstractIllager(final World p_i47509_1_) {
        super(p_i47509_1_);
    }
    
    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataManager.register(AbstractIllager.AGGRESSIVE, (Byte)0);
    }
    
    protected boolean isAggressive(final int p_193078_1_) {
        final int i = this.dataManager.get(AbstractIllager.AGGRESSIVE);
        return (i & p_193078_1_) != 0x0;
    }
    
    protected void setAggressive(final int p_193079_1_, final boolean p_193079_2_) {
        int i = this.dataManager.get(AbstractIllager.AGGRESSIVE);
        if (p_193079_2_) {
            i |= p_193079_1_;
        }
        else {
            i &= ~p_193079_1_;
        }
        this.dataManager.set(AbstractIllager.AGGRESSIVE, (byte)(i & 0xFF));
    }
    
    @Override
    public EnumCreatureAttribute getCreatureAttribute() {
        return EnumCreatureAttribute.ILLAGER;
    }
    
    public IllagerArmPose getArmPose() {
        return IllagerArmPose.CROSSED;
    }
    
    static {
        AGGRESSIVE = EntityDataManager.createKey(AbstractIllager.class, DataSerializers.BYTE);
    }
    
    public enum IllagerArmPose
    {
        CROSSED, 
        ATTACKING, 
        SPELLCASTING, 
        BOW_AND_ARROW;
    }
}
