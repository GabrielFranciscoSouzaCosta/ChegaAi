
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
    
    //metodo para listar usuarios
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("listar")
    public String listarUsuarios(){
        Gson g = new Gson();
        UsuarioDAO dao = new UsuarioDAO();
        List<Usuario> usuarios = dao.getUsuarios();
        
        return g.toJson(usuarios); 
    }
    //metodo para inserir usuario
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/inserir")
    public boolean inserirUsuario(String content){
        Gson g = new Gson();
        Usuario u = g.fromJson(content, Usuario.class);
        UsuarioDAO dao = new UsuarioDAO();
        return dao.inserirUsuario(u);    
    }
    //metodo para deletar usuario
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/deletarUsuario/{id}")
    public String deletarUsuario(@PathParam("id")int id){
        UsuarioDAO dao = new UsuarioDAO();
        
        if (dao.deletarUsuario(id)){
            return "true";
        }else {
            return "false";
        }
    }
    
    //metodo para buscar um usuario
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/buscarUsuario/{id}")
    public String buscarUsuario(@PathParam("id")int id){
        UsuarioDAO dao = new UsuarioDAO();
        Gson g = new Gson();
        
        Usuario usuario = dao.buscarUsuario(id);
        return g.toJson(usuario);
    }
    
    // atualizar usuario
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/atualizarUsuario")
    public boolean atualizarUsuario(String content){
        Gson g = new Gson();
        Usuario u = (Usuario) g.fromJson(content,Usuario.class);
        UsuarioDAO dao = new UsuarioDAO();
        return  dao.atualizarUsuario(u);
    }
    
    //metodo para autenticacao
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/autenticacao/{email}/{senha}")
    public String autenticacao(@PathParam("email") String email, @PathParam("senha") String senha ){
        UsuarioDAO dao = new UsuarioDAO();
        Gson g = new Gson();
        
        Sessao sessao = dao.autenticacao(email,senha);
        
        return g.toJson(sessao);
    }
    
      //metodo para sair da sessao
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/logout/{id}")
    public String logout(@PathParam("id")int id){
        UsuarioDAO dao = new UsuarioDAO();
        
        if (dao.logout(id)){
            return "true";
        }else {
            return "false";
        }
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
