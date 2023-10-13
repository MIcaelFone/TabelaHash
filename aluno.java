class Aluno{
private String nome;
private int matricula;
private String CPF;

public Aluno(){
    this.nome = "";
    this.matricula = 0;
    this.CPF = "";
}
public Aluno(String nome, int matricula, String CPF){
    this.nome = nome;
    this.matricula = matricula;
    this.CPF = CPF;
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
public String getCPF(){
    return this.CPF;
}
public void setCPF(String CPF){
    this.CPF = CPF;
}
}
