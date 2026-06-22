package integrado.prog2.entities;

import integrado.prog2.enums.Estado;
import integrado.prog2.enums.FormaPago;
import integrado.prog2.exception.StockInsuficienteException;
import integrado.prog2.interfaces.Calculable;

import java.time.LocalDate;
import java.util.ArrayList;

public class Pedido extends Base implements Calculable {

    private Usuario usuario;
    private LocalDate fecha;
    private Estado estado;
    private double total;
    private FormaPago formaPago;
    private ArrayList<DetallePedido> detalles;

    public Pedido(Long id, Usuario usuario) {
        super(id);
        this.usuario = usuario;
        this.fecha = LocalDate.now();
        this.estado = Estado.PENDIENTE;
        this.detalles = new ArrayList<>();
    }

    public Usuario getUsuario() { return usuario; }

    public Estado getEstado() { return estado; }

    public FormaPago getFormaPago() { return formaPago; }

    public double getTotal() { return total; }

    public LocalDate getFecha() { return fecha; }

    public void setEstado(Estado estado) {
        this.estado = this.estado;
    }

    public  void setFormaPago(FormaPago formaPago) {
        this.formaPago = formaPago;
    }

    public void addDetallePedido(Long idDetalle, int cantidad, Producto producto) throws StockInsuficienteException {

        if(cantidad <= 0) {
            throw new StockInsuficienteException("La cantidad debe ser mayor a 0");
        }

        if(cantidad > producto.getStock()) {
            throw new StockInsuficienteException("Stock insuficiente. Disponible: " + producto.getStock());
        }

        DetallePedido detalle = new DetallePedido(idDetalle, cantidad, producto);

        detalles.add(detalle);

        producto.setStock(producto.getStock() - cantidad);

        calcularTotal();
    }

    public DetallePedido findDetallePedidoByProducto(
            Producto producto) {

        for(DetallePedido d : detalles) {
            if(d.getProducto().equals(producto)) {
                return d;
            }
        }
        return null;
    }

    public void eliminarDetalles() {

        for(DetallePedido d : detalles) {
            d.setEliminado(true);
        }
    }

    @Override
    public void calcularTotal() {

        total = 0;

        for(DetallePedido d : detalles) {
            total += d.getSubtotal();
        }
    }

    @Override
    public String toString() {
        return String.format(
                "ID:%d | Usuario:%s | Estado:%s | Pago:%s | Total:$%.2f | Fecha:%s",
                id,
                usuario.getMail(),
                estado,
                formaPago,
                total,
                fecha
        );
    }
}