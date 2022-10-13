package com.egh;

import javax.swing.*;
import java.awt.*;

public class Utils
{

    public static Image getImage(String imageName)
    {
        return new ImageIcon("assets/images/" + imageName).getImage();
    }

    //TODO playAudio()
//    public static void playAudio(String fileName)
//    {
//        Media sound = new Media(new File("assets/audios/" + fileName).toURI().toString());
//        MediaPlayer mediaPlayer = new MediaPlayer(sound);
//        mediaPlayer.play();
//    }
}

