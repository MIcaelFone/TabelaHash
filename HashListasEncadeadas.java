import java.util.LinkedList;
import java.util.ArrayList;

public class HashListasEncadeadas {
    private int tamanhoVetor;
    private int maxItens;
    private int quantidadeItensAtual;
    private LinkedList<Aluno>[] estrutura;

    public HashListasEncadeadas(int tamanhoVetor, int maximoItens) {
        this.tamanhoVetor = tamanhoVetor;
        this.maxItens = maximoItens;
        this.quantidadeItensAtual = 0;
        this.estrutura = new LinkedList[tamanhoVetor];
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
        redimensionarTabela(); // Redimensiona a tabela quando o tamanhoVetor é alterado
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
        return aluno.getMatricula() % tamanhoVetor;
    }

    public void Inserir(Aluno aluno) {
        try {
            int posicao = funcaoHash(aluno);
            LinkedList<Aluno> lista = estrutura[posicao];

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

                float fatorDeCarga = (float) quantidadeItensAtual / (float) tamanhoVetor;
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

    private void redimensionarTabela() {
        int novoTamanho = tamanhoVetor * 2;
        LinkedList<Aluno>[] novaEstrutura = new LinkedList[novoTamanho];

        for (int i = 0; i < novoTamanho; i++) {
            novaEstrutura[i] = new LinkedList<Aluno>();
        }

        for (int i = 0; i < tamanhoVetor; i++) {
            for (Aluno aluno : estrutura[i]) {
                int posicao = funcaoHash(aluno); // Calcula a nova posição
                novaEstrutura[posicao].add(aluno); // Adiciona o aluno à nova posição
            }
        }


        estrutura = novaEstrutura;
        tamanhoVetor = novoTamanho;
    }
    
    public void Deletar(Aluno aluno) {
        int posicao = funcaoHash(aluno);
        LinkedList<Aluno> lista = estrutura[posicao];

        boolean alunoEncontrado = false;

        for (Aluno a : lista) {
            if (a.equals(aluno)) {
                alunoEncontrado = true;
                lista.remove(a);
                quantidadeItensAtual--;
                System.out.println("O aluno " + a.getNome() + " com a matrícula " + a.getMatricula() + " foi removido!");
                break;
            }
        }

        if (!alunoEncontrado) {
            System.out.println("Nenhum aluno encontrado com a matrícula " + aluno.getMatricula());
        }
    }

    public void Buscar(Aluno aluno) {
        int posicao = funcaoHash(aluno);
        long startTime = System.nanoTime();
        LinkedList<Aluno> lista = estrutura[posicao];
        ArrayList<Aluno> alunosEncontrados = new ArrayList<>();

        for (Aluno a : lista) {
            if (a.equals(aluno)) {
                alunosEncontrados.add(a);
            }
        }

        long endTime = System.nanoTime();
        long elapsedTime = endTime - startTime;
        double milliseconds = (double) elapsedTime / 1_000_000;

        if (alunosEncontrados.isEmpty()) {
            System.out.println("Nenhum aluno encontrado com a matrícula " + aluno.getMatricula());
        } else {
    
            for (Aluno encontrado : alunosEncontrados) {
                System.out.println("Matrícula: " + encontrado.getMatricula()+" / Nome: " + encontrado.getNome() );
             }
        }

        System.out.println("Tempo gasto na busca: " + milliseconds + " ms");
    }

    public void Imprimir() {
        System.out.println("Tabela Hash:");
        for (int i = 0; i < tamanhoVetor; i++) {
            LinkedList<Aluno> lista = estrutura[i];
            System.out.print("Posição " + i + " -> ");
            for (Aluno aluno : lista) {
                System.out.print(" Matrícula: " + aluno.getMatricula() + " / Nome: " + aluno.getNome() + ",");
            }
            System.out.println();
        }
    }
}
