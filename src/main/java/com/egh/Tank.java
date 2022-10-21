package com.egh;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Random;

public class Tank
{
    private int x;
    private int y;
    private Direction direction;
    private final boolean enemy;
    private final static int SPEED = 5;
    private boolean live = true;
    private int hp = MAX_HP;
    private static final int MAX_HP = 100;

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

    public Image getImage()
    {
        String prefix = enemy ? "e" : "";
        return direction.getImage(prefix + "tank");
    }

    public void draw(Graphics g)
    {
        int oldX = x, oldY = y;
        if (!isEnemy())
        {
            determinedDirection();
        }
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
            //敵人與自己以外相撞 會停止,所以原本才會一直停住
            if (tank != this && rectangle.intersects(tank.getRectangle()))
            {
                y = oldY;
                x = oldX;
                break;
            }
        }

        //敵人撞到我要停下來
        if (this.enemy && rectangle.intersects(GameClient.getInstance().getTank().getRectangle()))
        {
            y = oldY;
            x = oldX;
        }

        if (!enemy)
        {
            drawBloodBar(g);

            Blood blood = GameClient.getInstance().getBlood();
            if (blood.isLive() &&
                    rectangle.intersects(blood.getRectangle()))
            {
                hp = MAX_HP;
                blood.setLive(false);
            }

        }

        g.drawImage(getImage(), x, y, null);
    }

    private void drawBloodBar(Graphics g)
    {
        g.setColor(Color.WHITE);
        g.fillRect(x, y - 10, getImage().getWidth(null), 10);

        g.setColor(Color.RED);
        int width = hp * getImage().getWidth(null) / MAX_HP;
        g.fillRect(x, y - 10, width, 10);
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
                code |= Direction.UP.code;
                break;
            case KeyEvent.VK_DOWN:
                code |= Direction.DOWN.code;
                break;
            case KeyEvent.VK_LEFT:
                code |= Direction.LEFT.code;
                break;
            case KeyEvent.VK_RIGHT:
                code |= Direction.RIGHT.code;
                break;
            case KeyEvent.VK_CONTROL:
                fire();
                break;
            case KeyEvent.VK_A:
                superFire();
                break;
            case KeyEvent.VK_F2:
                GameClient.getInstance().restart();
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

    // 左上 code=10->   1010
    //當鬆開上方時        0010
    // code=8 ->       1000
    // 所以是 XOR 也就是 11 、00  都是 01是1

    public void keyReleased(KeyEvent e)
    {
        switch (e.getKeyCode())
        {
            case KeyEvent.VK_UP:
                code ^= Direction.UP.code;
                break;
            case KeyEvent.VK_DOWN:
                code ^= Direction.DOWN.code;
                break;
            case KeyEvent.VK_LEFT:
                code ^= Direction.LEFT.code;
                break;
            case KeyEvent.VK_RIGHT:
                code ^= Direction.RIGHT.code;
                break;
        }
    }

    void move()
    {
        if (stopped) return;
        x += direction.x * SPEED;
        y += direction.y * SPEED;
    }

    private int code;

    private void determinedDirection()
    {
        Direction newDirection = Direction.get(code);

        if (newDirection == null)
        {
            stopped = true;
        }
        else
        {
            direction = newDirection;
            stopped = false;
        }
    }

    private final Random random = new Random();
    private int step = random.nextInt(12);

    public void randomMove()
    {
        Direction[] dirs = Direction.values();
        if (step <= 0)
        {
            step = random.nextInt(12);
            direction = dirs[random.nextInt(dirs.length)];
            if (random.nextBoolean())
            {
                fire();
            }
        }
        step--;
    }

    public boolean isDying()
    {
        return hp <= MAX_HP * 0.5;
    }

    public static void main(String[] args)
    {
        System.out.println(5 ^ 4);
    }
}