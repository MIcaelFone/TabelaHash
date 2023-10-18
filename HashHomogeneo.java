import java.util.ArrayList;


public class HashHomogeneo {
    private int tamanhoVetor;
    private int maxItens;
    private int quantidadeItensAtual;
    private Aluno[] estrutura;
    Aluno aluno = new Aluno();
    
    public HashHomogeneo(int tamanhoVetor, int maximoItens) {
        this.tamanhoVetor = tamanhoVetor;
        this.maxItens = maximoItens;
        this.quantidadeItensAtual = 0;
        this.estrutura = new Aluno[tamanhoVetor];
        inicializarEstrutura(tamanhoVetor);
    }


    public int gettamanhoVetor() {
        return tamanhoVetor;
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


    public void settamanhoVetor(int tamanhoVetor) {
        this.tamanhoVetor = tamanhoVetor;
    }


    public void setMaxItens(int maxItens) {
        this.maxItens = maxItens;
    }



    public void inicializarEstrutura(int tamanhoVetor) {
        for (int i = 0; i < tamanhoVetor; i++) {
            estrutura[i] = aluno.getAlunoVazio();
        }
    }



    public int funcaoHash(Aluno aluno) {
        return aluno.getMatricula() % tamanhoVetor;
    }


    public void Inserir(Aluno aluno) {
        try {
            int posicao = funcaoHash(aluno);

            while (estrutura[posicao].getMatricula() >0) {
                posicao = (posicao + 1) % tamanhoVetor; // Próxima posição disponível
            }
            for (int i = 0; i < tamanhoVetor; i++) {
                if (estrutura[i].getMatricula() == aluno.getMatricula()) {
                    System.out.println("Matricula já existente");
                    return;
                }
            }
                   
            estrutura[posicao] = aluno;
            quantidadeItensAtual++;
            // Verifica e redimensiona a tabela se necessário
            float fatorDeCarga = (float) quantidadeItensAtual / (float) tamanhoVetor;

            System.out.println("Fator de carga: " + fatorDeCarga);
                
            if (fatorDeCarga >= 0.75) {
                redimensionarTabela();
            }
            

             
        } catch (NullPointerException e) {
            System.out.println("Não foi possível inserir o aluno.");
        }
    }



    private void redimensionarTabela() {
        int novoTamanho = tamanhoVetor * 2;
        Aluno[] novaEstrutura = new Aluno[novoTamanho];
        Aluno[] copiaEstrutura = estrutura.clone();

        for (int i = 0; i < novoTamanho; i++) {
            novaEstrutura[i] = aluno.getAlunoVazio();
        }
        for (int i = 0; i < tamanhoVetor; i++) {
            for(Aluno aluno : estrutura){
                if(aluno.getMatricula() != -1){
                    int local = funcaoHash(aluno); // Corrige a função de hash aqui
                    novaEstrutura[local]= aluno;
                }
            }
            
        }

        estrutura = novaEstrutura;
        tamanhoVetor = novoTamanho;
    }


    public boolean isFull() {
        return quantidadeItensAtual == maxItens;
    }


    public void Deletar(Aluno aluno) {
        int posicao = funcaoHash(aluno);

        while (estrutura[posicao].getMatricula() != -1) {
            if (estrutura[posicao].getMatricula() == aluno.getMatricula()) {
                System.out.println( "Matrícula: " + estrutura[posicao].getMatricula()+"  Aluno deletado:" + estrutura[posicao].getNome() );
                estrutura[posicao] = aluno.getAlunoDeletado();
                quantidadeItensAtual--;
                return;
            }
            posicao = (posicao + 1) % tamanhoVetor;
        }
        System.out.println("Aluno não encontrado.");
    }

  

    public void Buscar(Aluno aluno) {
        long startTime = System.currentTimeMillis(); // Captura o tempo inicial
        int posicao = funcaoHash(aluno);

       

        while (estrutura[posicao].getMatricula() != -1) {
            if (estrutura[posicao].getMatricula() == aluno.getMatricula()) {
                System.out.println( "Matrícula: " + estrutura[posicao].getMatricula()+" Aluno encontrado: " + estrutura[posicao].getNome());
            }
            posicao = (posicao + 1) % tamanhoVetor;

        }

       

        long endTime = System.currentTimeMillis(); // Captura o tempo final
        long elapsedTime = endTime - startTime; // Calcula o tempo gasto em nanossegundos

        // Converta o tempo para milissegundos
        
        System.out.println("Tempo gasto na busca: " + elapsedTime + "ms");
    }

  



    public void Imprimir(){
        System.out.println("Tabela Hash:");        
        for (int i = 0; i < tamanhoVetor; i++) {
                System.out.println("Posicao " + i + ": Matrícula: " + estrutura[i].getMatricula()+" ,Nome: " + estrutura[i].getNome());
            }
        
    }
        
    
    
            
        
    
}


    

