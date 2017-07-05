package DAO;
import Conexao.Con;
import java.util.ArrayList;
import java.util.List;
import modelo.Evento;
import org.neo4j.driver.v1.Record;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;

public class EventoDAO {

//metoto para listar evento
public List<Evento> getEventos(){
    List<Evento> eventos = new ArrayList<>(); 
    Evento e = null;
    Con c = new Con(); // cria o objeto de conexao
    Session session =  c.conecta(); // chama o metodo de conectar
    
    StatementResult result = session.run("match (n:Evento) return n.titulo as titulo, n.descricao as descricao, ID(n) as id, n.data as data");
    
    while(result.hasNext()){
        Record record = result.next();
        e= new Evento();
        e.setId(record.get("id").asInt());
        e.setDescricao(record.get("descricao").asString());
        e.setTitulo(record.get("titulo").asString());
        e.setData(record.get("data").asString());

        eventos.add(e);
    }
    c.encerraConexao();
    return eventos;
}
//metodo para inserir evento
public boolean inserirEvento(Evento e){
    String titulo = e.getTitulo();
    String descricao = e.getDescricao();
    String data = e.getData();
    
    Con c = new Con();
    Session session =  c.conecta(); // chama o metodo de conectar
    StatementResult result = session.run("CREATE (u:Evento {titulo:'"+ titulo +"', descricao:'"+descricao +"',data:'"+data+"'})");
    c.encerraConexao();
    if(result!= null){
        return true;
    }else{
        return false;
    }
}
//metodo para deletar evento
public boolean deletarEvento(int id){
    Con c = new Con();
    Session session = c.conecta(); // chama o metodo para conectar
    StatementResult result = session.run("MATCH (e:Evento) where ID(e)= "+id+" OPTIONAL MATCH (e)-[r]-()" +
"DELETE e,r");
c.encerraConexao();

if(result!= null){
        return true;
    }else{
        return false;
    }
}

// metodo para buscar um evento

public Evento buscarEvento(int id){
    Con c = new Con();
    Session session = c.conecta(); // chama o metodo para conectar
    StatementResult result = session.run("match (e:Evento) where ID(e)= "+ id+" return e.descricao as descricao, e.titulo as titulo,ID(e) as id, e.data as data");
    Record record = result.next();
    Evento e = new Evento();
    
    e.setId(record.get("id").asInt());
    e.setDescricao(record.get("descricao").asString());
    e.setTitulo(record.get("titulo").asString());
    e.setData(record.get("data").asString());
 c.encerraConexao();
 return e;
}

//metodo para atualizar evento
public boolean atualizarEvento(Evento e){
    Con c = new Con();
    int id = e.getId();
    Session session = c.conecta(); // chama o metodo para conectar
    StatementResult result = session.run("match (e:Evento) where ID(e)=" +id+" set e.titulo = '"+ e.getTitulo()+"', e.descricao ='"+e.getDescricao()+"', '"+ e.getData()+"' return e");
    c.encerraConexao();
    if(result!=null){
        return true;
    }else{
        return false;
    }
}
}
