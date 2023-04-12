package com.example.tp2_javafx;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Nom du programme : Bataille Navale JavaFX
 * @author Clément Pera
 * Date : Avril 2023
 * Résumé : Programme de bataille navale avec
 * interface graphique en réutilisant le code
 * crée dans le TP1.
 */

public class Main extends Application {
    public static Stage stg;
    @Override
    public void start(Stage stage) throws IOException {
        stg = stage;
        Stages.stagePlacementBateau();
    }

    public static void main(String[] args) {
        launch();
    }
}
