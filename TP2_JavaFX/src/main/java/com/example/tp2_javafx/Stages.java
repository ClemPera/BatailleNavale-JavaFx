package com.example.tp2_javafx;

import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class Stages {
    public static Pane s1 = new Pane();
    public static Scene scene1 = new Scene(s1, 800,600);;
    public static ObservableList scene1List = s1.getChildren();

    //Création des bateaux enemies
    static Bateau[] bateauxEnemy = {
            new Bateau(5, 0,0, new Image(Main.class.getResourceAsStream("classic.png"))),
            new Bateau(4, 0,0, new Image(Main.class.getResourceAsStream("classic.png"))),
            new Bateau(3, 0,0, new Image(Main.class.getResourceAsStream("classic.png"))),
            new Bateau(2, 0,0, new Image(Main.class.getResourceAsStream("classic.png"))),
            new Bateau(1, 0,0, new Image(Main.class.getResourceAsStream("classic.png")))
    };

    //Création des bateaux du joueur
    static Bateau[] bateaux = {
            new Bateau(5, 350,75, new Image(Main.class.getResourceAsStream("classic.png"))),
            new Bateau(4, 350,75+30, new Image(Main.class.getResourceAsStream("classic.png"))),
            new Bateau(3, 350,75+60, new Image(Main.class.getResourceAsStream("classic.png"))),
            new Bateau(2, 350,75+90, new Image(Main.class.getResourceAsStream("classic.png"))),
            new Bateau(1, 350,75+120, new Image(Main.class.getResourceAsStream("classic.png")))
    };

    /**
     * Initialisation de la première scène (Placement des bateaux)
     */
    public static void stage1Init(){
        for (Bateau bateau : bateaux) {
            scene1List.add(bateau.bPane);
            bateau.drag();
            bateau.tournerEvent();
        }

        Grille.creerG1();

        Grille.validerPlacement();

        Main.stg.setTitle("Hello!");
        Main.stg.setScene(scene1);
        Main.stg.show();
    }

    /**
     * Initialisation de la deuxième scène (Jeu)
     */
    public static void stage2Init(){
        for (Bateau bateau : bateaux) {
            bateau.tournPane.setOnMouseClicked(null);
            bateau.bPane.setOnDragDetected(null);
            bateau.tournerIv.setImage(null);
        }
        Stages.scene1List.remove(Grille.btn);

        Grille.creerG2();


    }
}
