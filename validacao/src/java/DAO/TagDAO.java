package DAO;

import Conexao.Con;
import java.util.ArrayList;
import java.util.List;
import modelo.Tag;
import org.neo4j.driver.v1.Record;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;

public class TagDAO {
    
//listar todas as tags
public List<Tag> getTags(int id){
    
    List<Tag> tags = new ArrayList<>(); // cria a lista de users
    Tag t = null;
    Con c = new Con(); // cria o objeto de conexao
    Session session =  c.conecta(); // chama o metodo de conectar
    StatementResult result = session.run("MATCH (u:Usuario), (n:Tag)"+
"WHERE ID(u)="+id+" and NOT (n)<-[:POSSUI]-(u)"+
"RETURN n.nome as nome, ID(n) as id");
        
    while(result.hasNext()){
        Record record = result.next();
        t = new Tag();
        t.setId(record.get("id").asInt());
        t.setNome(record.get("nome").asString());
        
        tags.add(t);
    }
    c.encerraConexao();
    return tags;
}

//inserir tags
public String inserirTag(int idTag, int idUsuario){ // t =  nome da tag. id = id do nodo a ser conectado com ela
          
    Con c = new Con();
    Session session =  c.conecta(); // chama o metodo de conectar
    
    try{
        session.run("MATCH (n) , (t:Tag) where ID(t)="+idTag+" and  ID(n) = "+idUsuario+" CREATE (n)-[:POSSUI]->(t)");
        c.encerraConexao();
        return "true";
    }catch(Exception e){
        
        c.encerraConexao();
        return "false";
    }             
    
}
   
    
//deletar tag
public boolean deletarTag(int id){
    Con c = new Con();
    Session session = c.conecta(); // chama o metodo para conectar
    StatementResult result = session.run("MATCH (t:Tag) where ID(t)= "+id+" DETACH DELETE t");
c.encerraConexao();

if(result!= null){
        return true;
    }else{
        return false;
    }

}

//deletar relacao da tag com usuario ou evento
public boolean deletarRelacao(int idTag, int idNodo){
    Con c = new Con();
    Session session = c.conecta(); // chama o metodo para conectar
    StatementResult result = session.run("match (n)-[r:POSSUI]->(t:Tag)  where ID(n)="+idNodo+" and ID(t)= "+idTag+" delete r");
    c.encerraConexao();

if(result!= null){
        return true;
    }else{
        return false;
    }

}



    //atualizar tags        
    public Tag buscarTag(int id){
    Con c = new Con();
    Session session = c.conecta(); // chama o metodo para conectar
    StatementResult result = session.run("match (t:Tag) where ID(t)= "+ id+" return t.nome as nome,ID(t) as id ");
    Record record = result.next();
    Tag t = new Tag();
    
    t.setId(record.get("id").asInt());
    t.setNome(record.get("nome").asString());
    c.encerraConexao();
 return t;
}
    
    
    //metodo para atualizar evento
public boolean atualizarTag(Tag t){
    Con c = new Con();
    int id = t.getId();
    Session session = c.conecta(); // chama o metodo para conectar
    StatementResult result = session.run("match (t:Tag) where ID(t)="+id+" set t.nome= '"+ t.getNome()+"' return t");
    c.encerraConexao();
    if(result!=null){
        return true;
    }else{
        return false;
    }
}
}
