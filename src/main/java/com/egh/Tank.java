package com.egh;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Tank
{
    private int x;
    private int y;
    private Direction direction;
    private final boolean enemy;
    private final static int SPEED = 5;
    private boolean live = true;
    private int hp = 100;

    public Tank(int x, int y, Direction direction)
    {
        this(x, y, direction, false);
    }

    public Tank(int x, int y, Direction direction, boolean enemy)
    {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.enemy = enemy;
    }

    public Image getImage()
    {
        String prefix = enemy ? "e" : "";
        return direction.getImage(prefix + "tank");
    }

    public void draw(Graphics g)
    {
        int oldX = x, oldY = y;
        determinedDirection();
        move();

        int tankWidth = getImage().getWidth(null);
        int tankHeight = getImage().getHeight(null);

        if (x < 0) x = 0;
        if (x > 800 - tankWidth)
        {
            x = 800 - tankWidth;
        }

        if (y < 0) y = 0;

        if (y > 600 - tankHeight)
        {
            y = 600 - tankHeight;
        }

        Rectangle rectangle = getRectangle();
        for (Wall wall : GameClient.getInstance().getWalls())
        {
            if (rectangle.intersects(wall.getRectangle()))
            {
                y = oldY;
                x = oldX;
                break;
            }
        }

        for (Tank tank : GameClient.getInstance().getEnemies())
        {
            if (rectangle.intersects(tank.getRectangle()))
            {
                y = oldY;
                x = oldX;
                break;
            }
        }

        g.drawImage(getImage(), x, y, null);
    }

    Rectangle getRectangle()
    {
        return new Rectangle(x, y, getImage().getWidth(null), getImage().getHeight(null));
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
            case KeyEvent.VK_CONTROL:
                fire();
                break;
            case KeyEvent.VK_A:
                superFire();
                break;
        }
    }

    private void superFire()
    {
        for (Direction direction : Direction.values())
        {
            Missile missile = new Missile(
                    x + getImage().getWidth(null) / 2 - 6,
                    y + getImage().getHeight(null) / 2 - 6,
                    enemy, direction);
            GameClient.getInstance().add(missile);
        }
    }

    private void fire()
    {
        Missile missile = new Missile(
                x + getImage().getWidth(null) / 2 - 6,
                y + getImage().getHeight(null) / 2 - 6,
                enemy, direction);
        GameClient.getInstance().add(missile);
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
        x += direction.x * SPEED;
        y += direction.y * SPEED;
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
            else if (up && !down && left && !right) direction = Direction.LEFT_UP;
            else if (up && !down && !left && right) direction = Direction.RIGHT_UP;
            else if (!up && down && left && !right) direction = Direction.LEFT_DOWN;
            else if (!up && down && !left && right) direction = Direction.RIGHT_DOWN;

            stopped = false;
        }
    }

    public boolean isEnemy()
    {
        return enemy;
    }

    public boolean isLive()
    {
        return live;
    }

    public void setLive(boolean live)
    {
        this.live = live;
    }

    public void setHp(int hp)
    {
        this.hp = hp;
    }

    public int getHp()
    {
        return hp;
    }
}
