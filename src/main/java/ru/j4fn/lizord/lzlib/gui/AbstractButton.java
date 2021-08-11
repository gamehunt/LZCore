package ru.j4fn.lizord.lzlib.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;

public class AbstractButton extends GuiButton implements IDrawable {
    public AbstractButton(int buttonId, String buttonText) {
        super(buttonId, 0, 0, 0, 0, buttonText);
    }

    @Override
    public void draw(int x, int y, int mx, int my, int msx, int msy) {
        this.x = x;
        this.y = y;
        this.width = mx - x;
        this.height = my - y;
        this.drawButton(Minecraft.getMinecraft(), msx, msy, 0f);
    }
}
