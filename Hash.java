public class Hash {
    private int maxPosicoes;
    private int maxItens;
    private int quantidadeItensAtual;
    private Aluno[] estrutura;
    
    Aluno aluno = new Aluno();

    public Hash(int tamanhoVetor, int maximoItens) {
        this.maxPosicoes = tamanhoVetor;
        this.maxItens = maximoItens;
        this.quantidadeItensAtual = 0;
        this.estrutura = new Aluno[tamanhoVetor];
        inicializarEstrutura(tamanhoVetor);
    }
    public int getMaxPosicoes() {
        return maxPosicoes;
    }
    
    public int getMaxItens() {
        return maxItens;
    }
    
    public int getQuantidadeItensAtual() {
        return quantidadeItensAtual;
    }

    public void setQuantidadeItensAtual(int quantidadeItensAtual) {
        this.quantidadeItensAtual = quantidadeItensAtual;
    }

    public void setMaxPosicoes(int maxPosicoes) {
        this.maxPosicoes = maxPosicoes;
    }

    public void setMaxItens(int maxItens) {
        this.maxItens = maxItens;
    }


    public void inicializarEstrutura(int tamanhoVetor) {
        for(int i=0;i<tamanhoVetor;i++){

            estrutura[i] =aluno.getAlunoVazio();

            
        }
    }
    
    public int funcaoHash(Aluno aluno){
        return aluno.getMatricula() % maxPosicoes;
        
    }
    

  

    public void Inserir(Aluno aluno) {
        try{    
        
            int local = funcaoHash(aluno);
            while(estrutura[local].getMatricula()>0){
            
                local = (local+1) % maxPosicoes;
    
            }   
               
              estrutura[local] = aluno;
              quantidadeItensAtual++;
            
        }catch(NullPointerException e){
            System.out.println("Não foi possível inserir o aluno");
    
            
             
        }
    }
        
    


    public boolean isFull(){
        if(this.quantidadeItensAtual == this.maxItens){
            return true;
        }
            return false;
        }   
        
    public void  Deletar(Aluno aluno){
        try{  
            
            int local = funcaoHash(aluno);
            while(estrutura[local].getMatricula()!=-1){
                if(estrutura[local].getMatricula() == aluno.getMatricula()){
                    aluno=estrutura[local];
                    System.out.println("Aluno encontrado: " + aluno.getNome() + " Matrícula: " + aluno.getMatricula());
                    quantidadeItensAtual--;  
                    estrutura[local] = aluno.getAlunoDeletado();      
                    break;
                    
            
            }
                local=(local+1) % maxPosicoes;
            }
            
                
            
        }catch(NullPointerException e){
            System.out.println("Aluno não encontrado");
                

        }
    }

    public void Buscar(Aluno aluno) {
        int local = funcaoHash(aluno);
        while (estrutura[local].getMatricula() != -1) {
            if (estrutura[local].getMatricula() == aluno.getMatricula()) {
                aluno = estrutura[local];
                System.out.println("Aluno encontrado: " + aluno.getNome() + " Matrícula: " + aluno.getMatricula());
                return;
            }
            local = (local + 1) % maxPosicoes;
        }
        System.out.println("Aluno não encontrado.");
    }

    
    public void Imprimir() {
        System.out.println("Tabela Hash:");
        try{  
            for (int i = 0; i <maxPosicoes ; i++) {
                if (estrutura[i].getMatricula()>0) {
                     
                    Aluno aluno = estrutura[i];
                    System.out.println("Posição " + i + ": Aluno: " + aluno.getNome() + " Matrícula: " + aluno.getMatricula());
            
                }
            }
            }catch(NullPointerException e){
                System.out.println("Não foi possível imprimir a tabela");
                
        }
    }

}   