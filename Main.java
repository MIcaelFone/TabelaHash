import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Digite o tamanho da tabela Hash:");
        int tamanhoVetor = input.nextInt();
        System.out.println("Digite o tamanho máximo de itens desejados:");
        int maximoItens = input.nextInt();

        Hash hash = new Hash(tamanhoVetor, maximoItens);

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
                    hash.inserir(aluno);
                    break;
                case 2:
                    System.out.println("Digite a matrícula do aluno:");
                    int matricula1 = input.nextInt();
                    hash.deletar(matricula1);
                    break;
                case 3:
                    System.out.println("Digite a matrícula do aluno:");
                    int matricula2 = input.nextInt();
                    hash.buscar(matricula2);
                    break;
                case 4:
                    hash.imprimir();
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