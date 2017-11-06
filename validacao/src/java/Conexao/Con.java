package Conexao;
import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.driver.v1.Session;

public class Con {
    
    Driver driver =  GraphDatabase.driver("bolt://localhost:7687", AuthTokens.basic("neo4j","8183"));
    Session session = driver.session();
    
    public Session conecta(){
        return this.session;
    }
    
    public void encerraConexao(){
        this.session.close();
    }
}
