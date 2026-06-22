package integrado.prog2.entities;

public class DetallePedido extends Base {

    private int cantidad;
    private double subtotal;
    private Producto producto;

    public DetallePedido(Long id, int cantidad, Producto producto) {
        super(id);
        this.cantidad = cantidad;
        this.producto = producto;
        this.subtotal = cantidad * producto.getPrecio();
    }

    public Producto getProducto() { return producto; }

    public double getSubtotal() { return subtotal; }

    @Override
    public String toString() {
        return producto.getNombre() + " x" + cantidad + " = $" + subtotal;
    }
}