package com.mycompany.minibodega;

import java.util.*;

public class Inventario {

    private HashMap<String, Producto> productos;
    private Hashtable<String, Empleado> empleados;
    private HashMap<String, Cliente> clientes;
    private List<Venta> ventas;
    private List<Gasto> gastos;

    public Inventario() {
        productos = new HashMap<>();
        empleados = new Hashtable<>();
        clientes = new HashMap<>();
        ventas = new ArrayList<>();
        gastos = new ArrayList<>();

        cargarProductosIniciales();
        cargarEmpleadosIniciales();
        cargarClientesIniciales();
    }

    private void cargarProductosIniciales() {
        productos.put("REF001", new Producto("Leche", 3500, 20));
        productos.put("REF002", new Producto("Arroz", 2800, 30));
        productos.put("REF003", new Producto("Pasta", 2500, 25));
        productos.put("REF004", new Producto("Aceite", 9000, 15));
        productos.put("REF005", new Producto("Frijol", 4000, 18));
        productos.put("REF006", new Producto("Azucar", 2700, 22));
        productos.put("REF007", new Producto("Sal", 1000, 35));
        productos.put("REF008", new Producto("Mantequilla", 6000, 12));
        productos.put("REF009", new Producto("Mayonesa", 3500, 14));
        productos.put("REF010", new Producto("Salsa de Tomate", 3000, 17));
    }

    private void cargarEmpleadosIniciales() {
        empleados.put("123456789", new Empleado("Carlos Pérez", "123456789", "Cajero", "3101234567", "Cra 1 #23-45"));
        empleados.put("987654321", new Empleado("Laura Gómez", "987654321", "Vendedor", "3129876543", "Cra 2 #12-34"));
        empleados.put("456789123", new Empleado("Andrés Ruiz", "456789123", "Domiciliario", "3134567890", "Cra 3 #45-67"));
    }

    private void cargarClientesIniciales() {
        clientes.put("1122334455", new Cliente("Ana Torres", "1122334455", "3112233445", "Calle 5 #67-89"));
        clientes.put("2233445566", new Cliente("Pedro Salas", "2233445566", "3123344556", "Calle 10 #12-34"));
        clientes.put("3344556677", new Cliente("Sandra Ríos", "3344556677", "3134455667", "Cra 6 #78-90"));
        clientes.put("4455667788", new Cliente("Luis Méndez", "4455667788", "3145566778", "Cra 7 #21-43"));
    }

    public void mostrarProductos() {
        System.out.println("");
        System.out.println("=== LISTA DE PRODUCTOS ===");
        System.out.println("");
        for (Map.Entry<String, Producto> entry : productos.entrySet()) {
            System.out.println("Referencia: " + entry.getKey());
            System.out.println(entry.getValue());
            System.out.println("");
        }
    }

    public void registrarVenta(String ref, int cantidad, String cedula) {

        List<Producto> lista = new ArrayList<>(productos.values());

        if (!productos.containsKey(ref)) {
            System.out.println("Producto inválido.");
            return;
        }

        if (productos.get(ref).getCantidad() < cantidad) {
            System.out.println("No hay suficientes unidades.");
            return;
        }

        productos.get(ref).reducirCantidad(cantidad);

        Map<Producto, Integer> productosVendidos = new HashMap<>();
        productosVendidos.put(productos.get(ref), cantidad);

        Venta venta = new Venta(productosVendidos, clientes.get(cedula));
        ventas.add(venta);

        if (clientes.get(cedula) != null) {
            clientes.get(cedula).agregarCompra(venta);
        }

        System.out.println("Venta registrada con éxito:");
        System.out.println(venta);
    }

    public void aumentarCantidad(String ref, int cantidad) {
        if (!productos.containsKey(ref)) {
            System.out.println("Producto inválido.");
            return;
        }

        productos.get(ref).aumentarCantidad(cantidad);
        System.out.println("Se agregaron " + cantidad + " unidades. "
                + "Datos actualizados: ");
        System.out.println(productos.get(ref));
    }

    public void registrarPedido(String codigoEmpleado, String codigoCliente, Map<String, Integer> referenciasYCantidades) {
        Empleado empleado = empleados.get(codigoEmpleado);
        Cliente cliente = clientes.get(codigoCliente);

        if (empleado == null) {
            System.out.println("Empleado no encontrado.");
            return;
        }

        if (cliente == null) {
            System.out.println("Cliente no encontrado.");
            return;
        }
        
        Map<Producto, Integer> productosPedido = new HashMap<>();
        boolean pedidoValido = true;

        for (Map.Entry<String, Integer> entry : referenciasYCantidades.entrySet()) {
            Producto producto = productos.get(entry.getKey());
            if (producto == null || producto.getCantidad() < entry.getValue()) {
                pedidoValido = false;
                break;
            }
            productosPedido.put(producto, entry.getValue());
        }

        if (!pedidoValido) {
            System.out.println("Pedido no válido. Revise cantidades y referencias.");
            return;
        }

        for (Map.Entry<Producto, Integer> entry : productosPedido.entrySet()) {
            entry.getKey().reducirCantidad(entry.getValue());
        }

        Pedido pedido = new Pedido(cliente, empleado, productosPedido, false); 
        cliente.agregarCompra(new Venta(productosPedido, cliente)); 
        ventas.add(new Venta(productosPedido, cliente));

        System.out.println("Pedido registrado exitosamente:");
        System.out.println(pedido);
    }

    public void registrarGasto(String descripcion, double valor) {
        Gasto gasto = new Gasto(descripcion, valor);
        gastos.add(gasto);
        System.out.println("Gasto registrado con éxito:");
        System.out.println(gasto);
    }

    public void mostrarReporteDiario() {

        System.out.println("--- Ventas ---");
        double totalVentas = 0;
        for (Venta v : ventas) {
            System.out.println(v);
            System.out.println();
            totalVentas += v.getTotal();
        }
        System.out.println("Total en ventas: $" + totalVentas);

        System.out.println("\n--- Gastos ---");
        double totalGastos = 0;
        for (Gasto g : gastos) {
            System.out.println(g);
            totalGastos += g.getValor();
        }
        System.out.println("Total en gastos: $" + totalGastos);

        double ganancia = totalVentas - totalGastos;
        System.out.println("Suma de efectivo que hay es : $" + ganancia);

    }

    public void mostrarEmpleados() {
        for (Map.Entry<String, Empleado> entry : empleados.entrySet()) {
            System.out.println("Empleado : " + entry.getKey());
            System.out.println(entry.getValue());
            System.out.println("");

        }
    }

    public void agregarEmpleado(String codigo, String nombre, String cedula, String cargo, String numeroContacto, String direccion) {
        empleados.put(codigo, new Empleado(nombre, cedula, cargo, numeroContacto, direccion));
        System.out.println("Empleado agregado con exito.");
        System.out.println(empleados.get(codigo));
    }

    public void mostrarClientes() {
        for (Map.Entry<String, Cliente> entry : clientes.entrySet()) {
            System.out.println("Cliente : " + entry.getKey());
            System.out.println(entry.getValue());
            System.out.println("");
        }
    }

    public void agregarCliente(String codigo, String nombre, String cedula, String numeroContacto, String direccion) {
        clientes.put(codigo, new Cliente(nombre, cedula, numeroContacto, direccion));
        System.out.println("Cliente Agregado con exito");
        System.out.println(clientes.get(codigo));
    }

    public void eliminarCliente(String codigo) {
        if (clientes.containsKey(codigo)) {
            clientes.remove(codigo);
            System.out.println("Cliente eliminado con éxito.");
        } else {
            System.out.println("Cliente no encontrado.");
        }
    }

    public void eliminarEmpleado(String codigo) {
        if (empleados.containsKey(codigo)) {
            empleados.remove(codigo);
            System.out.println("Empleado eliminado con éxito.");
        } else {
            System.out.println("Empleado no encontrado.");
        }
    }

    public void eliminarProducto(String referencia) {
        if (productos.containsKey(referencia)) {
            productos.remove(referencia);
            System.out.println("Producto eliminado con éxito.");
        } else {
            System.out.println("Producto no encontrado.");
        }
    }

    public void agregarProducto(String referencia, String nombre, double precio, int cantidad) {
        productos.put(referencia, new Producto(nombre, precio, cantidad));
        System.out.println("Producto agregado con éxito:");
        System.out.println(productos.get(referencia));
    }

}
