package com.mycompany.minibodega;
import java.util.*;

public class Cliente extends Persona {
     private List<Venta> historialCompras;
    public Cliente(String nombre, String cedula, String numeroContacto, String direccion) {
        super(nombre, cedula, numeroContacto, direccion);
        this.historialCompras = new ArrayList<>();
    }

    public Cliente() {
    }
    

    // Getter y Setter de historialCompras
    public List<Venta> getHistorialCompras() {
        return historialCompras;
    }

    public void setHistorialCompras(List<Venta> historialCompras) {
        this.historialCompras = historialCompras;
    }

    public void agregarCompra(Venta venta) {
        historialCompras.add(venta);
    }

    @Override
    public String toString() {
        return super.toString() + " - Historial de compras: " + historialCompras.size() + " compras";
    }
}
