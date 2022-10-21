package com.egh;

import java.awt.*;

public class Blood
{
    private final int x;
    private final int y;
    private boolean live = true;
    private final Image image;

    public Blood(int x, int y)
    {
        this.x = x;
        this.y = y;
        this.image = Utils.getImage("blood.png");
    }

    public boolean isLive()
    {
        return live;
    }

    public void setLive(boolean live)
    {
        this.live = live;
    }

    public void draw(Graphics g)
    {
        g.drawImage(image, x, y, null);
    }

    public Rectangle getRectangle()
    {
        return new Rectangle(x, y, image.getWidth(null), image.getHeight(null));
    }
}