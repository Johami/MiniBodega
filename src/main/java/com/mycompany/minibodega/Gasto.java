package com.mycompany.minibodega;
public class Gasto {
    private String descripcion;
    private double valor;

    public Gasto(String descripcion, double valor) {
        this.descripcion = descripcion;
        this.valor = valor;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getValor() {
        return valor;
    }

    @Override
    public String toString() {
        return "Gasto: " + descripcion + " - Valor: $" + valor;
    }
}