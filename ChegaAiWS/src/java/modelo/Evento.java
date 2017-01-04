package modelo;
public class Evento {
    
    int idEvento;
    String descricao;
    int idUsuario;
    String data;
    String endereco;

    public Evento() {
    }

    public Evento(int idEvento, String descricao, int idUsuario, String data, String endereco) {
        this.idEvento = idEvento;
        this.descricao = descricao;
        this.idUsuario = idUsuario;
        this.data = data;
        this.endereco = endereco;
    }

    public int getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}
