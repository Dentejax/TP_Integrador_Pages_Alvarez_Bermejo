package integrado.prog2.entities;

import integrado.prog2.enums.Rol;

public class Usuario extends Base {

    private String nombre;
    private String apellido;
    private String mail;
    private String celular;
    private String password;
    private Rol rol;

    public Usuario(Long id, String nombre, String apellido, String mail, String celular, String password, Rol rol) {
        super(id);
        this.nombre = nombre;
        this.apellido = apellido;
        this.mail = mail;
        this.celular = celular;
        this.password = password;
        this.rol = rol;
    }

    public String getNombre() { return nombre; }

    public String getApellido() { return apellido; }

    public String getMail() { return mail; }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    @Override
    public String toString() {
        return String.format(
                "ID:%d | %s %s | %s | %s",
                id,
                nombre,
                apellido,
                mail,
                rol
        );
    }
}