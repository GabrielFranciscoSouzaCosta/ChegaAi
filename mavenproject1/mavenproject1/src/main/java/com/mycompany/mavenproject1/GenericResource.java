package com.mycompany.mavenproject1;

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


@Path("generic")
public class GenericResource {
    @Context
    private UriInfo context;
    
    @GET
    @Produces
    public String ShowHelloWorld(){
        
        Driver driver =  GraphDatabase.driver("bolt://localhost:7687", AuthTokens.basic("neo4j","8183"));
        Session session = driver.session();
        
        StatementResult result = session.run("MATCH (n:Movie) return n.nome");
        
        while (result.hasNext()){
            Record record = result.next();
            System.out.println(record.get("title").asString());
        }
        return null;
        }
    }
    
    
    
 

   
   

   

