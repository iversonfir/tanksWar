package com.egh;

import java.awt.*;

public class Missile
{
    private int x;
    private int y;
    private boolean isEnemy;
    private Direction direction;
    private final static int SPEED = 12;

    public Missile(int x, int y, boolean isEnemy, Direction direction)
    {
        this.x = x;
        this.y = y;
        this.isEnemy = isEnemy;
        this.direction = direction;
    }

    public Image getImage()
    {
        return direction.getImage("missile");
    }

    void move()
    {
        switch (direction)
        {
            case UP:
                y -= SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
            case LEFT:
                x -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            case LEFT_UP:
                y -= SPEED;
                x -= SPEED;
                break;
            case RIGHT_UP:
                y -= SPEED;
                x += SPEED;
                break;
            case LEFT_DOWN:
                y += SPEED;
                x -= SPEED;
                break;
            case RIGHT_DOWN:
                y += SPEED;
                x += SPEED;
                break;
        }
    }

    public void draw(Graphics g)
    {
        move();
        if (x > 800 || x < 0 || y < 0 || y > 600)
        {
            return;
        }
        g.drawImage(getImage(), x, y, null);
    }
}
