class Aluno {
    private String nome;
    private int matricula;

    public Aluno(int matricula, String nome, String CPF) {
        this.matricula = matricula;
        this.nome = nome;
    }

    public int getMatricula() {
        return this.matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}