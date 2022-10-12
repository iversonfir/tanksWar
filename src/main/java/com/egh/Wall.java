package com.egh;

import java.awt.*;

public class Wall
{
    private final int x;
    private final int y;
    private final boolean horizontal;
    private final int bricks;
    private final Image brickImage;

    public Wall(int x, int y, boolean horizontal, int bricks)
    {
        this.brickImage = Utils.getImage("brick.png");
        this.x = x;
        this.y = y;
        this.horizontal = horizontal;
        this.bricks = bricks;
    }

    public Rectangle getRectangle()
    {
        return horizontal
                ? new Rectangle(x, y,
                brickImage.getWidth(null) * bricks,
                brickImage.getHeight(null))
                : new Rectangle(x, y,
                brickImage.getWidth(null),
                brickImage.getHeight(null) * bricks);
    }

    public void draw(Graphics g)
    {
        for (int i = 0; i < bricks; i++)
        {
            if (horizontal)
            {
                g.drawImage(brickImage, x + i * brickImage.getWidth(null), y, null);
            }
            else
            {
                g.drawImage(brickImage, x, y + i * brickImage.getHeight(null), null);
            }
        }
    }
}
