package integrado.prog2.entities;

import java.time.LocalDateTime;

public abstract class Base {

    protected Long id;
    protected boolean eliminado;
    protected LocalDateTime createdAt;

    public Base(Long id) {
        this.id = id;
        this.eliminado = false;
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public boolean isEliminado() {
        return eliminado;
    }

    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }
}