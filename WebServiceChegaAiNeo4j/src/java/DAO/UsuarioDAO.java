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

// listar todos usuarios
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
public Usuario buscarUsuario(int id){
    Con c = new Con();
    Session session = c.conecta(); // chama o metodo para conectar
    StatementResult result = session.run("match (u:Usuario) where ID(u)= "+ id+" return u.nome as nome, u.email as email,ID(u) as id ");
    Record record = result.next();
    Usuario u = new Usuario();
    
    u.setId(record.get("id").asInt());
    u.setNome(record.get("nome").asString());
    u.setEmail(record.get("email").asString());
 c.encerraConexao();
 return u;
}

//autenticar usuario
public Sessao autenticacao(String email, String senha){
    Con c = new Con();
    Session session = c.conecta(); // chama o metodo para conectar
    
    StatementResult result = session.run("match (u:Usuario) where u.email= '"+ email +"' AND u.senha ='" + senha+"'  return u.nome as nome, u.email as email,ID(u) as id, u.senha as senha ");
    
    if(result == null){
     
        return null;
        
    }else{
    
        Sessao criaSessao = new Sessao(); // instancia um objeto sessao
        Usuario u = new Usuario();
        
        Record record = result.next();        
    
            u.setId(record.get("id").asInt());
            u.setNome(record.get("nome").asString());
            u.setEmail(record.get("email").asString());    
        
        //aqui estou criando um nodo de sessao no banco 
        StatementResult result2  = session.run("create (n:Sessao{email:'"+record.get("email").asString()+"',senha:'"+ record.get("senha").asString() + "',nome:'"+record.get("nome").asString()+"'})");
        
        //Record record2 = result.next();
        //record2.get("id");
        criaSessao= new Sessao(u);
        
        c.encerraConexao();
        return criaSessao; // retorna o objeto sessao que foi gerada  
    }
}



// deletar usuario
public boolean deletarUsuario(int id){
    Con c = new Con();
    Session session = c.conecta(); // chama o metodo para conectar
    StatementResult result = session.run("MATCH (u:Usuario) where ID(u)= "+id+" OPTIONAL MATCH (u)-[r]-()" +
"DELETE u,r");
c.encerraConexao();

if(result!= null){
        return true;
    }else{
        return false;
    }

}


// atualizar usuario
public boolean atualizarUsuario(Usuario u){
    Con c = new Con();
    int id = u.getId();
    Session session = c.conecta(); // chama o metodo para conectar
    StatementResult result = session.run("match (u:Usuario) where ID(u)=" +id+" set u.nome = '"+ u.getNome()+"', u.email ='"+u.getEmail()+"' return u");
    c.encerraConexao();
    if(result!=null){
        return true;
    }else{
        return false;
    }
}

    
} 
