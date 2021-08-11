package ru.j4fn.lizord.lzlib.gui;

import net.minecraft.client.gui.Gui;

import java.util.ArrayList;

import static java.lang.Math.min;

public class GuiElementWrapper extends Gui implements IDrawable{
    private ArrayList<GuiElementWrapper> childs;

    private IDrawable base;

    private int posX, posY, sizeX, sizeY;

    public GuiElementWrapper(IDrawable baz){
        childs = new ArrayList<>();
        base = baz;
        posX = 0;
        posY = 0;
        sizeX = 100;
        sizeY = 100;
    }

    public ArrayList<GuiElementWrapper> getChilds(){
        return childs;
    }
    public void addChild(GuiElementWrapper child){
        childs.add(child);
    }

    @Override
    public void draw(int base_x, int base_y, int mx, int my, int mouseX, int mouseY) {
        if(mx > base_x + posX){
            mx = base_x + posX;
        }
        if(my > base_y + posY){
            my = base_y + posY;
        }
        if(base != null) {
            base.draw(base_x + posX, base_y + posY, min(mx, sizeX), min(my, sizeY), mouseX, mouseY);
        }
        for(GuiElementWrapper wr : getChilds()){
            wr.draw(base_x + posX, base_y + posY, mx, my, mouseX, mouseY);
        }
    }

    public void move(int x, int y){
        posX = x;
        posY = y;
    }

    public int getX(){
        return posX;
    }

    public int posY(){
        return posY;
    }

    public void setSize(int w, int h){
        sizeX = w;
        sizeY = h;
    }

    public int getWidth(){
        return sizeX;
    }

    public int getHeight(){
        return sizeY;
    }
}
