package projetobd;


public class Livro_Autor {
    
    private int id_livro;
    private int id_autor;
    private boolean autor_principal;

    public Livro_Autor(int id_livro, int id_autor, boolean autor_principal) {
        this.id_livro = id_livro;
        this.id_autor = id_autor;
        this.autor_principal = autor_principal;
    }
    
    public Livro_Autor(){}

    
    public int getId_livro() {
        return id_livro;
    }

    public void setId_livro(int id_livro) {
        this.id_livro = id_livro;
    }

    public int getId_autor() {
        return id_autor;
    }

    public void setId_autor(int id_autor) {
        this.id_autor = id_autor;
    }

    public boolean isAutor_principal() {
        return autor_principal;
    }

    public void setAutor_principal(boolean autor_principal) {
        this.autor_principal = autor_principal;
    }
    
    
}
