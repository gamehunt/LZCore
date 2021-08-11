package ru.j4fn.lizord.lzlib.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;

public class AbstractGuiTextBlock extends Gui implements IDrawable
{
    private String txt;
    private int    color;

    public void setText(String t){
        txt = t;
    }

    public void setColor(int c){
        color = c;
    }

    @Override
    public void draw(int x, int y, int mx, int my, int mouseX, int mouseY) {
        this.drawString(Minecraft.getMinecraft().fontRenderer, txt, x, y, color);
    }
}
