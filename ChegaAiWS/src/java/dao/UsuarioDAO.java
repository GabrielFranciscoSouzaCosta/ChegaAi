package dao;

import controle.C;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
   
   // metodo para
   
}
