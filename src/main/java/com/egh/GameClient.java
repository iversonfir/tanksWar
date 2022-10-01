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
        tank.draw(g);
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
}
