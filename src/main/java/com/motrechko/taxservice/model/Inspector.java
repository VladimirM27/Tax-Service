package com.motrechko.taxservice.model;

import java.util.Objects;

public class Inspector {
    private int idInspector;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    public int getIdInspector() {
        return idInspector;
    }

    public void setIdInspector(int idInspector) {
        this.idInspector = idInspector;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Inspector inspector = (Inspector) o;
        return idInspector == inspector.idInspector && Objects.equals(firstName, inspector.firstName) && Objects.equals(lastName, inspector.lastName) && Objects.equals(email, inspector.email) && Objects.equals(password, inspector.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idInspector, firstName, lastName, email, password);
    }

    @Override
    public String toString() {
        return "Inspector{" +
                "idInspector=" + idInspector +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
