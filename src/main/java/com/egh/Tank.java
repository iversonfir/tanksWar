package com.egh;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Tank
{
    private int x;
    private int y;
    private Direction direction;
    private final boolean isEnemy;

    public Tank(int x, int y, Direction direction)
    {
        this(x, y, direction, false);
    }

    public Tank(int x, int y, Direction direction, boolean isEnemy)
    {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.isEnemy = isEnemy;
    }

    public Image getImage()
    {
        String prefix = isEnemy ? "e" : "";
        switch (direction)
        {
            case UP:
                return Utils.getImage(prefix + "tankU.gif");
            case DOWN:
                return Utils.getImage(prefix + "tankD.gif");
            case LEFT:
                return Utils.getImage(prefix + "tankL.gif");
            case RIGHT:
                return Utils.getImage(prefix + "tankR.gif");
            case UP_LEFT:
                return Utils.getImage(prefix + "tankLU.gif");
            case UP_RIGHT:
                return Utils.getImage(prefix + "tankRU.gif");
            case DOWN_LEFT:
                return Utils.getImage(prefix + "tankLD.gif");
            case DOWN_RIGHT:
                return Utils.getImage(prefix + "tankRD.gif");
        }
        return null;
    }

    public void draw(Graphics g)
    {
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
