/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javax.ws.rs.core.MediaType;
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
    //inserir usuarios
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
    //metodo para buscar um usuario
    //metodo para atualizar usuario
    
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
