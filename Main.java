import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Escolha o tipo de tratamento:");
        System.out.println("1 - Tratamento com Listas Encadeadas");
        System.out.println("2 - Tratamento Homogêneo");
        int tipoTratamento = input.nextInt();

        if (tipoTratamento == 1) {
            runHeterogeneousTreatment(input);
        } else if (tipoTratamento == 2) {
            runHomogeneousTreatment(input);
        } else {
            System.out.println("Opção inválida.");
        }
    }

    public static void runHeterogeneousTreatment(Scanner input) {
        System.out.println("Digite o tamanho da tabela Hash:");
        int tamanhoVetor = input.nextInt();
        System.out.println("Digite o tamanho máximo de itens desejados:");
        int maximoItens = input.nextInt();

        HashListasEncadeadas hash = new HashListasEncadeadas(tamanhoVetor, maximoItens);

        while (true) {
            System.out.println("--------------------------------------");
            System.out.println("1- Inserir");
            System.out.println("2- Deletar");
            System.out.println("3- Buscar");
            System.out.println("4- Imprimir");
            System.out.println("5- Sair");
            System.out.println("Digite a opção desejada:");
            int opcao = input.nextInt();

            switch (opcao) {
                case 1:
                    System.out.println("Digite um nome de um aluno:");
                    String nome = input.next();
                    System.out.println("Digite a matrícula do aluno:");
                    int matricula = input.nextInt();
                    Aluno aluno = new Aluno(matricula, nome);
                    hash.Inserir(aluno);
                    break;
                case 2:
                    System.out.println("Digite a matrícula do aluno:");
                    int matricula_deleta = input.nextInt();
                    Aluno aluno_deleta = new Aluno(matricula_deleta, " ");
                    hash.Deletar(aluno_deleta);
                    break;
                case 3:
                    System.out.println("Digite a matrícula do aluno:");
                    int matricula_buscada = input.nextInt();
                    Aluno aluno_buscado = new Aluno(matricula_buscada, " ");
                    hash.Buscar(aluno_buscado);
                    break;
                case 4:
                    hash.Imprimir();
                    break;
                case 5:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }
    }

    public static void runHomogeneousTreatment(Scanner input) {
        System.out.println("Digite o tamanho da tabela Hash:");
        int tamanhoVetor = input.nextInt();
        System.out.println("Digite o tamanho máximo de itens desejados:");
        int maximoItens = input.nextInt();

        HashHomogeneo hash = new HashHomogeneo(tamanhoVetor, maximoItens);

        while (true) {
            System.out.println("--------------------------------------");
            System.out.println("1- Inserir");
            System.out.println("2- Deletar");
            System.out.println("3- Buscar");
            System.out.println("4- Imprimir");
            System.out.println("5- Sair");
            System.out.println("Digite a opção desejada:");
            int opcao = input.nextInt();

            switch (opcao) {
                case 1:
                    System.out.println("Digite um nome de um aluno:");
                    String nome = input.next();
                    System.out.println("Digite a matrícula do aluno:");
                    int matricula = input.nextInt();
                    Aluno aluno = new Aluno(matricula, nome);
                    hash.Inserir(aluno);
                    break;
                case 2:
                    System.out.println("Digite a matrícula do aluno:");
                    int matricula_deleta = input.nextInt();
                    Aluno aluno_deleta = new Aluno(matricula_deleta, " ");
                    hash.Deletar(aluno_deleta);
                    break;
                case 3:
                    System.out.println("Digite a matrícula do aluno:");
                    int matricula_buscada = input.nextInt();
                    Aluno aluno_buscado = new Aluno(matricula_buscada, " ");
                    hash.Buscar(aluno_buscado);
                    break;
                case 4:
                    hash.Imprimir();
                    break;
                case 5:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }
    }
}
