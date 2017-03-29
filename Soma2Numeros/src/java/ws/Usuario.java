
package ws;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="usuario")
class Usuario {
    String nome;
    String sobrenome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }
    
    
}
