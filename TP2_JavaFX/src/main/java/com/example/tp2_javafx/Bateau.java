package com.example.tp2_javafx;

import com.almasb.fxgl.entity.EntityPool;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.Pane;

import java.io.Serializable;

public class Bateau {
    private int type;
    private int direction;
    private int Xpos = 100;
    private int Ypos = 100;

    Bateau(int t, int x, int y, Image img){
        int offsetX = 0;
        int offsetY = 0;
        int imageSize = 0;

        if(t == 5) {
            offsetX = -12;
            offsetY = 25;
            imageSize = 53;
        }else if (t == 4){
            offsetX = -27;
            offsetY = 40;
            imageSize = 83;
        }else if (t == 3){
            offsetX = -27;
            offsetY = 40;
            imageSize = 83;
        }else if (t == 2){
            offsetX = -42;
            offsetY = 55;
            imageSize = 113;
        }else if (t == 1){
            offsetX = -55;
            offsetY = 68;
            imageSize = 140;
        }

        type = t;
        direction = 1;

        Pane bPane = new Pane();
        bPane.relocate(375+x,75+y);

        ImageView iv = new ImageView(img);
        iv.relocate(5,6);
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
        ImageView tourner = new ImageView();
        Pane tournPane = new Pane();
        tourner.setImage(tourn);
        tournPane.relocate(-3,-3);
        tourner.setFitWidth(20);
        tourner.setFitHeight(20);

        int finalOffsetX = offsetX;
        int finalOffsetY = offsetY;
        tournPane.setOnMouseClicked(event -> {
            if(iv.getRotate() == 0) {
                if(bataille.posOk(bataille.grilleJeu,Xpos, Ypos,2,type)){
                    iv.relocate(finalOffsetX, finalOffsetY);
                    iv.setRotate(90);
                    direction = 2;
                }
            } else{
                if(bataille.posOk(bataille.grilleJeu,Xpos, Ypos,1,type)) {
                    iv.relocate(5,6);
                    iv.setRotate(0);
                    direction = 1;
                }
            }
            event.consume();
        });
        tournPane.getChildren().add(tourner);
        bPane.getChildren().add(tournPane);
        Main.list.add(bPane);
    }

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
}