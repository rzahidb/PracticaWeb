
package org.example.proyectoweb.controller;

import org.example.proyectoweb.model.Usuario;
import org.example.proyectoweb.service.UsuarioService;

import javafx.collections.FXCollections;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class UsuarioController {

    @FXML
    private TableView<Usuario> tablaUsuarios;

    @FXML
    private TableColumn<Usuario, Integer> colId;

    @FXML
    private TableColumn<Usuario, String> colNombre;

    @FXML
    private TableColumn<Usuario, String> colUsername;

    @FXML
    private TableColumn<Usuario, String> colCorreo;

    @FXML
    private TableColumn<Usuario, String> colTelefono;

    @FXML
    private TableColumn<Usuario, String> colCiudad;

    @FXML
    private TableColumn<Usuario, String> colEmpresa;

    @FXML
    private Button btnConsultar;

    @FXML
    private Label lblEstado;

    private UsuarioService service = new UsuarioService();

    @FXML
    private void initialize() {

        colId.setCellValueFactory(
                new PropertyValueFactory<>("id")
        );

        colNombre.setCellValueFactory(
                new PropertyValueFactory<>("nombre")
        );

        colUsername.setCellValueFactory(
                new PropertyValueFactory<>("username")
        );

        colCorreo.setCellValueFactory(
                new PropertyValueFactory<>("correo")
        );

        colTelefono.setCellValueFactory(
                new PropertyValueFactory<>("telefono")
        );

        colCiudad.setCellValueFactory(
                new PropertyValueFactory<>("ciudad")
        );

        colEmpresa.setCellValueFactory(
                new PropertyValueFactory<>("empresa")
        );
    }

    @FXML
    private void consultarUsuarios() {

        Task<List<Usuario>> task = new Task<List<Usuario>>() {

            @Override
            protected List<Usuario> call() throws Exception {
                return service.ObtenerUsuario();
            }
        };

        btnConsultar.disableProperty().bind(
                task.runningProperty()
        );

        lblEstado.setText("Consultando API REST...");

        task.setOnSucceeded(event -> {

            List<Usuario> usuarios = task.getValue();

            tablaUsuarios.setItems(
                    FXCollections.observableArrayList(usuarios)
            );

            lblEstado.setText("Usuarios encontrados");
        });

        // En caso de error
        task.setOnFailed(event -> {

            Throwable error = task.getException();

            lblEstado.setText(error.getMessage());
        });

        Thread hilo = new Thread(task);
        hilo.setDaemon(true);
        hilo.start();
    }
}
