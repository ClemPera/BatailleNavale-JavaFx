package com.example.tp2_javafx;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    private static Pane root = new Pane();
    public static ObservableList list = root.getChildren();
    @Override
    public void start(Stage stage) throws IOException {
        //Grille.jeu();
        Grille.placementBateau();
        menu();

        Scene scene = new Scene(root, 800,400);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void menu(){
        MenuBar menuBar = new MenuBar();

        Menu menu1 = new Menu("Menu");
        Menu menu2 = new Menu("Menu 2");

        MenuItem m1 = new MenuItem("menu item1");
        MenuItem m2 = new MenuItem("menu item2");
        MenuItem m3 = new MenuItem("menu item3");
        MenuItem m4 = new MenuItem("menu item4");

        menu1.getItems().addAll(m1,m2);
        menu2.getItems().addAll(m3,m4);

        menuBar.getMenus().addAll(menu1, menu2);
        menuBar.setPrefWidth(800);

        list.add(menuBar);
    }

    public static void main(String[] args) {
        launch();
    }
}