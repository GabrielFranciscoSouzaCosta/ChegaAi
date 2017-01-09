package dao;

import controle.C;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
   
   //metodo para criar evento 
   
   public boolean criarEvento(Evento e){
       try {
           sql = "INSERT INTO Evento (descricao, idUsuario, data, endereço) VALUES (?,?,?,?);"; // pegar id do criador do evento
           con = C.cb();
           pst = con.prepareStatement(sql);
           pst.setString(1 , e.getDescricao());
           pst.setInt(2, e.getIdUsuario());
           pst.setString(3, e.getData());
           pst.setString(4, e.getEndereco());
           
           pst.execute();
           C.db();
           return true;
       } catch (ClassNotFoundException | SQLException ex) {
           Logger.getLogger(EventoDAO.class.getName()).log(Level.SEVERE, null, ex);
           return false;
       }

   }
   
   //metodo para deletar eventos
   public boolean deletarEvento(int id){
       try {
           sql = "DELETE FROM Evento WHERE idEvento = ?;";
           con = C.cb();
           pst = con.prepareStatement(sql);
           pst.setInt(1, id);
           pst.execute();
           C.cb();
           return true;
       } catch (ClassNotFoundException | SQLException ex) {
           Logger.getLogger(EventoDAO.class.getName()).log(Level.SEVERE, null, ex);
           return false;
       }
   }
    
}
