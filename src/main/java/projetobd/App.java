package projetobd;

import java.sql.*;
import java.util.Vector;


import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;



public class App {

    
    private final String url = "jdbc:postgresql://localhost:5432/Books";
    private String user;
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
        
        Connection conn = connect();
        conn.setAutoCommit(false);
        PreparedStatement pstmt = conn.prepareStatement(SQL);
        
        try {
            
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
            
            if (affectedRows > 0){
                JOptionPane.showMessageDialog(null, "Insert Realizado", "Transacao feita", 1);
            } else {
                conn.rollback();
            }
            
            conn.commit();
            
        }catch (SQLException | IllegalArgumentException  e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Nao foi possivel realizar o comando. Dando Rollback na transacao", 
                    "Transacao invalida", 2);
            
            try {
                if(conn != null){
                    conn.rollback();
                }
            }catch (SQLException | IllegalArgumentException  ex){
                System.out.println(ex.getMessage());
                JOptionPane.showMessageDialog(null, "Nao foi possivel realizar o comando. Dando Rollback na transacao", 
                    "Transacao invalida", 2);
            }
        } finally {
            this.close(pstmt);  
            this.close(conn);
        }
    }
    
    
    public void insertAutor(Autor aut) throws SQLException {
        String SQL = "INSERT INTO Autor(id, nome, bio)"
                        + "VALUES(?,?,?)";
        Connection conn = connect();
        conn.setAutoCommit(false);
        PreparedStatement pstmt = conn.prepareStatement(SQL);
        try {
            
            pstmt.setInt(1, aut.getId());
            pstmt.setString(2, aut.getNome());
            pstmt.setString(3, aut.getBio());
            
            int affectedRows = pstmt.executeUpdate();
            
            if (affectedRows > 0){
                JOptionPane.showMessageDialog(null, "Insert Realizado", "Transacao feita", 1);
            }else {
                conn.rollback();
            }
            
            conn.commit();
            
        }catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Nao foi possivel realizar o comando. Dando Rollback na transacao", 
                    "Transacao invalida", 2);
            
            try {
                if(conn != null){
                    conn.rollback();
                }
            }catch (SQLException ex){
                System.out.println(ex.getMessage());
                JOptionPane.showMessageDialog(null, "Nao foi possivel realizar o comando. Dando Rollback na transacao", 
                    "Transacao invalida", 2);
            }
        } finally {
            this.close(pstmt);  
            this.close(conn);
        }
    }
    
    public void insertGen(Genero gen) throws SQLException {
        String SQL = "INSERT INTO Genero(id, nome, descricao)"
                + "VALUES(?,?,?)";
        
        Connection conn = connect();
        conn.setAutoCommit(false);
        PreparedStatement pstmt = conn.prepareStatement(SQL);
        try {
            
            pstmt.setInt(1, gen.getId());
            pstmt.setString(2, gen.getNome());
            pstmt.setString(3, gen.getDescricao());
            
            int affectedRows = pstmt.executeUpdate();
            
            if (affectedRows > 0){
                JOptionPane.showMessageDialog(null, "Insert Realizado", "Transacao feita", 1);
            }else {
                conn.rollback();
            }
            
            conn.commit();
            
        }catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Nao foi possivel realizar o comando. Dando Rollback na transacao", 
                    "Transacao invalida", 2);
            
            try {
                if(conn != null){
                    conn.rollback();
                }
            }catch (SQLException ex){
                System.out.println(ex.getMessage());
                JOptionPane.showMessageDialog(null, "Nao foi possivel realizar o comando. Dando Rollback na transacao", 
                    "Transacao invalida", 2);
            }
        } finally {
            this.close(pstmt);
            this.close(conn);
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
    	String SQLDeleteLivroAutor = "DELETE FROM livro_autor WHERE id_livro=?";
        String SQLDeleteLivro = "DELETE FROM livro WHERE id=?";
    	
        Connection conn = null;
        PreparedStatement pstmt1 = null;
        PreparedStatement pstmt2 = null;
        
        
        try {
            conn = connect();
            conn.setAutoCommit(false);
            
            //delete na tabela livro_autor
            pstmt1 = conn.prepareStatement(SQLDeleteLivroAutor);
            pstmt1.setInt(1, cod);
            
            int affectedRows = pstmt1.executeUpdate();
            
            if(affectedRows > 0){
                //delete na tabela livro
                pstmt2 = conn.prepareStatement(SQLDeleteLivro);
                pstmt2.setInt(1, cod);
                int affectedRows1 = pstmt2.executeUpdate();
                
                System.out.println("certinho");
                JOptionPane.showMessageDialog(null, "Delete Realizado", "Transacao feita", 1);
            }else {
            	conn.rollback();
            	JOptionPane.showMessageDialog(null, "Nao foi possivel realizar o comando. Dando Rollback na transacao", 
                        "Transacao invalida", 2);
            }
            
            conn.commit();
            
            
        }catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Nao foi possivel realizar o comando. Dando Rollback na transacao", 
                    "Transacao invalida", 2);
            
            try {
                if(conn != null){
                    conn.rollback();
                }
            }catch (SQLException ex){
                System.out.println(ex.getMessage());
                JOptionPane.showMessageDialog(null, "Nao foi possivel realizar o comando. Dando Rollback na transacao", 
                    "Transacao invalida", 2);
            }
        } finally {
            this.close(pstmt1);
            this.close(pstmt2);
            this.close(conn);
            
        }
        
    }
    
    
    private App close(AutoCloseable closeable){ //metodo para fechar conexao com o bd
        try {
            if(closeable != null){
                closeable.close();
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return this;
    }
    
}
