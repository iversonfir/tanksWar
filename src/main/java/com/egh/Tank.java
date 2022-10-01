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
            case UP_LEFT:
                return new ImageIcon("assets/images/tankLU.gif").getImage();
            case UP_RIGHT:
                return new ImageIcon("assets/images/tankRU.gif").getImage();
            case DOWN_LEFT:
                return new ImageIcon("assets/images/tankLD.gif").getImage();
            case DOWN_RIGHT:
                return new ImageIcon("assets/images/tankRD.gif").getImage();
        }
        return null;
    }

    public void draw(Graphics g)
    {
        determinedDirection();
        move();
        g.drawImage(getImage(), x, y, null);
    }

    private boolean up, down, left, right;
    private boolean stopped;


    public void keyPressed(KeyEvent e)
    {
        switch (e.getKeyCode())
        {
            case KeyEvent.VK_UP:
                up = true;
                break;
            case KeyEvent.VK_DOWN:
                down = true;
                break;
            case KeyEvent.VK_LEFT:
                left = true;
                break;
            case KeyEvent.VK_RIGHT:
                right = true;
                break;
        }
    }

    public void keyReleased(KeyEvent e)
    {
        switch (e.getKeyCode())
        {
            case KeyEvent.VK_UP:
                up = false;
                break;
            case KeyEvent.VK_DOWN:
                down = false;
                break;
            case KeyEvent.VK_LEFT:
                left = false;
                break;
            case KeyEvent.VK_RIGHT:
                right = false;
                break;
        }
    }

    void move()
    {
        if (stopped) return;
        switch (direction)
        {
            case UP:
                y -= 5;
                break;
            case DOWN:
                y += 5;
                break;
            case LEFT:
                x -= 5;
                break;
            case RIGHT:
                x += 5;
                break;
            case UP_LEFT:
                y -= 5;
                x -= 5;
                break;
            case UP_RIGHT:
                y -= 5;
                x += 5;
                break;
            case DOWN_LEFT:
                y += 5;
                x -= 5;
                break;
            case DOWN_RIGHT:
                y += 5;
                x += 5;
                break;
        }
    }

    private void determinedDirection()
    {
        if (!up && !down && !left && !right)
        {
            stopped = true;
        }
        else
        {
            if (up && !down && !left && !right) direction = Direction.UP;
            else if (!up && down && !left && !right) direction = Direction.DOWN;
            else if (!up && !down && left && !right) direction = Direction.LEFT;
            else if (!up && !down && !left && right) direction = Direction.RIGHT;
            else if (up && !down && left && !right) direction = Direction.UP_LEFT;
            else if (up && !down && !left && right) direction = Direction.UP_RIGHT;
            else if (!up && down && left && !right) direction = Direction.DOWN_LEFT;
            else if (!up && down && !left && right) direction = Direction.DOWN_RIGHT;

            stopped = false;
        }
    }
}
