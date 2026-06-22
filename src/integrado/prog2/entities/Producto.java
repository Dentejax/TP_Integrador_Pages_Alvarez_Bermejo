package integrado.prog2.entities;

import integrado.prog2.enums.Estado;
import integrado.prog2.enums.FormaPago;

public class Producto extends Base {

    private String nombre;
    private double precio;
    private String descripcion;
    private int stock;
    private String imagen;
    private boolean disponible;
    private Categoria categoria;
    private Estado estado;
    private double total;
    private FormaPago formaPago;

    public Producto(Long id, String nombre, double precio, String descripcion, int stock, String imagen, boolean disponible, Categoria categoria) {
        super(id);
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
        this.stock = stock;
        this.imagen = imagen;
        this.disponible = disponible;
        this.categoria = categoria;
        this.estado = Estado.PENDIENTE;
        this.formaPago = FormaPago.EFECTIVO;
        this.total = 0;
    }

    public String getNombre() { return nombre; }

    public double getPrecio() { return precio; }

    public int getStock() { return stock; }

    public double getTotal() { return total; }

    public Categoria getCategoria() { return categoria; }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public void setFormaPago(FormaPago formaPago) {
        this.formaPago = formaPago;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return String.format(
                "ID:%d | %s | $%.2f | Stock:%d | Cat:%s",
                id,
                nombre,
                precio,
                stock,
                categoria.getNombre()
        );
    }
}