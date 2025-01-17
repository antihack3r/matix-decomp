// 
// Decompiled by Procyon v0.6.0
// 

package net.minecraft.entity.boss.dragon.phase;

import org.apache.logging.log4j.LogManager;
import javax.annotation.Nullable;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.util.math.Vec3d;
import org.apache.logging.log4j.Logger;

public class PhaseChargingPlayer extends PhaseBase
{
    private static final Logger LOGGER;
    private Vec3d targetLocation;
    private int timeSinceCharge;
    
    public PhaseChargingPlayer(final EntityDragon dragonIn) {
        super(dragonIn);
    }
    
    @Override
    public void doLocalUpdate() {
        if (this.targetLocation == null) {
            PhaseChargingPlayer.LOGGER.warn("Aborting charge player as no target was set.");
            this.dragon.getPhaseManager().setPhase(PhaseList.HOLDING_PATTERN);
        }
        else if (this.timeSinceCharge > 0 && this.timeSinceCharge++ >= 10) {
            this.dragon.getPhaseManager().setPhase(PhaseList.HOLDING_PATTERN);
        }
        else {
            final double d0 = this.targetLocation.squareDistanceTo(this.dragon.posX, this.dragon.posY, this.dragon.posZ);
            if (d0 < 100.0 || d0 > 22500.0 || this.dragon.isCollidedHorizontally || this.dragon.isCollidedVertically) {
                ++this.timeSinceCharge;
            }
        }
    }
    
    @Override
    public void initPhase() {
        this.targetLocation = null;
        this.timeSinceCharge = 0;
    }
    
    public void setTarget(final Vec3d p_188668_1_) {
        this.targetLocation = p_188668_1_;
    }
    
    @Override
    public float getMaxRiseOrFall() {
        return 3.0f;
    }
    
    @Nullable
    @Override
    public Vec3d getTargetLocation() {
        return this.targetLocation;
    }
    
    @Override
    public PhaseList<PhaseChargingPlayer> getPhaseList() {
        return PhaseList.CHARGING_PLAYER;
    }
    
    static {
        LOGGER = LogManager.getLogger();
    }
}
