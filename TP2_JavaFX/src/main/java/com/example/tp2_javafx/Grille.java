package com.example.tp2_javafx;

import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DataFormat;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseButton;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.*;
import javafx.scene.media.AudioClip;

public class Grille {
    static final DataFormat dragFormat = new DataFormat("MyButton");
    public static Pane draggingPane;
    public static Bateau bateau;
    public static GridPane grille = new GridPane();
    public static GridPane grille2 = new GridPane();
    public static Button btn = new Button();

    /**
     * Valide le placement des bateaux avec un bouton
     */
    public static void validerPlacement(){
        btn.relocate(355,40);
        btn.setText("Valider");

        Stages.scene1List.add(btn);

        btn.setOnAction(e ->{
            boolean ok = true;

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Il y a une erreur dans le placement des bateaux");

            int[][] grilleTest = new int[10][10];
            for (Bateau bateau : Stages.bateaux) {

                if (bateau.getX() != 100 && bataille.posOk(grilleTest, bateau.getX(), bateau.getY(), bateau.getDir(), bateau.getType()))
                    bataille.ajoutBateau(grilleTest, bateau.getX(), bateau.getY(), bateau.getDir(), bateau.getType());
                else {
                    System.out.println("1");
                    ok = false;
                }
            }

            if(ok) {
                for (Bateau bateau : Stages.bateaux) {
                    bataille.ajoutBateau(bataille.grilleJeu, bateau.getX(), bateau.getY(), bateau.getDir(), bateau.getType());
                }
                Stages.stageJeu();
            }
            else{
                alert.showAndWait();
            }
        });
    }

    /**
     * Active l'évènement de pose du bateau sur la grille
     * @param pane Pane sur laquelle on pose le bateau
     */
    public static void drop(StackPane pane){
        pane.setOnDragOver(e -> {
            Node node = (Node) e.getTarget();

            if(GridPane.getRowIndex(node) != null && GridPane.getColumnIndex(node) != null)
                if(bataille.posOk(bataille.grilleJeu,GridPane.getRowIndex(node),GridPane.getColumnIndex(node),bateau.getDir(),bateau.getType())) {
                    e.acceptTransferModes(TransferMode.MOVE);
                    e.consume();
                }
        });

        pane.setOnDragDropped(e -> {
            Dragboard db = e.getDragboard();

            Node node = (Node) e.getTarget();
            ((Pane)draggingPane.getParent()).getChildren().remove(draggingPane);
            pane.getChildren().add(draggingPane);
            e.setDropCompleted(true);

            if(GridPane.getRowIndex(node) != null && GridPane.getColumnIndex(node) != null)
                bateau.setPos(GridPane.getRowIndex(node), GridPane.getColumnIndex(node));

            draggingPane = null;

            e.setDropCompleted(true);
            e.consume();
        });
    }

    /**
     * Création de la grille du joueur
     */
    public static void creerG1() {
        arrierePlanG1();
        remplir(grille);

        grille.setGridLinesVisible(true);
        grille.relocate(20,40);

        int columnSize = 30;
        int rowSize = 30;

        grille.getColumnConstraints().addAll(new ColumnConstraints(columnSize,columnSize,columnSize), new ColumnConstraints(columnSize,columnSize,columnSize), new ColumnConstraints(columnSize,columnSize,columnSize), new ColumnConstraints(columnSize,columnSize,columnSize), new ColumnConstraints(columnSize,columnSize,columnSize), new ColumnConstraints(columnSize,columnSize,columnSize), new ColumnConstraints(columnSize,columnSize,columnSize), new ColumnConstraints(columnSize,columnSize,columnSize), new ColumnConstraints(columnSize,columnSize,columnSize), new ColumnConstraints(columnSize,columnSize,columnSize));
        grille.getRowConstraints().addAll(new RowConstraints(rowSize,rowSize,rowSize),new RowConstraints(rowSize,rowSize,rowSize),new RowConstraints(rowSize,rowSize,rowSize),new RowConstraints(rowSize,rowSize,rowSize),new RowConstraints(rowSize,rowSize,rowSize),new RowConstraints(rowSize,rowSize,rowSize),new RowConstraints(rowSize,rowSize,rowSize),new RowConstraints(rowSize,rowSize,rowSize),new RowConstraints(rowSize,rowSize,rowSize),new RowConstraints(rowSize,rowSize,rowSize));

        Stages.scene1List.add(grille);
    }

    /**
     * Création de la grille de l'adversaire
     */
    public static void creerG2() {
        arrierePlanG2();
        remplirEnemy(grille2);

        grille2.setGridLinesVisible(true);
        grille2.relocate(400,40);

        int columnSize = 30;
        int rowSize = 30;

        grille2.getColumnConstraints().addAll(new ColumnConstraints(columnSize,columnSize,columnSize), new ColumnConstraints(columnSize,columnSize,columnSize), new ColumnConstraints(columnSize,columnSize,columnSize), new ColumnConstraints(columnSize,columnSize,columnSize), new ColumnConstraints(columnSize,columnSize,columnSize), new ColumnConstraints(columnSize,columnSize,columnSize), new ColumnConstraints(columnSize,columnSize,columnSize), new ColumnConstraints(columnSize,columnSize,columnSize), new ColumnConstraints(columnSize,columnSize,columnSize), new ColumnConstraints(columnSize,columnSize,columnSize));
        grille2.getRowConstraints().addAll(new RowConstraints(rowSize,rowSize,rowSize),new RowConstraints(rowSize,rowSize,rowSize),new RowConstraints(rowSize,rowSize,rowSize),new RowConstraints(rowSize,rowSize,rowSize),new RowConstraints(rowSize,rowSize,rowSize),new RowConstraints(rowSize,rowSize,rowSize),new RowConstraints(rowSize,rowSize,rowSize),new RowConstraints(rowSize,rowSize,rowSize),new RowConstraints(rowSize,rowSize,rowSize),new RowConstraints(rowSize,rowSize,rowSize));

        Stages.scene1List.add(grille2);
    }

    /**
     * Remplissage de la grille du joueur
     * @param grid Grille à remplir
     */
    public static void remplir(GridPane grid) {
        for (int n = 0; n < 10; n++) {
            for (int i = 0; i < 10; i++) {
                StackPane sp = new StackPane();

                sp.setPrefWidth(30);
                sp.setPrefHeight(30);

                grid.add(sp, i, n);

                drop(sp);
            }
        }
    }

    /**
     * Remplissage de la grille de l'adversaire
     * @param grid Grille à remplir
     */
    public static void remplirEnemy(GridPane grid){
        int[][] tot = {
                bataille.initGrilleOrdi(5),
                bataille.initGrilleOrdi(4),
                bataille.initGrilleOrdi(3),
                bataille.initGrilleOrdi(2),
                bataille.initGrilleOrdi(1)};

        for (int n = 0; n < 10; n++) {
            for (int i = 0; i < 10; i++) {
                StackPane sp = new StackPane();

                sp.setPrefWidth(30);
                sp.setPrefHeight(30);

                grid.add(sp, i, n);

                for(int j = 0; j < 5; j++)
                {
                    if (tot[j][0] == n && tot[j][1] == i) {
                        Stages.bateauxEnemy[j].setPos(GridPane.getRowIndex(sp), GridPane.getColumnIndex(sp));

                        if (tot[j][2] == 2)
                            Stages.bateauxEnemy[j].tournerVer();
                    }
                }
            }
        }
    }

    /**
     * Gestion des évènements de clic sur la grille de l'adversaire
     * @throws ClassCastException Exception quand on clique sur la grille et pas sur la case
     */
    public static void clickEvent() {
        grille2.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                try {
                    StackPane sp = (StackPane) event.getTarget();

                    attaque(GridPane.getRowIndex(sp), GridPane.getColumnIndex(sp), true);
                } catch (ClassCastException e) {
                    System.out.println("Clic sur la grille");
                }
                grille2.setOnMouseClicked(null);
                attaqueOrdi();
            }
        });
    }

    /**
     * Attaque du
     * @param ligne numero de ligne
     * @param colonne numero de la colonne
     * @param joueur false si c'est l'ordi, true si c'est le joueur
     */
    public static void attaque(int ligne, int colonne, boolean joueur) {
        System.out.println(ligne + " " + colonne);
        int move;
        int offsetX;
        int offsetY;
        Bateau bateau[];

        ImageView feu = new ImageView(new Image(Main.class.getResourceAsStream("fire.gif")));
        feu.setFitWidth(30);
        feu.setFitHeight(30);

        ImageView eau = new ImageView(new Image(Main.class.getResourceAsStream("water.gif")));
        eau.setFitWidth(30);
        eau.setFitHeight(30);

        AudioClip explosion = new AudioClip(Main.class.getResource("explosion.mp3").toString());
        AudioClip explosionFinale = new AudioClip(Main.class.getResource("explosionFinale.mp3").toString());
        AudioClip couler = new AudioClip(Main.class.getResource("bubble.mp3").toString());

        if(joueur) {
            bateau = Stages.bateauxEnemy;
            move = bataille.mouvement(bataille.grilleOrdi, ligne, colonne);
            offsetX = 400;
            offsetY = 40;
        }
        else {
            bateau = Stages.bateaux;
            move = bataille.mouvement(bataille.grilleJeu, ligne, colonne);
            offsetX = 20;
            offsetY = 40;
        }

        if (move == 8) {
            System.out.println("Touché");
            //Animation rouge sur la case / Marqueur X

            feu.relocate(offsetX + colonne * 30, offsetY + ligne * 30);
            Stages.scene1List.add(feu);

            //Effet sonore explosion
            explosion.play();
        } else if (move == 9) {
            System.out.println("A l'eau");
            //Animation Bleue avec bubble sur la case / Marqueur O

            eau.relocate(offsetX + colonne * 30, offsetY + ligne * 30);
            Stages.scene1List.add(eau);

            //Effet sonore bubble
            couler.play();
        } else {
            System.out.println("Le bateau " + move + " a été coulé!");
            //Effet sonore explosion diff de touché
            explosionFinale.play();

            feu.relocate(offsetX + colonne * 30, offsetY + ligne * 30);
            Stages.scene1List.add(feu);

            if (move == 1) {
                bateau[4].bPane.relocate(offsetX+ bateau[4].getY() * 30+5, offsetY+ bateau[4].getX() * 30+5);
                Stages.scene1List.add(bateau[4].bPane);
            } else if (move == 2) {
                bateau[3].bPane.relocate(offsetX+ bateau[3].getY() * 30+5, offsetY+ bateau[3].getX() * 30+5);
                Stages.scene1List.add(bateau[3].bPane);
            } else if (move == 3) {
                bateau[2].bPane.relocate(offsetX+ bateau[2].getY() * 30+5, offsetY+ bateau[2].getX() * 30+5);
                Stages.scene1List.add(bateau[2].bPane);
            } else if (move == 4) {
                bateau[1].bPane.relocate(offsetX+ bateau[1].getY() * 30+5, offsetY+ bateau[1].getX() * 30+5);
                Stages.scene1List.add(bateau[1].bPane);
            } else if (move == 5) {
                bateau[0].bPane.relocate(offsetX+ bateau[0].getY() * 30+5, offsetY+ bateau[0].getX() * 30+5);
                Stages.scene1List.add(bateau[0].bPane);
            }

            if(joueur) {
                bataille.AfficherGrille(bataille.grilleJeu);
                if (bataille.vainqueur(bataille.grilleOrdi))
                    Stages.stageVictoire();
            }
            else {
                bataille.AfficherGrille(bataille.grilleOrdi);
                if (bataille.vainqueur(bataille.grilleJeu))
                    Stages.stageDefaite();
            }
        }
    }

    /**
     * Attaque de l'ordinateur
     */
    public static void attaqueOrdi(){
        int[] tir = bataille.tirOrdinateur();
        attaque(tir[0], tir[1], false);
        clickEvent();
    }

    /**
     * Création de l'arrière-plan de la grille du joueur
     */
    public static void arrierePlanG1(){
        ImageView ap = new ImageView();
        Image arrierePlan = new Image(Main.class.getResourceAsStream("bg.jpg"));
        ap.setImage(arrierePlan);
        ap.relocate(20,40);
        ap.setFitWidth(300);
        ap.setFitHeight(300);

        Stages.scene1List.add(ap);
    }

    /**
     * Création de l'arrière-plan de la grille de l'adversaire
     */
    public static void arrierePlanG2(){
        Image arrierePlan = new Image(Main.class.getResourceAsStream("bg.jpg"));
        ImageView ap = new ImageView();
        ap.setImage(arrierePlan);
        ap.relocate(400,40);
        ap.setFitWidth(300);
        ap.setFitHeight(300);

        Stages.scene1List.add(ap);
    }
}
