package com.egh;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameClient extends JComponent
{
    private final Tank tank;
    public GameClient()
    {
        this.tank = new Tank(400, 300, Direction.DOWN);
        this.setPreferredSize(new Dimension(800, 600));
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        g.drawImage(tank.getImage(), tank.getX(), tank.getY(), null);
    }

    public static void main(String[] args)
    {
        JFrame frame = new JFrame();
        frame.setTitle("坦克大戰");
        GameClient client = new GameClient();
        frame.add(client);
        frame.setIconImage(new ImageIcon("assets/images/icon.png").getImage());
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.addKeyListener(new KeyAdapter()
        {
            @Override
            public void keyPressed(KeyEvent e)
            {
                switch (e.getKeyCode())
                {
                    case KeyEvent.VK_UP:
                        client.tank.setY(client.tank.getY() - 5);
                        client.tank.setDirection(Direction.UP);
                        break;
                    case KeyEvent.VK_DOWN:
                        client.tank.setY(client.tank.getY() + 5);
                        client.tank.setDirection(Direction.DOWN);
                        break;
                    case KeyEvent.VK_LEFT:
                        client.tank.setX(client.tank.getX() - 5);
                        client.tank.setDirection(Direction.LEFT);
                        break;
                    case KeyEvent.VK_RIGHT:
                        client.tank.setX(client.tank.getX() + 5);
                        client.tank.setDirection(Direction.RIGHT);
                        break;
                }
                client.repaint();
            }
        });
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
