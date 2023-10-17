import java.util.LinkedList;

public class HashHeterogeneo {
    private int maxPosicoes;
    private int maxItens;
    private int quantidadeItensAtual;
    private LinkedList<Aluno>[] estrutura;

    Aluno aluno = new Aluno();

    public HashHeterogeneo(int tamanhoVetor, int maximoItens) {
        this.maxPosicoes = tamanhoVetor;
        this.maxItens = maximoItens;
        this.quantidadeItensAtual = 0;
        this.estrutura = new LinkedList[tamanhoVetor];
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
            estrutura[i] = new LinkedList<Aluno>();
        }
    }

    public int funcaoHash(Aluno aluno) {
        return aluno.getMatricula() % maxPosicoes;
    }

    public void Inserir(Aluno aluno) {
        try {
            int local = funcaoHash(aluno);

            // Verifica se já existe algum aluno na mesma posição
            if (estrutura[local].isEmpty()) {
                estrutura[local].add(aluno);
            } else {
                // Tratamento heterogêneo apenas quando há colisão
                estrutura[local].add(aluno);
            }

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
        LinkedList<Aluno>[] novaEstrutura = new LinkedList[novoTamanho];

        for (int i = 0; i < novoTamanho; i++) {
            novaEstrutura[i] = new LinkedList<Aluno>();
        }

        for (int i = 0; i < maxPosicoes; i++) {
            for (Aluno aluno : estrutura[i]) {
                int local = funcaoHash(aluno);
                novaEstrutura[local].add(aluno);
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
        estrutura[local].removeIf(a -> a.getMatricula() == aluno.getMatricula());
        quantidadeItensAtual--;
    }

    public void Buscar(Aluno aluno) {
        int local = funcaoHash(aluno);
        LinkedList<Aluno> lista = estrutura[local];
        for (Aluno a : lista) {
            if (a.getMatricula() == aluno.getMatricula()) {
                System.out.println("Aluno encontrado: " + a.getNome() + " Matrícula: " + a.getMatricula());
                return;
            }
        }
        System.out.println("Aluno não encontrado.");
    }

    public void Imprimir() {
        System.out.println("Tabela Hash:");
        for (int i = 0; i < maxPosicoes; i++) {
            LinkedList<Aluno> lista = estrutura[i];
            for (Aluno aluno : lista) {
                System.out.println("Posição " + i + ": Aluno: " + aluno.getNome() + " Matrícula: " + aluno.getMatricula());
            }
        }
    }
}
