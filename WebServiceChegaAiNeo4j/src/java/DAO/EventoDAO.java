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
//metodo para inserir evento
public String inserirEvento(Evento e, int idUsuario){
    
    String titulo = e.getTitulo();
    String descricao = e.getDescricao();
    String data = e.getData();
    String endereco = e.getEndereco();
    

    Con c = new Con();
    Session session =  c.conecta(); // chama o metodo de conectar
    StatementResult result = session.run("CREATE (u:Evento {titulo:'"+ titulo +"', descricao:'"+descricao +"',data:'"+data+"', endereco:'"+endereco+"'}) return ID(u) as id");
    
    Record record = result.next();
    // aqui estou rodando o comando de criar a relação de usuario criou evento
    session.run("match  (u:Usuario), (e:Evento) where  ID(u) ="+idUsuario+"  and  ID(e) = "+record.get("id").asInt()+" CREATE (u)-[:CRIA]->(e)");
    c.encerraConexao();
    
    if(result!= null){
        return ""+record.get("id").asInt();
    }else{
        return "false";
    }
}
//metodo para deletar evento
public boolean deletarEvento(int id){
    Con c = new Con();
    Session session = c.conecta(); // chama o metodo para conectar
    StatementResult result = session.run("MATCH (e:Evento) where ID(e)= "+id+" detach delete e");
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
    StatementResult result = session.run("match (e:Evento) where ID(e)= "+ id+" return e.descricao as descricao, e.titulo as titulo,ID(e) as id, e.data as data, e.endereco as endereco");
    Record record = result.next();
    Evento e = new Evento();
    
    e.setId(record.get("id").asInt());
    e.setDescricao(record.get("descricao").asString());
    e.setTitulo(record.get("titulo").asString());
    e.setData(record.get("data").asString());
    e.setEndereco(record.get("endereco").asString());

 c.encerraConexao();
 return e;
}


//metodo para listar eventos criados pelo usuario
public List<Evento> eventosUsuario(int id){
    List<Evento> eventos = new ArrayList<>(); 
    Evento e = null;
    Con c = new Con(); // cria o objeto de conexao
    Session session =  c.conecta(); // chama o metodo de conectar
    
    StatementResult result = session.run("match (u:Usuario)-[r:CRIA]->(n:Evento)  where ID(u)="+id+" return n.titulo as titulo, n.descricao as descricao, ID(n) as id, n.data as data, n.endereco as endereco");
    
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
public List<Evento> recomendacoes(int id){
    List<Evento> eventos = new ArrayList<>(); 
    Evento e = null;
    Con c = new Con(); // cria o objeto de conexao
    Session session =  c.conecta(); // chama o metodo de conectar
    
    StatementResult result = session.run("match (u:Usuario)-[:POSSUI]->(t:Tag)<-[:POSSUI]-(n:Evento)  where ID(u)="+id+" return n.titulo as titulo, n.descricao as descricao, ID(n) as id, n.data as data, n.endereco as endereco");
    
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



//metodo para atualizar evento
public boolean atualizarEvento(Evento e){
    Con c = new Con();
    int id = e.getId();
    Session session = c.conecta(); // chama o metodo para conectar
    StatementResult result = session.run("match (e:Evento) where ID(e)=" +id+" set e.titulo = '"+ e.getTitulo()+"', e.descricao ='"+e.getDescricao()+"', e.data='"+ e.getData()+"', e.endereco='"+e.getEndereco()+"'return e");
    c.encerraConexao();
    if(result!=null){
        return true;
    }else{
        return false;
    }
}
}
