module com.example.projetoacai2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.projetoacai2 to javafx.fxml;
    exports com.example.projetoacai2;
}