package com.mycompany.minibodega;
public class Producto {
    private String nombre;
    private double precio;
    private int cantidad;

    public Producto() {
    }

    public Producto(String nombre, double precio, int cantidad) {
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    
    public void reducirCantidad(int cantidadVendida) {
        if (cantidadVendida <= cantidad) {
            this.cantidad -= cantidadVendida;
        } else {
            System.out.println("Cantidad de producto insuficiente.");
        }
    }

    public void aumentarCantidad(int cantidadAgregada) {
        if (cantidadAgregada > 0) {
            this.cantidad += cantidadAgregada;
        }
    }

    @Override
    public String toString() {
        return nombre + " - $" + precio + " - Cantidad: " + cantidad;
    }
    
    
}
