package projetobd;

import java.sql.Date;


public class Livro {
    private int id;
    private String titulo;
    private String descricao;
    private Date ano;
    private String isbn;
    private int id_genero;

    public Livro(int id, String titulo, String descricao, Date ano, String isbn, int id_genero) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.ano = ano;
        this.isbn = isbn;
        this.id_genero = id_genero;
    }
    
    public Livro(){
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getAno() {
        return ano;
    }

    public void setAno(Date ano) {
        this.ano = ano;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getId_genero() {
        return id_genero;
    }

    public void setId_genero(int id_genero) {
        this.id_genero = id_genero;
    }
    
    
    
}
