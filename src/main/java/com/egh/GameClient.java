package com.egh;

import javax.swing.*;
import java.awt.*;

public class GameClient extends JComponent
{
    public GameClient()
    {
        this.setPreferredSize(new Dimension(800, 600));
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        g.drawImage(
                new ImageIcon("assets/images/tankD.gif").getImage(),
                400, 300, null);
    }

    public static void main(String[] args)
    {
        JFrame frame = new JFrame();
        frame.setTitle("坦克大戰");
        frame.add(new GameClient());
        frame.setIconImage(new ImageIcon("assets/images/icon.png").getImage());
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
