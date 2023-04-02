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
    private int Xpos;
    private int Ypos;
    private Image img = new Image(Main.class.getResourceAsStream("bateau2Cases.png"));;

    Bateau(int t){
        Pane bPane = new Pane();
        bPane.relocate(375,75);

        type = t;
        direction = 4;

        ImageView iv = new ImageView(img);
        iv.relocate(5,6);

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

        tournPane.setOnMouseClicked(event -> {
            if(iv.getRotate() == 90)
            {
                iv.relocate(5,6);
                iv.setRotate(0);
                direction = 1;
            } else{
                iv.setRotate(90);
                iv.relocate(-3, 15);
                direction = 2;
                System.out.println(bataille.posOk(bataille.grilleJeu,Xpos-1, Ypos-1,direction,type));
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