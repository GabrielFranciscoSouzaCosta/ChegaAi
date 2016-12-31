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
   public boolean inserirUsuario(Usuario u){
       try {
           sql =  "INSERT INTO Usuario (nome, cpf, senha,celular,email) VALUES (?,?,?,?,?);";
           con = C.cb();
           pst = con.prepareStatement(sql);
           pst.setString(1, u.getNome());
           pst.setString(2, u.getCpf());
           pst.setString(3, u.getSenha());
           pst.setString(4, u.getCelular());
           pst.setString(5, u.getEmail());

           pst.execute();
           C.db();
           return true;
       } catch (ClassNotFoundException | SQLException ex) {
           Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
           return false;
       }
   }
   
   //metodo para deletar usuario
    
   public boolean deletarUsuario(int id){
       try {
           sql = "DELETE FROM Usuario WHERE idUsuario = ?;";
           con = C.cb();
           pst = con.prepareStatement(sql);
           pst.setInt(1, id);
           pst.execute();
           C.db();
           return true;
       } catch (ClassNotFoundException | SQLException ex) {
           Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
           return false;
       }
   }
   
   //metodo para update de usuario
   public boolean atualizarUsuario(Usuario u){
        
       try {
           sql = "UPDATE Usuario SET nome = ? , cpf = ? , senha = ? , celular = ? , email = ? WHERE idUsuario = ?;";
           con = C.cb();
           pst = con.prepareStatement(sql);
           pst.setString(1, u.getNome());
           pst.setString(2, u.getCpf());
           pst.setString(3, u.getSenha());
           pst.setString(4, u.getCelular());
           pst.setString(5, u.getEmail());
           pst.setInt(6, u.getId());
           pst.executeUpdate();
           C.db();
           return true;
       } catch (ClassNotFoundException | SQLException ex) {
           Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
           return false;
       }
   }
   
   
}
