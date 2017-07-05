package modelo;

public class Sessao {
    
    int id;
    Usuario u;

    public Sessao() {
    }
    
    public Sessao(Usuario u) {
        this.u = u;
    }
    
    public Sessao(int id, Usuario u) {
        this.id = id;
        this.u = u;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario getU() {
        return u;
    }

    public void setU(Usuario u) {
        this.u = u;
    }
        
}
