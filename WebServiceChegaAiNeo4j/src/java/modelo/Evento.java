
package modelo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="evento")
public class Evento {
    int id;
    String titulo;
    String descricao;
    String data;
    String endereco;
            

    public Evento(int id, String titulo, String descricao, String data, String endereco) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.data = data;
        this.endereco = endereco;
    }
    
    public Evento(String titulo, String descricao, String data, String endereco) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.data = data;
        this.endereco = endereco;
    }
    
    public Evento(){
    
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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
