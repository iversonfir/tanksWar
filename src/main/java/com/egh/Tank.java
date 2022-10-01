package com.egh;

import javax.swing.*;
import java.awt.*;

public class Tank
{
    private int x;
    private int y;
    private Direction direction;

    public Tank(int x, int y, Direction direction)
    {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public Image getImage()
    {
        switch (direction)
        {
            case UP:
                return new ImageIcon("assets/images/tankU.gif").getImage();
            case DOWN:
                return new ImageIcon("assets/images/tankD.gif").getImage();
            case LEFT:
                return new ImageIcon("assets/images/tankL.gif").getImage();
            case RIGHT:
                return new ImageIcon("assets/images/tankR.gif").getImage();
        }
        return null;
    }

    public int getX()
    {
        return x;
    }

    public void setX(int x)
    {
        this.x = x;
    }

    public int getY()
    {
        return y;
    }

    public void setY(int y)
    {
        this.y = y;
    }

    public void setDirection(Direction direction)
    {
        this.direction = direction;
    }
}
