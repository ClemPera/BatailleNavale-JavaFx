package com.example.tp2_javafx;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        //Scene scene = new Scene(fxmlLoader.load(), 320, 240);

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

        Text text1 = new Text("test");

        GridPane grid = new GridPane();
        GridPane gridEnemy = new GridPane();
        fillGrid(grid);
        fillGrid(gridEnemy);
        grid.setGridLinesVisible(true);
        grid.relocate(20,40);
        gridEnemy.setGridLinesVisible(true);
        gridEnemy.relocate(400,40);

        int columnSize = 30;
        int rowSize = 30;

        grid.getColumnConstraints().addAll(new ColumnConstraints(columnSize,columnSize,columnSize), new ColumnConstraints(columnSize,columnSize,columnSize), new ColumnConstraints(columnSize,columnSize,columnSize), new ColumnConstraints(columnSize,columnSize,columnSize), new ColumnConstraints(columnSize,columnSize,columnSize), new ColumnConstraints(columnSize,columnSize,columnSize), new ColumnConstraints(columnSize,columnSize,columnSize), new ColumnConstraints(columnSize,columnSize,columnSize), new ColumnConstraints(columnSize,columnSize,columnSize), new ColumnConstraints(columnSize,columnSize,columnSize), new ColumnConstraints(columnSize,columnSize,columnSize));
        grid.getRowConstraints().addAll(new RowConstraints(rowSize,rowSize,rowSize),new RowConstraints(rowSize,rowSize,rowSize),new RowConstraints(rowSize,rowSize,rowSize),new RowConstraints(rowSize,rowSize,rowSize),new RowConstraints(rowSize,rowSize,rowSize),new RowConstraints(rowSize,rowSize,rowSize),new RowConstraints(rowSize,rowSize,rowSize),new RowConstraints(rowSize,rowSize,rowSize),new RowConstraints(rowSize,rowSize,rowSize),new RowConstraints(rowSize,rowSize,rowSize),new RowConstraints(rowSize,rowSize,rowSize));

        gridEnemy.getColumnConstraints().addAll(new ColumnConstraints(columnSize,columnSize,columnSize), new ColumnConstraints(columnSize,columnSize,columnSize), new ColumnConstraints(columnSize,columnSize,columnSize), new ColumnConstraints(columnSize,columnSize,columnSize), new ColumnConstraints(columnSize,columnSize,columnSize), new ColumnConstraints(columnSize,columnSize,columnSize), new ColumnConstraints(columnSize,columnSize,columnSize), new ColumnConstraints(columnSize,columnSize,columnSize), new ColumnConstraints(columnSize,columnSize,columnSize), new ColumnConstraints(columnSize,columnSize,columnSize), new ColumnConstraints(columnSize,columnSize,columnSize));
        gridEnemy.getRowConstraints().addAll(new RowConstraints(rowSize,rowSize,rowSize),new RowConstraints(rowSize,rowSize,rowSize),new RowConstraints(rowSize,rowSize,rowSize),new RowConstraints(rowSize,rowSize,rowSize),new RowConstraints(rowSize,rowSize,rowSize),new RowConstraints(rowSize,rowSize,rowSize),new RowConstraints(rowSize,rowSize,rowSize),new RowConstraints(rowSize,rowSize,rowSize),new RowConstraints(rowSize,rowSize,rowSize),new RowConstraints(rowSize,rowSize,rowSize),new RowConstraints(rowSize,rowSize,rowSize));


        ImageView bg1 = new ImageView();
        Image image1 = new Image(this.getClass().getResourceAsStream("bg.jpg"));
        bg1.setImage(image1);
        bg1.relocate(20,40);
        bg1.setFitWidth(330);
        bg1.setFitHeight(330);

        ImageView bg2 = new ImageView();
        bg2.setImage(image1);
        bg2.relocate(400,40);
        bg2.setFitWidth(330);
        bg2.setFitHeight(330);

        Pane root = new Pane();
        ObservableList list = root.getChildren();
        list.add(bg1);
        list.add(bg2);
        list.add(grid);
        list.add(gridEnemy);
        list.add(menuBar);

        Scene scene = new Scene(root, 800,400);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public void fillGrid(GridPane grid) {
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
                Label lab = new Label("0");
                lab.setStyle("-fx-font: 14 arial");
                grid.add(lab, i, n);
                GridPane.setHalignment(lab, HPos.CENTER);
            }
        }
    }

    public static void main(String[] args) {
        launch();
    }
}