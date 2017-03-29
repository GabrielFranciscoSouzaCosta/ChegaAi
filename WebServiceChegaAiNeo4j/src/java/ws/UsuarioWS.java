/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import java.sql.SQLException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.driver.v1.Record;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;

@Path("usuario")
public class UsuarioWS {
    
    Driver driver =  GraphDatabase.driver("bolt://localhost:7687", AuthTokens.basic("neo4j","8183"));
    Session session = driver.session();
    
    @Context
    private UriInfo context;

    public UsuarioWS() {
    }
    
    //metodo para listar usuario
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("listar")
    public String listarUsuarios() throws ClassNotFoundException, SQLException{
        String retorno =" ";
        
        StatementResult result = session.run("match (n) return n.name");
        while(result.hasNext()){
            Record record = result.next();
            retorno = record.get("n.name").asString();
        }
        
        return retorno;
       
    }
    //metodo para inserir usuario
    
    //metodo para deletar usuario
    
    //metodo para atualizar usuario
    
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
