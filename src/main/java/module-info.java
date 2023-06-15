module com.example.projetoacai {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.projetoacai to javafx.fxml;
    exports com.example.projetoacai;
}