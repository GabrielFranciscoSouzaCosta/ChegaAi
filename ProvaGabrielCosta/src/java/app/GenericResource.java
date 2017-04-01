/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import recurso.Operacao;
import recurso.Saida;
import recurso.Time;


/**
 * REST Web Service
 *
 * @author gabriel
 */
@Path("generic")
public class GenericResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of GenericResource
     */
    public GenericResource() {
    }

    /**
     * Retrieves representation of an instance of app.GenericResource
     * @param x
     * @param y
     * @return an instance of java.lang.String
     */
    
    // questao 1
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("questao1/{nome}/{sobrenome}/{x}/{y}")
    public String questao1(@PathParam("nome") String nome,@PathParam("sobrenome") String sobrenome,@PathParam("y") String y,@PathParam("x") String x){
     
        int num2  = Integer.parseInt(x);
        int num1 = Integer.parseInt(y);
        int media = (num1+num2)/2;
        
        
        return "Nome: " + nome + " " + sobrenome + " nota: " + " " +media; 
        
    }
    
    //questao2
    @POST
    @Consumes(MediaType.TEXT_XML)
    @Path("questao2")
    public String time(Time time) {
        
        String nome = time.getNome();
        int ano = time.getAno();
        if (nome.equals("internacional") && ano == 1909){
            return "Campeao de tudo";
        }else{
            return "informe um time melhor";
        }
        
    }
    
    //questao 3
    @Path("/questao3")
    @PUT
    @Consumes(MediaType.TEXT_XML)
    @Produces(MediaType.TEXT_XML)
    public Contato getContatoXML(Contato contato) {
        String aux = contato.getNome();
        String nome = contato.getSobrenome();
        String sobrenome = aux;
        
        contato.setNome(nome);
        contato.setSobrenome(sobrenome);
        return contato;
    }
    //questao 4
    @Path("/questao4")
    @PUT
    @Consumes(MediaType.TEXT_XML)
    @Produces(MediaType.TEXT_XML)
    public Saida entrada(Operacao operacao) {
        
        int x = operacao.getX();
        int y = operacao.getY();
        
        
        Saida saida = new Saida();
        saida.setDivisao(y/x);
        saida.setMultiplicacao(y*x);
        saida.setSoma(x+y);
        saida.setSubtracao(x-y);
        
        return saida;
    }
    
    
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public String getXml() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of GenericResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }
}
