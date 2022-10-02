package com.egh;

import javax.swing.*;
import java.awt.*;

public class Wall
{
    private final int x;
    private final int y;
    private final boolean horizontal;
    private final int bricks;

    public Wall(int x, int y, boolean horizontal, int bricks)
    {
        this.x = x;
        this.y = y;
        this.horizontal = horizontal;
        this.bricks = bricks;
    }

    public void draw(Graphics g)
    {
        Image brickImage = Utils.getImage("brick.png");
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
