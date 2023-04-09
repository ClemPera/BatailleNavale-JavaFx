package com.example.tp2_javafx;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.Pane;

public class Bateau {
    private int type;
    private int direction;
    private int Xpos = 100;
    private int Ypos = 100;
    public Pane tournPane = new Pane();
    public Pane bPane = new Pane();
    public ImageView tournerIv = new ImageView();
    public ImageView iv = new ImageView();

    public int offsetX = 0;
    public int offsetY = 0;
    public int imageSize = 0;
    Bateau(int t, int x, int y, Image img, boolean estEnemy){

        if (t == 5) {
            offsetX = -12;
            offsetY = 25;
            imageSize = 53;
        } else if (t == 4) {
            offsetX = -27;
            offsetY = 40;
            imageSize = 83;
        } else if (t == 3) {
            offsetX = -27;
            offsetY = 40;
            imageSize = 83;
        } else if (t == 2) {
            offsetX = -42;
            offsetY = 55;
            imageSize = 113;
        } else if (t == 1) {
            offsetX = -55;
            offsetY = 68;
            imageSize = 140;
        }

        type = t;
        direction = 1;

        bPane.relocate(x, y);

        iv.setImage(img);
        iv.relocate(5, 6);
        iv.setRotate(0);
        iv.setFitWidth(imageSize);
        iv.setFitHeight(20);


        bPane.getChildren().add(iv);

        if (!estEnemy) {
            drag();
            tournerEvent();
        }

    }
    public void drag(){
        bPane.setOnDragDetected(event -> {
            Dragboard db = bPane.startDragAndDrop(TransferMode.MOVE);
            db.setDragView(bPane.snapshot(null, null));
            ClipboardContent content = new ClipboardContent();

            content.put(Grille.dragFormat, " ");
            db.setContent(content);

            Grille.bateau = this;
            Grille.draggingPane = bPane;
            event.consume();
        });
    }

    public void tournerEvent() {
        Image tourn = new Image(Main.class.getResourceAsStream("rotate.png"));
        tournerIv.setImage(tourn);
        tournPane.relocate(-3, -3);
        tournerIv.setFitWidth(20);
        tournerIv.setFitHeight(20);

        tournPane.setOnMouseClicked(event -> {
            tourner();
            event.consume();
        });

        tournPane.getChildren().add(tournerIv);
        bPane.getChildren().add(tournPane);
    }

    public void tourner(){
        if (iv.getRotate() == 0) {
            if (bataille.posOk(bataille.grilleJeu, Xpos, Ypos, 2, type)) {
                tournerVer();
            }
        } else {
            if (bataille.posOk(bataille.grilleJeu, Xpos, Ypos, 1, type)) {
                tournerHor();
            }
        }
    }
    public void tournerVer(){
        iv.relocate(offsetX, offsetY);
        iv.setRotate(90);
        direction = 2;
    }
    public void tournerHor(){
        iv.relocate(5, 6);
        iv.setRotate(0);
        direction = 1;
    }
    /*
    Bateau(int t, int x, int y, Image img){
        int offsetX = 0;
        int offsetY = 0;
        int imageSize = 0;

        if (t == 5) {
            offsetX = -12;
            offsetY = 25;
            imageSize = 53;
        } else if (t == 4) {
            offsetX = -27;
            offsetY = 40;
            imageSize = 83;
        } else if (t == 3) {
            offsetX = -27;
            offsetY = 40;
            imageSize = 83;
        } else if (t == 2) {
            offsetX = -42;
            offsetY = 55;
            imageSize = 113;
        } else if (t == 1) {
            offsetX = -55;
            offsetY = 68;
            imageSize = 140;
        }

        type = t;
        direction = 1;

        bPane.relocate(375 + x, 75 + y);

        ImageView iv = new ImageView(img);
        iv.relocate(5, 6);
        iv.setFitWidth(imageSize);
        iv.setFitHeight(20);

        bPane.setOnDragDetected(event -> {
            Dragboard db = bPane.startDragAndDrop(TransferMode.MOVE);
            db.setDragView(bPane.snapshot(null, null));
            ClipboardContent content = new ClipboardContent();

            content.put(Grille.dragFormat, " ");
            db.setContent(content);

            Grille.bateau = this;
            Grille.draggingPane = bPane;
            event.consume();
        });
        bPane.getChildren().add(iv);

        Image tourn = new Image(Main.class.getResourceAsStream("rotate.png"));
        tourner.setImage(tourn);
        tournPane.relocate(-3, -3);
        tourner.setFitWidth(20);
        tourner.setFitHeight(20);

        int finalOffsetX = offsetX;
        int finalOffsetY = offsetY;
        tournPane.setOnMouseClicked(event -> {
            if (iv.getRotate() == 0) {
                if (bataille.posOk(bataille.grilleJeu, Xpos, Ypos, 2, type)) {
                    iv.relocate(finalOffsetX, finalOffsetY);
                    iv.setRotate(90);
                    direction = 2;
                }
            } else {
                if (bataille.posOk(bataille.grilleJeu, Xpos, Ypos, 1, type)) {
                    iv.relocate(5, 6);
                    iv.setRotate(0);
                    direction = 1;
                }
            }
            event.consume();
        });

        tournPane.getChildren().add(tourner);
        bPane.getChildren().add(tournPane);
        Stages.scene1List.add(bPane);
    }
     */

    public int getType(){
        return type;
    }

    public int getDir(){
        return direction;
    }

    public int getX(){
        return Xpos;
    }

    public int getY(){
        return Ypos;
    }

    public void setPos(int x, int y){
        Xpos = x;
        Ypos = y;
    }
    public void setDir(int d){
        direction = d;
    }
}