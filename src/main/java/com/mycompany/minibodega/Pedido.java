package com.mycompany.minibodega;
import java.util.*;

public class Pedido {
    private Cliente cliente;
    private Empleado empleado;
    private Map<Producto, Integer> productos;
    private double total;

    public Pedido(Cliente cliente, Empleado empleado, Map<Producto, Integer> productos) {
        this.cliente = cliente;
        this.empleado = empleado;
        this.productos = productos;
        calcularTotal();
    }

    private void calcularTotal() {
        total = 0;
        for (Map.Entry<Producto, Integer> entry : productos.entrySet()) {
            Producto producto = entry.getKey();
            int cantidad = entry.getValue();
            total += producto.getPrecio() * cantidad;
        }

        // Descuento para clientes con más de 5 compras reciben 50%
        if (cliente.getHistorialCompras().size() >= 5) {
            total *= 0.50;
        }
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public Map<Producto, Integer> getProductos() {
        return productos;
    }


    public double getTotal() {
        return total;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Pedido de " + cliente.getNombre());
        sb.append("\nAtendido por: ").append(empleado.getNombre());
        sb.append("\nProductos:");
        for (Map.Entry<Producto, Integer> entry : productos.entrySet()) {
            sb.append("\n - ").append(entry.getKey().getNombre()).append(" x").append(entry.getValue());
        }
        sb.append("\nTotal: $").append(total);
        return sb.toString();
    }
}
