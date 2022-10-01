package com.egh;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

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

    public void move(KeyEvent e)
    {
        switch (e.getKeyCode())
        {
            case KeyEvent.VK_UP:
                y -= 5;
                direction = Direction.UP;
                break;
            case KeyEvent.VK_DOWN:
                y += 5;
                direction = Direction.DOWN;
                break;
            case KeyEvent.VK_LEFT:
                x -= 5;
                direction = Direction.LEFT;
                break;
            case KeyEvent.VK_RIGHT:
                x += 5;
                direction = Direction.RIGHT;
                break;
        }
    }

    public void draw(Graphics g)
    {
        g.drawImage(getImage(), x, y, null);
    }
}
