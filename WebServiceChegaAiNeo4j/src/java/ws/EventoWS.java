package ws;
import DAO.EventoDAO;
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
    public String listarUsuarios(){
        Gson g = new Gson();
        EventoDAO dao = new EventoDAO();
        List<Evento> eventos = dao.getEventos();
        
        return g.toJson(eventos);
    }
    
    // inserir eventos 
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/inserir/{idUsuario}/{titulo}/{descricao}/{data}/{endereco}")
    public String inserirUsuario(@PathParam("idUsuario") int idUsuario, @PathParam("titulo") String titulo,@PathParam("descricao") String descricao,@PathParam("data") String data,@PathParam("endereco") String endereco){

        Evento e = new Evento(titulo,descricao,data,endereco);
        EventoDAO dao = new EventoDAO();
        return dao.inserirEvento(e,idUsuario);    
    }
    
    //metodo para deletar evento
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
    
    //metodo para listar 1 evento 
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/buscarEvento/{id}")
    public String buscarEvento(@PathParam("id")int id){
        EventoDAO dao = new EventoDAO();
        Gson g = new Gson();
        
        Evento evento = dao.buscarEvento(id);
        return g.toJson(evento);
    }
    
    // atualizar evento
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/atualizarEvento")
    public boolean atualizarEvento(String content){
        Gson g = new Gson();
        Evento e = (Evento) g.fromJson(content,Evento.class);
        EventoDAO dao = new EventoDAO();
        return  dao.atualizarEvento(e);
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }
}
