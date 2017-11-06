
package ws;


import DAO.UsuarioDAO;
import com.google.gson.Gson;
import java.util.List;
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
import modelo.Sessao;
import modelo.Usuario;

@Path("usuario")
public class UsuarioWS {
    @Context
    private UriInfo context;

    public UsuarioWS() {
    }
       
    //metodo para inserir usuario
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{inserir}")
    public String inserirUsuario(@PathParam("inserir")String content){
        Usuario u = new Usuario(content);
        UsuarioDAO dao = new UsuarioDAO();
        return dao.inserirUsuario(u);
    }
         
    //
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public String getXml() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

   
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }
}
