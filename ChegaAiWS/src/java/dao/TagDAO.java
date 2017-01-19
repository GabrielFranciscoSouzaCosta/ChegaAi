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
import modelo.Tag;
import modelo.Usuario;

public class TagDAO {
    private PreparedStatement pst;
    private ResultSet rs;
    private Connection con;
    private String sql;
    
    // metodo para listar as tags
    public List<Tag> getTag() throws ClassNotFoundException, SQLException{
        List<Tag> tags = new ArrayList<>();
        Tag t = null;
        sql = "SELECT * FROM Tag;";
        con = C.cb(); //conexao com o banco
        pst = con.prepareStatement(sql);
        rs = pst.executeQuery();
        
        while(rs.next()){
            t = new Tag();
            t.setId(rs.getInt("idTag"));
            t.setNome(rs.getString("nome"));
            tags.add(t);
        }
        C.db();
        return tags;
    }
    // metodo para inserir tags
    public boolean inserirTag(Tag t){
        try {
            sql = "INSERT INTO Tag (nome) VALUES (?);";
            con = C.cb();
            pst = con.prepareStatement(sql);
            pst.setString(1, t.getNome());
            pst.execute();
            C.db();
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(TagDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    //metodo para deletar tag
    public boolean deletarTag(int id){
        try {
            sql = "DELETE FROM Tag WHERE idTag = ?;";
            con = C.cb();
            pst = con.prepareStatement(sql);
            pst.setInt(1,id);
            pst.execute();
            C.db();
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(TagDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    //metodo para atualizar tags
    public boolean atualizarTag(Tag t){
       try {
           sql = "UPDATE Tag SET nome = ?  WHERE idTag = ?;";
           con = C.cb();
           pst = con.prepareStatement(sql);
           pst.setString(1, t.getNome());
           pst.setInt(2, t.getId());
           pst.executeUpdate();
           C.db();
           return true;
       } catch (ClassNotFoundException | SQLException ex){
           Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
           return false;
       }
   }
    
    
}
