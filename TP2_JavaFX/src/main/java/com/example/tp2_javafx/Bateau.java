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

    public int offset = 0;
    public int imageSize = 0;

    /**
     * Constructeur du bateau
     * @param t Type du bateau
     * @param x Position X du bateau
     * @param y Position Y du bateau
     * @param img Image du bateau
     */

    Bateau(int t, int x, int y, Image img){

        if (t == 5) {
            offset = -16;
            imageSize = 53;
        } else if (t == 4) {
            offset = -31;
            imageSize = 83;
        } else if (t == 3) {
            offset = -31;
            imageSize = 83;
        } else if (t == 2) {
            offset = -46;
            imageSize = 113;
        } else if (t == 1) {
            offset = -60;
            imageSize = 140;
        }

        type = t;
        direction = 1;

        bPane.relocate(x, y);
        bPane.setMaxWidth(20);
        bPane.setMaxHeight(20);

        iv.setImage(img);
        iv.relocate(0, 0);
        iv.setRotate(0);
        iv.setFitWidth(imageSize);
        iv.setFitHeight(20);

        //bPane.setStyle("-fx-background-color: red;"); //DEBUG

        bPane.getChildren().add(iv);
    }

    /**
     * Active l'évènement drag and drop sur le bateau
     */
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

    /**
     * Active l'évènement de rotation du bateau
     */

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

    /**
     * Tourne le bateau
     */
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

    /**
     * Tourne le bateau en vertical
     */
    public void tournerVer(){
        iv.relocate(offset, -offset);
        iv.setRotate(90);
        direction = 2;
    }

    /**
     * Tourne le bateau à l'horizontal
     */
    public void tournerHor(){
        iv.relocate(0, 0);
        iv.setRotate(0);
        direction = 1;
    }

    /**
     * Retourne le type du bateau
     * @return Type du bateau
     */

    public int getType(){
        return type;
    }

    /**
     * Retourne la direction du bateau
     * @return Direction du bateau
     */
    public int getDir(){
        return direction;
    }

    /**
     * Retourne la position X du bateau
     * @return Position X du bateau
     */
    public int getX(){
        return Xpos;
    }

    /**
     * Retourne la position Y du bateau
     * @return Position Y du bateau
     */
    public int getY(){
        return Ypos;
    }

    /**
     * Définit la position du bateau
     * @param x Position X du bateau
     * @param y Position Y du bateau
     */
    public void setPos(int x, int y){
        Xpos = x;
        Ypos = y;
    }

    /**
     * Définit la direction du bateau
     * @param d Direction du bateau
     */
    public void setDir(int d){
        direction = d;
    }
}