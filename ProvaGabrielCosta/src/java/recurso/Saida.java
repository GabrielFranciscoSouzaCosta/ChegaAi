/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recurso;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="saida")
public class Saida {
    int soma;
    int subtracao;
    int divisao;
    int multiplicacao;

    public int getSoma() {
        return soma;
    }

    public void setSoma(int soma) {
        this.soma = soma;
    }

    public int getSubtracao() {
        return subtracao;
    }

    public void setSubtracao(int subtracao) {
        this.subtracao = subtracao;
    }

    public int getDivisao() {
        return divisao;
    }

    public void setDivisao(int divisao) {
        this.divisao = divisao;
    }

    public int getMultiplicacao() {
        return multiplicacao;
    }

    public void setMultiplicacao(int multiplicacao) {
        this.multiplicacao = multiplicacao;
    }
    
    
}
