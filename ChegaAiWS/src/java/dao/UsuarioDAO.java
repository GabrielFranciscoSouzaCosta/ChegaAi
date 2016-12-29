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
import modelo.Usuario;

public class UsuarioDAO {
   private PreparedStatement pst;
   private ResultSet rs;
   private Connection con;
   private String sql;
   
   // metodo para listar os usuarios
   public List<Usuario> getUsuarios() throws ClassNotFoundException, SQLException{
       List<Usuario> usuarios = new ArrayList<>();
       Usuario u = null;
       sql = "SELECT * FROM Usuario"; // 
       con = C.cb();// cria conexao com o banco
       pst = con.prepareStatement(sql);
       rs = pst.executeQuery();
       
       while(rs.next()){
        u = new Usuario();
        u.setId(rs.getInt("idUsuario"));
        u.setNome(rs.getString("nome"));
        u.setCpf(rs.getString("cpf"));
        u.setSenha(rs.getString("senha"));
        u.setCelular(rs.getString("celular"));
        u.setEmail(rs.getString("email"));
        usuarios.add(u);
    }
    C.db();
    return usuarios;
   }
   
   // metodo para criar usuario
   public boolean InserirUsuario(Usuario u){
       try {
           sql =  "INSERT INTO Usuario (nome, senha) VALUES (?,?);";
           con = C.cb();
           pst = con.prepareStatement(sql);
           pst.setString(1, u.getNome());
           pst.setString(2, u.getSenha());
           pst.execute();
           C.db();
           return true;
       } catch (ClassNotFoundException | SQLException ex) {
           Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
           return false;
       }
   }
   
   
   
}
