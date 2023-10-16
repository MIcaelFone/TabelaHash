public class Hash {
    private int max_itens;
    private int max_posicoes;
     private int quantidade_itens_atual;
    private Aluno [] Estrutura;

    public int getMax_posicoes() {
        return max_posicoes;
    }

    public void setMax_posicoes(int max_posicoes) {
        this.max_posicoes = max_posicoes;
    }

   
    public int getQuantidade_itens_atual() {
        return quantidade_itens_atual;
    }

    public int getMax_itens() {
        return max_itens;
    }

    public void setMax_itens(int max_itens) {
        this.max_itens = max_itens;
    }
   
    Aluno aluno = new Aluno();
    public Hash(){

    }
      public Hash(int tamanho_vetor, int maximo_itens){
        this.max_itens = maximo_itens;
        this.max_posicoes = tamanho_vetor;
        this.quantidade_itens_atual = 0;
        Estrutura = new Aluno[tamanho_vetor];
         
    }
    public void inseridotabelaValoresVazio(int max_itens){
        for (int i =0; i<max_itens;i++){

            Estrutura[i]= aluno.getAlunoVazio();



            
        }
    }
    
    public int FuncaoHash(Aluno aluno){
        return aluno.getMatricula() % max_posicoes;
        
    }
    public void inserir(Aluno aluno){
    try{    
        
        int local = FuncaoHash(aluno);
        while(Estrutura[local].getMatricula()>0){
            local++;
            local = local % max_posicoes;

        }   
           
          Estrutura[local] = aluno;
          quantidade_itens_atual++;
        
    }catch(NullPointerException e){
        System.out.println("Não foi possível inserir o aluno");

        
         
    }
    }
    public boolean isFull(){
        if(this.quantidade_itens_atual == this.max_itens){
            return true;
        }
        return false;
    }   
  
    public void  Deletar(Aluno aluno){
    try{  
        
        int local = FuncaoHash(aluno);
        while(Estrutura[local].getMatricula()!=-1){
            if(Estrutura[local].getMatricula() != -1){
                Estrutura[local] = aluno.getAlunoVazio();
                quantidade_itens_atual--;  
                System.out.println("Aluno encontrado e deletado");
                break;
                
        
        }
            local=(local+1) % max_posicoes;
        }
        
         
       
    }catch(NullPointerException e){
        System.out.println("Aluno não encontrado");
           

    }
    }
    public void Busca(Aluno aluno){
     try{  
        
        int local=FuncaoHash(aluno);
        while(Estrutura[local].getMatricula() !=-1){
            if(Estrutura[local].getMatricula()==aluno.getMatricula()){
               aluno= Estrutura[local];
               System.out.println("Aluno encontrado: "+ aluno.getNome()+" Matricula: "+aluno.getMatricula());
                break;
            }
            local=(local+1) % max_posicoes;
        }

        
              
        
        
      
    }catch(NullPointerException e){
        System.out.println("Aluno não encontrado");
           

    }
    }
    public void imprimir() {
        System.out.println("Tabela Hash:");
     try{  
        for (int i = 0; i < max_posicoes; i++) {
            if (Estrutura[i].getMatricula()>0) {
                
                Aluno aluno = Estrutura[i];
                System.out.println("Posição " + i + ": Aluno: " + aluno.getNome() + " Matrícula: " + aluno.getMatricula());
        
        }
        }
    }catch(NullPointerException e){
          
    }
           

    

    }
    
}
  
   

    

