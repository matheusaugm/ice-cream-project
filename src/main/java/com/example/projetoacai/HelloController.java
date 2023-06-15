package com.example.projetoacai;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {

    @FXML
    private Label welcomeText;

    @FXML
    void onSelecionarItemUm(ActionEvent event1) {
        welcomeText.setText("Welcome to JavaFX Application!");/*APLICAR MÉTODO QUE SELECIONA O ITEM*/
    }

    @FXML
    void onSelecionarItemDois(ActionEvent event2) {
        welcomeText.setText("Welcome to JavaFX Application!");/*APLICAR MÉTODO QUE SELECIONA O ITEM*/
    }

    @FXML
    void onSelecionarItemTres(ActionEvent event3) {
        welcomeText.setText("Welcome to JavaFX Application!");/*APLICAR MÉTODO QUE SELECIONA O ITEM*/
    }

    @FXML
    void onSelecionarItemQuatro(ActionEvent event4) {
        welcomeText.setText("Welcome to JavaFX Application!");/*APLICAR MÉTODO QUE SELECIONA O ITEM*/
    }
}