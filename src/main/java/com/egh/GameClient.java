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

    private Tank tank;
    private List<Tank> enemies;
    private final List<Wall> walls;
    private final List<Missile> missiles;
    private final List<Explosion> explosions;

    public List<Wall> getWalls()
    {
        return walls;
    }

    public void add(Missile missile)
    {
        missiles.add(missile);
    }

    public void add(Explosion explosion)
    {
        explosions.add(explosion);
    }

    public List<Tank> getEnemies()
    {
        return enemies;
    }

    public GameClient()
    {
        tankInit();
        this.missiles = new ArrayList<>();
        this.explosions = new ArrayList<>();
        this.walls = wallsCreate();
        enemiesInit(3, 5);
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

    private void enemiesInit(int raw, int column)
    {
        enemies = new ArrayList<>();
        for (int i = 0; i < raw; i++)
        {
            for (int j = 0; j < column; j++)
            {
                enemies.add(new Tank(300 + j * 50, 400 + 40 * i, Direction.UP, true));
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 800, 600);

        if (!tank.isLive())
        {
            g.setColor(Color.RED);
            g.setFont(new Font(null, Font.BOLD, 100));
            g.drawString("Game Over", 100, 200);

            g.setFont(new Font(null, Font.BOLD, 60));
            g.drawString("Press F2 Restart", 130, 360);
        }
        else
        {
            tank.draw(g);
            enemies.removeIf(t -> !t.isLive());
            if (enemies.isEmpty())
            {
                restart();
            }

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
            explosions.removeIf(e -> !e.isLive());
            for (Explosion explosion : explosions)
            {
                explosion.draw(g);
            }
        }
    }

    public void restart()
    {
        enemiesInit(3, 5);
        tankInit();
    }

    private void tankInit()
    {
        tank = new Tank(400, 100, Direction.DOWN);
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
            if (client.tank.isLive())
            {
                for (Tank tank : client.enemies)
                {
                    tank.randomMove();
                }
            }
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
