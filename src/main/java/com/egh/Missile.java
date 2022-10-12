package com.egh;

import java.awt.*;

public class Missile
{
    private int x;
    private int y;
    private boolean enemy;
    private Direction direction;
    private final static int SPEED = 12;
    private boolean live = true;

    public Missile(int x, int y, boolean enemy, Direction direction)
    {
        this.x = x;
        this.y = y;
        this.enemy = enemy;
        this.direction = direction;
    }

    public Image getImage()
    {
        return direction.getImage("missile");
    }

    void move()
    {
        x += direction.x * SPEED;
        y += direction.y * SPEED;
    }

    public void draw(Graphics g)
    {
        move();
        if (x > 800 || x < 0 || y < 0 || y > 600)
        {
            live = false;
            return;
        }

        Rectangle rectangle = getRectangle();
        for (Wall wall : GameClient.getInstance().getWalls())
        {
            if (rectangle.intersects(wall.getRectangle()))
            {
                live = false;
                return;
            }
        }

        if (enemy)
        {
            Tank tank = GameClient.getInstance().getTank();
            if (rectangle.intersects(tank.getRectangle()))
            {
                tank.setHp(tank.getHp() - 20);
                if (tank.getHp() <= 0)
                {
                    tank.setLive(false);
                }
                live = false;
            }
        }
        else
        {
            for (Tank tank : GameClient.getInstance().getEnemies())
            {
                if (rectangle.intersects(tank.getRectangle()))
                {
                    tank.setLive(false);
                    live = false;
                    break;
                }
            }
        }

        g.drawImage(getImage(), x, y, null);
    }

    private Rectangle getRectangle()
    {
        return new Rectangle(x, y, getImage().getWidth(null), getImage().getHeight(null));
    }

    public boolean isLive()
    {
        return live;
    }
}
