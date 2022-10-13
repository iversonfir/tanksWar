package com.egh;

import java.awt.*;

public class Explosion
{
    private int x;
    private int y;
    private int step = 0;
    private boolean live = true;

    public Explosion(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics g)
    {
        if (step > 10)
        {
            live = false;
            return;
        }
        g.drawImage(Utils.getImage(step++ + ".gif"), x, y, null);
    }

    public boolean isLive()
    {
        return live;
    }
}
