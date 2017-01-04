package dao;

import controle.C;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Evento;
import modelo.Usuario;

public class EventoDAO {
   private PreparedStatement pst;
   private ResultSet rs;
   private Connection con;
   private String sql;
   
   //metodo para listar os eventos
   
   public List<Evento> getEventos() throws ClassNotFoundException, SQLException{
       List<Evento> eventos = new ArrayList<>();
       Evento e = null;
       
        sql = "SELECT * FROM Evento";
        con= C.cb(); // cria a conexao com o banco
        pst = con.prepareStatement(sql);
        rs=pst.executeQuery();
    while(rs.next()){
        e = new Evento();
        e.setIdEvento(rs.getInt("idEvento"));
        e.setDescricao(rs.getString("descricao"));
        e.setData(rs.getString("data"));
        e.setEndereco(rs.getString("endereço"));
        
        eventos.add(e);
    }
    C.db();
    return eventos;
   }
   
    
}
