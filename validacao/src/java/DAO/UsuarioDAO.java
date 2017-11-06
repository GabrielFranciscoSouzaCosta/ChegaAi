package DAO;

import Conexao.Con;
import java.util.ArrayList;
import java.util.List;
import modelo.Sessao;
import modelo.Usuario;
import org.neo4j.driver.v1.Record;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;

public class UsuarioDAO {
    
// inserir usuario
public String inserirUsuario(Usuario u){
    
    String nome = u.getNome();
    
    
    Con c = new Con();
    Session session =  c.conecta(); // chama o metodo de conectar
    StatementResult result = session.run("CREATE (u:Usuario {nome:'"+ nome +"'}) return ID(u) as id");
    Record record = result.next();
    int resposta = record.get("id").asInt();
    c.encerraConexao();
    
    if(result!= null){
        return ""+resposta;
    }else{
        return "false";
    }
    
}
} 
