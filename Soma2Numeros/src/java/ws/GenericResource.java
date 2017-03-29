
package ws;

import javax.json.Json;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("ws")
public class GenericResource {

    @Context
    private UriInfo context;

  
    public GenericResource() {
    }
    
    // somar
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("soma/{x}/{y}")
    public int soma(@PathParam("x") String x,@PathParam("y") String y){
     
        int numero1 = Integer.parseInt(x);
        int numero2 = Integer.parseInt(y);
        
        return numero1+numero2;
    }
    
    // subtrair
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("subtrair/{x}/{y}")
    public int subtrair(@PathParam("x") String x,@PathParam("y") String y){
     
        int numero1 = Integer.parseInt(x);
        int numero2 = Integer.parseInt(y);
        
        return numero1-numero2;
    }
    
    //dividir
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("dividir/{x}/{y}")
    public int dividir(@PathParam("x") String x,@PathParam("y") String y){
     
        int numero1 = Integer.parseInt(x);
        int numero2 = Integer.parseInt(y);
        
        return numero1/numero2;
    }
    
    //multiplicar
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("multiplicar/{x}/{y}")
    public int multiplicar(@PathParam("x") String x,@PathParam("y") String y){
     
        int numero1 = Integer.parseInt(x);
        int numero2 = Integer.parseInt(y);
        
        return numero1*numero2;
    }
    
    //estou com sorte
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("estoucomsorte/{x}")
    public String estouComSorte(@PathParam("x") String x){
     
        int numero1 = Integer.parseInt(x);
        int numero2 = (int) (Math.random()*10);
        
        if(numero2 == numero1){
            return "acertou mizeravi";
        }else{
            return "errou";
        }
        
    }
    
    // nome e sobrenome com xml
    @POST
    @Consumes(MediaType.TEXT_XML)
    @Path("nome")
    public String nome(Usuario usuario) {
        return usuario.getNome() + " " +  usuario.getSobrenome();
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
