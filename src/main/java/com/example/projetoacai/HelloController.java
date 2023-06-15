package com.example.projetoacai;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class HelloController {
    @FXML
    private Label welcomeText;
    @FXML
    private TextField txt1;
    @FXML
    private TextField txt2;
    @FXML
    private TextField txt3;
    @FXML
    private TextField txt4;
    @FXML
    private TextField txt5;
    @FXML
    private TextField txt6;
    @FXML
    private TextField txt7;

    @FXML
    protected void onFecharPedido(ActionEvent event1) {
        welcomeText.setText("Roi, Letícia né?");/* APLICAR MÉTODO QUE SELECIONA O ITEM */
    }
}