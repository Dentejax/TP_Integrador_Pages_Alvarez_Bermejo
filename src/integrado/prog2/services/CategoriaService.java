package integrado.prog2.services;

import integrado.prog2.entities.Categoria;
import integrado.prog2.exception.EntidadNoEncontradaException;

import java.util.ArrayList;
import java.util.List;

public class CategoriaService {

    private final List<Categoria> categorias;
    private Long nextId;

    public CategoriaService() {
        categorias = new ArrayList<>();
        nextId = 1L;
    }

    public Categoria crear(String nombre, String descripcion) {

        if(nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }

        for(Categoria c : categorias) {

            if(!c.isEliminado() && c.getNombre().equalsIgnoreCase(nombre)) {
                throw new IllegalArgumentException("Ya existe una categoría con ese nombre");
            }
        }

        Categoria categoria = new Categoria(nextId++, nombre, descripcion);

        categorias.add(categoria);

        return categoria;
    }

    public List<Categoria> listar() {

        List<Categoria> resultado = new ArrayList<>();

        for(Categoria c : categorias) {

            if(!c.isEliminado()) {
                resultado.add(c);
            }
        }

        return resultado;
    }

    public Categoria buscarPorId(Long id) throws EntidadNoEncontradaException {

        for(Categoria c : categorias) {

            if(c.getId().equals(id)
                    && !c.isEliminado()) {

                return c;
            }
        }

        throw new EntidadNoEncontradaException("Categoría no encontrada");
    }

    public void editar(Long id, String nombre, String descripcion) throws EntidadNoEncontradaException {

        Categoria categoria = buscarPorId(id);

        categoria.setNombre(nombre);
        categoria.setDescripcion(descripcion);
    }

    public void eliminar(Long id) throws EntidadNoEncontradaException {

        Categoria categoria = buscarPorId(id);

        categoria.setEliminado(true);
    }
}