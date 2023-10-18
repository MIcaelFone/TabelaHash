import java.util.LinkedList;
import java.util.ArrayList;

public class HashListasEncadeadas {
    private int maxPosicoes; // Número máximo de posições na tabela hash
    private int maxItens; // Número máximo de itens que a tabela pode conter
    private int quantidadeItensAtual; // Número atual de itens na tabela
    private LinkedList<Aluno>[] estrutura; // Estrutura de armazenamento da tabela hash
    Aluno aluno = new Aluno(); // Objeto Aluno usado para operações

    // Construtor da classe
    public HashListasEncadeadas(int tamanhoVetor, int maximoItens) {
        this.maxPosicoes = tamanhoVetor;
        this.maxItens = maximoItens;
        this.quantidadeItensAtual = 0;
        this.estrutura = new LinkedList[tamanhoVetor];
        inicializarEstrutura(tamanhoVetor);
    }

    // Obtém o número máximo de posições na tabela hash
    public int getMaxPosicoes() {
        return maxPosicoes;
    }

    // Obtém o número máximo de itens que a tabela pode conter
    public int getMaxItens() {
        return maxItens;
    }

    // Obtém o número atual de itens na tabela
    public int getQuantidadeItensAtual() {
        return quantidadeItensAtual;
    }

    // Define o número atual de itens na tabela
    public void setQuantidadeItensAtual(int quantidadeItensAtual) {
        this.quantidadeItensAtual = quantidadeItensAtual;
    }

    // Define o número máximo de posições na tabela hash
    public void setMaxPosicoes(int maxPosicoes) {
        this.maxPosicoes = maxPosicoes;
    }

    // Define o número máximo de itens que a tabela pode conter
    public void setMaxItens(int maxItens) {
        this.maxItens = maxItens;
    }

    // Inicializa a estrutura de dados da tabela hash
    public void inicializarEstrutura(int tamanhoVetor) {
        for (int i = 0; i < tamanhoVetor; i++) {
            estrutura[i] = new LinkedList<Aluno>();
        }
    }

    // Calcula a posição na tabela hash com base na matrícula do aluno
    public int funcaoHash(Aluno aluno) {
        return aluno.getMatricula() % maxPosicoes;
    }

    // Insere um aluno na tabela hash
    // Insere um aluno na tabela hash
    public void Inserir(Aluno aluno) {
        try {
            int local = funcaoHash(aluno);
            LinkedList<Aluno> lista = estrutura[local];

            // Verifica se já existe um aluno com a mesma matrícula
            boolean matriculaExistente = false;
            for (Aluno a : lista) {
                if (a.getMatricula() == aluno.getMatricula()) {
                    matriculaExistente = true;
                    break;
                }
            }

            if (!matriculaExistente) {
                lista.add(aluno);
                quantidadeItensAtual++;

                float fatorDeCarga = (float) quantidadeItensAtual / (float) maxPosicoes;
                System.out.println("Fator de Carga após inserção: " + fatorDeCarga);

                if (fatorDeCarga >= 0.75) {
                    redimensionarTabela();
                }
            } else {
                System.out.println("Já existe um aluno com a mesma matrícula na tabela.");
            }
        } catch (NullPointerException e) {
            System.out.println("Não foi possível inserir o aluno.");
        }
    }



    // Redimensiona a tabela hash quando o fator de carga excede um limite
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

    // Verifica se a tabela hash está cheia
    public boolean isFull() {
        return quantidadeItensAtual == maxItens;
    }

    // Remove um aluno da tabela hash
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

    // Busca um aluno na tabela hash
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

    // Imprime a tabela hash
    public void Imprimir() {
        System.out.println("Tabela Hash:");
        for (int i = 0; i < maxPosicoes; i++) {
            LinkedList<Aluno> lista = estrutura[i];
            System.out.print("Posição " + i + " -> ");
            for (Aluno aluno : lista) {
                System.out.print("Matrícula " + aluno.getMatricula() + " - Aluno " + aluno.getNome() + ", ");
            }
            System.out.println();
        }
    }
}
