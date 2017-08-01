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
public List<Tag> getTags(){
    
    List<Tag> tags = new ArrayList<>(); // cria a lista de users
    Tag t = null;
    Con c = new Con(); // cria o objeto de conexao
    Session session =  c.conecta(); // chama o metodo de conectar
    StatementResult result = session.run("match (n:Tag) return n.nome as nome, ID(n) as id");
        
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
public String inserirTag(String t, int id){ // t =  nome da tag. id = id do nodo a ser conectado com ela
    int idTag = 0;
    int relacao = 0;    
    Con c = new Con();
    Session session =  c.conecta(); // chama o metodo de conectar
    try{
        StatementResult result = session.run("MATCH (t:Tag) WHERE t.nome='"+t+"' return ID(t) as id");
        Record record = result.next();
        idTag = (record.get("id").asInt());
  
            try{// teste para ver se a tag existe
                StatementResult result2 = session.run("match (n)-[r:POSSUI]->(t:Tag) where ID(n) = "+id+" and ID(t)="+idTag+" return ID(r) as r");
                Record record2 = result2.next();
                relacao = (record2.get("r").asInt());
                return "existe o nodo e a relacao";
            }catch(Exception e){
                session.run("MATCH (n) , (t:Tag) where t.nome='"+t+"' and  ID(n) = "+id+" CREATE (n)-[:POSSUI]->(t)");
                c.encerraConexao();
                return "tag existe, mas nao existe a relacao, vamo criar";
            }
    
    }catch(Exception e){// se entrar aqui a tag nao existe e se cria a relação e a tag nova
        session.run("MATCH (n) where ID(n) = "+id+" CREATE (t:Tag {nome:'"+t+"'}) CREATE (n)-[:POSSUI]->(t)");
        c.encerraConexao();
        return "tag nao existe";
    }             
    
}
   
    
    //deletar tags
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
