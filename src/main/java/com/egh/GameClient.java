package com.egh;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameClient extends JComponent
{

    private static final GameClient INSTANCE = new GameClient();

    public static GameClient getInstance()
    {
        return INSTANCE;
    }

    private final Tank tank;
    private final List<Tank> enemies;
    private final List<Wall> walls;
    private final List<Missile> missiles;

    public List<Wall> getWalls()
    {
        return walls;
    }

    //TODO 為啥要加 Sync
    public void add(Missile missile)
    {
        missiles.add(missile);
    }


    public void remove(Missile missile)
    {
        missiles.remove(missile);
    }

    public List<Tank> getEnemies()
    {
        return enemies;
    }

    public GameClient()
    {
        this.tank = new Tank(400, 100, Direction.DOWN);
        this.enemies = enemiesCreate(3, 5);
        this.missiles = new ArrayList<>();
        this.walls = wallsCreate();
        this.setPreferredSize(new Dimension(800, 600));
    }

    private List<Wall> wallsCreate()
    {
        return Arrays.asList(
                new Wall(200, 140, true, 15),
                new Wall(200, 540, true, 15),
                new Wall(100, 80, false, 15),
                new Wall(700, 80, false, 15)
        );
    }

    private List<Tank> enemiesCreate(int raw, int column)
    {
        List<Tank> tanks = new ArrayList<>();
        for (int i = 0; i < raw; i++)
        {
            for (int j = 0; j < column; j++)
            {
                tanks.add(new Tank(300 + j * 50, 400 + 40 * i, Direction.UP, true));
            }
        }
        return tanks;
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 800, 600);
        tank.draw(g);
        for (Tank enemy : enemies)
        {
            enemy.draw(g);
        }
        for (Wall wall : walls)
        {
            wall.draw(g);
        }
        missiles.removeIf(m -> !m.isLive());
        for (Missile missile : missiles)
        {
            missile.draw(g);
        }
    }

    public static void main(String[] args)
    {
        JFrame frame = new JFrame();
        frame.setTitle("坦克大戰");
        GameClient client = GameClient.getInstance();
        frame.add(client);
        frame.setIconImage(Utils.getImage("icon.png"));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.addKeyListener(new KeyAdapter()
        {
            @Override
            public void keyPressed(KeyEvent e)
            {
                client.tank.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e)
            {
                client.tank.keyReleased(e);
            }
        });
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        while (true)
        {
            client.repaint();
            try
            {
                Thread.sleep(50);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }

    public Tank getTank()
    {
        return tank;
    }
}
