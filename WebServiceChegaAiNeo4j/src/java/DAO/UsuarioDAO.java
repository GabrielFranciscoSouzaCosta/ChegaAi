package DAO;

import Conexao.Con;
import java.util.ArrayList;
import java.util.List;
import modelo.Usuario;
import org.neo4j.driver.v1.Record;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;

public class UsuarioDAO {

// listar usuarios
public List<Usuario> getUsuarios(){
    
    List<Usuario> usuarios = new ArrayList<>(); // cria a lista de users
    Usuario u = null;
    Con c = new Con(); // cria o objeto de conexao
    Session session =  c.conecta(); // chama o metodo de conectar
    StatementResult result = session.run("match (n:Usuario) return n.nome as nome, n.email as email, n.senha as senha, ID(n) as id");
        
    while(result.hasNext()){
        Record record = result.next();
        u = new Usuario();
        u.setId(record.get("id").asInt());
        u.setNome(record.get("nome").asString());
        u.setEmail(record.get("email").asString());
        u.setSenha(record.get("senha").asString());
        
        usuarios.add(u);
    }
    c.encerraConexao();
    return usuarios;
}
    
    
// inserir usuario
public boolean inserirUsuario(Usuario u){
    String nome = u.getNome();
    String email = u.getEmail();
    String senha = u.getSenha();
    
    Con c = new Con();
    Session session =  c.conecta(); // chama o metodo de conectar
    StatementResult result = session.run("CREATE (u:Usuario {nome:'"+ nome +"', senha:'"+senha + "', email:'"+email +"'})");
    c.encerraConexao();
    
    if(result!= null){
        return true;
    }else{
        return false;
    }
    
    
    
}
//buscar um usuario



// deletar usuario

// criar usuario    

    
}
