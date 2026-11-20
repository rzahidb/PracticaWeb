
package org.example.proyectoweb.controller;

import org.example.proyectoweb.model.Customer;
import org.example.proyectoweb.service.CustomerService;

import javafx.collections.FXCollections;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.math.BigDecimal;
import java.util.List;

public class CustomerController {

    @FXML
    private TableView<Customer> tblCustomers;

    @FXML
    private TableColumn<Customer, Integer> colId;

    @FXML
    private TableColumn<Customer, String> colNombre;

    @FXML
    private TableColumn<Customer, String> colApellido;

    @FXML
    private TableColumn<Customer, String> colCorreo;

    @FXML
    private TableColumn<Customer, String> colTelefono;

    @FXML
    private TableColumn<Customer, String> colPais;

    @FXML
    private TableColumn<Customer, String> colCiudad;

    @FXML
    private TableColumn<Customer, BigDecimal> colIngresoAnual;

    @FXML
    private TableColumn<Customer, Integer> colPuntaje;

    @FXML
    private TableColumn<Customer, Boolean> colDeudaPendiente;

    @FXML
    private TableColumn<Customer, BigDecimal> colMontoDeuda;

    @FXML
    private TableColumn<Customer, String> colEstado;

    @FXML
    private Button btnConsultar;

    @FXML
    private Label lblEstado;

    private CustomerService service = new CustomerService();

    @FXML
    private void initialize() {

        colId.setCellValueFactory(
                new PropertyValueFactory<>("id")
        );

        colNombre.setCellValueFactory(
                new PropertyValueFactory<>("firstName")
        );

        colApellido.setCellValueFactory(
                new PropertyValueFactory<>("lastName")
        );

        colCorreo.setCellValueFactory(
                new PropertyValueFactory<>("email")
        );

        colTelefono.setCellValueFactory(
                new PropertyValueFactory<>("phone")
        );

        colPais.setCellValueFactory(
                new PropertyValueFactory<>("country")
        );

        colCiudad.setCellValueFactory(
                new PropertyValueFactory<>("city")
        );

        colIngresoAnual.setCellValueFactory(
                new PropertyValueFactory<>("annualIncome")
        );

        colPuntaje.setCellValueFactory(
                new PropertyValueFactory<>("creditScore")
        );

        colDeudaPendiente.setCellValueFactory(
                new PropertyValueFactory<>("hasUnpaidDebt")
        );

        colMontoDeuda.setCellValueFactory(
                new PropertyValueFactory<>("unpaidDebtAmount")
        );

        colEstado.setCellValueFactory(
                new PropertyValueFactory<>("status")
        );
    }

    @FXML
    private void consultarCustomers() {

        Task<List<Customer>> task = new Task<List<Customer>>() {

            @Override
            protected List<Customer> call() throws Exception {
                return service.obtenerCustomers();
            }
        };

        btnConsultar.disableProperty().bind(
                task.runningProperty()
        );

        lblEstado.setText("Consultando Supabase...");

        task.setOnSucceeded(event -> {

            List<Customer> customers = task.getValue();

            tblCustomers.setItems(
                    FXCollections.observableArrayList(customers)
            );

            lblEstado.setText(
                    "Clientes encontrados: " + customers.size()
            );
        });

        // En caso de error
        task.setOnFailed(event -> {

            Throwable error = task.getException();

            lblEstado.setText(
                    "Error: " + error.getMessage()
            );
        });

        Thread hilo = new Thread(task);
        hilo.setDaemon(true);
        hilo.start();
    }
}
