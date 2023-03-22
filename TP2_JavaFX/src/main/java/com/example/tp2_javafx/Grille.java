package com.example.tp2_javafx;

import javafx.geometry.HPos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

public class Grille {
    public static void grille(){
        arrierePlan();
        creer();
        Bateaux.ajout();
    }
    public static void creer() {
        GridPane grille = new GridPane();
        GridPane grilleEnnemi = new GridPane();
        remplir(grille);
        remplir(grilleEnnemi);

        grille.setGridLinesVisible(true);
        grille.relocate(20,40);
        grilleEnnemi.setGridLinesVisible(true);
        grilleEnnemi.relocate(400,40);

        int columnSize = 30;
        int rowSize = 30;

        grille.getColumnConstraints().addAll(new ColumnConstraints(columnSize,columnSize,columnSize), new ColumnConstraints(columnSize,columnSize,columnSize), new ColumnConstraints(columnSize,columnSize,columnSize), new ColumnConstraints(columnSize,columnSize,columnSize), new ColumnConstraints(columnSize,columnSize,columnSize), new ColumnConstraints(columnSize,columnSize,columnSize), new ColumnConstraints(columnSize,columnSize,columnSize), new ColumnConstraints(columnSize,columnSize,columnSize), new ColumnConstraints(columnSize,columnSize,columnSize), new ColumnConstraints(columnSize,columnSize,columnSize), new ColumnConstraints(columnSize,columnSize,columnSize));
        grille.getRowConstraints().addAll(new RowConstraints(rowSize,rowSize,rowSize),new RowConstraints(rowSize,rowSize,rowSize),new RowConstraints(rowSize,rowSize,rowSize),new RowConstraints(rowSize,rowSize,rowSize),new RowConstraints(rowSize,rowSize,rowSize),new RowConstraints(rowSize,rowSize,rowSize),new RowConstraints(rowSize,rowSize,rowSize),new RowConstraints(rowSize,rowSize,rowSize),new RowConstraints(rowSize,rowSize,rowSize),new RowConstraints(rowSize,rowSize,rowSize),new RowConstraints(rowSize,rowSize,rowSize));

        grilleEnnemi.getColumnConstraints().addAll(new ColumnConstraints(columnSize,columnSize,columnSize), new ColumnConstraints(columnSize,columnSize,columnSize), new ColumnConstraints(columnSize,columnSize,columnSize), new ColumnConstraints(columnSize,columnSize,columnSize), new ColumnConstraints(columnSize,columnSize,columnSize), new ColumnConstraints(columnSize,columnSize,columnSize), new ColumnConstraints(columnSize,columnSize,columnSize), new ColumnConstraints(columnSize,columnSize,columnSize), new ColumnConstraints(columnSize,columnSize,columnSize), new ColumnConstraints(columnSize,columnSize,columnSize), new ColumnConstraints(columnSize,columnSize,columnSize));
        grilleEnnemi.getRowConstraints().addAll(new RowConstraints(rowSize,rowSize,rowSize),new RowConstraints(rowSize,rowSize,rowSize),new RowConstraints(rowSize,rowSize,rowSize),new RowConstraints(rowSize,rowSize,rowSize),new RowConstraints(rowSize,rowSize,rowSize),new RowConstraints(rowSize,rowSize,rowSize),new RowConstraints(rowSize,rowSize,rowSize),new RowConstraints(rowSize,rowSize,rowSize),new RowConstraints(rowSize,rowSize,rowSize),new RowConstraints(rowSize,rowSize,rowSize),new RowConstraints(rowSize,rowSize,rowSize));

        Main.list.add(grille);
        Main.list.add(grilleEnnemi);
    }
    public static void remplir(GridPane grid) {
        for (char i = 'A'; i <= 'J'; i++) {
            Label lab = new Label(String.valueOf(i));
            lab.setStyle("-fx-font-weight: bold");
            grid.add(lab, i-64, 0);
            GridPane.setHalignment(lab, HPos.CENTER);
        }

        for (int i = 1; i <= 10; i++) {
            Label lab = new Label(String.valueOf(i));
            lab.setStyle("-fx-font-weight: bold");
            grid.add(lab, 0, i);
            GridPane.setHalignment(lab, HPos.CENTER);
        }

        /*
        for (int n = 1; n <= 10; n++) {
            for (int i = 1; i <= 10; i++) {
                Label lab = new Label("0");
                lab.setStyle("-fx-font: 14 arial");
                grid.add(lab, i, n);
                GridPane.setHalignment(lab, HPos.CENTER);
            }
        }
         */
    }
    public static void arrierePlan(){
        ImageView ap1 = new ImageView();
        Image arrierePlan = new Image(Main.class.getResourceAsStream("bg.jpg"));
        ap1.setImage(arrierePlan);
        ap1.relocate(20,40);
        ap1.setFitWidth(330);
        ap1.setFitHeight(330);

        ImageView ap2 = new ImageView();
        ap2.setImage(arrierePlan);
        ap2.relocate(400,40);
        ap2.setFitWidth(330);
        ap2.setFitHeight(330);

        Main.list.add(ap1);
        Main.list.add(ap2);

    }

}
