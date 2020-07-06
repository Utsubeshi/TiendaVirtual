package com.mitienda.tiendavirtual.model;

public class Usuario  {

    private String Id;
    private String nombres;
    private String paterno;
    private String materno;
    private String DNI;
    private String telefono;
    private String email;
    private String direccion;

    public Usuario(String nombres, String paterno, String materno, String DNI, String telefono, String direccion) {
        this.nombres = nombres;
        this.paterno = paterno;
        this.materno = materno;
        this.DNI = DNI;
        this.telefono = telefono;
        this.email = email;
        this.direccion = direccion;
    }

    public Usuario (){}

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getPaterno() {
        return paterno;
    }

    public void setPaterno(String paterno) {
        this.paterno = paterno;
    }

    public String getMaterno() {
        return materno;
    }

    public void setMaterno(String materno) {
        this.materno = materno;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
