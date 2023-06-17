package projetobd;

import java.sql.*;
import java.util.Vector;


import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;



public class App {

    
    private final String url = "jdbc:postgresql://localhost:5432/Books";
    private String user;
    //private String senha;
    private char[] password;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public char[] getPassword() {
        return password;
    }

    public void setPassword(char[] password) {
        this.password = password;
    }
    
    
        
        //String user, String password

    public Connection connect() throws SQLException  { //metodo para conectar no BD
        return DriverManager.getConnection(url, user, String.valueOf(password));
    }
   
        
    public void relatorioAutores(DefaultTableModel modelo) throws SQLException {
        String SQL = "SELECT au.nome Autor, g.nome Genero, count(g.nome) Quantidade "
                + "FROM autor au join livro_autor la on au.id=la.id_autor "
                + "join livro l on l.id=la.id_livro "
                + "join genero g on l.id_genero=g.id "
                + "group by au.nome, g.nome";
        
        ResultSet rs = null;
        
        try (Connection conn = connect(); 
                PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Vector<String> data = new Vector<>();
                data.add(rs.getString("Autor"));
                data.add(rs.getString("Genero"));
                data.add(String.valueOf(rs.getInt("Quantidade")));
                          
                modelo.addRow(data);
            }
        }catch (SQLException e ) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Comando Invalido", "Transacao invalida", 2);
        }
        
    }
    
    public void insertRelatLivros(Autor aut, Livro liv, Genero gen) throws SQLException{
        String SQL = "INSERT INTO relatoriolivros(id_autor, id_livro, id_genero, autor, livro, genero, ano, isbn, descricao)"
                + " VALUES (?,?,?,?,?,?,?,?,?)";
        try(Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            
            pstmt.setInt(1, aut.getId());
            pstmt.setInt(2, liv.getId());
            pstmt.setInt(3, gen.getId());
            pstmt.setString(4, aut.getNome());
            pstmt.setString(5, liv.getTitulo());
            pstmt.setString(6, gen.getNome());
            pstmt.setDate(7, liv.getAno());
            pstmt.setString(8, liv.getIsbn());
            pstmt.setString(9, liv.getDescricao());
            
            int affectedRows = pstmt.executeUpdate();
        }catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Comando Invalido", "Transacao invalida", 2);
    		System.out.println(e.getMessage());
    	}
    }
    
    
    public void insertAutor(Autor aut) throws SQLException {
        String SQL = "INSERT INTO Autor(id, nome, bio)"
                        + "VALUES(?,?,?)";
        
        try (Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(SQL)){
            
            pstmt.setInt(1, aut.getId());
            pstmt.setString(2, aut.getNome());
            pstmt.setString(3, aut.getBio());
            
            int affectedRows = pstmt.executeUpdate();
          
        }catch (SQLException e) {
    		System.out.println(e.getMessage());
    	}
    }
    
    public void insertGen(Genero gen) throws SQLException {
        String SQL = "INSERT INTO Genero(id, nome, descricao)"
                + "VALUES(?,?,?)";
        
        try (Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(SQL)){
            
            pstmt.setInt(1, gen.getId());
            pstmt.setString(2, gen.getNome());
            pstmt.setString(3, gen.getDescricao());
            
            int affectedRows = pstmt.executeUpdate();
        }catch (SQLException e) {
    		System.out.println(e.getMessage());
    	}
    }
    
    
    
    public void relatorioLivros(DefaultTableModel modelo) throws SQLException{ //arrumar o codigo
    	
    	String SQL = "SELECT * FROM RelatorioLivros";
    	ResultSet rs = null;
    	
    	try(Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(SQL)) {
    		
    		rs = pstmt.executeQuery();
    		
    		while (rs.next()){
                Vector<String> data = new Vector<>();
                
                data.add(String.valueOf(rs.getInt("id_autor")));
                data.add(String.valueOf(rs.getInt("id_livro")));
                data.add(String.valueOf(rs.getInt("id_genero")));
                data.add(rs.getString("autor"));
                data.add(rs.getString("livro"));
                data.add(rs.getString("genero"));
                data.add((rs.getDate("ano")).toString());
                data.add(rs.getString("isbn"));
                data.add(rs.getString("descricao"));
                             
                modelo.addRow(data);
            }
    	}
    	catch (SQLException e) {
    		System.out.println(e.getMessage());
    		JOptionPane.showMessageDialog(null, "Comando Invalido", "Transacao invalida", 2);
    	}
    }
    
    
    public void DeleteLivro(int cod) throws SQLException {
    	String SQL = "DELETE FROM Livro WHERE id = ?";
    	
    	try (Connection conn = connect();
    			PreparedStatement pstmt = conn.prepareStatement(SQL)) {
    		pstmt.setInt(1, cod);
    		
    		int affectedRows = pstmt.executeUpdate();
    	}
    }
    
}
