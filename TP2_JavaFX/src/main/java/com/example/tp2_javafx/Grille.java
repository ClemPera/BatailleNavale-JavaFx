package com.example.tp2_javafx;

import com.almasb.fxgl.dev.DebugCameraScene;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import java.util.ArrayList;
import java.util.List;

public class Grille {
    public static GridPane grille = new GridPane();
    public static List<List<Label>> grilleList= new ArrayList<>();
    public static GridPane grille2 = new GridPane();
    public static List<List<Label>> grille2List = new ArrayList<>();
    public static void placementBateau(){
        arrierePlanG1();
        creerG1();
        Bateaux.select();
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

        grille.getColumnConstraints().addAll(new ColumnConstraints(columnSize,columnSize,columnSize), new ColumnConstraints(columnSize,columnSize,columnSize), new ColumnConstraints(columnSize,columnSize,columnSize), new ColumnConstraints(columnSize,columnSize,columnSize), new ColumnConstraints(columnSize,columnSize,columnSize), new ColumnConstraints(columnSize,columnSize,columnSize), new ColumnConstraints(columnSize,columnSize,columnSize), new ColumnConstraints(columnSize,columnSize,columnSize), new ColumnConstraints(columnSize,columnSize,columnSize), new ColumnConstraints(columnSize,columnSize,columnSize), new ColumnConstraints(columnSize,columnSize,columnSize));
        grille.getRowConstraints().addAll(new RowConstraints(rowSize,rowSize,rowSize),new RowConstraints(rowSize,rowSize,rowSize),new RowConstraints(rowSize,rowSize,rowSize),new RowConstraints(rowSize,rowSize,rowSize),new RowConstraints(rowSize,rowSize,rowSize),new RowConstraints(rowSize,rowSize,rowSize),new RowConstraints(rowSize,rowSize,rowSize),new RowConstraints(rowSize,rowSize,rowSize),new RowConstraints(rowSize,rowSize,rowSize),new RowConstraints(rowSize,rowSize,rowSize),new RowConstraints(rowSize,rowSize,rowSize));

        Main.list.add(grille);
    }
    public static void creerG2() {
        arrierePlanG2();
        remplir(grille2);

        grille2.setGridLinesVisible(true);
        grille2.relocate(400,40);

        int columnSize = 30;
        int rowSize = 30;

        grille2.getColumnConstraints().addAll(new ColumnConstraints(columnSize,columnSize,columnSize), new ColumnConstraints(columnSize,columnSize,columnSize), new ColumnConstraints(columnSize,columnSize,columnSize), new ColumnConstraints(columnSize,columnSize,columnSize), new ColumnConstraints(columnSize,columnSize,columnSize), new ColumnConstraints(columnSize,columnSize,columnSize), new ColumnConstraints(columnSize,columnSize,columnSize), new ColumnConstraints(columnSize,columnSize,columnSize), new ColumnConstraints(columnSize,columnSize,columnSize), new ColumnConstraints(columnSize,columnSize,columnSize), new ColumnConstraints(columnSize,columnSize,columnSize));
        grille2.getRowConstraints().addAll(new RowConstraints(rowSize,rowSize,rowSize),new RowConstraints(rowSize,rowSize,rowSize),new RowConstraints(rowSize,rowSize,rowSize),new RowConstraints(rowSize,rowSize,rowSize),new RowConstraints(rowSize,rowSize,rowSize),new RowConstraints(rowSize,rowSize,rowSize),new RowConstraints(rowSize,rowSize,rowSize),new RowConstraints(rowSize,rowSize,rowSize),new RowConstraints(rowSize,rowSize,rowSize),new RowConstraints(rowSize,rowSize,rowSize),new RowConstraints(rowSize,rowSize,rowSize));

        Main.list.add(grille2);
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

        for (int n = 1; n <= 10; n++) {
            for (int i = 1; i <= 10; i++) {
                Label lab = new Label("");
                lab.setStyle("-fx-font: 14 arial");
                lab.setMinSize(30,30);

                lab.setOnDragOver(new EventHandler<DragEvent>() {
                    public void handle(DragEvent event) {
                        if (event.getGestureSource() != lab && event.getDragboard().hasString()) {
                            event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                        }

                        event.consume();
                    }
                });

                int finalI = i;
                int finalN = n;
                lab.setOnDragDropped((DragEvent event) -> {
                    Dragboard db = event.getDragboard();
                    if (db.hasString()) {
                        System.out.println("Dropped: " + db.getString() + finalI + " " + finalN);
                        event.setDropCompleted(true);
                    } else {
                        event.setDropCompleted(false);
                    }
                    event.consume();
                });

                grid.add(lab, i, n);
                GridPane.setHalignment(lab, HPos.CENTER);
            }
        }
    }
    public static void arrierePlanG1(){
        ImageView ap = new ImageView();
        Image arrierePlan = new Image(Main.class.getResourceAsStream("bg.jpg"));
        ap.setImage(arrierePlan);
        ap.relocate(20,40);
        ap.setFitWidth(330);
        ap.setFitHeight(330);

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
