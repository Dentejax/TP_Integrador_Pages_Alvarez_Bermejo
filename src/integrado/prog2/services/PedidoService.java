package integrado.prog2.services;

import integrado.prog2.entities.Pedido;
import integrado.prog2.entities.Producto;
import integrado.prog2.entities.Usuario;
import integrado.prog2.enums.Estado;
import integrado.prog2.enums.FormaPago;
import integrado.prog2.exception.EntidadNoEncontradaException;
import integrado.prog2.exception.StockInsuficienteException;

import java.util.ArrayList;
import java.util.List;

public class PedidoService {

    private final List<Pedido> pedidos;
    private Long nextId;
    private Long nextDetalleId;

    public PedidoService() {
        pedidos = new ArrayList<>();
        nextId = 1L;
        nextDetalleId = 1L;
    }

    public Pedido crear(Usuario usuario) {

        Pedido pedido = new Pedido(nextId++, usuario);

        pedidos.add(pedido);

        return pedido;
    }

    public void agregarDetalle(Pedido pedido, Producto producto, int cantidad) throws StockInsuficienteException {

        pedido.addDetallePedido(nextDetalleId++, cantidad, producto);

        pedido.calcularTotal();
    }

    public List<Pedido> listar() {

        List<Pedido> resultado = new ArrayList<>();

        for(Pedido p : pedidos) {

            if(!p.isEliminado()) {
                resultado.add(p);
            }
        }

        return resultado;
    }

    public void actualizarEstado(Long id, Estado estado) throws EntidadNoEncontradaException {

        Pedido pedido = buscarPorId(id);

        pedido.setEstado(estado);
    }

    public void actualizarFormaPago(Long id, FormaPago formaPago) throws EntidadNoEncontradaException {

        Pedido pedido = buscarPorId(id);

        pedido.setFormaPago(formaPago);
    }

    public Pedido buscarPorId(Long id) throws EntidadNoEncontradaException {

        for(Pedido p : pedidos) {

            if(p.getId().equals(id) && !p.isEliminado()) {
                return p;
            }
        }

        throw new EntidadNoEncontradaException("Pedido no encontrado");
    }

    public void eliminar(Long id) throws EntidadNoEncontradaException {

        Pedido pedido = buscarPorId(id);

        pedido.setEliminado(true);
    }
}