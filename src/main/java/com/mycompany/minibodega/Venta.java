package com.mycompany.minibodega;

import java.util.*;


public class Venta {
    
    private Map<Producto, Integer> productosVendidos;
    private double total;
    private Cliente cliente; 

    public Venta(Map<Producto, Integer> productosVendidos, Cliente cliente) {
        this.productosVendidos = new HashMap<>(productosVendidos);
        this.cliente = cliente;
        calcularTotal();
    }

    private void calcularTotal() {
        total = 0;
        for (Map.Entry<Producto, Integer> entry : productosVendidos.entrySet()) {
            Producto p = entry.getKey();
            int cantidad = entry.getValue();
            total += p.getPrecio() * cantidad;
        }
    }

    public Map<Producto, Integer> getProductosVendidos() {
        return productosVendidos;
    }

    public double getTotal() {
        return total;
    }

    public Cliente getCliente() {
        return cliente;
    }

    @Override
    public String toString() {
    StringBuilder sb = new StringBuilder("=== DETALLE DE VENTA ===\n");
    for (Map.Entry<Producto, Integer> entry : productosVendidos.entrySet()) {
        sb.append("- ").append(entry.getKey().getNombre())
          .append(" x").append(entry.getValue())
          .append(" ($").append(entry.getKey().getPrecio()).append(" c/u)\n");
    }
    sb.append("Total: $").append(total).append("\n");
    if (cliente != null) {
        sb.append("Cliente: ").append(cliente.getNombre());
    }
    return sb.toString();
}
}