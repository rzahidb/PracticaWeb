
package org.example.proyectoweb.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.proyectoweb.model.Customer;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.List;

public class CustomerService {

    private static final String URL =
            "https://sqpvqkkdfgtzdgrjrgxs.supabase.co/rest/v1/customers";

    public List<Customer> obtenerCustomers()
            throws IOException, InterruptedException {

        String apiKey = System.getenv("SUPABASE_API_KEY");

        if (apiKey == null || apiKey.isBlank()) {
            throw new IOException("Falta configurar SUPABASE_API_KEY");
        }

        HttpClient cliente = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL))
                .timeout(Duration.ofSeconds(15))
                .header("apikey", apiKey)
                .header("Accept", "application/json")
                .GET()
                .build();

        HttpResponse<String> response = cliente.send(
                request,
                HttpResponse.BodyHandlers.ofString()
        );

        if (response.statusCode() != 200) {
            throw new IOException(
                    "Error al obtener clientes. HTTP: "
                            + response.statusCode()
            );
        }

        ObjectMapper mapper = new ObjectMapper();

        JsonNode datos = mapper.readTree(response.body());

        return mapper.convertValue(
                datos,
                new TypeReference<List<Customer>>() {}
        );
    }
}
