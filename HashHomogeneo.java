public class HashHomogeneo {
    private int tamanhoVetor;
    private int maxItens;
    private int quantidadeItensAtual;
    private Aluno[] estrutura;

    public HashHomogeneo(int tamanhoVetor, int maximoItens) {
        this.tamanhoVetor = tamanhoVetor;
        this.maxItens = maximoItens;
        this.quantidadeItensAtual = 0;
        this.estrutura = new Aluno[tamanhoVetor];
        inicializarEstrutura(tamanhoVetor);
    }

    public void Inserir(Aluno aluno) {
        if (isFull()) {
            System.out.println("A tabela está cheia. Não é possível inserir mais alunos.");
            return;
        }

        int posicao = funcaoHash(aluno);

        while (estrutura[posicao] != null && estrutura[posicao].getMatricula() != -1) {
            if (estrutura[posicao].getMatricula() == aluno.getMatricula()) {
                System.out.println("Matrícula já existente.");
                return;
            }
            posicao = (posicao + 1) % tamanhoVetor; // Próxima posição disponível
        }

        estrutura[posicao] = aluno;
        quantidadeItensAtual++;

        // Verifica e redimensiona a tabela se necessário
        float fatorDeCarga = (float) quantidadeItensAtual / (float) tamanhoVetor;
        System.out.println("Fator de carga: " + fatorDeCarga);

        if (fatorDeCarga >= 0.75) {
            redimensionarTabela();
        }
    }

    public void Deletar(Aluno aluno) {
        int posicao = funcaoHash(aluno);

        while (estrutura[posicao] != null) {
            if (estrutura[posicao].getMatricula() == aluno.getMatricula()) {
                System.out.println("O aluno " + estrutura[posicao].getNome() + " com a matrícula " + estrutura[posicao].getMatricula() + " foi removido!");
                estrutura[posicao] = Aluno.getAlunoDeletado();
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

        while (estrutura[posicao] != null) {
            if (estrutura[posicao].getMatricula() == aluno.getMatricula()) {
                System.out.println("O aluno " + estrutura[posicao].getNome() + " com a matrícula " + estrutura[posicao].getMatricula() + " foi encontrado!");
                return;
            }
            posicao = (posicao + 1) % tamanhoVetor;
        }

        System.out.println("Aluno não encontrado.");

        long endTime = System.currentTimeMillis(); // Captura o tempo final
        long elapsedTime = endTime - startTime; // Calcula o tempo gasto em milissegundos

        System.out.println("Tempo gasto na busca: " + elapsedTime + " ms");
    }

    public void Imprimir() {
        System.out.println("Tabela Hash:");
        for (int i = 0; i < tamanhoVetor; i++) {
            Aluno aluno = estrutura[i];
            if (aluno == null) {
                System.out.println("Posição " + i + " -> Vazio");
            } else if (aluno.getMatricula() == -1) {
                System.out.println("Posição " + i + " -> Removido");
            } else {
                System.out.println("Posição " + i + " -> Matrícula " + aluno.getMatricula() + " / Nome " + aluno.getNome());
            }
        }
    }

    private void redimensionarTabela() {
        int novoTamanho = tamanhoVetor * 2;
        Aluno[] novaEstrutura = new Aluno[novoTamanho];

        for (int i = 0; i < novoTamanho; i++) {
            novaEstrutura[i] = null;
        }

        for (int i = 0; i < tamanhoVetor; i++) {
            if (estrutura[i] != null) {
                int posicao = funcaoHash(estrutura[i]);
                while (novaEstrutura[posicao] != null) {
                    posicao = (posicao + 1) % novoTamanho;
                }
                novaEstrutura[posicao] = estrutura[i];
            }
        }

        estrutura = novaEstrutura;
        tamanhoVetor = novoTamanho;
    }

    public boolean isFull() {
        return quantidadeItensAtual == maxItens;
    }

    public void inicializarEstrutura(int tamanhoVetor) {
        for (int i = 0; i < tamanhoVetor; i++) {
            estrutura[i] = null;
        }
    }

    public int funcaoHash(Aluno aluno) {
        return aluno.getMatricula() % tamanhoVetor;
    }
}
