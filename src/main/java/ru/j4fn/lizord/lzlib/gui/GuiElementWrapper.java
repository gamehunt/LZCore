package ru.j4fn.lizord.lzlib.gui;

import com.sun.javaws.exceptions.InvalidArgumentException;
import net.minecraft.client.gui.Gui;

import java.util.ArrayList;

import static java.lang.Math.min;

public class GuiElementWrapper extends Gui implements IDrawable{
    private ArrayList<GuiElementWrapper> childs;

    private IDrawable base;

    private int posX, posY, sizeX, sizeY;

    private LayoutType layout;
    private int        gridSize;

    public GuiElementWrapper(IDrawable baz){
        childs = new ArrayList<>();
        base = baz;
        posX = 0;
        posY = 0;
        sizeX = 100;
        sizeY = 100;
        layout = LayoutType.None;
    }

    public void setLayout(LayoutType type, int grid) throws InvalidArgumentException {
        if(gridSize < 1){
            throw new InvalidArgumentException(new String[]{"gridSize < 1"});
        }
        layout = type;
        gridSize = grid;
    }

    public ArrayList<GuiElementWrapper> getChilds(){
        return childs;
    }
    public void addChild(GuiElementWrapper child){
        childs.add(child);
    }

    @Override
    public void draw(int base_x, int base_y, int mx, int my, int mouseX, int mouseY) {
        if(base != null) {
            base.draw(base_x + posX, base_y + posY, min(mx, base_x + posX + sizeX), min(my, base_y + posY + sizeY), mouseX, mouseY);
        }
        for(GuiElementWrapper wr : getChilds()){
            int finalPosX = 0,finalPosY = 0,finalLimitX = 0,finalLimitY = 0,limitXParentSize = 0,limitYParentSize = 0;
            switch(layout){
                case None:
                    finalPosX = base_x + posX;
                    finalPosY = base_y + posY;
                    break;
                case Vertical:
                    finalPosX = base_x + posX;
                    finalPosY = base_y + posY - wr.posY + gridSize * wr.posY;
                    break;
                case Horizontal:
                    finalPosX = base_x + posX - wr.posX + gridSize * wr.posX;
                    finalPosY = base_y + posY;
                    break;
            }

            limitXParentSize = finalPosX + sizeX;
            limitYParentSize = finalPosY + sizeY;

            finalLimitX = min(mx, limitXParentSize);
            finalLimitY = min(my, limitYParentSize);

            wr.draw(finalPosX, finalPosY, finalLimitX, finalLimitY, mouseX, mouseY);
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
