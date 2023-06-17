package projetobd;


public class Autor {
    private int id;
    private String nome;
    private String bio;
    
    
    public Autor(int id, String nome, String bio){
        this.id = id;
        this.nome = nome;
        this.bio = bio;
    }
    
    public Autor(){
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
    
    
}
