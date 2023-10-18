import java.util.LinkedList;
import java.util.ArrayList;

public class HashExterno {
    private int maxPosicoes;
    private int maxItens;
    private int quantidadeItensAtual;
    private LinkedList<Aluno>[] estrutura;
    Aluno aluno = new Aluno();

    public HashExterno(int tamanhoVetor, int maximoItens) {
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

            estrutura[local].add(aluno);

            quantidadeItensAtual++;

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
        LinkedList<Aluno> lista = estrutura[local];

        ArrayList<Aluno> alunosRemovidos = new ArrayList<>();
        lista.removeIf(a -> {
            if (a.getMatricula() == aluno.getMatricula()) {
                alunosRemovidos.add(a);
                return true;
            }
            return false;
        });

        quantidadeItensAtual -= alunosRemovidos.size();

        if (alunosRemovidos.isEmpty()) {
            System.out.println("Nenhum aluno encontrado com a matrícula " + aluno.getMatricula());
        } else {
            System.out.println("Alunos removidos com a matrícula " + aluno.getMatricula() + ":");
            for (Aluno removido : alunosRemovidos) {
                System.out.println("Nome: " + removido.getNome() + ", Matrícula: " + removido.getMatricula());
            }
        }
    }

    public void Buscar(Aluno aluno) {
        int local = funcaoHash(aluno);
        long startTime = System.nanoTime(); // Captura o tempo inicial
        LinkedList<Aluno> lista = estrutura[local];
        ArrayList<Aluno> alunosEncontrados = new ArrayList<>();

        for (Aluno a : lista) {
            if (a.getMatricula() == aluno.getMatricula()) {
                alunosEncontrados.add(a);
            }
        }

        long endTime = System.nanoTime(); // Captura o tempo final
        long elapsedTime = endTime - startTime; // Calcula o tempo gasto em nanossegundos

        // Converte o tempo para milissegundos
        double milliseconds = (double) elapsedTime / 1_000_000;

        if (alunosEncontrados.isEmpty()) {
            System.out.println("Nenhum aluno encontrado com a matrícula " + aluno.getMatricula());
        } else {
            System.out.println("Alunos encontrados com a matrícula " + aluno.getMatricula() + ":");
            for (Aluno encontrado : alunosEncontrados) {
                System.out.println("Nome: " + encontrado.getNome() + ", Matrícula: " + encontrado.getMatricula());
            }
        }

        System.out.println("Tempo gasto na busca: " + milliseconds + " ms");
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
