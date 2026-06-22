package integrado.prog2.services;

import integrado.prog2.entities.Usuario;
import integrado.prog2.enums.Rol;
import integrado.prog2.exception.EntidadNoEncontradaException;
import integrado.prog2.exception.MailDuplicadoException;

import java.util.ArrayList;
import java.util.List;

public class UsuarioService {

    private final List<Usuario> usuarios;

    private Long nextId;

    public UsuarioService() {
        usuarios = new ArrayList<>();
        nextId = 1L;
    }

    public Usuario crear(String nombre, String apellido, String mail, String celular, String password, Rol rol) throws MailDuplicadoException {

        validarMail(mail);

        Usuario usuario = new Usuario(nextId++, nombre, apellido, mail, celular, password, rol);

        usuarios.add(usuario);

        return usuario;
    }

    private void validarMail(String mail) throws MailDuplicadoException {

        for(Usuario u : usuarios) {

            if(!u.isEliminado() && u.getMail().equalsIgnoreCase(mail)) {
                throw new MailDuplicadoException("El mail ya existe");
            }
        }
    }

    public void editar(Long id, String nombre, String apellido, String mail, String celular) throws EntidadNoEncontradaException, MailDuplicadoException {

        Usuario usuario = buscarPorId(id);

        for(Usuario u : usuarios) {

            if(!u.getId().equals(id) && !u.isEliminado() && u.getMail().equalsIgnoreCase(mail)) {
                throw new MailDuplicadoException("El mail ya existe");
            }
        }

        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setMail(mail);
        usuario.setCelular(celular);
    }

    public List<Usuario> listar() {

        List<Usuario> resultado = new ArrayList<>();

        for(Usuario u : usuarios) {

            if(!u.isEliminado()) {
                resultado.add(u);
            }
        }

        return resultado;
    }

    public Usuario buscarPorId(Long id) throws EntidadNoEncontradaException {

        for(Usuario u : usuarios) {

            if(u.getId().equals(id) && !u.isEliminado()) {

                return u;
            }
        }

        throw new EntidadNoEncontradaException("Usuario no encontrado");
    }

    public void eliminar(Long id) throws EntidadNoEncontradaException {

        Usuario usuario = buscarPorId(id);

        usuario.setEliminado(true);
    }
}