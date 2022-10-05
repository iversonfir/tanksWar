package com.egh;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class TankTest
{
    @Test
    public void getImage()
    {
        for (Direction direction : Direction.values())
        {
            Tank tank = new Tank(0, 0, direction);
            assertTrue(tank.getImage().getWidth(null) > 0, direction.toString() + " fail");

            Tank enemyTank = new Tank(0, 0, direction, true);
            assertTrue(enemyTank.getImage().getWidth(null) > 0, "enemy tank " + direction.toString() + " fail");
        }
    }
}