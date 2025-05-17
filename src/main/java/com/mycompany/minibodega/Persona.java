package com.mycompany.minibodega;
public class Persona {
    
    private String nombre;
    private String cedula;
    private String numeroContacto;
    private String direccion;

    public Persona() {
    }

    public Persona(String nombre, String cedula, String numeroContacto, String direccion) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.numeroContacto = numeroContacto;
        this.direccion = direccion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNumeroContacto() {
        return numeroContacto;
    }

    public void setNumeroContacto(String numeroContacto) {
        this.numeroContacto = numeroContacto;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    @Override
    public String toString() {
        return nombre + " - Cedula: " + cedula;
    }
}
