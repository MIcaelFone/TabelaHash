public class Hash {
    private int FuncaoHash(Aluno aluno){
        return aluno.getMatricula() % 100;
    }
    private int maximo_itens;
    private int tamanho_vetor;
    private int quantidade_itens_atual;
    private Aluno[] vetor;
    
    public Hash(int tamanho_vetor){
        this.maximo_itens = maximo_itens;
        this.tamanho_vetor = maximo_itens / 10;
        this.quantidade_itens_atual = 0;
        this.vetor = new Aluno[this.tamanho_vetor];
    }
    public boolean isFull(){
        if(this.quantidade_itens_atual == this.maximo_itens){
            return true;
        }
        return false;
    }   
    public int

    
}
