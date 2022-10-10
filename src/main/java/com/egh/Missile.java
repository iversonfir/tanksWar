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
        switch (direction)
        {
            case UP:
                return Utils.getImage("missileU.gif");
            case DOWN:
                return Utils.getImage("missileD.gif");
            case LEFT:
                return Utils.getImage("missileL.gif");
            case RIGHT:
                return Utils.getImage("missileR.gif");
            case UP_LEFT:
                return Utils.getImage("missileLU.gif");
            case UP_RIGHT:
                return Utils.getImage("missileRU.gif");
            case DOWN_LEFT:
                return Utils.getImage("missileLD.gif");
            case DOWN_RIGHT:
                return Utils.getImage("missileRD.gif");
        }
        return null;
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
            case UP_LEFT:
                y -= SPEED;
                x -= SPEED;
                break;
            case UP_RIGHT:
                y -= SPEED;
                x += SPEED;
                break;
            case DOWN_LEFT:
                y += SPEED;
                x -= SPEED;
                break;
            case DOWN_RIGHT:
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
