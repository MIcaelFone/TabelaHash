public class HashTable {
    private int maxPosicoes;
    private int maxItens;
    private int quantidadeItensAtual;
    private Aluno[] estrutura;

    Aluno aluno = new Aluno();

    public HashTable(int tamanhoVetor, int maximoItens) {
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
            estrutura[i] = null;
        }
    }

    public int funcaoHash(Aluno aluno) {
        return aluno.getMatricula() % maxPosicoes;
    }

    public void Inserir(Aluno aluno) {
        try {
            int local = funcaoHash(aluno);

            while (estrutura[local] != null) {
                local = (local + 1) % maxPosicoes; // Próxima posição disponível
            }

            estrutura[local] = aluno;
            quantidadeItensAtual++;

            // Verifica e redimensiona a tabela se necessário
            float fatorDeCarga = (float) quantidadeItensAtual / (float) maxPosicoes;
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

        for (int i = 0; i < maxPosicoes; i++) {
            if (copiaEstrutura[i] != null) {
                int local = funcaoHash(copiaEstrutura[i]);

                while (novaEstrutura[local] != null) {
                    local = (local + 1) % novoTamanho;
                }

                novaEstrutura[local] = copiaEstrutura[i];
            }
        }

        estrutura = novaEstrutura;
        maxPosicoes = novoTamanho;
    }

    public boolean isFull() {
        return quantidadeItensAtual == maxItens;
    }

    public void Deletar(Aluno aluno) {
        int local = funcaoHash(aluno);

        while (estrutura[local] != null) {
            if (estrutura[local].getMatricula() == aluno.getMatricula()) {
                estrutura[local] = null; // Marca como nulo para deletar
                quantidadeItensAtual--;
                return;
            }
            local = (local + 1) % maxPosicoes;
        }
        System.out.println("Aluno não encontrado.");
    }

    public void Buscar(Aluno aluno) {
        int local = funcaoHash(aluno);

        while (estrutura[local] != null) {
            if (estrutura[local].getMatricula() == aluno.getMatricula()) {
                System.out.println("Aluno encontrado: " + estrutura[local].getNome() + " Matrícula: " + estrutura[local].getMatricula());
                return;
            }
            local = (local + 1) % maxPosicoes;
        }
        System.out.println("Aluno não encontrado.");
    }

    public void Imprimir() {
        System.out.println("Tabela Hash:");
        for (int i = 0; i < maxPosicoes; i++) {
            if (estrutura[i] != null) {
                System.out.println("Posição " + i + ": Aluno: " + estrutura[i].getNome() + " Matrícula: " + estrutura[i].getMatricula());
            }
        }
    }
}
