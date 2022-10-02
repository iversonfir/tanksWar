package com.egh;

import javax.swing.*;
import java.awt.*;

public class Utils
{

    public static Image getImage(String imageName)
    {
        return new ImageIcon("assets/images/" + imageName).getImage();
    }
}
