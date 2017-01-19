package ws;

import com.google.gson.Gson;
import dao.EventoDAO;
import dao.TagDAO;
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
import modelo.Tag;

@Path("tags")
public class TagsWS {

    @Context
    private UriInfo context;
    
    public TagsWS() {
    }

    
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public String getXml() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }
    
    // get tags
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("get/tags")
    public String getTags() throws ClassNotFoundException, SQLException{
        Gson g = new Gson();
        TagDAO dao = new TagDAO();
        List<Tag> tags = dao.getTag();
        return g.toJson(tags);   
    }
    
    //inserir tags
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("inserirTag")
    public boolean inserirEvento(String content){
        Gson g = new Gson();
        Tag t = g.fromJson(content, Tag.class);
        TagDAO dao = new TagDAO();
        return dao.inserirTag(t);
    }
    
    //deletar tags
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/deletarTag/{id}")
    public String deletarTag(@PathParam("id")int id){
        TagDAO dao = new TagDAO();
        if (dao.deletarTag(id)){
            return "true";
        }else {
            return "false";
        }
    }
    
    
    //atualizar tags
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/atualizarTag")
    public boolean atualizarTag(String content){
        Gson g = new Gson();
        Tag t = (Tag) g.fromJson(content,Tag.class);
        TagDAO dao = new TagDAO();
        return  dao.atualizarTag(t);
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }
}
