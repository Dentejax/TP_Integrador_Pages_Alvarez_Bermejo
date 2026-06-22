package integrado.prog2.services;

import integrado.prog2.entities.Categoria;
import integrado.prog2.entities.Producto;
import integrado.prog2.exception.EntidadNoEncontradaException;
import integrado.prog2.exception.StockInvalidoException;

import java.util.ArrayList;
import java.util.List;

public class ProductoService {

    private final List<Producto> productos;

    private Long nextId;

    public ProductoService() {
        productos = new ArrayList<>();
        nextId = 1L;
    }

    public Producto crear(String nombre, double precio, String descripcion, int stock, String imagen, boolean disponible, Categoria categoria) throws StockInvalidoException {

        if(precio < 0) {
            throw new StockInvalidoException("Precio inválido");
        }

        if(stock < 0) {
            throw new StockInvalidoException("Stock inválido");
        }

        Producto producto = new Producto(nextId++, nombre, precio, descripcion, stock, imagen, disponible, categoria);

        productos.add(producto);

        return producto;
    }

    public List<Producto> listar() {

        List<Producto> resultado = new ArrayList<>();

        for(Producto p : productos) {

            if(!p.isEliminado()) {
                resultado.add(p);
            }
        }

        return resultado;
    }

    public Producto buscarPorId(Long id) throws EntidadNoEncontradaException {

        for(Producto p : productos) {

            if(p.getId().equals(id) && !p.isEliminado()) {
                return p;
            }
        }

        throw new EntidadNoEncontradaException("Producto no encontrado");
    }

    public void editar(Long id, double precio, int stock) throws EntidadNoEncontradaException, StockInvalidoException {

        if(precio < 0) {
            throw new StockInvalidoException("Precio inválido");
        }

        if(stock < 0) {
            throw new StockInvalidoException("Stock inválido");
        }

        Producto producto = buscarPorId(id);

        producto.setPrecio(precio);
        producto.setStock(stock);
    }

    public void eliminar(Long id) throws EntidadNoEncontradaException {

        Producto producto = buscarPorId(id);

        producto.setEliminado(true);
    }

    public List<Producto> listarPorCategoria(Categoria categoria) {

        List<Producto> resultado = new ArrayList<>();

        for(Producto p : productos) {

            if(!p.isEliminado() && p.getCategoria().equals(categoria)) {
                resultado.add(p);
            }
        }

        return resultado;
    }
}