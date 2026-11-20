
package org.example.proyectoweb.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Customer {

    private int id;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    private String email;
    private String phone;

    @JsonProperty("date_of_birth")
    private String dateOfBirth;

    private String country;
    private String city;
    private String address;

    @JsonProperty("annual_income")
    private BigDecimal annualIncome;

    @JsonProperty("credit_score")
    private int creditScore;

    @JsonProperty("has_unpaid_debt")
    private boolean hasUnpaidDebt;

    @JsonProperty("unpaid_debt_amount")
    private BigDecimal unpaidDebtAmount;

    private String status;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("updated_at")
    private String updatedAt;

    public Customer() {
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getAddress() {
        return address;
    }

    public BigDecimal getAnnualIncome() {
        return annualIncome;
    }

    public int getCreditScore() {
        return creditScore;
    }

    public boolean getHasUnpaidDebt() {
        return hasUnpaidDebt;
    }

    public BigDecimal getUnpaidDebtAmount() {
        return unpaidDebtAmount;
    }

    public String getStatus() {
        return status;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }
}
