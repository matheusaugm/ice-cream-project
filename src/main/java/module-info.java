module com.example.projetoacai {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.example.projetoacai to javafx.fxml;

    exports com.example.projetoacai;
}