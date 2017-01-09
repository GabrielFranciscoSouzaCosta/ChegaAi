
package ws;

import com.google.gson.Gson;
import dao.EventoDAO;
import java.sql.SQLException;
import java.util.List;
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
import modelo.Evento;

/**
 * REST Web Service
 *
 * @author gabriel
 */
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

    //listar eventos
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("get/eventos")
    public String getEventos() throws ClassNotFoundException, SQLException{
        Gson g = new Gson();
        EventoDAO dao = new EventoDAO();
        List<Evento> eventos = dao.getEventos();
        
        return g.toJson(eventos);
    }
    //inserir eventos
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("inserirEvento")
    public boolean inserirEvento(String content){
        Gson g = new Gson();
        Evento e = g.fromJson(content, Evento.class);
        EventoDAO dao = new EventoDAO();
        return dao.criarEvento(e);
    }
    
    //deletar eventos
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/deletarEvento/{id}")
    public String deletarEvento(@PathParam("id")int id){
        EventoDAO dao = new EventoDAO();
        
        if (dao.deletarEvento(id)){
            return "true";
        }else {
            return "false";
        }
    }
    
    //update evento
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/atualizarEvento")
    public boolean atualizarEvento(String content){
        Gson g = new Gson();
        Evento e = (Evento) g.fromJson(content,Evento.class);
        EventoDAO dao = new EventoDAO();
        return  dao.atualizarEvento(e);
    }
    
    /**
     * PUT method for updating or creating an instance of EventoWS
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }
}
