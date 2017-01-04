
package ws;

import com.google.gson.Gson;
import dao.EventoDAO;
import dao.UsuarioDAO;
import java.sql.SQLException;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import modelo.Evento;
import modelo.Usuario;

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
    
    
    
    /**
     * PUT method for updating or creating an instance of EventoWS
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }
}
