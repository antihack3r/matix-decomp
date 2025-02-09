// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.util.render;

import de.paxii.clarinet.Wrapper;
import org.lwjgl.opengl.GL11;

public class GL11Helper
{
    public static void drawBox(final double x, final double y, final double z, final double x2, final double y2, final double z2) {
        GL11.glBegin(7);
        GL11.glVertex3d(x, y, z);
        GL11.glVertex3d(x, y2, z);
        GL11.glVertex3d(x2, y, z);
        GL11.glVertex3d(x2, y2, z);
        GL11.glVertex3d(x2, y, z2);
        GL11.glVertex3d(x2, y2, z2);
        GL11.glVertex3d(x, y, z2);
        GL11.glVertex3d(x, y2, z2);
        GL11.glEnd();
        GL11.glBegin(7);
        GL11.glVertex3d(x2, y2, z);
        GL11.glVertex3d(x2, y, z);
        GL11.glVertex3d(x, y2, z);
        GL11.glVertex3d(x, y, z);
        GL11.glVertex3d(x, y2, z2);
        GL11.glVertex3d(x, y, z2);
        GL11.glVertex3d(x2, y2, z2);
        GL11.glVertex3d(x2, y, z2);
        GL11.glEnd();
        GL11.glBegin(7);
        GL11.glVertex3d(x, y2, z);
        GL11.glVertex3d(x2, y2, z);
        GL11.glVertex3d(x2, y2, z2);
        GL11.glVertex3d(x, y2, z2);
        GL11.glVertex3d(x, y2, z);
        GL11.glVertex3d(x, y2, z2);
        GL11.glVertex3d(x2, y2, z2);
        GL11.glVertex3d(x2, y2, z);
        GL11.glEnd();
        GL11.glBegin(7);
        GL11.glVertex3d(x, y, z);
        GL11.glVertex3d(x2, y, z);
        GL11.glVertex3d(x2, y, z2);
        GL11.glVertex3d(x, y, z2);
        GL11.glVertex3d(x, y, z);
        GL11.glVertex3d(x, y, z2);
        GL11.glVertex3d(x2, y, z2);
        GL11.glVertex3d(x2, y, z);
        GL11.glEnd();
        GL11.glBegin(7);
        GL11.glVertex3d(x, y, z);
        GL11.glVertex3d(x, y2, z);
        GL11.glVertex3d(x, y, z2);
        GL11.glVertex3d(x, y2, z2);
        GL11.glVertex3d(x2, y, z2);
        GL11.glVertex3d(x2, y2, z2);
        GL11.glVertex3d(x2, y, z);
        GL11.glVertex3d(x2, y2, z);
        GL11.glEnd();
        GL11.glBegin(7);
        GL11.glVertex3d(x, y2, z2);
        GL11.glVertex3d(x, y, z2);
        GL11.glVertex3d(x, y2, z);
        GL11.glVertex3d(x, y, z);
        GL11.glVertex3d(x2, y2, z);
        GL11.glVertex3d(x2, y, z);
        GL11.glVertex3d(x2, y2, z2);
        GL11.glVertex3d(x2, y, z2);
        GL11.glEnd();
    }
    
    public static void drawOutlinedBox(final double x, final double y, final double z, final double x2, final double y2, final double z2, final float l1) {
        GL11.glLineWidth(l1);
        GL11.glBegin(1);
        GL11.glVertex3d(x, y, z);
        GL11.glVertex3d(x, y2, z);
        GL11.glVertex3d(x2, y, z);
        GL11.glVertex3d(x2, y2, z);
        GL11.glVertex3d(x2, y, z2);
        GL11.glVertex3d(x2, y2, z2);
        GL11.glVertex3d(x, y, z2);
        GL11.glVertex3d(x, y2, z2);
        GL11.glEnd();
        GL11.glBegin(1);
        GL11.glVertex3d(x2, y2, z);
        GL11.glVertex3d(x2, y, z);
        GL11.glVertex3d(x, y2, z);
        GL11.glVertex3d(x, y, z);
        GL11.glVertex3d(x, y2, z2);
        GL11.glVertex3d(x, y, z2);
        GL11.glVertex3d(x2, y2, z2);
        GL11.glVertex3d(x2, y, z2);
        GL11.glEnd();
        GL11.glBegin(1);
        GL11.glVertex3d(x, y2, z);
        GL11.glVertex3d(x2, y2, z);
        GL11.glVertex3d(x2, y2, z2);
        GL11.glVertex3d(x, y2, z2);
        GL11.glVertex3d(x, y2, z);
        GL11.glVertex3d(x, y2, z2);
        GL11.glVertex3d(x2, y2, z2);
        GL11.glVertex3d(x2, y2, z);
        GL11.glEnd();
        GL11.glBegin(1);
        GL11.glVertex3d(x, y, z);
        GL11.glVertex3d(x2, y, z);
        GL11.glVertex3d(x2, y, z2);
        GL11.glVertex3d(x, y, z2);
        GL11.glVertex3d(x, y, z);
        GL11.glVertex3d(x, y, z2);
        GL11.glVertex3d(x2, y, z2);
        GL11.glVertex3d(x2, y, z);
        GL11.glEnd();
        GL11.glBegin(1);
        GL11.glVertex3d(x, y, z);
        GL11.glVertex3d(x, y2, z);
        GL11.glVertex3d(x, y, z2);
        GL11.glVertex3d(x, y2, z2);
        GL11.glVertex3d(x2, y, z2);
        GL11.glVertex3d(x2, y2, z2);
        GL11.glVertex3d(x2, y, z);
        GL11.glVertex3d(x2, y2, z);
        GL11.glEnd();
        GL11.glBegin(1);
        GL11.glVertex3d(x, y2, z2);
        GL11.glVertex3d(x, y, z2);
        GL11.glVertex3d(x, y2, z);
        GL11.glVertex3d(x, y, z);
        GL11.glVertex3d(x2, y2, z);
        GL11.glVertex3d(x2, y, z);
        GL11.glVertex3d(x2, y2, z2);
        GL11.glVertex3d(x2, y, z2);
        GL11.glEnd();
    }
    
    public static void enableDefaults() {
        Wrapper.getRenderer().disableLightmap();
        GL11.glEnable(3042);
        GL11.glDisable(2929);
        GL11.glEnable(2848);
        GL11.glDisable(3553);
        GL11.glHint(3154, 4354);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(32925);
        GL11.glEnable(32926);
        GL11.glShadeModel(7425);
        GL11.glLineWidth(1.8f);
        GL11.glEnable(3042);
        GL11.glDisable(2929);
        GL11.glEnable(2848);
        GL11.glDisable(3553);
        GL11.glHint(3154, 4354);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(32925);
        GL11.glEnable(32926);
        GL11.glShadeModel(7425);
        GL11.glDepthMask(false);
    }
    
    public static void disableDefaults() {
        GL11.glDisable(3042);
        GL11.glEnable(3553);
        GL11.glDisable(2848);
        GL11.glEnable(2929);
        GL11.glDisable(32925);
        GL11.glDisable(32926);
        GL11.glDepthMask(true);
        GL11.glDisable(32926);
        GL11.glDisable(32925);
        GL11.glEnable(3553);
        GL11.glDisable(2848);
        GL11.glEnable(2929);
        GL11.glDisable(3042);
        Wrapper.getRenderer().enableLightmap();
    }
    
    public static void drawBox(final double x, final double y, final double z, final float width) {
        GL11.glPushMatrix();
        GL11.glTranslated(0.0, 0.0, (double)(-width));
        drawRect(-width, 1.0f, width, 0.0f);
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        GL11.glTranslated(0.0, 0.0, (double)width);
        drawRect(-width, 1.0f, width, 0.0f);
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        GL11.glTranslated((double)width, 0.0, 0.0);
        GL11.glRotatef(90.0f, 0.0f, 1.0f, 0.0f);
        drawRect(-width, 1.0f, width, 0.0f);
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        GL11.glTranslated((double)(-width), 0.0, 0.0);
        GL11.glRotatef(90.0f, 0.0f, 1.0f, 0.0f);
        drawRect(-width, 1.0f, width, 0.0f);
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        GL11.glTranslated(0.0, 0.0, (double)(-width));
        GL11.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
        drawRect(-width, width * 2.0f, width, 0.0f);
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        GL11.glTranslated(0.0, 1.0, (double)(-width));
        GL11.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
        drawRect(-width, width * 2.0f, width, 0.0f);
        GL11.glPopMatrix();
    }
    
    public static void drawRect(final float x, final float y, final float x1, final float y1) {
        GL11.glBegin(7);
        GL11.glVertex2f(x, y);
        GL11.glVertex2f(x1, y);
        GL11.glVertex2f(x1, y1);
        GL11.glVertex2f(x, y1);
        GL11.glEnd();
        GL11.glBegin(7);
        GL11.glVertex2f(x, y1);
        GL11.glVertex2f(x1, y1);
        GL11.glVertex2f(x1, y);
        GL11.glVertex2f(x, y);
        GL11.glEnd();
    }
}
