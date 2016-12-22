package ws;

import com.google.gson.Gson;
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
import modelo.Usuario;

@Path("usuario")
public class UsuarioWS {

    @Context
    private UriInfo context;
    
    public UsuarioWS() {
    }

    @GET
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }
    // listar usuarios
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("get/usuarios")
    public String getUsuarios() throws ClassNotFoundException, SQLException{
        Gson g = new Gson();
        UsuarioDAO dao = new UsuarioDAO();
        List<Usuario> usuarios = dao.getUsuarios();
        
        return g.toJson(usuarios);
    }
    

    @PUT
    @Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
