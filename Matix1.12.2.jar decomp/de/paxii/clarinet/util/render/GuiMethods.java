// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.util.render;

import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.math.AxisAlignedBB;
import java.lang.reflect.Field;
import de.paxii.clarinet.Wrapper;
import org.lwjgl.opengl.GL11;
import net.minecraft.client.gui.Gui;

public class GuiMethods extends Gui
{
    public static void drawGradientBorderedRect(final double x, final double y, final double x2, final double y2, final float l1, final int col1, final int col2, final int col3) {
        final float f = (col1 >> 24 & 0xFF) / 255.0f;
        final float f2 = (col1 >> 16 & 0xFF) / 255.0f;
        final float f3 = (col1 >> 8 & 0xFF) / 255.0f;
        final float f4 = (col1 & 0xFF) / 255.0f;
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(2848);
        GL11.glDisable(3042);
        GL11.glPushMatrix();
        GL11.glColor4f(f2, f3, f4, f);
        GL11.glLineWidth(1.0f);
        GL11.glBegin(1);
        GL11.glVertex2d(x, y);
        GL11.glVertex2d(x, y2);
        GL11.glVertex2d(x2, y2);
        GL11.glVertex2d(x2, y);
        GL11.glVertex2d(x, y);
        GL11.glVertex2d(x2, y);
        GL11.glVertex2d(x, y2);
        GL11.glVertex2d(x2, y2);
        GL11.glEnd();
        GL11.glPopMatrix();
        drawGradientRect(x, y, x2, y2, col2, col3);
        GL11.glEnable(3042);
        GL11.glEnable(3553);
        GL11.glDisable(2848);
    }
    
    public static void drawGradientRect(final double x, final double y, final double x2, final double y2, final int col1, final int col2) {
        final float f = (col1 >> 24 & 0xFF) / 255.0f;
        final float f2 = (col1 >> 16 & 0xFF) / 255.0f;
        final float f3 = (col1 >> 8 & 0xFF) / 255.0f;
        final float f4 = (col1 & 0xFF) / 255.0f;
        final float f5 = (col2 >> 24 & 0xFF) / 255.0f;
        final float f6 = (col2 >> 16 & 0xFF) / 255.0f;
        final float f7 = (col2 >> 8 & 0xFF) / 255.0f;
        final float f8 = (col2 & 0xFF) / 255.0f;
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(2848);
        GL11.glShadeModel(7425);
        GL11.glPushMatrix();
        GL11.glBegin(7);
        GL11.glColor4f(f2, f3, f4, f);
        GL11.glVertex2d(x2, y);
        GL11.glVertex2d(x, y);
        GL11.glColor4f(f6, f7, f8, f5);
        GL11.glVertex2d(x, y2);
        GL11.glVertex2d(x2, y2);
        GL11.glEnd();
        GL11.glPopMatrix();
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glDisable(2848);
        GL11.glShadeModel(7424);
    }
    
    public static void drawRoundedRect(int x, int y, int x1, int y1, final int borderC, final int insideC) {
        x *= 2;
        x1 *= 2;
        y *= 2;
        y1 *= 2;
        GL11.glScalef(0.5f, 0.5f, 0.5f);
        Gui.drawVerticalLine(x, y + 3, y1 - 4, borderC);
        Gui.drawVerticalLine(x1 - 1, y + 3, y1 - 4, borderC);
        Gui.drawHorizontalLine(x + 4, x1 - 5, y, borderC);
        Gui.drawHorizontalLine(x + 4, x1 - 5, y1 - 1, borderC);
        Gui.drawVerticalLine(x + 1, y + 1, y + 4, borderC);
        Gui.drawHorizontalLine(x + 3, x + 2, y + 1, borderC);
        Gui.drawVerticalLine(x1 - 2, y + 1, y + 4, borderC);
        Gui.drawHorizontalLine(x1 - 4, x1 - 3, y + 1, borderC);
        Gui.drawVerticalLine(x + 1, y1 - 2, y1 - 5, borderC);
        Gui.drawHorizontalLine(x + 3, x + 2, y1 - 2, borderC);
        Gui.drawVerticalLine(x1 - 2, y1 - 2, y1 - 5, borderC);
        Gui.drawHorizontalLine(x1 - 3, x1 - 4, y1 - 2, borderC);
        drawRect(x + 2, y + 2, x1 - 2, y1 - 2, insideC);
        Gui.drawVerticalLine(x + 1, y + 3, y1 - 4, insideC);
        Gui.drawVerticalLine(x1 - 2, y + 3, y1 - 4, insideC);
        Gui.drawHorizontalLine(x + 4, x1 - 5, y + 1, insideC);
        Gui.drawHorizontalLine(x + 4, x1 - 5, y1 - 2, insideC);
        GL11.glScalef(2.0f, 2.0f, 2.0f);
    }
    
    public static void drawRect(final int x, final int y, final int x2, final int y2, final int col1) {
        final float f = (col1 >> 24 & 0xFF) / 255.0f;
        final float f2 = (col1 >> 16 & 0xFF) / 255.0f;
        final float f3 = (col1 >> 8 & 0xFF) / 255.0f;
        final float f4 = (col1 & 0xFF) / 255.0f;
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(2848);
        GL11.glPushMatrix();
        GL11.glColor4f(f2, f3, f4, f);
        GL11.glBegin(7);
        GL11.glVertex2d((double)x2, (double)y);
        GL11.glVertex2d((double)x, (double)y);
        GL11.glVertex2d((double)x, (double)y2);
        GL11.glVertex2d((double)x2, (double)y2);
        GL11.glEnd();
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glPopMatrix();
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glDisable(2848);
    }
    
    public static void drawBar(final int x, final int y, final int x2, final int y2, final float l1, final int col1, final int col2, final int col3, final int var, final boolean inverse) {
        final int height = (y2 - y) / 2;
        if (inverse) {
            drawRect(x, y, x2, y2, col1);
            drawRect(x2 - var * 4 - 2, y, x2, y + height, col2);
            drawRect(x2 - var * 4 - 2, y + height, x2, y2, col3);
        }
        else {
            drawRect(x, y, x2, y2, col1);
            drawRect(x, y, x + var * 4 + 2, y + height, col2);
            drawRect(x, y + height, x + var * 4 + 2, y2, col3);
        }
        final float f = (col1 >> 24 & 0xFF) / 255.0f;
        final float f2 = (col1 >> 16 & 0xFF) / 255.0f;
        final float f3 = (col1 >> 8 & 0xFF) / 255.0f;
        final float f4 = (col1 & 0xFF) / 255.0f;
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(2848);
        GL11.glPushMatrix();
        GL11.glColor4f(f2, f3, f4, f);
        GL11.glLineWidth(l1);
        GL11.glBegin(1);
        GL11.glVertex2d((double)x, (double)y);
        GL11.glVertex2d((double)x, (double)y2);
        GL11.glVertex2d((double)x2, (double)y2);
        GL11.glVertex2d((double)x2, (double)y);
        GL11.glVertex2d((double)x, (double)y);
        GL11.glVertex2d((double)x2, (double)y);
        GL11.glVertex2d((double)x, (double)y2);
        GL11.glVertex2d((double)x2, (double)y2);
        GL11.glEnd();
        GL11.glPopMatrix();
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glDisable(2848);
    }
    
    public static void drawBorderedRect(final int x, final int y, final int x2, final int y2, final float l1, final int col1, final int col2) {
        drawRect(x, y, x2, y2, col2);
        final float f = (col1 >> 24 & 0xFF) / 255.0f;
        final float f2 = (col1 >> 16 & 0xFF) / 255.0f;
        final float f3 = (col1 >> 8 & 0xFF) / 255.0f;
        final float f4 = (col1 & 0xFF) / 255.0f;
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(2848);
        GL11.glPushMatrix();
        GL11.glColor4f(f2, f3, f4, f);
        GL11.glLineWidth(l1);
        GL11.glBegin(1);
        GL11.glVertex2d((double)x, (double)y);
        GL11.glVertex2d((double)x, (double)y2);
        GL11.glVertex2d((double)x2, (double)y2);
        GL11.glVertex2d((double)x2, (double)y);
        GL11.glVertex2d((double)x, (double)y);
        GL11.glVertex2d((double)x2, (double)y);
        GL11.glVertex2d((double)x, (double)y2);
        GL11.glVertex2d((double)x2, (double)y2);
        GL11.glEnd();
        GL11.glPopMatrix();
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glDisable(2848);
    }
    
    public static void drawStringWithMaxWidth(final String s, final int x, final int y, final int c, final int maxWidth) {
        GL11.glPushMatrix();
        float scaleFactor = 1.0f;
        for (float f1 = 1.0f; Wrapper.getFontRenderer().getStringWidth(s) * f1 > maxWidth; f1 -= 0.01f) {
            scaleFactor = f1;
        }
        GL11.glScalef(scaleFactor, scaleFactor, scaleFactor);
        Wrapper.getFontRenderer().drawString(s, (int)(x * (1.0f / scaleFactor)), (int)(y * (1.0f / scaleFactor)), c);
        GL11.glPopMatrix();
    }
    
    public static void drawHollowBorderedRect(final int x, final int y, final int x2, final int y2, final float l1, final int col1) {
        final float f = (col1 >> 24 & 0xFF) / 255.0f;
        final float f2 = (col1 >> 16 & 0xFF) / 255.0f;
        final float f3 = (col1 >> 8 & 0xFF) / 255.0f;
        final float f4 = (col1 & 0xFF) / 255.0f;
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(2848);
        GL11.glPushMatrix();
        GL11.glColor4f(f2, f3, f4, f);
        GL11.glLineWidth(l1);
        GL11.glBegin(1);
        GL11.glVertex2d((double)x, (double)y);
        GL11.glVertex2d((double)x, (double)y2);
        GL11.glVertex2d((double)x2, (double)y2);
        GL11.glVertex2d((double)x2, (double)y);
        GL11.glVertex2d((double)x, (double)y);
        GL11.glVertex2d((double)x2, (double)y);
        GL11.glVertex2d((double)x, (double)y2);
        GL11.glVertex2d((double)x2, (double)y2);
        GL11.glEnd();
        GL11.glPopMatrix();
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glDisable(2848);
    }
    
    public static void drawVerticalLine(final int x, final int y, final int y2, final float l1, final int col1) {
        final float f = (col1 >> 24 & 0xFF) / 255.0f;
        final float f2 = (col1 >> 16 & 0xFF) / 255.0f;
        final float f3 = (col1 >> 8 & 0xFF) / 255.0f;
        final float f4 = (col1 & 0xFF) / 255.0f;
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(2848);
        GL11.glPushMatrix();
        GL11.glColor4f(f2, f3, f4, f);
        GL11.glLineWidth(l1);
        GL11.glBegin(1);
        GL11.glVertex2d((double)x, (double)y);
        GL11.glVertex2d((double)x, (double)y2);
        GL11.glEnd();
        GL11.glPopMatrix();
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glDisable(2848);
    }
    
    public static void drawHorizontalLine(final int x, final int x2, final int y, final float l1, final int col1) {
        final float f = (col1 >> 24 & 0xFF) / 255.0f;
        final float f2 = (col1 >> 16 & 0xFF) / 255.0f;
        final float f3 = (col1 >> 8 & 0xFF) / 255.0f;
        final float f4 = (col1 & 0xFF) / 255.0f;
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(2848);
        GL11.glPushMatrix();
        GL11.glColor4f(f2, f3, f4, f);
        GL11.glLineWidth(l1);
        GL11.glBegin(1);
        GL11.glVertex2d((double)x, (double)y);
        GL11.glVertex2d((double)x2, (double)y);
        GL11.glEnd();
        GL11.glPopMatrix();
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glDisable(2848);
    }
    
    public static void drawDiagonalLine(final int x, final int x2, final int y, final float l1, final int col1) {
        final float f = (col1 >> 24 & 0xFF) / 255.0f;
        final float f2 = (col1 >> 16 & 0xFF) / 255.0f;
        final float f3 = (col1 >> 8 & 0xFF) / 255.0f;
        final float f4 = (col1 & 0xFF) / 255.0f;
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(2848);
        GL11.glPushMatrix();
        GL11.glColor4f(f2, f3, f4, f);
        GL11.glLineWidth(l1);
        GL11.glBegin(1);
        GL11.glVertex2d((double)x, (double)y);
        GL11.glVertex2d((double)y, (double)x2);
        GL11.glEnd();
        GL11.glPopMatrix();
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glDisable(2848);
    }
    
    public static void drawCircle(final int xx, final int yy, final int radius, final int col) {
        final float f = (col >> 24 & 0xFF) / 255.0f;
        final float f2 = (col >> 16 & 0xFF) / 255.0f;
        final float f3 = (col >> 8 & 0xFF) / 255.0f;
        final float f4 = (col & 0xFF) / 255.0f;
        final int sections = 70;
        final double dAngle = 6.283185307179586 / sections;
        GL11.glPushMatrix();
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(2848);
        GL11.glShadeModel(7425);
        GL11.glBegin(2);
        for (int i = 0; i < sections; ++i) {
            final float x = (float)(radius * Math.cos(i * dAngle));
            final float y = (float)(radius * Math.sin(i * dAngle));
            GL11.glColor4f(f2, f3, f4, f);
            GL11.glVertex2f(xx + x, yy + y);
        }
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glEnd();
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glDisable(2848);
        GL11.glPopMatrix();
    }
    
    public static void drawFilledCircle(final int xx, final int yy, final float radius, final int col) {
        final float f = (col >> 24 & 0xFF) / 255.0f;
        final float f2 = (col >> 16 & 0xFF) / 255.0f;
        final float f3 = (col >> 8 & 0xFF) / 255.0f;
        final float f4 = (col & 0xFF) / 255.0f;
        final int sections = 50;
        final double dAngle = 6.283185307179586 / sections;
        GL11.glPushMatrix();
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(2848);
        GL11.glBegin(6);
        for (int i = 0; i < sections; ++i) {
            final float x = (float)(radius * Math.sin(i * dAngle));
            final float y = (float)(radius * Math.cos(i * dAngle));
            GL11.glColor4f(f2, f3, f4, f);
            GL11.glVertex2f(xx + x, yy + y);
        }
        GL11.glEnd();
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glDisable(2848);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glPopMatrix();
    }
    
    public static void drawTri(final int i, final int j, final int k) {
        GL11.glRotatef(180.0f, 0.0f, 0.0f, 1.0f);
        final float f = (k >> 24 & 0xFF) / 255.0f;
        final float f2 = (k >> 16 & 0xFF) / 255.0f;
        final float f3 = (k >> 8 & 0xFF) / 255.0f;
        final float f4 = (k & 0xFF) / 255.0f;
        GL11.glColor4f(f2, f3, f4, f);
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glEnable(2848);
        GL11.glBlendFunc(770, 771);
        GL11.glBegin(4);
        GL11.glVertex2d((double)i, (double)(j + 2));
        GL11.glVertex2d((double)(i + 2), (double)(j - 2));
        GL11.glVertex2d((double)(i - 2), (double)(j - 2));
        GL11.glEnd();
        GL11.glDisable(2848);
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glRotatef(-180.0f, 0.0f, 0.0f, 1.0f);
    }
    
    public static void drawFlagTri(final int i, final int j, final int k) {
        final float f = (k >> 24 & 0xFF) / 255.0f;
        final float f2 = (k >> 16 & 0xFF) / 255.0f;
        final float f3 = (k >> 8 & 0xFF) / 255.0f;
        final float f4 = (k & 0xFF) / 255.0f;
        GL11.glColor4f(f2, f3, f4, 255.0f);
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glEnable(2848);
        GL11.glBlendFunc(770, 771);
        GL11.glBegin(4);
        GL11.glVertex2d((double)i, (double)(j + 2));
        GL11.glVertex2d((double)i, (double)(j - 2));
        GL11.glVertex2d((double)(i - 2), (double)j);
        GL11.glEnd();
        GL11.glDisable(2848);
        GL11.glEnable(3553);
        GL11.glDisable(3042);
    }
    
    public static void drawRightTri(final int x, final int y, final int size, final int color) {
        final float f = (color >> 24 & 0xFF) / 255.0f;
        final float f2 = (color >> 16 & 0xFF) / 255.0f;
        final float f3 = (color >> 8 & 0xFF) / 255.0f;
        final float f4 = (color & 0xFF) / 255.0f;
        GL11.glPushMatrix();
        GL11.glColor4f(f2, f3, f4, 255.0f);
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glEnable(2848);
        GL11.glBlendFunc(770, 771);
        GL11.glBegin(4);
        GL11.glVertex2d((double)x, (double)y);
        GL11.glVertex2d((double)(x - size), (double)(y - size));
        GL11.glVertex2d((double)(x - size), (double)(y + size));
        GL11.glEnd();
        GL11.glDisable(2848);
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glRotatef(-180.0f, 0.0f, 0.0f, 1.0f);
        GL11.glPopMatrix();
    }
    
    public static void drawUpTri(final int x, final int y, final int size, final int color) {
        final float f = (color >> 24 & 0xFF) / 255.0f;
        final float f2 = (color >> 16 & 0xFF) / 255.0f;
        final float f3 = (color >> 8 & 0xFF) / 255.0f;
        final float f4 = (color & 0xFF) / 255.0f;
        GL11.glPushMatrix();
        GL11.glTranslatef((float)x, (float)y, 0.0f);
        GL11.glRotatef(270.0f, 0.0f, 0.0f, 1.0f);
        GL11.glColor4f(f2, f3, f4, 255.0f);
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glEnable(2848);
        GL11.glBlendFunc(770, 771);
        GL11.glBegin(4);
        GL11.glVertex2d(0.0, 0.0);
        GL11.glVertex2d((double)(-size), (double)(-size));
        GL11.glVertex2d((double)(-size), (double)size);
        GL11.glEnd();
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glDisable(2848);
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glPopMatrix();
    }
    
    public static void drawDownTri(final int x, final int y, final int size, final int color) {
        final float f = (color >> 24 & 0xFF) / 255.0f;
        final float f2 = (color >> 16 & 0xFF) / 255.0f;
        final float f3 = (color >> 8 & 0xFF) / 255.0f;
        final float f4 = (color & 0xFF) / 255.0f;
        GL11.glPushMatrix();
        GL11.glTranslatef((float)x, (float)y, 0.0f);
        GL11.glRotatef(90.0f, 0.0f, 0.0f, 1.0f);
        GL11.glColor4f(f2, f3, f4, 255.0f);
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glEnable(2848);
        GL11.glBlendFunc(770, 771);
        GL11.glBegin(4);
        GL11.glVertex2d(0.0, 0.0);
        GL11.glVertex2d((double)(-size), (double)(-size));
        GL11.glVertex2d((double)(-size), (double)size);
        GL11.glEnd();
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glDisable(2848);
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glPopMatrix();
    }
    
    public static void drawDownTri(final int i, final int j, final int k) {
        final float f = (k >> 24 & 0xFF) / 255.0f;
        final float f2 = (k >> 16 & 0xFF) / 255.0f;
        final float f3 = (k >> 8 & 0xFF) / 255.0f;
        final float f4 = (k & 0xFF) / 255.0f;
        GL11.glColor4f(f2, f3, f4, 255.0f);
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glEnable(2848);
        GL11.glBlendFunc(770, 771);
        GL11.glBegin(4);
        GL11.glVertex2d((double)i, (double)(j + 2));
        GL11.glVertex2d((double)(i + 2), (double)(j - 2));
        GL11.glVertex2d((double)(i - 2), (double)(j - 2));
        GL11.glEnd();
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glDisable(2848);
        GL11.glEnable(3553);
        GL11.glDisable(3042);
    }
    
    public static void drawDownLeft(final int i, final int j, final int k) {
        final float f = (k >> 24 & 0xFF) / 255.0f;
        final float f2 = (k >> 16 & 0xFF) / 255.0f;
        final float f3 = (k >> 8 & 0xFF) / 255.0f;
        final float f4 = (k & 0xFF) / 255.0f;
        GL11.glColor4f(f2, f3, f4, 255.0f);
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glEnable(2848);
        GL11.glBlendFunc(770, 771);
        GL11.glBegin(4);
        GL11.glVertex2d((double)(i - 2), (double)(j + 2));
        GL11.glVertex2d((double)(i + 2), (double)(j - 1));
        GL11.glVertex2d((double)(i - 2), (double)(j - 2));
        GL11.glEnd();
        GL11.glDisable(2848);
        GL11.glEnable(3553);
        GL11.glDisable(3042);
    }
    
    public static void drawDownRight(final int i, final int j, final int k) {
        final float f = (k >> 24 & 0xFF) / 255.0f;
        final float f2 = (k >> 16 & 0xFF) / 255.0f;
        final float f3 = (k >> 8 & 0xFF) / 255.0f;
        final float f4 = (k & 0xFF) / 255.0f;
        GL11.glColor4f(f2, f3, f4, 255.0f);
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glEnable(2848);
        GL11.glBlendFunc(770, 771);
        GL11.glBegin(4);
        GL11.glVertex2d((double)(i + 2), (double)(j + 2));
        GL11.glVertex2d((double)(i + 2), (double)(j - 2));
        GL11.glVertex2d((double)(i - 2), (double)(j - 1));
        GL11.glEnd();
        GL11.glDisable(2848);
        GL11.glEnable(3553);
        GL11.glDisable(3042);
    }
    
    public static void drawUpLeft(final int i, final int j, final int k) {
        final float f = (k >> 24 & 0xFF) / 255.0f;
        final float f2 = (k >> 16 & 0xFF) / 255.0f;
        final float f3 = (k >> 8 & 0xFF) / 255.0f;
        final float f4 = (k & 0xFF) / 255.0f;
        GL11.glColor4f(f2, f3, f4, 255.0f);
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glEnable(2848);
        GL11.glBlendFunc(770, 771);
        GL11.glBegin(4);
        GL11.glVertex2d((double)(i - 2), (double)(j + 2));
        GL11.glVertex2d((double)(i + 2), (double)(j - 1));
        GL11.glVertex2d((double)(i - 2), (double)(j - 2));
        GL11.glEnd();
        GL11.glDisable(2848);
        GL11.glEnable(3553);
        GL11.glDisable(3042);
    }
    
    public static void drawUpRight(final int i, final int j, final int k) {
        final float f = (k >> 24 & 0xFF) / 255.0f;
        final float f2 = (k >> 16 & 0xFF) / 255.0f;
        final float f3 = (k >> 8 & 0xFF) / 255.0f;
        final float f4 = (k & 0xFF) / 255.0f;
        GL11.glColor4f(f2, f3, f4, 255.0f);
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glEnable(2848);
        GL11.glBlendFunc(770, 771);
        GL11.glBegin(4);
        GL11.glVertex2d((double)(i - 1), (double)(j + 2));
        GL11.glVertex2d((double)(i + 1), (double)(j - 3));
        GL11.glVertex2d((double)(i - 3), (double)(j - 2));
        GL11.glEnd();
        GL11.glDisable(2848);
        GL11.glEnable(3553);
        GL11.glDisable(3042);
    }
    
    public static void drawUpTri(final int i, final int j, final int k) {
        final float f = (k >> 24 & 0xFF) / 255.0f;
        final float f2 = (k >> 16 & 0xFF) / 255.0f;
        final float f3 = (k >> 8 & 0xFF) / 255.0f;
        final float f4 = (k & 0xFF) / 255.0f;
        GL11.glColor4f(f2, f3, f4, 255.0f);
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glEnable(2848);
        GL11.glBlendFunc(770, 771);
        GL11.glBegin(4);
        GL11.glVertex2d((double)i, (double)j);
        GL11.glVertex2d((double)(i + 2), (double)(j + 4));
        GL11.glVertex2d((double)(i - 2), (double)(j + 4));
        GL11.glEnd();
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glDisable(2848);
        GL11.glEnable(3553);
        GL11.glDisable(3042);
    }
    
    public static void drawTriSight(final int i, final int j, final int k) {
        final float f = (k >> 24 & 0xFF) / 255.0f;
        final float f2 = (k >> 16 & 0xFF) / 255.0f;
        final float f3 = (k >> 8 & 0xFF) / 255.0f;
        final float f4 = (k & 0xFF) / 255.0f;
        GL11.glColor4f(f2, f3, f4, f);
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glEnable(2848);
        GL11.glBlendFunc(770, 771);
        GL11.glBegin(4);
        GL11.glVertex2d((double)i, (double)(j + 2));
        GL11.glVertex2d((double)(i + 4), (double)(j - 4));
        GL11.glVertex2d((double)(i - 4), (double)(j - 4));
        GL11.glEnd();
        GL11.glDisable(2848);
        GL11.glEnable(3553);
        GL11.glDisable(3042);
    }
    
    public static void drawFullCircle(final int i, final int j, final double d, final int k) {
        final float f = (k >> 24 & 0xFF) / 255.0f;
        final float f2 = (k >> 16 & 0xFF) / 255.0f;
        final float f3 = (k >> 8 & 0xFF) / 255.0f;
        final float f4 = (k & 0xFF) / 255.0f;
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glEnable(2848);
        GL11.glBlendFunc(770, 771);
        GL11.glColor4f(f2, f3, f4, f);
        GL11.glBegin(6);
        for (int l = 0; l <= 360; ++l) {
            final double d2 = Math.sin(l * 3.141592653589793 / 180.0) * d;
            final double d3 = Math.cos(l * 3.141592653589793 / 180.0) * d;
            GL11.glVertex2d(i + d2, j + d3);
        }
        GL11.glEnd();
        GL11.glDisable(2848);
        GL11.glEnable(3553);
        GL11.glDisable(3042);
    }
    
    public static void DrawCircle(final float f, final float f1, final float f2, final int i, final int j) {
        final float f3 = (j >> 24 & 0xFF) / 255.0f;
        final float f4 = (j >> 16 & 0xFF) / 255.0f;
        final float f5 = (j >> 8 & 0xFF) / 255.0f;
        final float f6 = (j & 0xFF) / 255.0f;
        final float f7 = (float)(6.283185307179586 / i);
        final float f8 = (float)Math.cos(f7);
        final float f9 = (float)Math.sin(f7);
        GL11.glColor4f(f4, f5, f6, f3);
        float f10 = f2;
        float f11 = 0.0f;
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glEnable(2848);
        GL11.glBlendFunc(770, 771);
        GL11.glBegin(2);
        for (int k = 0; k < i; ++k) {
            GL11.glVertex2f(f10 + f, f11 + f1);
            final float f12 = f10;
            f10 = f8 * f10 - f9 * f11;
            f11 = f9 * f12 + f8 * f11;
        }
        GL11.glEnd();
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glDisable(2848);
    }
    
    public static void drawBorderedRect(final int x, final int y, final int x1, final int y1, final int size, final int borderC, final int insideC) {
        Gui.drawVerticalLine(x, y, y1 - 1, borderC);
        Gui.drawVerticalLine(x1 - 1, y, y1 - 1, borderC);
        Gui.drawHorizontalLine(x, x1 - 1, y, borderC);
        Gui.drawHorizontalLine(x, x1 - 1, y1 - 1, borderC);
        drawRect(x + size, y + size, x1 - size, y1 - size, insideC);
    }
    
    public static void drawWBorderedRect(final int x, final int y, final int x1, final int y1, final int size, final int borderC, final int insideC) {
        Gui.drawVerticalLine(x, y, y1 - 1, borderC);
        Gui.drawVerticalLine(x1 - 1, y, y1 - 1, borderC);
        Gui.drawHorizontalLine(x + 1, x1 - 2, y, borderC);
        Gui.drawHorizontalLine(x + 1, x1 - 2, y1 - 1, borderC);
        drawRect(x + size, y + size, x1 - size, y1 - size, insideC);
    }
    
    public static void drawWHollowBorderedRect2(final int x, final int y, final int x1, final int y1, final int size, final int borderC) {
        Gui.drawVerticalLine(x, y, y1 - 1, borderC);
        Gui.drawVerticalLine(x1 - 1, y, y1 - 1, borderC);
        Gui.drawHorizontalLine(x + 1, x1 - 2, y, borderC);
        Gui.drawHorizontalLine(x + 1, x1 - 2, y1 - 1, borderC);
    }
    
    public static void drawMoreRoundedBorderedRect(final int x, final int y, final int x1, final int y1, final int size, final int borderC, final int insideC) {
        Gui.drawVerticalLine(x, y + 1, y1 - 2, borderC);
        Gui.drawVerticalLine(x1 - 1, y + 1, y1 - 2, borderC);
        Gui.drawHorizontalLine(x + 2, x1 - 3, y, borderC);
        Gui.drawHorizontalLine(x + 2, x1 - 3, y1 - 1, borderC);
        Gui.drawHorizontalLine(x + 1, x + 1, y + 1, borderC);
        Gui.drawHorizontalLine(x1 - 2, x1 - 2, y + 1, borderC);
        Gui.drawHorizontalLine(x1 - 2, x1 - 2, y1 - 2, borderC);
        Gui.drawHorizontalLine(x + 1, x + 1, y1 - 2, borderC);
        drawRect(x + size, y + size, x1 - size, y1 - size, insideC);
    }
    
    public static void drawHLine(float par1, float par2, final float par3, final int par4) {
        if (par2 < par1) {
            final float var5 = par1;
            par1 = par2;
            par2 = var5;
        }
        drawRect((int)par1, (int)par3, (int)par2 + 1, (int)par3 + 1, par4);
    }
    
    public void drawGradientRectWithOutline(final int xs, final int ys, final int xe, final int ye, final int c, final int c1, final int c2) {
        this.drawGradientRect(xs + 1, ys + 1, xe - 1, ye - 1, c1, c2);
        GL11.glPushMatrix();
        GL11.glScalef(0.5f, 0.5f, 0.5f);
        this.drawWHollowBorderedRect(xs * 2 + 1, ys * 2 + 1, xe * 2 - 1, ye * 2 - 1, 1, c);
        GL11.glPopMatrix();
    }
    
    public void drawGradientRectWithOutline(final int xs, final int ys, final int xe, final int ye, final int c, final int c1, final int c2, final int c3) {
        this.drawGradientRect(xs + 1, ys + 1, xe - 1, ye - 1, c1, c2);
        Gui.drawVerticalLine(xs + 1, ys, ye - 1, c3);
        Gui.drawHorizontalLine(xs + 1, xe - 2, ys + 1, c3);
        GL11.glPushMatrix();
        GL11.glScalef(0.5f, 0.5f, 0.5f);
        this.drawWHollowBorderedRect(xs * 2 + 1, ys * 2 + 1, xe * 2 - 1, ye * 2 - 1, 1, c);
        GL11.glPopMatrix();
    }
    
    public void drawSmallRect(final int x, final int y, final int x1, final int y1, final int CO1) {
        GL11.glPushMatrix();
        GL11.glScalef(0.5f, 0.5f, 0.5f);
        drawRect(x * 2, y * 2, x1 * 2, y1 * 2, CO1);
        GL11.glPopMatrix();
    }
    
    public void drawSmallWHollowBorderedRect(final int x, final int y, final int x1, final int y1, final int CO1) {
        GL11.glPushMatrix();
        GL11.glScalef(0.5f, 0.5f, 0.5f);
        this.drawWHollowBorderedRect(x * 2, y * 2, x1 * 2, y1 * 2, CO1);
        GL11.glPopMatrix();
    }
    
    public void drawWHollowBorderedRect(final int x, final int y, final int x1, final int y1, final int CO1) {
        Gui.drawVerticalLine(x, y, y1 - 1, CO1);
        Gui.drawVerticalLine(x1 - 1, y, y1 - 1, CO1);
        Gui.drawHorizontalLine(x + 1, x1 - 2, y, CO1);
        Gui.drawHorizontalLine(x + 1, x1 - 2, y1 - 1, CO1);
    }
    
    public void drawBarMethod(final int x, final int y, final int x1, final int y1, final int CO1, final int CO2, final int CO3) {
        this.drawSmallWBorderedBarRect(x, y, x1, y1, CO1, CO2, CO3);
    }
    
    public void drawSmallWBorderedBarRect(final int x, final int y, final int x1, final int y1, final int CO1, final int CO2, final int CO3) {
        GL11.glPushMatrix();
        GL11.glScalef(0.5f, 0.5f, 0.5f);
        this.drawWBorderedBarRect(x * 2, y * 2, x1 * 2, y1 * 2, CO1, CO2, CO3);
        GL11.glPopMatrix();
    }
    
    public void drawWBorderedBarRect(final int x, final int y, final int x1, final int y1, final int CO1, final int CO2, final int CO3) {
        drawRect(x + 1, y + 1, x1 - 1, y1 - 1, CO2);
        drawRect(x + 1, y + 1, x1 - 1, y1 - 6, CO3);
        Gui.drawVerticalLine(x, y, y1 - 1, CO1);
        Gui.drawVerticalLine(x1 - 1, y, y1 - 1, CO1);
        Gui.drawHorizontalLine(x + 1, x1 - 2, y, CO1);
        Gui.drawHorizontalLine(x + 1, x1 - 2, y1 - 1, CO1);
    }
    
    public void drawGradientRect2(final int x, final int y, final int x2, final int y2, final int col1, final int col2) {
        final float f = (col1 >> 24 & 0xFF) / 255.0f;
        final float f2 = (col1 >> 16 & 0xFF) / 255.0f;
        final float f3 = (col1 >> 8 & 0xFF) / 255.0f;
        final float f4 = (col1 & 0xFF) / 255.0f;
        final float f5 = (col2 >> 24 & 0xFF) / 255.0f;
        final float f6 = (col2 >> 16 & 0xFF) / 255.0f;
        final float f7 = (col2 >> 8 & 0xFF) / 255.0f;
        final float f8 = (col2 & 0xFF) / 255.0f;
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(2848);
        GL11.glShadeModel(7425);
        GL11.glPushMatrix();
        GL11.glBegin(7);
        GL11.glColor4f(f2, f3, f4, f);
        GL11.glVertex2d((double)x2, (double)y);
        GL11.glVertex2d((double)x, (double)y);
        GL11.glColor4f(f6, f7, f8, f5);
        GL11.glVertex2d((double)x, (double)y2);
        GL11.glVertex2d((double)x2, (double)y2);
        GL11.glEnd();
        GL11.glPopMatrix();
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glDisable(2848);
        GL11.glShadeModel(7424);
    }
    
    public void drawRoundedGradientRect(int x, int y, int x1, int y1, final int size, final int col1, final int col2, final int borderC) {
        x *= 2;
        y *= 2;
        x1 *= 2;
        y1 *= 2;
        GL11.glScalef(0.5f, 0.5f, 0.5f);
        Gui.drawVerticalLine(x, y + 1, y1 - 2, borderC);
        Gui.drawVerticalLine(x1 - 1, y + 1, y1 - 2, borderC);
        Gui.drawHorizontalLine(x + 2, x1 - 3, y, borderC);
        Gui.drawHorizontalLine(x + 2, x1 - 3, y1 - 1, borderC);
        Gui.drawHorizontalLine(x + 1, x + 1, y + 1, borderC);
        Gui.drawHorizontalLine(x1 - 2, x1 - 2, y + 1, borderC);
        Gui.drawHorizontalLine(x1 - 2, x1 - 2, y1 - 2, borderC);
        Gui.drawHorizontalLine(x + 1, x + 1, y1 - 2, borderC);
        final float f = (col1 >> 24 & 0xFF) / 255.0f;
        final float f2 = (col1 >> 16 & 0xFF) / 255.0f;
        final float f3 = (col1 >> 8 & 0xFF) / 255.0f;
        final float f4 = (col1 & 0xFF) / 255.0f;
        final float f5 = (col2 >> 24 & 0xFF) / 255.0f;
        final float f6 = (col2 >> 16 & 0xFF) / 255.0f;
        final float f7 = (col2 >> 8 & 0xFF) / 255.0f;
        final float f8 = (col2 & 0xFF) / 255.0f;
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(2848);
        GL11.glShadeModel(7425);
        GL11.glPushMatrix();
        GL11.glBegin(7);
        GL11.glColor4f(f2, f3, f4, f);
        GL11.glVertex2d((double)x1, (double)y);
        GL11.glVertex2d((double)x, (double)y);
        GL11.glColor4f(f6, f7, f8, f5);
        GL11.glVertex2d((double)x, (double)y1);
        GL11.glVertex2d((double)x1, (double)y1);
        GL11.glEnd();
        GL11.glPopMatrix();
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glDisable(2848);
        GL11.glShadeModel(7424);
        GL11.glScalef(2.0f, 2.0f, 2.0f);
    }
    
    public void drawGradientBorderedRect(final int x, final int y, final int x2, final int y2, final float l1, final int col1, final int col2, final int col3) {
        this.drawGradientRect(x, y, x2, y2, col2, col3);
        final float f = (col1 >> 24 & 0xFF) / 255.0f;
        final float f2 = (col1 >> 16 & 0xFF) / 255.0f;
        final float f3 = (col1 >> 8 & 0xFF) / 255.0f;
        final float f4 = (col1 & 0xFF) / 255.0f;
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(2848);
        GL11.glPushMatrix();
        GL11.glColor4f(f2, f3, f4, f);
        GL11.glLineWidth(l1);
        GL11.glBegin(1);
        GL11.glVertex2d((double)x, (double)y);
        GL11.glVertex2d((double)x, (double)y2);
        GL11.glVertex2d((double)x2, (double)y2);
        GL11.glVertex2d((double)x2, (double)y);
        GL11.glVertex2d((double)x, (double)y);
        GL11.glVertex2d((double)x2, (double)y);
        GL11.glVertex2d((double)x, (double)y2);
        GL11.glVertex2d((double)x2, (double)y2);
        GL11.glEnd();
        GL11.glPopMatrix();
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glDisable(2848);
    }
    
    public void drawWHollowBorderedRect(final int x, final int y, final int x1, final int y1, final int size, final int borderC) {
        Gui.drawVerticalLine(x, y + 1, y1 - 2, borderC);
        Gui.drawVerticalLine(x1 - 1, y + 1, y1 - 2, borderC);
        Gui.drawHorizontalLine(x + 2, x1 - 3, y, borderC);
        Gui.drawHorizontalLine(x + 2, x1 - 3, y1 - 1, borderC);
        Gui.drawHorizontalLine(x + 1, x + 1, y + 1, borderC);
        Gui.drawHorizontalLine(x1 - 2, x1 - 2, y + 1, borderC);
        Gui.drawHorizontalLine(x1 - 2, x1 - 2, y1 - 2, borderC);
        Gui.drawHorizontalLine(x + 1, x + 1, y1 - 2, borderC);
    }
    
    public void drawMoreRoundedBorderedRect2(int x, int y, int x1, int y1, final int size, final int borderC, final int insideC) {
        x *= 2;
        y *= 2;
        x1 *= 2;
        y1 *= 2;
        GL11.glScalef(0.5f, 0.5f, 0.5f);
        Gui.drawVerticalLine(x, y + 1, y1 - 2, borderC);
        Gui.drawVerticalLine(x1 - 1, y + 1, y1 - 2, borderC);
        Gui.drawHorizontalLine(x + 2, x1 - 3, y, borderC);
        Gui.drawHorizontalLine(x + 2, x1 - 3, y1 - 1, borderC);
        Gui.drawHorizontalLine(x + 1, x + 1, y + 1, borderC);
        Gui.drawHorizontalLine(x1 - 2, x1 - 2, y + 1, borderC);
        Gui.drawHorizontalLine(x1 - 2, x1 - 2, y1 - 2, borderC);
        Gui.drawHorizontalLine(x + 1, x + 1, y1 - 2, borderC);
        drawRect(x + size, y + size, x1 - size, y1 - size, insideC);
        GL11.glScalef(2.0f, 2.0f, 2.0f);
    }
    
    public void color(final int c) {
        final float f = (c >> 24 & 0xFF) / 255.0f;
        final float f2 = (c >> 16 & 0xFF) / 255.0f;
        final float f3 = (c >> 8 & 0xFF) / 255.0f;
        final float f4 = (c & 0xFF) / 255.0f;
        GL11.glColor4f(f2, f3, f4, f);
    }
    
    public void drawBorderedGradientRect(final int x, final int y, final int x1, final int y1, final int size, final int borderC, final int insideC1, final int insideC2) {
        Gui.drawVerticalLine(x, y, y1 - 1, borderC);
        Gui.drawVerticalLine(x1 - 1, y, y1 - 1, borderC);
        Gui.drawHorizontalLine(x, x1 - 1, y, borderC);
        Gui.drawHorizontalLine(x, x1 - 1, y1 - 1, borderC);
        this.drawGradientRect(x + size, y + size, x1 - size, y1 - size, insideC1, insideC2);
    }
    
    public void drawWBorderedGradientRect(final int x, final int y, final int x1, final int y1, final int size, final int borderC, final int insideC1, final int insideC2) {
        Gui.drawVerticalLine(x, y, y1 - 1, borderC);
        Gui.drawVerticalLine(x1 - 1, y, y1 - 1, borderC);
        Gui.drawHorizontalLine(x + 1, x1 - 2, y, borderC);
        Gui.drawHorizontalLine(x + 1, x1 - 2, y1 - 1, borderC);
        this.drawGradientRect(x + size, y + size, x1 - size, y1 - size, insideC1, insideC2);
    }
    
    public void drawBorderedRectWithString(final String s, final int x, final int y, final int x1, final int y1, final int size, final int borderC, final int insideC, final int SC) {
        Gui.drawVerticalLine(x, y, y1 - 1, borderC);
        Gui.drawVerticalLine(x1 - 1, y, y1 - 1, borderC);
        Gui.drawHorizontalLine(x, x1 - 1, y, borderC);
        Gui.drawHorizontalLine(x, x1 - 1, y1 - 1, borderC);
        drawRect(x + size, y + size, x1 - size, y1 - size, insideC);
        final int S1 = (x1 - x) / 2;
        final int S2 = x1 - S1 - Wrapper.getFontRenderer().getStringWidth(s) / 2;
        final int S3 = (y1 - y) / 2;
        final int S4 = y1 - S3;
        Wrapper.getFontRenderer().drawString(s, S2, S4 - 4, SC);
    }
    
    public void drawBorderedGradientRectWithString(final String s, final int x, final int y, final int x1, final int y1, final int size, final int borderC, final int insideC1, final int insideC2, final int SC) {
        Gui.drawVerticalLine(x, y, y1 - 1, borderC);
        Gui.drawVerticalLine(x1 - 1, y, y1 - 1, borderC);
        Gui.drawHorizontalLine(x, x1 - 1, y, borderC);
        Gui.drawHorizontalLine(x, x1 - 1, y1 - 1, borderC);
        this.drawGradientRect(x + size, y + size, x1 - size, y1 - size, insideC1, insideC2);
        final int S1 = (x1 - x) / 2;
        final int S2 = x1 - S1 - Wrapper.getFontRenderer().getStringWidth(s) / 2;
        final int S3 = (y1 - y) / 2;
        final int S4 = y1 - S3;
        Wrapper.getFontRenderer().drawString(s, S2, S4 - 4, SC);
    }
    
    public void drawRectGui(final String s, final int X, final int Y, final boolean F) {
        drawRect(X, Y, X + 80, Y + 12, 1124073472);
        drawRect(X + 80, Y, X + 86, Y + 12, F ? -117375232 : -100728832);
        Wrapper.getFontRenderer().drawString(s, X + 3, Y + 2, 16777215);
    }
    
    public void drawBorderedRectGui(final String s, final int X, final int Y, final boolean F) {
        drawBorderedRect(X, Y, X + 80, Y + 12, 1, -16777216, 1124073472);
        drawRect(X + 80, Y, X + 86, Y + 12, F ? -117375232 : -100728832);
        Wrapper.getFontRenderer().drawString(s, X + 3, Y + 2, 16777215);
    }
    
    public void drawBorderedRectGuiArraylist(final String s, final int X, final int Y) {
        drawBorderedRect(X, Y, X + 80, Y + 12, 1, -16777216, 1124073472);
        Wrapper.getFontRenderer().drawString(s, X + 3, Y + 2, 16777215);
    }
    
    public void drawSmallStringWithShadow(final String s, final int x, final int y, final int c) {
        GL11.glScalef(0.5f, 0.5f, 0.5f);
        Wrapper.getFontRenderer().drawString(s, x * 2, y * 2, c);
        GL11.glScalef(2.0f, 2.0f, 2.0f);
    }
    
    public void drawSmallString(final String s, final int x, final int y, final int c) {
        GL11.glScalef(0.5f, 0.5f, 0.5f);
        Wrapper.getFontRenderer().drawString(s, x * 2, y * 2, c);
        GL11.glScalef(2.0f, 2.0f, 2.0f);
    }
    
    public int getOpenGLVar(String name) {
        try {
            try {
                final Field f = GL11.class.getDeclaredField(name);
                return f.getInt(null);
            }
            catch (final NoSuchFieldException e) {
                final Field[] af = GL11.class.getDeclaredFields();
                if (!name.startsWith("GL_")) {
                    name = "GL_" + name;
                }
                for (final Field f2 : af) {
                    if (f2.getName().toLowerCase().replace("_", "").equals(name)) {
                        return f2.getInt(null);
                    }
                }
            }
        }
        catch (final IllegalAccessException e2) {
            e2.printStackTrace();
        }
        return -1;
    }
    
    public String getOpenGLVar(final int id) {
        final Field[] declaredFields;
        final Field[] af = declaredFields = GL11.class.getDeclaredFields();
        for (final Field f : declaredFields) {
            try {
                if (f.getInt(null) == id) {
                    return f.getName();
                }
            }
            catch (final IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    
    public void drawCircle(final int x, final int y, final double r, final int numseg, final int c) {
        final float f = (c >> 24 & 0xFF) / 255.0f;
        final float f2 = (c >> 16 & 0xFF) / 255.0f;
        final float f3 = (c >> 8 & 0xFF) / 255.0f;
        final float f4 = (c & 0xFF) / 255.0f;
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glEnable(2848);
        GL11.glBlendFunc(770, 771);
        GL11.glColor4f(f2, f3, f4, f);
        GL11.glBegin(2);
        for (int i = 0; i <= numseg; ++i) {
            final double x2 = Math.sin(i * 3.141592653589793 / (numseg / 2)) * r;
            final double y2 = Math.cos(i * 3.141592653589793 / (numseg / 2)) * r;
            GL11.glVertex2d(x + x2, y + y2);
        }
        GL11.glEnd();
        GL11.glDisable(2848);
        GL11.glEnable(3553);
        GL11.glDisable(3042);
    }
    
    public void drawFilledCircle(final int x, final int y, final double r, final int numseg, final int c) {
        final float f = (c >> 24 & 0xFF) / 255.0f;
        final float f2 = (c >> 16 & 0xFF) / 255.0f;
        final float f3 = (c >> 8 & 0xFF) / 255.0f;
        final float f4 = (c & 0xFF) / 255.0f;
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glEnable(2848);
        GL11.glBlendFunc(770, 771);
        GL11.glColor4f(f2, f3, f4, f);
        GL11.glBegin(6);
        for (int i = 0; i <= numseg; ++i) {
            final double x2 = Math.sin(i * 3.141592653589793 / (numseg / 2)) * r;
            final double y2 = Math.cos(i * 3.141592653589793 / (numseg / 2)) * r;
            GL11.glVertex2d(x + x2, y + y2);
        }
        GL11.glEnd();
        GL11.glDisable(2848);
        GL11.glEnable(3553);
        GL11.glDisable(3042);
    }
    
    public void drawTriangle(final int x, final int y, final double side, final int c) {
        final float f = (c >> 24 & 0xFF) / 255.0f;
        final float f2 = (c >> 16 & 0xFF) / 255.0f;
        final float f3 = (c >> 8 & 0xFF) / 255.0f;
        final float f4 = (c & 0xFF) / 255.0f;
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glEnable(2848);
        GL11.glBlendFunc(770, 771);
        GL11.glColor4f(f2, f3, f4, f);
        GL11.glBegin(6);
        GL11.glVertex2d(x + side / 2.0, (double)y);
        GL11.glVertex2d(x - side / 2.0, (double)y);
        final double a = Math.sqrt(side * side - side / 2.0 * (side / 2.0));
        GL11.glVertex2d((double)x, y + a);
        GL11.glEnd();
        GL11.glDisable(2848);
        GL11.glEnable(3553);
        GL11.glDisable(3042);
    }
    
    public void drawRect(final double x, final double y, final double d, final double e, final int color) {
        final float alpha = (color >> 24 & 0xFF) / 255.0f;
        final float red = (color >> 16 & 0xFF) / 255.0f;
        final float green = (color >> 8 & 0xFF) / 255.0f;
        final float blue = (color & 0xFF) / 255.0f;
        this.drawRect(x, y, d, e, red, green, blue, alpha);
    }
    
    public void drawRect(final double x, final double y, final double x1, final double y1, final float r, final float g, final float b, final float a) {
        this.start2dRendering();
        this.enableSmoothing();
        GL11.glColor4f(r, g, b, a);
        this.drawRect(x, y, x1, y1);
        this.end2dRendering();
        this.disableSmoothing();
    }
    
    public void drawRect(final double x, final double y, final double x1, final double y1) {
        GL11.glBegin(7);
        GL11.glVertex2d(x, y1);
        GL11.glVertex2d(x1, y1);
        GL11.glVertex2d(x1, y);
        GL11.glVertex2d(x, y);
        GL11.glEnd();
    }
    
    public void enableSmoothing() {
        GL11.glEnable(2848);
        GL11.glHint(3154, 4354);
    }
    
    public void disableSmoothing() {
        GL11.glDisable(2848);
    }
    
    public void start2dRendering() {
        GL11.glDisable(2929);
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
    }
    
    public void end2dRendering() {
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glEnable(2929);
    }
    
    public void drawOutlinedBox(final AxisAlignedBB box) {
        GL11.glBegin(3);
        GL11.glVertex3d(box.minX, box.minY, box.minZ);
        GL11.glVertex3d(box.maxX, box.minY, box.minZ);
        GL11.glVertex3d(box.maxX, box.minY, box.maxZ);
        GL11.glVertex3d(box.minX, box.minY, box.maxZ);
        GL11.glVertex3d(box.minX, box.minY, box.minZ);
        GL11.glEnd();
        GL11.glBegin(3);
        GL11.glVertex3d(box.minX, box.maxY, box.minZ);
        GL11.glVertex3d(box.maxX, box.maxY, box.minZ);
        GL11.glVertex3d(box.maxX, box.maxY, box.maxZ);
        GL11.glVertex3d(box.minX, box.maxY, box.maxZ);
        GL11.glVertex3d(box.minX, box.maxY, box.minZ);
        GL11.glEnd();
        GL11.glBegin(1);
        GL11.glVertex3d(box.minX, box.minY, box.minZ);
        GL11.glVertex3d(box.minX, box.maxY, box.minZ);
        GL11.glVertex3d(box.maxX, box.minY, box.minZ);
        GL11.glVertex3d(box.maxX, box.maxY, box.minZ);
        GL11.glVertex3d(box.maxX, box.minY, box.maxZ);
        GL11.glVertex3d(box.maxX, box.maxY, box.maxZ);
        GL11.glVertex3d(box.minX, box.minY, box.maxZ);
        GL11.glVertex3d(box.minX, box.maxY, box.maxZ);
        GL11.glEnd();
    }
    
    public static void drawTexturedModalRect(final int x, final int y, final int textureX, final int textureY, final int width, final int height, final double zLevel) {
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        final Tessellator tessellator = Tessellator.getInstance();
        final BufferBuilder bufferbuilder = tessellator.getBuffer();
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
        bufferbuilder.pos(x, y + height, zLevel).tex(textureX * 0.00390625f, (textureY + height) * 0.00390625f).endVertex();
        bufferbuilder.pos(x + width, y + height, zLevel).tex((textureX + width) * 0.00390625f, (textureY + height) * 0.00390625f).endVertex();
        bufferbuilder.pos(x + width, y, zLevel).tex((textureX + width) * 0.00390625f, textureY * 0.00390625f).endVertex();
        bufferbuilder.pos(x, y, zLevel).tex(textureX * 0.00390625f, textureY * 0.00390625f).endVertex();
        tessellator.draw();
    }
}
