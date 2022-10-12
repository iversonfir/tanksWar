package com.egh;

import java.awt.*;

public enum Direction
{
    UP("U", 0, -1),
    DOWN("D", 0, 1),
    LEFT("L", -1, 0),
    RIGHT("R", 1, 0),
    LEFT_UP("LU", -1, -1),
    RIGHT_UP("RU", 1, -1),
    LEFT_DOWN("LD", -1, 1),
    RIGHT_DOWN("RD", 1, 1);

    private final String abbrev;
    final int x;
    final int y;

    Direction(String abbrev, int x, int y)
    {
        this.abbrev = abbrev;
        this.x = x;
        this.y = y;
    }

    Image getImage(String prefix)
    {
        return Utils.getImage(prefix + abbrev + ".gif");
    }
}
