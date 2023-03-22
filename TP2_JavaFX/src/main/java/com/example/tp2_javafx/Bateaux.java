package com.example.tp2_javafx;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Bateaux {
    public static void ajout(){
        Image b1= new Image(Main.class.getResourceAsStream("bateau2Cases.png"));
        Image b2= new Image(Main.class.getResourceAsStream("bateau3Cases.png"));
        Image b3= new Image(Main.class.getResourceAsStream("bateau4Cases.png"));
        Image b4= new Image(Main.class.getResourceAsStream("bateau5Cases.png"));

        ImageView bateau1 = new ImageView();
        ImageView bateau2 = new ImageView();
        ImageView bateau12 = new ImageView();
        ImageView bateau3 = new ImageView();
        ImageView bateau4 = new ImageView();

        bateau1.setImage(b1);
        bateau1.relocate(55,75);
        bateau1.setFitWidth(50);
        bateau1.setFitHeight(20);

        bateau12.setImage(b2);
        bateau12.relocate(55,105);
        bateau12.setFitWidth(50);
        bateau12.setFitHeight(20);

        bateau2.setImage(b2);
        bateau2.relocate(55,135);
        bateau2.setFitWidth(80);
        bateau2.setFitHeight(20);

        bateau3.setImage(b3);
        bateau3.relocate(55,165);
        bateau3.setFitWidth(110);
        bateau3.setFitHeight(20);

        bateau4.setImage(b4);
        bateau4.relocate(55,195);
        bateau4.setFitWidth(140);
        bateau4.setFitHeight(20);

        Main.list.add(bateau1);
        Main.list.add(bateau12);
        Main.list.add(bateau2);
        Main.list.add(bateau3);
        Main.list.add(bateau4);
    }
}
