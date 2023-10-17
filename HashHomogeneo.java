public class HashHomogeneo {
    private int maxPosicoes;
    private int maxItens;
    private int quantidadeItensAtual;
    private Aluno[] estrutura;

    Aluno aluno = new Aluno();

    public HashHomogeneo(int tamanhoVetor, int maximoItens) {
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
        for (int i = 0; i < tamanhoVetor; i++) {
            estrutura[i] = aluno.getAlunoVazio();
        }
    }

    public int funcaoHash(Aluno aluno) {
        return aluno.getMatricula() % maxPosicoes;
    }

    public void Inserir(Aluno aluno) {
        try {
            int chave= funcaoHash(aluno);

            while (estrutura[chave].getMatricula() != -1  ) {
                chave = (chave + 1) % maxPosicoes; // Próxima posição disponível
            }

            estrutura[chave] = aluno;
            quantidadeItensAtual++;

            // Verifica e redimensiona a tabela se necessário
            float fatorDeCarga = (float) quantidadeItensAtual / (float) maxPosicoes;
            System.out.println("Fator de carga: " + fatorDeCarga);
            if (fatorDeCarga >= 0.75) {
                redimensionarTabela();
            }
        } catch (NullPointerException e) {
            System.out.println("Não foi possível inserir o aluno.");
        }
    }

    private void redimensionarTabela() {
        int novoTamanho = maxPosicoes * 2;
        Aluno[] novaEstrutura = new Aluno[novoTamanho];
        Aluno[] copiaEstrutura = estrutura.clone();
       
        for (int i = 0; i < novoTamanho; i++) {
            novaEstrutura[i] = aluno.getAlunoVazio();
             int chave= funcaoHash(copiaEstrutura[i]);
            
            while (copiaEstrutura[chave]!= novaEstrutura[i]) {
                chave = (chave + 1) % novoTamanho;
            }

                    novaEstrutura[chave] = copiaEstrutura[i];   
                }
            

            estrutura = novaEstrutura;
            maxPosicoes = novoTamanho;
    }

    public boolean isFull() {
        return quantidadeItensAtual == maxItens;
    }

    public void Deletar(Aluno aluno) {
        int chave = funcaoHash(aluno);

        while (estrutura[chave].getMatricula() != -1) {
            if (estrutura[chave].getMatricula() == aluno.getMatricula()) {
                System.out.println("Aluno deletado: " + estrutura[chave].getNome() + " Matrícula: " + estrutura[chave].getMatricula());
                estrutura[chave] =aluno.getAlunoDeletado(); // Marca como nulo para deletar
                quantidadeItensAtual--;
                return;

            }
            chave = (chave + 1) % maxPosicoes;
        }
        System.out.println("Aluno não encontrado.");

    }

    public void Buscar(Aluno aluno) {
        int chave = funcaoHash(aluno);

        while ( estrutura[chave].getMatricula() != -1) {
            if (estrutura[chave].getMatricula() == aluno.getMatricula()) {
                System.out.println("Aluno encontrado: " + estrutura[chave].getNome() + " Matrícula: " + estrutura[chave].getMatricula());
                return;
            }
            chave = (chave + 1) % maxPosicoes;
        }
        System.out.println("Aluno não encontrado.");
    }

    public void Imprimir() {
        System.out.println("Tabela Hash:");
        for (int i = 0; i < maxPosicoes; i++) {
            if (estrutura[i] != null) {
                System.out.println("Chave " + i + ":  Valor:" + estrutura[i].getNome() +"," + estrutura[i].getMatricula());
            }
        }
    }
}
