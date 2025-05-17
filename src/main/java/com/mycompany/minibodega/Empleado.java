package com.mycompany.minibodega;
public class Empleado extends Persona {
    private String cargo;
    
    public Empleado(String nombre, String cedula, String cargo, String numeroContacto, String direccion) {
        super(nombre, cedula, numeroContacto, direccion);
        this.cargo = cargo;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
    
     @Override
    public String toString() {
        return super.toString() + " - Cargo: " + cargo;
    }
}
