package ws;
import DAO.TagDAO;
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
import modelo.Tag;

@Path("tag")
public class TagWS {

    @Context
    private UriInfo context;

    public TagWS() {
    }
    
    //metodo para listar tags
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("listar/{id}")
    public String listarTags(@PathParam("id") int id){
        Gson g = new Gson(); // cria objeto da google
        TagDAO dao = new TagDAO(); // cria objeto data controle 
        List<Tag> tags = dao.getTags(id); // cria lista de tags 
        
        return g.toJson(tags); 
    }
    
//chama o metodo para conectar o usuario a tag
@GET
@Produces(MediaType.TEXT_PLAIN)
@Path("/possui/{idTag}/{idUsuario}")
public String inserirTag(@PathParam("idTag")int content, @PathParam("idUsuario") int id){
    
        TagDAO dao = new TagDAO();
        return dao.inserirTag(content,id);    
    }
     
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public String getXml(){
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }
}
