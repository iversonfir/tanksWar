package com.egh;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

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
    private final AtomicInteger enemyKilled = new AtomicInteger(0);
    private final Blood blood;
    private static final int RAW_ENEMIES_AMOUNT = 3;
    private static final int COLUMN_ENEMIES_AMOUNT = 5;

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
        this.walls = wallsInit();
        enemiesInit();
        this.blood = new Blood(400, 250);
        this.setPreferredSize(new Dimension(800, 600));
    }

    public Tank getTank()
    {
        return tank;
    }

    public Blood getBlood()
    {
        return blood;
    }


    private List<Wall> wallsInit()
    {
        return Arrays.asList(
                new Wall(200, 140, true, 15),
                new Wall(200, 540, true, 15),
                new Wall(100, 160, false, 12),
                new Wall(700, 160, false, 12)
        );
    }

    private void enemiesInit()
    {
        enemies = new ArrayList<>();
        for (int i = 0; i < RAW_ENEMIES_AMOUNT; i++)
        {
            for (int j = 0; j < COLUMN_ENEMIES_AMOUNT; j++)
            {
                enemies.add(new Tank(300 + j * 50, 400 + 40 * i, Direction.UP, true));
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        drawBackGround(g);

        if (tank.isLive())
        {
            drawScoreBoard(g);
            tank.draw(g);
            drawEnemies(g);
            drawWalls(g);
            drawMissiles(g);
            drawExplosions(g);
            if (tank.isDying())
            {
                blood.setLive(true);
            }
            if (blood.isLive())
            {
                blood.draw(g);
            }
            return;
        }

        drawGameOver(g);
    }

    private void drawBackGround(Graphics g)
    {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 800, 600);
    }

    private void drawGameOver(Graphics g)
    {
        g.setColor(Color.RED);
        g.setFont(new Font(null, Font.BOLD, 100));
        g.drawString("Game Over", 100, 200);

        g.setFont(new Font(null, Font.BOLD, 60));
        g.drawString("Press F2 Restart", 130, 360);
    }

    private void drawExplosions(Graphics g)
    {
        explosions.removeIf(e -> !e.isLive());
        for (Explosion explosion : explosions)
        {
            explosion.draw(g);
        }
    }

    private void drawMissiles(Graphics g)
    {
        missiles.removeIf(m -> !m.isLive());
        for (Missile missile : missiles)
        {
            missile.draw(g);
        }
    }

    private void drawWalls(Graphics g)
    {
        for (Wall wall : walls)
        {
            wall.draw(g);
        }
    }

    private void drawEnemies(Graphics g)
    {
        int count = enemies.size();
        enemies.removeIf(t -> !t.isLive());
        enemyKilled.addAndGet(count - enemies.size());

        if (enemies.isEmpty())
        {
            enemiesInit();
        }

        for (Tank enemy : enemies)
        {
            enemy.draw(g);
        }
    }

    private void drawScoreBoard(Graphics g)
    {
        g.setColor(Color.WHITE);
        g.setFont(new Font(null, Font.BOLD, 16));
        g.drawString("Missiles: " + missiles.size(), 10, 30);
        g.drawString("Explosions: " + explosions.size(), 10, 50);
        g.drawString("Player Tank Hp: " + tank.getHp(), 10, 70);
        g.drawString("Enemy Left: " + enemies.size(), 10, 90);
        g.drawString("Enemy Killed: " + enemyKilled.get(), 10, 110);
    }

    public void restart()
    {
        enemiesInit();
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
}