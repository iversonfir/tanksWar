package com.egh;

import java.awt.*;

public enum Direction
{
    LEFT("L", -1, 0, 8),
    RIGHT("R", 1, 0, 4),
    UP("U", 0, -1, 2),
    DOWN("D", 0, 1, 1),
    LEFT_UP("LU", -1, -1, 10),
    LEFT_DOWN("LD", -1, 1, 9),
    RIGHT_UP("RU", 1, -1, 6),
    RIGHT_DOWN("RD", 1, 1, 5);

    private final String abbrev;
    final int x;
    final int y;
    final int code;

    Direction(String abbrev, int x, int y, int code)
    {
        this.abbrev = abbrev;
        this.x = x;
        this.y = y;
        this.code = code;
    }

    Image getImage(String prefix)
    {
        return Utils.getImage(prefix + abbrev + ".gif");
    }

    static Direction get(int code)
    {
        for (Direction dir : Direction.values())
        {
            if (dir.code == code)
            {
                return dir;
            }
        }
        return null;
    }
}
