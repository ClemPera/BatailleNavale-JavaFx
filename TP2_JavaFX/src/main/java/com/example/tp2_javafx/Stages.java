package com.example.tp2_javafx;

import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;

public class Stages {
    public static Pane s1 = new Pane();
    public static Pane s2 = new Pane();
    public static Scene scene1 = new Scene(s1, 800,400);;
    public static Scene scene2 = new Scene(s2, 800,400);;
    public static ObservableList scene1List = s1.getChildren();
    public static ObservableList scene2List = s2.getChildren();

static Bateau[] bateaux = {
    new Bateau(5, 0,0, new Image(Main.class.getResourceAsStream("classic.png"))),
    new Bateau(4, 0,30, new Image(Main.class.getResourceAsStream("classic.png"))),
    new Bateau(3, 0,60, new Image(Main.class.getResourceAsStream("classic.png"))),
    new Bateau(2, 0,90, new Image(Main.class.getResourceAsStream("classic.png"))),
    new Bateau(1, 0,120, new Image(Main.class.getResourceAsStream("classic.png")))
};

    public static void stage1Init(){
        Grille.placementBateau();

        Grille.valider();

        Main.stg.setTitle("Hello!");
        Main.stg.setScene(scene1);
        Main.stg.show();
    }

    public static void stage2Init(){

        for (Bateau bateau : bateaux) {
            bateau.tournPane.setOnMouseClicked(null);
            bateau.bPane.setOnDragDetected(null);
            bateau.tourner.setImage(null);
        }

        Stages.scene1List.remove(Grille.btn);

        Grille.jeu();
    }
}
