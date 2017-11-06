package DAO;
import Conexao.Con;
import java.util.ArrayList;
import java.util.List;
import modelo.Evento;
import org.neo4j.driver.v1.Record;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;
public class EventoDAO {
    
//metoto para listar eventos
public List<Evento> getEventos(){
    List<Evento> eventos = new ArrayList<>(); 
    Evento e = null;
    Con c = new Con(); // cria o objeto de conexao
    Session session =  c.conecta(); // chama o metodo de conectar
    
    StatementResult result = session.run("match (n:Evento) return n.titulo as titulo, n.descricao as descricao, ID(n) as id, n.data as data, n.endereco as endereco");
    
    while(result.hasNext()){
        Record record = result.next();
        e= new Evento();
        e.setId(record.get("id").asInt());
        e.setDescricao(record.get("descricao").asString());
        e.setTitulo(record.get("titulo").asString());
        e.setData(record.get("data").asString());
        e.setEndereco(record.get("endereco").asString());

        eventos.add(e);
    }
    c.encerraConexao();
    return eventos;
}

//naoTenhoInteresse
public boolean naoTenhoInteresse(int idUsuario, int idEvento){
    Con c = new Con();
    Session session = c.conecta(); // chama o metodo para conectar
    StatementResult result = session.run("match (u:Usuario) , (e:Evento)  where ID(u) = "+idUsuario+" and ID(e)= "+idEvento+" create (u)-[:NaoTenhoInteresse]->(e)");
c.encerraConexao();

if(result!= null){
        return true;
    }else{
        return false;
    }
}

//tenho interesse
public boolean tenhoInteresse(int idUsuario, int idEvento){
    Con c = new Con();
    Session session = c.conecta(); // chama o metodo para conectar
    StatementResult result = session.run("match (u:Usuario) , (e:Evento)  where ID(u) = "+idUsuario+" and ID(e)= "+idEvento+" create (u)-[:TenhoInteresse]->(e)");
c.encerraConexao();

if(result!= null){
        return true;
    }else{
        return false;
    }
}  

public boolean positivo(int idUsuario, int idEvento){
    Con c = new Con();
    Session session = c.conecta(); // chama o metodo para conectar
    StatementResult result = session.run("match (u:Usuario) , (e:Evento)  where ID(u) = "+idUsuario+" and ID(e)= "+idEvento+" create (u)-[:NEGATIVO]->(e)");
c.encerraConexao();

if(result!= null){
        return true;
    }else{
        return false;
    }
}

public boolean negativo(int idUsuario, int idEvento){
    Con c = new Con();
    Session session = c.conecta(); // chama o metodo para conectar
    StatementResult result = session.run("match (u:Usuario) , (e:Evento)  where ID(u) = "+idUsuario+" and ID(e)= "+idEvento+" create (u)-[:POSITIVO]->(e)");
c.encerraConexao();

if(result!= null){
        return true;
    }else{
        return false;
    }
}     






// recomendacoes
public List<Evento> recomendacoes(int id){
    List<Evento> eventos = new ArrayList<>(); 
    Evento e = null;
    Con c = new Con(); // cria o objeto de conexao
    Session session =  c.conecta(); // chama o metodo de conectar
    
    StatementResult result = session.run("match (u:Usuario)-[:POSSUI]->(t:Tag)<-[:POSSUI]-(n:Evento)  where ID(u)="+id+" and NOT (n)<-[:NaoTenhoInteresse]-(u) and NOT (n)<-[:TenhoInteresse]-(u) return n.titulo as titulo, n.descricao as descricao, ID(n) as id, n.data as data, n.endereco as endereco LIMIT 8");
    
    while(result.hasNext()){
        Record record = result.next();
        e= new Evento();
        e.setId(record.get("id").asInt());
        e.setDescricao(record.get("descricao").asString());
        e.setTitulo(record.get("titulo").asString());
        e.setData(record.get("data").asString());
        e.setEndereco(record.get("endereco").asString());

        eventos.add(e);
    }
    c.encerraConexao();
    return eventos;
}

// recomendacoes
public List<Evento> maisRecomendacoes(int id){
    List<Evento> eventos = new ArrayList<>(); 
    Evento e = null;
    Con c = new Con(); // cria o objeto de conexao
    Session session =  c.conecta(); // chama o metodo de conectar
    
    StatementResult result = session.run("match (u:Usuario)-[:TenhoInteresse]->(e:Evento)-[:POSSUI]->(t:Tag)<-[:POSSUI]-(r:Evento) where ID(u)="+id+" and NOT (r)<-[:NaoTenhoInteresse]-(u) and NOT (r)<-[:TenhoInteresse]-(u) and NOT (r)<-[:NEGATIVO]-(u) and NOT (r)<-[:POSITIVO]-(u) return r.titulo as titulo, r.descricao as descricao, ID(r) as id, r.data as data, r.endereco as endereco LIMIT 1");
    
    while(result.hasNext()){
        Record record = result.next();
        e= new Evento();
        e.setId(record.get("id").asInt());
        e.setDescricao(record.get("descricao").asString());
        e.setTitulo(record.get("titulo").asString());
        e.setData(record.get("data").asString());
        e.setEndereco(record.get("endereco").asString());

        eventos.add(e);
    }
    c.encerraConexao();
    return eventos;
}


}
