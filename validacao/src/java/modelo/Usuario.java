package modelo;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Usuario")
public class Usuario {
    
    int id;
    String nome;

    public Usuario(String nome) {
        this.nome = nome;
    }   
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
