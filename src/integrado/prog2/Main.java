package integrado.prog2;

import integrado.prog2.entities.Categoria;
import integrado.prog2.entities.Producto;
import integrado.prog2.entities.Usuario;
import integrado.prog2.entities.Pedido;
import integrado.prog2.exception.EntidadNoEncontradaException;
import integrado.prog2.exception.MailDuplicadoException;
import integrado.prog2.services.CategoriaService;
import integrado.prog2.services.ProductoService;
import integrado.prog2.services.UsuarioService;
import integrado.prog2.services.PedidoService;
import integrado.prog2.enums.*;

import java.util.Scanner;

public class Main {

    private static final Scanner sc = new Scanner(System.in);

    private static final CategoriaService categoriaService = new CategoriaService();
    private static final ProductoService productoService = new ProductoService();
    private static final UsuarioService usuarioService = new UsuarioService();
    private static final PedidoService pedidoService = new PedidoService();

    public static void main(String[] args) {

        //Hecho sin JDBC, por lo tanto el paquete "config" estara vacio
        int opcion;

        do {

            System.out.println("\n===== FOOD STORE =====");
            System.out.println("1. Categorias");
            System.out.println("2. Productos");
            System.out.println("3. Usuarios");
            System.out.println("4. Pedidos");
            System.out.println("0. Salir");

            opcion = leerInt("Seleccione una opcion: ");

            switch (opcion) {

                case 1:
                    menuCategorias();
                    break;

                case 2:
                    menuProductos();
                    break;

                case 3:
                    menuUsuarios();
                    break;

                case 4:
                    menuPedidos();
                    break;

                case 0:
                    System.out.println("Programa finalizado");
                    break;

                default:
                    System.out.println("Opcion invalida");
            }

        } while (opcion != 0);
    }

    private static void menuCategorias() {

        int opcion;

        do {

            System.out.println("\n=== CATEGORIAS ===");
            System.out.println("1. Listar");
            System.out.println("2. Crear");
            System.out.println("3. Editar");
            System.out.println("4. Eliminar");
            System.out.println("0. Volver");

            opcion = leerInt("Seleccione: ");

            switch (opcion) {

                case 1:
                    listarCategorias();
                    break;

                case 2:
                    crearCategoria();
                    break;

                case 3:
                    editarCategoria();
                    break;

                case 4:
                    eliminarCategoria();
                    break;

                case 0:
                    break;

                default:
                    System.out.println("Opcion invalida");
            }

        } while (opcion != 0);
    }

    private static void listarCategorias() {

        if(categoriaService.listar().isEmpty()) {
            System.out.println("No hay categorias cargadas");
            return;
        }

        for(Categoria c : categoriaService.listar()) {
            System.out.println(c);
        }
    }

    private static void crearCategoria() {

        try {

            System.out.println("Nombre: ");
            String nombre = sc.nextLine();

            System.out.println("Descripcion: ");
            String descripcion = sc.nextLine();

            Categoria categoria = categoriaService.crear(nombre, descripcion);

            System.out.println("Categoria creada. ID = " + categoria.getId());

        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    private static void editarCategoria() {

        if(categoriaService.listar().isEmpty()) {
            System.out.println("No hay categorías cargadas");
            return;
        }

        try {

            listarCategorias();

            Long id = leerLong("ID a editar: ");

            Categoria categoria = categoriaService.buscarPorId(id);

            System.out.println("Nuevo nombre: ");
            String nombre = sc.nextLine();

            System.out.println("Nueva descripcion: ");
            String descripcion = sc.nextLine();

            categoriaService.editar(id, nombre, descripcion);

            System.out.println("Categoria actualizada");

        } catch (EntidadNoEncontradaException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void eliminarCategoria() {

        if(categoriaService.listar().isEmpty()) {

            System.out.println("No hay categorías cargadas");
            return;
        }

        try {

            listarCategorias();

            Long id = leerLong("ID a eliminar: ");

            categoriaService.eliminar(id);

            System.out.println("Categoria eliminada");

        } catch (EntidadNoEncontradaException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void menuProductos() {

        int opcion;

        do {

            System.out.println("\n=== PRODUCTOS ===");
            System.out.println("1. Listar");
            System.out.println("2. Crear");
            System.out.println("3. Editar");
            System.out.println("4. Eliminar");
            System.out.println("0. Volver");

            opcion = leerInt("Seleccione: ");

            switch(opcion) {

                case 1:
                    listarProductos();
                    break;

                case 2:
                    crearProducto();
                    break;

                case 3:
                    editarProducto();
                    break;

                case 4:
                    eliminarProducto();
                    break;

                case 0:
                    break;

                default:
                    System.out.println("Opción inválida");
            }

        } while(opcion != 0);
    }

    private static void listarProductos() {

        if(productoService.listar().isEmpty()) {
            System.out.println("No hay productos cargados");
            return;
        }

        for(Producto p : productoService.listar()) {
            System.out.println(p);
        }
    }

    private static void crearProducto() {

        try {

            if(categoriaService.listar().isEmpty()) {
                System.out.println("Debe crear una categoría primero");
                return;
            }

            System.out.println("\nCategorías:");

            listarCategorias();

            Long idCategoria = leerLong("ID categoría: ");

            Categoria categoria = categoriaService.buscarPorId(idCategoria);

            System.out.println("Nombre: ");
            String nombre = sc.nextLine();

            System.out.println("Descripción: ");
            String descripcion = sc.nextLine();

            System.out.println("Precio: ");
            double precio = Double.parseDouble(sc.nextLine());

            System.out.println("Stock: ");
            int stock = Integer.parseInt(sc.nextLine());

            System.out.println("Imagen: ");
            String imagen = sc.nextLine();

            System.out.println("Disponible (true/false): ");
            boolean disponible = Boolean.parseBoolean(sc.nextLine());

            Producto producto = productoService.crear(nombre, precio, descripcion, stock, imagen, disponible, categoria);

            System.out.println("Producto creado ID: " + producto.getId());

        } catch(Exception e) {

            System.out.println("ERROR: " + e.getMessage());
        }
    }

    private static void editarProducto() {

        if(productoService.listar().isEmpty()) {
            System.out.println("No hay productos cargados");
            return;
        }

        try {

            Long id = leerLong("ID producto: ");

            Producto producto = productoService.buscarPorId(id);

            System.out.println("Nuevo precio: ");
            double precio = Double.parseDouble(sc.nextLine());

            System.out.println("Nuevo stock: ");
            int stock = Integer.parseInt(sc.nextLine());

            productoService.editar(id, precio, stock);

        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void eliminarProducto() {

        if(productoService.listar().isEmpty()) {
            System.out.println("No hay productos cargados");
            return;
        }

        try {

            listarProductos();

            Long id = leerLong("ID producto: ");

            productoService.eliminar(id);

            System.out.println("Producto eliminado");

        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void menuUsuarios() {

        int opcion;

        do {

            System.out.println("\n=== USUARIOS ===");
            System.out.println("1. Listar");
            System.out.println("2. Crear");
            System.out.println("3. Editar");
            System.out.println("4. Eliminar");
            System.out.println("0. Volver");

            opcion = leerInt("Seleccione: ");

            switch(opcion) {

                case 1:
                    listarUsuarios();
                    break;

                case 2:
                    crearUsuario();
                    break;

                case 3:
                    editarUsuario();
                    break;

                case 4:
                    eliminarUsuario();
                    break;

                case 0:
                    break;

                default:
                    System.out.println("Opción inválida");
            }

        } while(opcion != 0);
    }

    private static void listarUsuarios() {

        if(usuarioService.listar().isEmpty()) {
            System.out.println("No hay usuarios cargados");
            return;
        }

        for(Usuario u : usuarioService.listar()) {
            System.out.println(u);
        }
    }

    private static void crearUsuario() {

        try {

            System.out.println("Nombre: ");
            String nombre = sc.nextLine();

            System.out.println("Apellido: ");
            String apellido = sc.nextLine();

            System.out.println("Mail: ");
            String mail = sc.nextLine();

            System.out.println("Celular: ");
            String celular = sc.nextLine();

            System.out.println("Password: ");
            String password = sc.nextLine();

            System.out.println("Rol:");
            System.out.println("1. ADMIN");
            System.out.println("2. USUARIO");

            int opcionRol = leerInt("Seleccione: ");

            Rol rol;

            if(opcionRol == 1) {
                rol = Rol.ADMIN;
            } else {
                rol = Rol.USUARIO;
            }

            Usuario usuario = usuarioService.crear(nombre, apellido, mail, celular, password, rol);

            System.out.println("Usuario creado ID: " + usuario.getId());

        } catch(Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    private static void editarUsuario() {

        if(usuarioService.listar().isEmpty()) {
            System.out.println("No hay usuarios cargados");
            return;
        }

        try {

            listarUsuarios();

            Long id = leerLong("ID usuario: ");

            Usuario usuario = usuarioService.buscarPorId(id);

            System.out.println("Usuario encontrado:");
            System.out.println(usuario);

            System.out.println("Nombre: ");
            String nombre = sc.nextLine();

            System.out.println("Apellido: ");
            String apellido = sc.nextLine();

            System.out.println("Mail: ");
            String mail = sc.nextLine();

            System.out.println("Celular: ");
            String celular = sc.nextLine();

            usuarioService.editar(id, nombre, apellido, mail, celular);

            System.out.println("Usuario actualizado");

        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void eliminarUsuario() {

        if(usuarioService.listar().isEmpty()) {
            System.out.println("No hay usuarios cargados");
            return;
        }

        try {

            listarUsuarios();

            Long id = leerLong("ID usuario: ");

            Usuario usuario = usuarioService.buscarPorId(id);

            System.out.println(usuario);

            System.out.println("Confirmar (S/N): ");
            String respuesta = sc.nextLine();

            if(respuesta.equalsIgnoreCase("S")) {

                usuarioService.eliminar(id);

                System.out.println("Usuario eliminado");
            }

        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void menuPedidos() {

        int opcion;

        do {

            System.out.println("\n=== PEDIDOS ===");
            System.out.println("1. Listar");
            System.out.println("2. Crear");
            System.out.println("3. Actualizar Estado");
            System.out.println("4. Eliminar");
            System.out.println("0. Volver");

            opcion = leerInt("Seleccione: ");

            switch(opcion) {

                case 1:
                    listarPedidos();
                    break;

                case 2:
                    crearPedido();
                    break;

                case 3:
                    actualizarEstadoPedido();
                    break;

                case 4:
                    eliminarPedido();
                    break;
            }

        } while(opcion != 0);
    }

    private static void listarPedidos() {

        if(pedidoService.listar().isEmpty()) {
            System.out.println("No hay pedidos cargados");
            return;
        }

        for(Pedido p : pedidoService.listar()) {
            System.out.println(p);
        }
    }

    private static void crearPedido() {

        try {

            if(usuarioService.listar().isEmpty()) {
                System.out.println("Debe existir un usuario");
                return;
            }

            if(productoService.listar().isEmpty()) {
                System.out.println("Debe existir un producto");
                return;
            }

            listarUsuarios();

            Long idUsuario = leerLong("ID usuario: ");

            Usuario usuario = usuarioService.buscarPorId(idUsuario);
            Pedido pedido = pedidoService.crear(usuario);

            String continuar;

            do {

                listarProductos();

                Long idProducto = leerLong("ID producto: ");

                Producto producto = productoService.buscarPorId(idProducto);

                int cantidad = leerInt("Cantidad: ");

                pedidoService.agregarDetalle(pedido, producto, cantidad);

                System.out.println("Agregar otro producto? (S/N): ");

                continuar = sc.nextLine();

            } while(
                    continuar.equalsIgnoreCase("S")
            );

            System.out.println("Pedido creado correctamente");

        } catch(Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    private static void actualizarEstadoPedido() {

        if(pedidoService.listar().isEmpty()) {
            System.out.println("No hay pedidos cargados");
            return;
        }

        try {

            listarPedidos();

            Long id = leerLong("ID pedido: ");

            Pedido pedido = pedidoService.buscarPorId(id);

            System.out.println(pedido);

            System.out.println("1. PENDIENTE");
            System.out.println("2. CONFIRMADO");
            System.out.println("3. TERMINADO");
            System.out.println("4. CANCELADO");

            int opcion = leerInt("Estado: ");

            Estado estado;

            switch(opcion) {

                case 2:
                    estado = Estado.CONFIRMADO;
                    break;

                case 3:
                    estado = Estado.TERMINADO;
                    break;

                case 4:
                    estado = Estado.CANCELADO;
                    break;

                default:
                    estado = Estado.PENDIENTE;
            }

            pedidoService.actualizarEstado(id, estado);

            System.out.println("Estado actualizado");

        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void eliminarPedido() {

        if(pedidoService.listar().isEmpty()) {
            System.out.println("No hay pedidos cargados");
            return;
        }

        try {

            listarPedidos();

            Long id = leerLong("ID pedido: ");

            Pedido pedido = pedidoService.buscarPorId(id);

            System.out.println(pedido);

            System.out.println("Confirmar (S/N): ");
            String respuesta = sc.nextLine();

            if(respuesta.equalsIgnoreCase("S")) {
                pedidoService.eliminar(id);
                System.out.println("Pedido eliminado");
            }

        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static int leerInt(String mensaje) {

        while (true) {

            try {

                System.out.println(mensaje);

                int valor = Integer.parseInt(sc.nextLine());

                return valor;

            } catch (Exception e) {
                System.out.println("Ingrese un numero valido");
            }
        }
    }

    private static Long leerLong(String mensaje) {

        while (true) {

            try {

                System.out.println(mensaje);

                Long valor = Long.parseLong(sc.nextLine());

                return valor;

            } catch (Exception e) {
                System.out.println("Ingrese un ID valido");
            }
        }
    }
}