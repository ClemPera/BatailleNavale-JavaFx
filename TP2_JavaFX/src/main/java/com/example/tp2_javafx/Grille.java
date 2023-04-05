package com.example.tp2_javafx;

import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DataFormat;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.*;

import java.nio.file.spi.FileSystemProvider;

public class Grille {
    static final DataFormat dragFormat = new DataFormat("MyButton");
    public static Pane draggingPane;
    public static Bateau bateau;
    public static GridPane grille = new GridPane();
    public static GridPane grille2 = new GridPane();

    public static void valider(Bateau B1, Bateau B2, Bateau B3, Bateau B4, Bateau B5){
        Button btn = new Button();
        btn.relocate(355,40);
        btn.setText("Valider");

        Main.list.add(btn);

        btn.setOnAction(e ->{
            boolean ok = true;

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Il y a une erreur dans le placement des bateaux");
            if(B1.getX() == 100 || B2.getX() == 100 || B3.getX() == 100 || B4.getX() == 100 || B5.getX() == 100) {
                alert.showAndWait();
                System.out.println("Nooo");
            }
            else{
                int[][] grilleTest = new int [10][10];

                if(bataille.posOk(grilleTest,B1.getX(), B1.getY(),B1.getDir(),B1.getType()))
                    bataille.ajoutBateau(grilleTest, B1.getX(), B1.getY(), B1.getDir(), B1.getType());
                else {
                    System.out.println("1");
                    ok = false;
                }
                if(bataille.posOk(grilleTest,B2.getX(), B2.getY(),B2.getDir(),B2.getType()))
                    bataille.ajoutBateau(grilleTest, B2.getX(), B2.getY(), B2.getDir(), B2.getType());
                else {
                    System.out.println("2");
                    ok = false;
                }
                if(bataille.posOk(grilleTest,B3.getX(), B3.getY(),B3.getDir(),B3.getType()))
                    bataille.ajoutBateau(grilleTest, B3.getX(), B3.getY(), B3.getDir(), B3.getType());
                else {
                    System.out.println("3");
                    ok = false;
                }
                if(bataille.posOk(grilleTest,B4.getX(), B4.getY(),B4.getDir(),B4.getType()))
                    bataille.ajoutBateau(grilleTest, B4.getX(), B4.getY(), B4.getDir(), B4.getType());
                else {
                    System.out.println("4");
                    ok = false;
                }
                if(bataille.posOk(grilleTest,B5.getX(), B5.getY(),B5.getDir(),B5.getType()))
                    bataille.ajoutBateau(grilleTest, B5.getX(), B5.getY(), B5.getDir(), B5.getType());
                else {
                    System.out.println("5");
                    ok = false;
                }


                if(ok) {
                    bataille.ajoutBateau(bataille.grilleJeu, B1.getX(), B1.getY(), B1.getDir(), B1.getType());
                    bataille.ajoutBateau(bataille.grilleJeu, B2.getX(), B2.getY(), B2.getDir(), B2.getType());
                    bataille.ajoutBateau(bataille.grilleJeu, B3.getX(), B3.getY(), B3.getDir(), B3.getType());
                    bataille.ajoutBateau(bataille.grilleJeu, B4.getX(), B4.getY(), B4.getDir(), B4.getType());
                    bataille.ajoutBateau(bataille.grilleJeu, B5.getX(), B5.getY(), B5.getDir(), B5.getType());
                    System.out.println("yesss");
                }
                else{
                    alert.showAndWait();
            }
        }
        });
    }
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

            //posOk sur cette position et si ce n'est pas ok mettre un message d'erreur and retry
            //Si c'est Ok mettre la position dans 2 tableaux avec la valeur des row et column?

            draggingPane = null;

            e.setDropCompleted(true);
            e.consume();
        });
    }
    public static void placementBateau(){
        arrierePlanG1();
        creerG1();
    }
    public static void jeu(){
        creerG1();
        creerG2();
    }
    public static void creerG1() {
        arrierePlanG1();

        remplir(grille);

        grille.setGridLinesVisible(true);
        grille.relocate(20,40);

        int columnSize = 30;
        int rowSize = 30;

        grille.getColumnConstraints().addAll(new ColumnConstraints(columnSize,columnSize,columnSize), new ColumnConstraints(columnSize,columnSize,columnSize), new ColumnConstraints(columnSize,columnSize,columnSize), new ColumnConstraints(columnSize,columnSize,columnSize), new ColumnConstraints(columnSize,columnSize,columnSize), new ColumnConstraints(columnSize,columnSize,columnSize), new ColumnConstraints(columnSize,columnSize,columnSize), new ColumnConstraints(columnSize,columnSize,columnSize), new ColumnConstraints(columnSize,columnSize,columnSize), new ColumnConstraints(columnSize,columnSize,columnSize));
        grille.getRowConstraints().addAll(new RowConstraints(rowSize,rowSize,rowSize),new RowConstraints(rowSize,rowSize,rowSize),new RowConstraints(rowSize,rowSize,rowSize),new RowConstraints(rowSize,rowSize,rowSize),new RowConstraints(rowSize,rowSize,rowSize),new RowConstraints(rowSize,rowSize,rowSize),new RowConstraints(rowSize,rowSize,rowSize),new RowConstraints(rowSize,rowSize,rowSize),new RowConstraints(rowSize,rowSize,rowSize),new RowConstraints(rowSize,rowSize,rowSize));

        Main.list.add(grille);
    }
    public static void creerG2() {
        arrierePlanG2();
        remplir(grille2);

        grille2.setGridLinesVisible(true);
        grille2.relocate(400,40);

        int columnSize = 30;
        int rowSize = 30;

        grille2.getColumnConstraints().addAll(new ColumnConstraints(columnSize,columnSize,columnSize), new ColumnConstraints(columnSize,columnSize,columnSize), new ColumnConstraints(columnSize,columnSize,columnSize), new ColumnConstraints(columnSize,columnSize,columnSize), new ColumnConstraints(columnSize,columnSize,columnSize), new ColumnConstraints(columnSize,columnSize,columnSize), new ColumnConstraints(columnSize,columnSize,columnSize), new ColumnConstraints(columnSize,columnSize,columnSize), new ColumnConstraints(columnSize,columnSize,columnSize), new ColumnConstraints(columnSize,columnSize,columnSize));
        grille2.getRowConstraints().addAll(new RowConstraints(rowSize,rowSize,rowSize),new RowConstraints(rowSize,rowSize,rowSize),new RowConstraints(rowSize,rowSize,rowSize),new RowConstraints(rowSize,rowSize,rowSize),new RowConstraints(rowSize,rowSize,rowSize),new RowConstraints(rowSize,rowSize,rowSize),new RowConstraints(rowSize,rowSize,rowSize),new RowConstraints(rowSize,rowSize,rowSize),new RowConstraints(rowSize,rowSize,rowSize),new RowConstraints(rowSize,rowSize,rowSize));

        Main.list.add(grille2);
    }
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

    public static void arrierePlanG1(){
        ImageView ap = new ImageView();
        Image arrierePlan = new Image(Main.class.getResourceAsStream("bg.jpg"));
        ap.setImage(arrierePlan);
        ap.relocate(20,40);
        ap.setFitWidth(300);
        ap.setFitHeight(300);

        Main.list.add(ap);
    }

    public static void arrierePlanG2(){
        Image arrierePlan = new Image(Main.class.getResourceAsStream("bg.jpg"));
        ImageView ap = new ImageView();
        ap.setImage(arrierePlan);
        ap.relocate(400,40);
        ap.setFitWidth(330);
        ap.setFitHeight(330);

        Main.list.add(ap);
    }
}
