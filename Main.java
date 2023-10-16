import java.util.Scanner;

public class Main {
    Aluno aluno = new Aluno();
    

    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Digite o tamanho da tabela Hash:");
        int tamanho_vetor = input.nextInt();
        System.out.println("Digite o tamanho máximo de itens desejados:");
        int maximo_itens = input.nextInt();

        Hash hash = new Hash(tamanho_vetor, maximo_itens);
        hash.inseridotabelaValoresVazio(maximo_itens);
       
        float Fatordecarga = (float) maximo_itens / tamanho_vetor;
        System.out.println("Fator de carga: "+Fatordecarga);
    while(true){
        
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
                System.out.println("Digite a matricula do aluno:");
                int matricula = input.nextInt();
                Aluno aluno=new Aluno(matricula,nome);
                hash.inserir(aluno);             
                break;
            case 2:
          
                System.out.println("Digite a matricula do aluno:");
                int matricula1 = input.nextInt();

                Aluno aluno1=new Aluno(matricula1," ");
                hash.Deletar(aluno1);
                break;
            case 3:
                System.out.println("Digite a matricula do aluno:");
                int matricula2 = input.nextInt();
                Aluno aluno2=new Aluno(matricula2," ");
                hash.Busca(aluno2);
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