package ws;
import DAO.EventoDAO;
import com.google.gson.Gson;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import modelo.Evento;

@Path("evento")
public class EventoWS {

    @Context
    private UriInfo context;

    public EventoWS() {
    }

    @GET
    @Produces(MediaType.APPLICATION_XML)
    public String getXml() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }
    // listar eventos
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("listar")
    public String listarEventos(){
        Gson g = new Gson();
        EventoDAO dao = new EventoDAO();
        List<Evento> eventos = dao.getEventos();
        
        return g.toJson(eventos);
    }
    
    //listar recomendações
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("recomendacoes/{id}")
    public String listarRecomendacoes(@PathParam("id") int idUsuario){
        Gson g = new Gson();
        EventoDAO dao = new EventoDAO();
        List<Evento> eventos = dao.recomendacoes(idUsuario);
        
        return g.toJson(eventos);
    }
    
    //listar recomendações
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("maisRecomendacoes/{id}")
    public String maisRecomendacoes(@PathParam("id") int idUsuario){
        Gson g = new Gson();
        EventoDAO dao = new EventoDAO();
        List<Evento> eventos = dao.maisRecomendacoes(idUsuario);
        
        return g.toJson(eventos);
    }
      
    //metodo para criar relacao de usuario nao tem interesse em evento
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/naoTenhoInteresse/{idUsuario}/{idEvento}")
    public String naoTenhoInteresse(@PathParam("idUsuario")int idUsuario , @PathParam("idEvento")int idEvento){
        EventoDAO dao = new EventoDAO();
        
        if (dao.naoTenhoInteresse(idUsuario,idEvento)){
            return "true";
        }else {
            return "false";
        }
    }
       //metodo para criar relacao de usuario tem interesse em evento
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/tenhoInteresse/{idUsuario}/{idEvento}")
    public String tenhoInteresse(@PathParam("idUsuario")int idUsuario , @PathParam("idEvento")int idEvento){
        EventoDAO dao = new EventoDAO();
        
        if (dao.tenhoInteresse(idUsuario,idEvento)){
            return "true";
        }else {
            return "false";
        }
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/positivo/{idUsuario}/{idEvento}")
    public String positivo(@PathParam("idUsuario")int idUsuario , @PathParam("idEvento")int idEvento){
        EventoDAO dao = new EventoDAO();
        
        if (dao.positivo(idUsuario,idEvento)){
            return "true";
        }else {
            return "false";
        }
    }
    
     @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/negativo/{idUsuario}/{idEvento}")
    public String negativo(@PathParam("idUsuario")int idUsuario , @PathParam("idEvento")int idEvento){
        EventoDAO dao = new EventoDAO();
        
        if (dao.negativo(idUsuario,idEvento)){
            return "true";
        }else {
            return "false";
        }
    }
    
    
    
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }
}
