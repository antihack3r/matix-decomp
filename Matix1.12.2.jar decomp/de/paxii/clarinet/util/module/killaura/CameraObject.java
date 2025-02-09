// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.util.module.killaura;

import net.minecraft.entity.player.EntityPlayer;

class CameraObject
{
    private float cameraYaw;
    private float cameraPitch;
    private float cameraYawHead;
    
    public void saveCamera(final EntityPlayer p) {
        this.cameraYaw = p.rotationYaw;
        this.cameraPitch = p.rotationPitch;
        this.cameraYawHead = p.rotationYawHead;
    }
    
    public void restoreCamera(final EntityPlayer p) {
        p.rotationYaw = this.cameraYaw;
        p.rotationPitch = this.cameraPitch;
        p.rotationYawHead = this.cameraYawHead;
    }
}
