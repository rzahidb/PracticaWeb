module org.example.proyectoweb {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires com.fasterxml.jackson.databind;

    opens org.example.proyectoweb.controller to javafx.fxml;
    opens org.example.proyectoweb.model
            to javafx.base, com.fasterxml.jackson.databind;

    exports org.example.proyectoweb;
}