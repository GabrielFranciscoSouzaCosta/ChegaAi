package ws;

import Conexao.Con;
import DAO.TagDAO;
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
import modelo.Tag;
import org.neo4j.driver.v1.Record;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;

@Path("tag")
public class TagWS {

    @Context
    private UriInfo context;

    public TagWS() {
    }
    
    //metodo para listar tags
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("listar")
    public String listarTags(){
        Gson g = new Gson(); // cria objeto da google
        TagDAO dao = new TagDAO(); // cria objeto data controle 
        List<Tag> tags = dao.getTags(); // cria lista de tags 
        
        return g.toJson(tags); 
    }
    
    //metodo para inserir tag
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/inserir")
    public boolean inserirUsuario(String content){
        Gson g = new Gson();
        Tag t = g.fromJson(content, Tag.class);
        TagDAO dao = new TagDAO();
        return dao.inserirTag(t);    
    }
    
    //metodo para deletar tag
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
    
    
    // metodo para buscar uma tag
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/buscarTag/{id}")
    public String buscarTag(@PathParam("id")int id){
        TagDAO dao = new TagDAO();
        Gson g = new Gson();
        
        Tag t = dao.buscarTag(id);
        return g.toJson(t);
    }
    
    // atualizar tag
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/atualizarTag")
    public boolean atualizarTag(String content){
        Gson g = new Gson();
        Tag t = (Tag) g.fromJson(content,Tag.class);
        TagDAO dao = new TagDAO();
        return  dao.atualizarTag(t);
    }
    
    
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
