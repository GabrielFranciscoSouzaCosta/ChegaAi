
package recurso;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "time")
public class Time {
    String nome;
    int ano;

    public Time() {
    }

    public Time(String nome, int ano) {
        this.nome = nome;
        this.ano = ano;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }
    
    
    
    
}
