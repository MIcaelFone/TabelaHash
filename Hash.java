public class Hash {
    private int maxPosicoes;
    private int maxItens;
    private int quantidadeItensAtual;
    private Aluno[] estrutura;

    public Hash(int tamanhoVetor, int maximoItens) {
        this.maxPosicoes = tamanhoVetor;
        this.maxItens = maximoItens;
        this.quantidadeItensAtual = 0;
        this.estrutura = new Aluno[tamanhoVetor];
        inicializarEstrutura();
    }

    public void inicializarEstrutura() {
        for (int i = 0; i < maxPosicoes; i++) {
            estrutura[i] = null;
        }
    }

    public int funcaoHash(int matricula) {
        return matricula % maxPosicoes;
    }

    public void inserir(Aluno aluno) {
        if (quantidadeItensAtual >= maxItens) {
            System.out.println("A tabela está cheia. Não é possível inserir mais elementos.");
            return;
        }

        int local = funcaoHash(aluno.getMatricula());
        while (estrutura[local] != null) {
            local = (local + 1) % maxPosicoes;
        }

        estrutura[local] = aluno;
        quantidadeItensAtual++;
    }

    public void deletar(int matricula) {
        int local = funcaoHash(matricula);
        while (estrutura[local] != null) {
            if (estrutura[local].getMatricula() == matricula) {
                estrutura[local] = null;
                quantidadeItensAtual--;
                System.out.println("Aluno encontrado e deletado.");
                return;
            }
            local = (local + 1) % maxPosicoes;
        }
        System.out.println("Aluno não encontrado.");
    }

    public void buscar(int matricula) {
        int local = funcaoHash(matricula);
        while (estrutura[local] != null) {
            if (estrutura[local].getMatricula() == matricula) {
                Aluno aluno = estrutura[local];
                System.out.println("Aluno encontrado: " + aluno.getNome() + " Matrícula: " + aluno.getMatricula());
                return;
            }
            local = (local + 1) % maxPosicoes;
        }
        System.out.println("Aluno não encontrado.");
    }

    public void imprimir() {
        System.out.println("Tabela Hash:");
        for (int i = 0; i < maxPosicoes; i++) {
            if (estrutura[i] != null) {
                Aluno aluno = estrutura[i];
                System.out.println("Posição " + i + ": Aluno: " + aluno.getNome() + " Matrícula: " + aluno.getMatricula());
            }
        }
    }
}