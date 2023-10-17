class Aluno{
private String nome;
private int matricula;


public Aluno(){
    
    
}
public Aluno(int matricula,String nome){
    this.nome = nome;
    this.matricula = matricula;
    
}
public String getNome(){
    return this.nome;
}
public void setNome(String nome){
    this.nome = nome;
}
public int getMatricula(){
    return this.matricula;
}
public void setMatricula(int matricula){
    this.matricula = matricula;
}
private static final Aluno aluno_delatado = new Aluno(-2, "Deletado");
private static final Aluno aluno_vazio = new Aluno(-1, "N/A");

// Resto da classe Aluno

public Aluno getAlunoDeletado() {
    return aluno_delatado;
}
public Aluno getAlunoVazio(){
    return  aluno_vazio;
}
}
