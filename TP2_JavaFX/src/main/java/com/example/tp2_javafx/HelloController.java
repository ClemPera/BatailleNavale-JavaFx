package com.example.tp2_javafx;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.w3c.dom.Text;

public class HelloController {
    @FXML
    private Label welcomeText;
    private Text g1_00;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

}