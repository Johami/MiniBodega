package com.mycompany.minibodega;


import java.util.*;

public class MiniBodega {

    public static final Scanner sc = new Scanner(System.in);
    public static Inventario inventario = new Inventario();

    public static void main(String[] args) {
        mostrarMenu();
    }

    public static void mostrarMenu() {
        int opcion;
        do {
            System.out.println("========= MENÚ PRINCIPAL =========");
            System.out.println("1.  Mostrar productos");
            System.out.println("2.  Registrar venta");
            System.out.println("3.  Registrar ingreso de un producto");
            System.out.println("4.  Registrar pedido");
            System.out.println("5.  Registrar gasto");
            System.out.println("6.  Mostrar reporte diario");
            System.out.println("7.  Mostrar empleados");
            System.out.println("8.  Agregar empleado");
            System.out.println("9.  Mostrar clientes");
            System.out.println("10. Agregar cliente");
            System.out.println("11. Eliminar empleado");
            System.out.println("12. Eliminar cliente");
            System.out.println("13. Agregar producto");
            System.out.println("14. Eliminar producto");
            System.out.println("0. Salir");
            System.out.print("Elige una opción: ");
            opcion = sc.nextInt();
            sc.nextLine();
            System.out.println("");
            switch (opcion) {
                case 1:
                    inventario.mostrarProductos();
                    break;
                case 2:
                    System.out.println("Registrar Venta: ");
                    System.out.println("");
                    inventario.mostrarProductos();
                    System.out.println("Ingrese la referencia del producto que va a vender");
                    String vender = sc.next();
                    System.out.print("Ingrese cantidad a vender: ");
                    int cantidadVenta = sc.nextInt();
                    System.out.println("Clientes: ");
                    System.out.println("");
                    inventario.mostrarClientes();
                    System.out.println("");
                    System.out.println("Ingrese el codigo del comprador, si no es un clientes regular ingrese 0");
                    String cedula = sc.next();
                    inventario.registrarVenta(vender, cantidadVenta, cedula);
                    sc.nextLine();
                    break;
                case 3:
                    System.out.println("Registrar ingreso de un producto ");
                    System.out.println("");
                    inventario.mostrarProductos();
                    System.out.println("Ingrese la referencia del producto que va a registrar");
                    String registrar = sc.next();
                    System.out.print("Ingrese cantidad a ingresar: ");
                    int cantidadRegistro = sc.nextInt();
                    inventario.aumentarCantidad(registrar, cantidadRegistro);
                    break;
                case 4:
                    System.out.println("Registrar Pedido: ");

                    inventario.mostrarEmpleados();
                    System.out.print("Ingrese el código del empleado que atiende el pedido: ");
                    String codigoVendedor = sc.nextLine();

                    inventario.mostrarClientes();
                    System.out.print("Ingrese el código del cliente: ");
                    String codigoComprador = sc.nextLine();

                    Map<String, Integer> referenciasYCantidades = new HashMap<>();
                    int agregarProductos = 0;

                    do {
                        inventario.mostrarProductos();
                        System.out.print("Ingrese la referencia del producto: ");
                        String refProducto = sc.nextLine();

                        System.out.print("Ingrese la cantidad: ");
                        int cantidad = Integer.parseInt(sc.nextLine());

                        referenciasYCantidades.put(refProducto, cantidad);

                        System.out.print("¿Desea agregar otro producto? (0 = sí, 1 = no): ");
                        agregarProductos = Integer.parseInt(sc.nextLine());

                    } while (agregarProductos != 1);

                    inventario.registrarPedido(codigoVendedor, codigoComprador, referenciasYCantidades);
                    break;

                case 5:
                    System.out.println(" Registrar gasto");
                    System.out.println("");
                    System.out.println("Ingrese la información acerca del gasto");
                    String descripcionGasto = sc.nextLine();
                    System.out.println("Ingrese el valor requerido");
                    double gasto = sc.nextDouble();
                    inventario.registrarGasto(descripcionGasto, gasto);

                    break;
                case 6:

                    System.out.println("Reporte actual de las ventas y gastos: ");
                    System.out.println("");
                    inventario.mostrarReporteDiario();
                    break;
                case 7:
                    inventario.mostrarEmpleados();
                    break;
                case 8:
                    System.out.println("Agregar empleado");
                    System.out.println("");

                    System.out.println("Nombre : ");
                    String nombreEmpleado = sc.nextLine();
                    System.out.println("Cedula: ");
                    String cedulaEmpleado = sc.next();
                    System.out.println("Cargo:");
                    String cargoEmpleado = sc.next();
                    System.out.println("Numero de contacto ");
                    String numeroContactoEmpleado = sc.next();
                    System.out.println("Direccion de domicilio");
                    String direccionEmpleado = sc.nextLine();
                    System.out.println("Asignar codigo");
                    String codigoEmpleado = sc.next();
                    inventario.agregarEmpleado(codigoEmpleado, nombreEmpleado, cedulaEmpleado, cargoEmpleado, numeroContactoEmpleado, direccionEmpleado);
                    break;
                case 9:
                    inventario.mostrarClientes();
                    break;
                case 10:
                    System.out.println("Agregar Cliente");
                    System.out.println("");
                    System.out.println("Nombre : ");
                    String nombreCliente = sc.nextLine();
                    System.out.println("Cedula: ");
                    String cedulaCliente = sc.next();
                    System.out.println("Numero de contacto ");
                    String numeroContactoCliente = sc.next();
                    System.out.println("Direccion de domicilio");
                    String direccionCliente = sc.nextLine();
                    System.out.println("Asignar codigo");
                    String codigoCliente = sc.next();
                    inventario.agregarCliente(codigoCliente, nombreCliente, cedulaCliente, numeroContactoCliente, direccionCliente);
                    break;
                case 11:
                    System.out.println("Eliminar empleado");
                    System.out.println("");
                    inventario.mostrarEmpleados();
                    System.out.println("Ingrese el codigo del empleado a eliminar");
                    String codigoEmpleadoEliminar = sc.next();
                    inventario.eliminarEmpleado(codigoEmpleadoEliminar);
                    break;
                case 12:
                    System.out.println("Eliminar cliente");
                    System.out.println("");
                    inventario.mostrarClientes();
                    System.out.println("Ingrese el codigo del cliente a eliminar");
                    String codigoClienteEliminar = sc.next();
                    inventario.eliminarCliente(codigoClienteEliminar);
                    break;
                case 13:
                    System.out.println("Agregar producto");
                    System.out.println("");
                    System.out.println("Nombre del producto: ");
                    String nombreProducto = sc.nextLine();
                    System.out.println("Referencia: ");
                    String referenciaProducto = sc.next();
                    System.out.println("Precio de venta:");
                    double precioVenta = sc.nextDouble();
                    System.out.println("Cantidad:");
                    int cantidadProducto = sc.nextInt();
                    inventario.agregarProducto(referenciaProducto, nombreProducto, precioVenta, cantidadProducto);
                    break;
                case 14:
                    System.out.println("Eliminar producto");
                    System.out.println("");
                    inventario.mostrarProductos();
                    System.out.println("Ingrese la referencia del producto a eliminar");
                    String referenciaProductoEliminar = sc.next();
                    inventario.eliminarProducto(referenciaProductoEliminar);
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 0);
    }

}
